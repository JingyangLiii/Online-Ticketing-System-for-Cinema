

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public abstract class BaseScreen {
	protected ArrayList<String>  list=new ArrayList<String>();
	protected JFrame frame;
	protected ScreenMovieTime smt;
	public BaseScreen(ScreenMovieTime smt) {
		this.smt=smt;
		initialize();
		frame.setVisible(true);

	}
	public abstract int[][] getseat();
	public abstract void initialize() ;
	/*
	 * It is the method that initialize screen and but button and return button
	 */
	protected void initialize1(){
		Button button_2 = new Button("screen");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.GRAY);
		button_2.setBounds(82, 307, 406, 24);
		frame.getContentPane().add(button_2);



		Button buybutton=new Button("Buy");
		buybutton.setBounds(488, 337, 56, 24);
		buybutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(list.size()!=0){
				frame.setVisible(false);
				new UI_ChooseTicketTpe(smt,list);
				}
			}

			});
		frame.getContentPane().add(buybutton);

		Button returnbutton=new Button("return");
		returnbutton.setBounds(30, 337, 56, 24);
		returnbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				frame.setVisible(false);
				new UI_MovieInfo();
			}

			});
		frame.getContentPane().add(returnbutton);

	}
}
