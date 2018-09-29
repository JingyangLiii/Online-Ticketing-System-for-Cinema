

import java.lang.String;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Clock {

	public static void main(String[] args) {
			int hour = 0 ;
			int clearHour = 0;
			int minute = 0;
			int second = 0;
		while(true){
			Calendar rightNow = Calendar.getInstance();
			hour = rightNow.get(Calendar.HOUR_OF_DAY);
			minute = rightNow.get(Calendar.MINUTE);
			second = rightNow.get(Calendar.SECOND);
			if(hour == clearHour && minute == 00 &&second == 00) {
				Report r = new Report();			//need to set the number of screens
				System.out.println("Report has been sent? "+r.sendReport(3));
				File file1 = new File("screen1.txt");
				File file2 = new File("screen2.txt");
				File file3 = new File("screen3.txt");
				file1.delete();
				file2.delete();
				file3.delete();
				try{
					Thread.sleep(1000);
				}
				catch(Exception e){
					System.exit(0);
				}
			}
		}
	}
}
