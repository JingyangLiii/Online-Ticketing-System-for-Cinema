

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleImpl implements Interface_Schedule{

/**
 * This is a method that determine whether the time of the movie is before the system time
 * @param time system time
 * @param s it contain the information of movie time
 * @return true time of the movie is before the system time false time of the movie is after the system time
 */
@Override
public boolean calculatetime(Date time,String s){

	int nhours=time.getHours();
	int nminute=time.getMinutes();
    int hours=Integer.parseInt(s.split(":")[0]);
	int minutes=Integer.parseInt(s.split(":")[1]);
	if(hours*60+minutes>nhours*60+nminute){
		return true;
	}
	else
		return false;
}
/**This is a method that get the schedule which movie we have chose in different schedule file
 * @param movie movie's name
 * @return a list of schedule with screen number and time
 */
@Override
public List<Map<String, String>> getschedule(String movie){
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    for(int i=1;i<4;i++){
    	System.out.println("schedule"+i+".txt");
    	String file[]=FileOperation.getfile("schedule"+i+".txt");
    	for(int h=0;h<file.length;h++){
    	String moviename = FileOperation.seperateit(file[h],0);
    	if(moviename.equals(movie)){

    		String []token=FileOperation.seperateit(file[h],1).split(" ");
    		for(int j=0;j<token.length;j++){
    		Map<String,String> map = new HashMap<String, String>();
    		map.put(i+"", token[j]);
    		System.out.println(i+","+token[j]);
    		list.add(map);
    		}
    		}
    }
    }
    return list;
}
}
