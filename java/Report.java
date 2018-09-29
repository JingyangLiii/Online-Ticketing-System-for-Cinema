


import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**This is a class to read from files and generate daily report
 * @author Jingyi Zhu
 *
 */
public class Report {
	private String yesterday;
	private int screenNum,ticketType;
	private String fileName, movieName, time;//file name of the reading file
	private String content;//content that read from the file
	private Ticket t;//for each entry that read in the content
	private String[] entry;//content of one entry
	private String[] timeforOneMovie,seatforOneMovie;
	private String tType[] = {"Child","Adult","Senior","Student"};
	private int[] num;//for writing file
	private ArrayList<String> movieList,fileList;
	private ArrayList<Ticket> tList,sList;

	public int getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}


	public int getTicketType() {
		return ticketType;
	}

	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}

	/**This is a method to generate and send report
	 * @param screenNum the number of screens
	 * @return whether the report is sent
	 */
	public boolean sendReport(int screenNum){
		//set the date of the report
		setDate();
		//set the number of screens
		setScreenNum(screenNum);
		//set number of ticket types
		setTicketType(4);

		/*generate the report*/
		//first get the schedule
		sList = setSchedule();
		//second get all the ticket
		tList = setType();

		/*write report*/
		return writeReport();

	}//end method setSchedule

	/**This is a method that get all the schedule in different schedule file
	 * @return a list of ticket with movie name and time
	 */
	public ArrayList<Ticket> setSchedule(){
		try{
			int hallNumber=0;
			movieList = new ArrayList<String>();
			tList = new ArrayList<Ticket>();
			fileList = new ArrayList<String>();
			for(int i = 1;i <= screenNum; i++){
				fileList.add( "schedule"+i+".txt");
			}
			for(String s:fileList){//for file iteration
				//read from the schedule
				fileName = s;
				hallNumber++;
				RandomAccessFile rf= new RandomAccessFile(fileName,"rw");
				content = rf.readLine();
				while(content !=null){//not to the end of the file
					entry = content.split("/");
					if(entry.length==0){
						System.out.println("maybe a blank line");
						break;//while
					}//end if
					if(entry.length<2){//entry less than 2 /
						System.out.println("bad entry");
						content = rf.readLine();
						continue;
					}//end if
					setMovieList(entry[0]);
					timeforOneMovie=entry[1].split(" ");//break the time of one movie
					for(int i = 0;i<timeforOneMovie.length;i++){//for each time
						t = new Ticket();//make a new ticket
						t.setName(entry[0]);//set name of the movie
						t.setTime(timeforOneMovie[i]);//set time of the movie
						t.setHallNum(hallNumber);//set hall number
						tList.add(t);// add ticket to the schedule list
					}//end for
					content = rf.readLine();
				}//while
				rf.close();
			}//end for
			if(tList.size()==0){//no schedule
				System.out.println("no schedule has been set");
				return null;
			}else{//have a list
				return tList;
			}
		}catch(IOException e){
			System.out.println("some thing wrong with reading the file"+fileName);
		}
		return null;
	}//end method setSchedule

	/**This is a method to set all the movieName
	 * @param movieName the name to be checked
	 */
	public void setMovieList(String movieName){
		int flag = 0;
		if(movieList.size()==0){
			movieList.add(movieName);
		}else{
			for(String s:movieList){
				if(s.equals(movieName)){
					flag++;
				}
			}//end for
			if(flag==0){
				movieList.add(movieName);
			}
		}//end else
	}//end the method set movie list

	/**This is a method to set the type of each ticket
	 * @return the array list with all the ticket
	 */
	public ArrayList<Ticket> setType(){
		try{
			ArrayList<Ticket> ticketWithTypeList = new ArrayList<Ticket>();//new list
			Ticket ticketWithType;//ticket
			fileList = new ArrayList<String>();
			for(int i = 1;i <= screenNum; i++){
				fileList.add( "screen"+i+".txt");
			}
			//read from the each screen
			for(String s:fileList){
				fileName = s;
				RandomAccessFile rf= new RandomAccessFile(fileName,"rw");
				content = rf.readLine();
				while(content !=null){//not to the end of the file
					entry = content.split("/");
					if(entry.length==0){
						System.out.println("maybe a blank line");
						break;//while
					}//end if
					if(entry.length<3){//no seat selection
						System.out.println("bad entry,no seat has been selected.");
						content = rf.readLine();
						continue;
					}//end if seat selection
					seatforOneMovie=entry[2].split(" ");//split seat info
					for(int j = 0;j< sList.size();j++){//read from the list
						t = sList.get(j);//get a ticket from schedule list
						if((entry[0].equals(t.getName()))&&(entry[1].equals(t.getTime()))){//one movie at a particular time
							for(int i = 0;i<seatforOneMovie.length;i++){//read from the file
								ticketWithType = new Ticket();//new ticket
								ticketWithType.setName(entry[0]);//set movie name
								ticketWithType.setTime(entry[1]);//set movie time
								ticketWithType.setHallNum(t.getHallNum());//set hull number
								ticketWithType.setTicketType(seatforOneMovie[i].charAt(4)+"");//set ticket type
								ticketWithTypeList.add(ticketWithType);	//add ticket to the list
							}//end for
						}//end if
					}//end for each seat
					content = rf.readLine();
				}//while
				rf.close();
			}//end for
			if(ticketWithTypeList.size()==0){//no set selection
				System.out.println("no seats has been selected");
				return null;
			}else{//have a list
				return ticketWithTypeList;
			}//end else
		}catch(IOException e){
			System.out.println("some thing wrong with reading the file"+fileName);
		}//end catch
		return null;
	}//end setType

	/**This is a method that figures out the total number of the tickets
	 * @return a array with numbers of ticket for each screen
	 */
	public int calTicketNum(){
		if(tList != null)
			return tList.size();
		else
			return 0;
	}//end method ticketNum

	/**This is a method that calculates ticket number for each screen
	 * @return the array contains the number for each screen
	 */
	public int[] calTicketByScreen(){
		int[] TicketNumScreen = new int[3];
		if (tList ==null){
			for(int j = 0;j<3;j++)
				TicketNumScreen[j] =0;
			return TicketNumScreen;
		}
		for(int i = 0;i < 3;i++){
			for(int j = 0;j<tList.size();j++)
			if(tList.get(j).getHallNum()==i+1){
				TicketNumScreen[i]++;
			}//end if
		}//end for
		return TicketNumScreen;
	}//end calByScreen

	/**This is a method to calculate number for each movie
	 * @return the number for each movie
	 */
	public int[] calTicketByMovie(){
		if(tList == null){
			return null;
		}
		int[] tName = new int[movieList.size()];
		for(int j = 0; j < movieList.size(); j++){
			fileName = movieList.get(j);
			for(int i = 0; i < tList.size();i++){
				t = tList.get(i);
				if(fileName.equals(t.getName())){
					tName[j]++;
				}//end if
			}//end for
		}//end for in the movieList
		return tName;
	}//end calTicektByMovie


	/**This method is used to calculate the num of tickets sold for each specific movie
	 * @return an array contains ticket number for each movie
	 */
	public int[] calTicketBySpeMovie(){
		if(tList==null){
			return null;
		}
		int[] tNum = new int[sList.size()];
		int i = 0,j = 0;//used to pinpoint the location of the array
		do{
			movieName = sList.get(j).getName();//get movie
			time = sList.get(j).getTime();//getTime
			for(Ticket t: tList){//search in the ticket
				if(movieName.equals(t.getName())&&time.equals(t.getTime())){//same movie same time
					tNum[i]++;//add num
				}//end if
			}//end for ticket
			j++;//add ticket in schedule
			i++;//add in tNum
		}while(j < sList.size());//end for each movie
		return tNum;
	}//end calTicketBySpeMovie

	/**This method is used to calculate the num of tickets sold for each type
	 * @return an array contains ticket number for each type
	 */
	public int[] calTicketByType(){
		if(tList==null){
			return null;
		}
		int[] tNum = new int[4];
		for(Ticket t: tList){
				tNum[Integer.parseInt(t.getTicketType())]++;
		}//end for
		return tNum;
	}//end calTicketByType

	/**This method is used to generate the report that will be sent to the administrator
	 * @return whether the report is successfully generated
	 */
	public boolean writeReport(){
		fileName = yesterday+"Report.txt";
		try{
			RandomAccessFile rf = new RandomAccessFile(fileName,"rw");
			//write the date
			rf.write((yesterday+"\r\n").getBytes());
			rf.write("\r\n".getBytes());

			//write number of tickets sold
			content ="TICKETS SUMMARY";
			rf.write((content+"\r\n").getBytes());
			rf.write("\r\n".getBytes());
			content = "All tickets sold: "+calTicketNum();
			rf.write((content+"\r\n").getBytes());
			rf.write("\r\n".getBytes());

			//write number of tickets for each screen
			content ="TICKETS SORT BY SCREEN(ALL "+screenNum+" SCREENS)";
			rf.write((content+"\r\n").getBytes());
			rf.write("\r\n".getBytes());
			num = calTicketByScreen();
			for(int i = 1;i <= screenNum;i++){
				content = "Screen "+ i +": "+num[i-1] +" tickets";
				rf.write((content+"\r\n").getBytes());
			}//end for screen
			rf.write("\r\n".getBytes());

			//write the number for specific movie
			content ="TICKETS SORT BY SCHEDULE";
			rf.write(content.getBytes());
			num = calTicketBySpeMovie();
			if(num!=null){
				content ="(ALL "+num.length+" MOVIES)";
				rf.write((content+"\r\n").getBytes());
				rf.write("\r\n".getBytes());
				for(int i = 0;i < sList.size();i++){
					t = sList.get(i);
					content = t.getName()+" at "+t.getTime()+" tickets sold : "+num[i];
					rf.write((content+"\r\n").getBytes());
				}//end for
			}else{
				content ="(ALL "+0+" MOVIES)";
				rf.write((content+"\r\n").getBytes());
			}
			rf.write("\r\n".getBytes());

			//write the number for different type
			content ="TICKETS SORT BY TYPE";
			rf.write(content.getBytes());
			num = calTicketBySpeMovie();
			content ="(ALL "+ticketType+" TYPES)";
			rf.write((content+"\r\n").getBytes());
			rf.write("\r\n".getBytes());
			num = calTicketByType();
			if(num!=null){
				for(int i = 0;i < ticketType;i++){
					content =tType[i]+" tickets sold :"+num[i];
					rf.write((content+"\r\n").getBytes());
				}
			}else{
				for(int i = 0;i < ticketType;i++){
					content =tType[i]+" tickets sold : 0";
					rf.write((content+"\r\n").getBytes());
				}
			}
			rf.write("\r\n".getBytes());

			//write the number for different movie
			content ="TICKETS SORT BY MOVIE";
			rf.write(content.getBytes());
			num = calTicketBySpeMovie();
			content ="(ALL "+movieList.size()+" MOVIES)";
			rf.write((content+"\r\n").getBytes());
			rf.write("\r\n".getBytes());
			num = calTicketByMovie();
			if(num != null){
				for(int i = 0; i < movieList.size(); i++){
					content = movieList.get(i) +" tickets sold : "+num[i];
					rf.write((content+"\r\n").getBytes());
				}
			}else{
				for(int i = 0; i < movieList.size(); i++){
					content = movieList.get(i) +" tickets sold : 0";
					rf.write((content+"\r\n").getBytes());
				}
			}
			rf.write("\r\n".getBytes());
			rf.close();
			return true;
		}catch (IOException e) {
		      System.out.println("Errors occurred");
	          System.exit(1);
	          return false;
		}//end catch

	}//end method writeReport

	/**This is a method that set the report date
	 *
	 */
	public void setDate(){//set the date
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		yesterday = df.format(cal.getTime());
	}//end setDate
}//end class Report
