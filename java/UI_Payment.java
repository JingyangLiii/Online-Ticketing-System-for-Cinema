

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;

public class UI_Payment {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public UI_Payment() {

		initialize();
		frame.setVisible(true);
		//a timer that wait for 5 second
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
			public void run() {

                frame.setVisible(false);
                this.cancel();
            //    new UI_PrintedTicket(ticketID);
                new UI_Welcome();
            }
        }, 5000);


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Table.selectionBackground"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Purchase has been done");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(85, 28, 277, 85);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Please take the printed tickets to the gate");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(34, 133, 372, 52);
		frame.getContentPane().add(lblNewLabel_1);



	}
}
