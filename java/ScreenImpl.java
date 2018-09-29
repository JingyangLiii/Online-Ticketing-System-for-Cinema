



public class ScreenImpl implements Interface_Screen {

	/**
	 * This is method that determine which seats have been purchased
	 * @return int[][] the seat information
	 */
	@Override
	public int[][] loadseat(ScreenMovieTime smt,int[][] seat){
		System.out.println("screen"+smt.getScreen()+".txt");
		String file[]=FileOperation.getfile("screen"+smt.getScreen()+".txt");
		String time[]=new String[file.length];
	    String moviename[]=new String[file.length];
	    for(int j=0;j<file.length;j++){
		moviename[j]=FileOperation.seperateit(file[j],0);
		time[j]=FileOperation.seperateit(file[j],1);
		System.out.println(smt.getMoviename()+"   "+moviename[j]+"  "+smt.getTime());
		if(smt.getMoviename().equals(moviename[j])&smt.getTime().equals(time[j])){

			if (file[j].split("/").length==2){}
			else{
			String cseat=FileOperation.seperateit(file[j],2);
			String []token=cseat.split(" ");
			for(int i=0;i<token.length;i++){
				int row=token[i].charAt(0)-'A';

				int column=Integer.parseInt(token[i].substring(2, 3));
			 	seat[seat.length-1-row][column]=2;

			}
			}}

	}
	    System.out.println(time);
		return seat;
}



}
