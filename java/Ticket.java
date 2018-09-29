
import java.io.*;
import java.lang.String;
import java.util.*;
/**This is a class that read data from files and get parameters to store and generate the information about tickets
 * @author Jingyang Li
 *
 */
public class Ticket{
	private String name;
	private int hallNum;
	private String ticketType;
	private String time;
	private String[] seatNum;
	private String[] ticketID;
	public Ticket(){}

	public Ticket(ScreenMovieTime smt,String ticketType){
		this.name=smt.getMoviename();
		this.time=smt.getTime();
		this.hallNum=smt.getScreen();
		this.ticketType=ticketType;
	}

	/**This is a method that set the name of movie
	 * @param name the name of the movie
	 */
	public void setName(String name){
		this.name = name;
		}

	/**This method is used to get the name of movie
	 * @return the name of movie
	 */
	public String getName(){
		return name;}


	/**This is a method that set the time of movie
	 * @param time the time of the movie
	 */
	public void setTime(String time){
		this.time = time;
		}

	/**This method is used to get the time of movie
	 * @return the time of movie
	 */
	public String getTime(){
		return time;}


	/**This is a method that set the selected number of seat of movie
	 * @param seatNum the array of all selected number of seat of this movie
	 */
	public void setSeatNum(String seatNum[]){
		this.seatNum=seatNum;
		int test = 0;
		long length = 0;
		String rest = "";
		String restContents = "";
		String fileName = "screen1.txt";
		String tmp = "";
		String contents = name + "/" + time + "/";
		if(hallNum == 2)				//judge which file should be read
		fileName = "screen2.txt";
		else if(hallNum == 3)
		fileName = "screen3.txt";
		for(int i = 0;i < seatNum.length; i++){				//generate selected number of seat
			rest = rest + seatNum[i] + "_" + ticketType + " ";
		}
		contents = contents + rest;							//generate the content written into file
		try {
		  RandomAccessFile randf=new RandomAccessFile(fileName,"rw");
		  tmp = randf.readLine();
			if(tmp == null){					  //if the file is empty, then write all content into file
			  randf.write(contents.getBytes());
			  test = 1;                          //record that content has been written into file
			}
		  while (tmp != null) {					 //if the file is not empty, then add the selected number of seat into the end of line which contained the this movie
			String str[] = tmp.split("/");       //split the content of this line with "/"
			if((str[0].equals(name)) && (str[1].equals(time))){  //judge the time and name of this movie
				length = randf.getFilePointer() ;			//recode the location
				ArrayList<String> restContentList = new ArrayList<String>();
				restContents= randf.readLine();
				int offset=0;
				while(restContents != null){	//write the rest contents in file into arraylist
					offset=2;
					restContents = "\r\n" + restContents;
					restContentList.add(restContents);
					restContents= randf.readLine();
				}
				randf.seek(length-offset);								//move pointer to recoded location
				randf.write((rest).getBytes());			//write contents into file
				for(String rContents : restContentList){		//write rest contents into file
					randf.write(rContents.getBytes());
				}
				test = 1;  										//record that content has been written into file
				break;
			}
            tmp = randf.readLine();
		  }
		  if(test == 0){										//if the file is not empty and does not have information of this movie, then write the all content into the end of file
		  contents = "\r\n" + contents;
		  randf.write(contents.getBytes());}
		  randf.close();
		  }
		catch (IOException e) {
	      System.out.println("Errors occured");
          System.exit(1);
		}
	}

	/**This method is used to get the time of movie
	 * @return the array contains the selected number of seat of this movie
	 */
	public String[] getSeatNum(){
		return seatNum;
		}
	/**
	 * @return
	 */

	/**This is a method that set the hall number of movie
	 * @param hallNum an int of hall number this movie
	 */
	public void setHallNum(int hallNum){
		this.hallNum = hallNum;
		}

	/**This method is used to get the time of movie
	 * @return an int of hall number this movie
	 */
	public int getHallNum(){
		return hallNum;}
	/**
	 * @return
	 */

	/**This is a method that set the type of ticket selected by customer
	 * @param ticketType a string of the type of ticket
	 */
	public void setTicketType(String ticketType){
		this.ticketType = ticketType;
		}

	/**This method is used to get the type of ticket
	 * @return a string of the type of ticket
	 */
	public String getTicketType(){
		return ticketType;}
	/**
	 * @return
	 */

	/**This is a method that generate the ID of ticket
	 */
	public void setTicketID(){
		String ID = "";
		int i = 0;
		int randNum[] = new int[8];
		int test = 0;
		ticketID = new String[seatNum.length];
		try {
		  RandomAccessFile randf=new RandomAccessFile("TicketID.txt","rw");
		  ArrayList<String> IDList = new ArrayList<String>();
		  ID = randf.readLine();
		  while(ID != null){       //read all ID from file
			IDList.add(ID);
		    ID = randf.readLine();
		  }
		  for(int j = 0;j < seatNum.length;j++){ //generate ID randomly and make sure there is no repeat, then write into file
			while(test == 0){
			  ID = "";              //release
			  for(int k = 0;k <8;k++){		     ///generate ID randomly
			    randNum[k] = (int)(Math.random()*4+1);
				ID = ID + String.valueOf(randNum[k]);
			  }
			  for(String tmpID :IDList){		//check whether the ID is repeated
				if(ID.equals(tmpID)){
				  test = 1; 					//record ID is repeated
				  break;
				}
			  }
			  if(test == 1)						//continue the loop
				test = 0;
			  else
				break;
			}
			  ticketID[j]= ID;
			  ID =  ID + "\r\n";
			  randf.write(ID.getBytes());      //write ID into file
		   }
		  randf.close();}
		catch (IOException e) {
	      System.out.println("Errors occured");
          System.exit(1);}
		}

	/**This method is used to get the ID of ticket
	 * @return a string of ID of ticket
	 */
	public String[] getTicketID(){
		return ticketID;
		}
	/**
	 * @return
	 */

	/**This is a method that generate the file in .txt of ticket
	 */
	public void getTicket(){
	  for(int i =0;i < seatNum.length;i++){
		String fileName = ticketID[i] + ".txt";
		try {
			RandomAccessFile randf=new RandomAccessFile(fileName,"rw");
			randf.write(("Ticket ID: " + ticketID[i] + "\r\n" + "\r\n").getBytes());
			randf.write(("File name: " + name + "\r\n" + "\r\n").getBytes());
			randf.write(("Number of hall: " + String.valueOf(hallNum) + "\r\n" + "\r\n").getBytes());
			randf.write(("Type of ticket: " + TicketType(ticketType) + "\r\n" + "\r\n").getBytes()); //transfer into byte
			randf.write(("Time: " + time+"\r\n" + "\r\n").getBytes());
			randf.write(("Number of seat: " + seatNum[i].substring(0, 2)+"\r\n" + "\r\n").getBytes());
			randf.close();
		}
		catch (IOException e) {
	      System.out.println("Errors occured");
          System.exit(1);
		}
	  }
	}

	/**This is a method that transform the type of ticket in number into the type of ticket in words and return the type of ticket
	 * @param ticketType a string of type of ticket
	 * @return a a string of type of ticket
	 */
	public String TicketType(String ticketType){
		if(ticketType.equals("0")){
			return "Child";
		}
		if(ticketType.equals("1")){
			return "Adult";
		}
		if(ticketType.equals("2")){
			return "Senior";
		}
		else{
			return "Student";
		}
	}
	/**
	 * @return
	 */



}
