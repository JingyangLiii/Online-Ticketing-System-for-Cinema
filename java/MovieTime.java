

import java.util.ArrayList;
import java.util.Map;

public class MovieTime {
private Movie movie;
public MovieTime(Movie movie){
this.movie=movie;
}

public Movie getMovie() {
	return movie;
}

public void setMovie(Movie movie) {
	this.movie = movie;
}

private ArrayList<Map<String, String>> screen_time;


public ArrayList<Map<String, String>> getScreen_time() {
	return screen_time;
}

public void setScreen_time(ArrayList<Map<String, String>> screen_time) {
	this.screen_time = screen_time;
}
}
