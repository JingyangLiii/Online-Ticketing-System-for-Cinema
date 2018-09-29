

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI_MovieInfo {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public UI_MovieInfo() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
        String file[]=FileOperation.getfile("movieInfo.txt");
		frame.setBounds(0, 0, 100*file.length, 100*file.length);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(file.length, 0, 0, 0));
		JLabel buy[]=new JLabel [file.length];




	//	Movie []movie=new Movie[file.length];

		for(int j=0;j<file.length;j++){

			Movie movie=readmovie(file[j]);

			JLabel movie_name = new JLabel(movie.getMovie_name());
			JLabel movie_time = new JLabel(movie.getDuration());
			ImageIcon movieImage = new ImageIcon(movie.getPicNum());

			JLabel Image = new  JLabel ();

			movieImage.setImage(movieImage.getImage().getScaledInstance(100,100,50));
			Image.setIcon(movieImage);
			Image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JPanel moviePanel= new JPanel();
			moviePanel.setLayout(new GridLayout(0,4,0,0));
			movie_name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			movie_time.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			buy[j]=new JLabel("buy it now");
			buy[j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			final int t=j;
			buy[j].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Movie movie=new Movie();

					movie.setMovie_name(FileOperation.seperateit(file[t],0));
					movie.setDuration(FileOperation.seperateit(file[t],1));
					movie.setPicNum(FileOperation.seperateit(file[t],2));

					MovieTime movie1=new MovieTime(movie);
					new UI_Schedule(movie1);
					frame.setVisible(false);

				}
			});
			moviePanel.add(movie_name);
			moviePanel.add(movie_time);
			moviePanel.add(Image);
			moviePanel.add(buy[j]);

			frame.getContentPane().add(moviePanel);
		}


	}
	/*This is a method that select all the movie information from movieinfo.txt and set the information into moviename duration picture name
	 * par
	 */
	public Movie readmovie(String file){
		Movie movie=new Movie();
		movie.setMovie_name(FileOperation.seperateit(file,0));
		movie.setDuration(FileOperation.seperateit(file,1));
		movie.setPicNum(FileOperation.seperateit(file,2));
		return movie;
	}


}
