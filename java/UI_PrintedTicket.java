


import javax.swing.JFrame;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI_PrintedTicket {

	private JFrame frame;



	/**
	 * Create the application.
	 * @param ticketID[] a list of ticket ID
	 */
	public UI_PrintedTicket(String ticketID[]) {
		initialize(ticketID);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String ticketID[]) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 434, 191);
		for(int i=0;i<ticketID.length;i++){

			String filecontent[]=FileOperation.getfile(ticketID[i]+".txt");
			for(int j=1;j<filecontent.length;j++)
			textArea.append(filecontent[j]+"\n");
		}
		frame.getContentPane().add(textArea);

		JButton btnNewButton = new JButton("Return");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				new UI_MovieInfo();
			}
		});
		btnNewButton.setBounds(34, 217, 93, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new UI_Payment();
			}
		});
		btnNewButton_1.setBounds(298, 217, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
