

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Interface_Schedule {
	public boolean calculatetime(Date time,String s);
	public List<Map<String, String>> getschedule(String movie);
}
