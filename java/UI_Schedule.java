

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;

public class UI_Schedule {

	private JFrame frame;
	private MovieTime movie;
	/**
	 * Create the application.
	 * @param movie the object movie 
	 */
	public UI_Schedule(MovieTime movie){
		this.movie=movie;
		initialize();
		frame.setVisible(true);
	}




	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(movie.getMovie().getMovie_name());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 229, 434, 32);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("show time");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(216, 7, 113, 48);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));


		ScheduleImpl impl= new ScheduleImpl();
		List<Map<String, String>> list = impl.getschedule(movie.getMovie().getMovie_name());
		/*new ArrayList<Map<String, String>>();

	    for(int i=1;i<4;i++){
	    	System.out.println("schedule"+i+".txt");
	    	String file[]=FileOperation.getfile("schedule"+i+".txt");
	    	for(int h=0;h<file.length;h++){
	    	String moviename = FileOperation.seperateit(file[h],0);
	    	if(moviename.equals(movie.getMovie().getMovie_name())){

	    		String []token=FileOperation.seperateit(file[h],1).split(" ");
	    		for(int j=0;j<token.length;j++){
	    		Map<String,String> map = new HashMap<String, String>();
	    		map.put(i+"", token[j]);
	    		System.out.println(i+","+token[j]);
	    		list.add(map);
	    		}
	    		}
	    }
	    }*/

		JPanel panel = new JPanel();
		panel.setBounds(150, 65, 250, 150);
		frame.getContentPane().add(panel);

	    panel.setLayout(new GridLayout(3, 0, 0, 4));
		JLabel movie_time[] = new JLabel [9];

		for(int i=0;i<3;i++){
	    JLabel lblNewLabel_2 = new JLabel("screen"+(i+1));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		movie_time[3*i] = new JLabel();

		movie_time[3*i].setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(movie_time[3*i]);
		movie_time[3*i+1] = new JLabel();
		movie_time[3*i+1].setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(movie_time[3*i+1]);

		movie_time[3*i+2] = new JLabel();
		movie_time[3*i+2].setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(movie_time[3*i+2]);
		}
		int h=0;
		int p=0;
        for (Map<String, String> map1 : list) {
            for (String s : map1.keySet()) {
            	if(map1.keySet().toString().substring(1,2).equals(h+"")){
            		p++;
            		int iparse=((Integer.parseInt(map1.keySet().toString().substring(1,2)))-1)*3+p;
                    movie_time[iparse].setText(map1.get(s));

                    if(impl.calculatetime(new Date(),map1.get(s))){
                    	movie_time[iparse].setForeground(Color.black);
                    	final int y=iparse;
                    	movie_time[iparse].addMouseListener(new MouseAdapter() {
            				@Override
            				public void mouseClicked(MouseEvent arg0) {


            					String label=movie_time[iparse].getText();
            					int screen=y/3+1;
            					ScreenMovieTime smt =new ScreenMovieTime(screen,movie.getMovie().getMovie_name(),label);

            					frame.setVisible(false);
            					if(screen==3){
        							new Screen3(smt);}
        					    else {
        						new Screen(smt);}
            				}


            			});
                    }
                    else movie_time[iparse].setForeground(Color.gray);


            	}

               // System.out.println(map1.get(s) + "  "+map1.keySet().toString().substring(1,2));
                else{
                	h=Integer.parseInt(map1.keySet().toString().substring(1,2));
                	p=0;
                	int iparse=((Integer.parseInt(map1.keySet().toString().substring(1,2)))-1)*3+p;
                    movie_time[iparse].setText(map1.get(s));
                    if(impl.calculatetime(new Date(),map1.get(s))){
                    	movie_time[iparse].setForeground(Color.black);
                    	final int y=iparse;
                    	movie_time[iparse].addMouseListener(new MouseAdapter() {
            				@Override
            				public void mouseClicked(MouseEvent arg0) {
            					String label=movie_time[iparse].getText();
            					int screen=y/3+1;
            					ScreenMovieTime smt =new ScreenMovieTime(screen,movie.getMovie().getMovie_name(),label);

            					frame.setVisible(false);
            					if(screen==3){
            							new Screen3(smt);}
            					else {
            						new Screen(smt);}

            				}
            			});

                    }
                    else movie_time[iparse].setForeground(Color.gray);
                }

                }



        }
		JLabel lblNewLabel_5 = new JLabel();
		ImageIcon movieImage = new ImageIcon(movie.getMovie().getPicNum());
		movieImage.setImage(movieImage.getImage().getScaledInstance(100,100,50));
		lblNewLabel_5.setIcon(movieImage);
		lblNewLabel_5.setBounds(10, 21, 143, 201);

		Button returnbutton=new Button("return");
		returnbutton.setBounds(30, 200, 56, 24);
		returnbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frame.setVisible(false);
				new UI_MovieInfo();
			}

			});
		frame.getContentPane().add(returnbutton);
		frame.getContentPane().add(lblNewLabel_5);
	}

}
