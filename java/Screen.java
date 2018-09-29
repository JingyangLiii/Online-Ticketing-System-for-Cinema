

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class Screen extends BaseScreen{



	/**
	 * Create the application.
	 * @param smt the screen movie time
	 */
	public Screen(ScreenMovieTime smt) {
		super(smt);

	}
	/**
	 * Initialize the contents of the seat panel.
	 */
	@Override
	public void initialize() {
		ScreenImpl q =new ScreenImpl();
		int seat[][]=q.loadseat(this.smt,getseat());

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 600, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		initialize1();
		Button button[][] = new Button [4][8];

		for(int i=3;i>=0;i--){
			int h=1;
			for(int j=7;j>=0;j--){
		if(seat[i][j]==1){
		if(j<=3){
		JPanel panel_1=new JPanel();

		panel_1.setBounds(45+48*j, 15+70*i, 30, 45);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		Button button_1 = new Button();
		button_1.setBounds(0, 0, 30, 15);
		button_1.setBackground(Color.WHITE);
		panel_1.add(button_1);

		button[i][j] = new Button(h+"");
		button[i][j].setBounds(0, 15, 30, 30);
		button[i][j].setBackground(Color.blue);
		button[i][j].setForeground(Color.WHITE);
		final int t= i;
		final int p=h;
		final int k=j;
		button[i][j].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				button[t][k].setBackground(Color.red);
				System.out.println((char)('D'-t)+"  "+p);

				list.add((char)('D'-t)+""+p+""+k);
			}
		});
		panel_1.add(button[i][j]);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(36+48*j, 24+70*i,48, 24);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		frame.getContentPane().add(panel);
		h++;}
		else{
			JPanel panel_1=new JPanel();

			panel_1.setBounds(305+48*(j-3), 225+70*(i-3), 30, 45);
			frame.getContentPane().add(panel_1);
			panel_1.setLayout(null);

			Button button_1 = new Button();
			button_1.setBounds(0, 0, 30, 15);
			button_1.setBackground(Color.WHITE);
			panel_1.add(button_1);

			button[i][j] = new Button(h+"");
			button[i][j].setBounds(0, 15, 30, 30);
			button[i][j].setBackground(Color.BLUE);
			button[i][j].setForeground(Color.WHITE);
			final int t= i;
			final int p=h;
			final int k=j;
			button[i][j].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					button[t][k].setBackground(Color.red);
					System.out.println((char)('D'-t)+"  "+p);
					list.add((char)('D'-t)+""+p+""+k);

				}
				});
			panel_1.add(button[i][j]);

			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(296+48*(j-3), 234+70*(i-3),48, 24);
			panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

			frame.getContentPane().add(panel);
			h++;
		}
		}
		if(seat[i][j]==2){
			if(j<=3){
				JPanel panel_1=new JPanel();

				panel_1.setBounds(45+48*j, 15+70*i, 30, 45);
				frame.getContentPane().add(panel_1);
				panel_1.setLayout(null);

				Button button_1 = new Button();
				button_1.setBounds(0, 0, 30, 15);
				button_1.setBackground(Color.WHITE);
				panel_1.add(button_1);

				button[i][j] = new Button(h+"");
				button[i][j].setBounds(0, 15, 30, 30);
				button[i][j].setBackground(Color.gray);
				button[i][j].setForeground(Color.WHITE);

				panel_1.add(button[i][j]);

				JPanel panel = new JPanel();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setBounds(36+48*j, 24+70*i,48, 24);
				panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

				frame.getContentPane().add(panel);
				h++;}
				else{
					JPanel panel_1=new JPanel();

					panel_1.setBounds(305+48*(j-3), 225+70*(i-3), 30, 45);
					frame.getContentPane().add(panel_1);
					panel_1.setLayout(null);

					Button button_1 = new Button();
					button_1.setBounds(0, 0, 30, 15);
					button_1.setBackground(Color.WHITE);
					panel_1.add(button_1);

					button[i][j] = new Button(h+"");
					button[i][j].setBounds(0, 15, 30, 30);
					button[i][j].setBackground(Color.GRAY);
					button[i][j].setForeground(Color.WHITE);

					panel_1.add(button[i][j]);

					JPanel panel = new JPanel();
					panel.setBackground(Color.LIGHT_GRAY);
					panel.setBounds(296+48*(j-3), 234+70*(i-3),48, 24);
					panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

					frame.getContentPane().add(panel);
					h++;
				}
		}
		}

		}

		String LabelABCD[]={"D","C","B","A"};
		for(int i=0; i<4; i++){
		JLabel lblD = new JLabel(LabelABCD[i]);
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		lblD.setBounds(0, 24+70*i, 30, 24);
		frame.getContentPane().add(lblD);
		JLabel lblD1 = new JLabel(LabelABCD[i]);
		lblD1.setHorizontalAlignment(SwingConstants.CENTER);
		lblD1.setBounds(550, 24+70*i, 30, 24);
		frame.getContentPane().add(lblD1);
		}



	}
	/**
	 * This is method that determine which the screen seat structure
	 * @return int[][] the screen seat structure
	 */
	@Override
	public int[][] getseat(){
		int seat[][]={{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1}};
		//int seat[][]={{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,0},{0,0,1,1,1,1,0,0},{0,0,0,1,1,0,0,0}};
		if(smt.getScreen()==1){
		}if(smt.getScreen()==2){
			seat[3][0]=0;
			seat[3][7]=0;

		}
		return seat;

	}
}
