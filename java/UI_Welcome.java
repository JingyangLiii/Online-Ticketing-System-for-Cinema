


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class UI_Welcome {

	private JFrame frame;



	/**
	 * Create the application.
	 */
	public UI_Welcome() {

		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Table.selectionBackground"));
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new UI_MovieInfo();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome to BUPT cinema");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(103, 76, 229, 35);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblClickToContinue = new JLabel("Click to continue");
		lblClickToContinue.setFont(new Font("Arial", Font.PLAIN, 15));
		lblClickToContinue.setBounds(152, 133, 180, 47);
		frame.getContentPane().add(lblClickToContinue);
	}
}
