

public class ScreenMovieTime {
//mutable
private int screen;
private String moviename;
private String time;
public ScreenMovieTime(int screen,String moviename,String time){
	this.screen=screen;
	this.moviename=moviename;
	this.time=time;
}
public int getScreen() {
	return screen;
}
public void setScreen(int screen) {
	this.screen = screen;
}
public String getMoviename() {
	return moviename;
}
public void setMoviename(String moviename) {
	this.moviename = moviename;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

}
