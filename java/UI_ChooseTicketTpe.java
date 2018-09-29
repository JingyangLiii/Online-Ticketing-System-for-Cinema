

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UI_ChooseTicketTpe {

	private JFrame frame;
	private JTable table;
	private ScreenMovieTime smt;
	private ArrayList<String>  list=new ArrayList<String>();



	/**
	 * Create the application.
	 * @param smt the screen movie time
	 * @param list a list of strings
	 */
	public UI_ChooseTicketTpe(ScreenMovieTime smt, ArrayList<String>  list) {
		this.smt=smt;
		this.list=list;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			 System.out.println(table.getSelectedRow());

				Ticket test = new Ticket(smt,table.getSelectedRow()+"");
		        int size=list.size();
		        String[] array = list.toArray(new String[size]);
		        System.out.println(array[0]);
				test.setSeatNum(array);
				test.setTicketID();
				test.getTicket();
				new UI_PrintedTicket(test.getTicketID());
			//	new UI_Payment(test.getTicketID());
				frame.setVisible(false);

			}
		});

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);
		table.setToolTipText("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Child", "2 to 17 years old", "None", "50%", "Buy"},
				{"Adult", "18 to 54 years old", "None", "20%","Buy" },
				{"Senior", "55 years and older", "None", "20%", "Buy"},
				{"Student", "18 years and older who are in full time education", "Student ID", "15%", "Buy"},
			},
			new String[] {
				"Type", "Desciption", "ID required", "Discount", "New column"
			}

			));
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setMinWidth(17);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());

		//frame.add(new JScrollPane(table));
		table.setBounds(30, 30, 380, 180);
		frame.getContentPane().add(table);

	}
}
/*
 * It is a class that calculate the most suitable length and width of jtable
 */
class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer {
   public TableCellTextAreaRenderer() {
       setLineWrap(true);
       setWrapStyleWord(true);
   }

   @Override
public Component getTableCellRendererComponent(JTable table, Object value,
           boolean isSelected, boolean hasFocus, int row, int column) {

       int maxPreferredHeight = 0;
       for (int i = 0; i < table.getColumnCount(); i++) {
           setText("" + table.getValueAt(row, i));
           setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
           maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
       }

       if (table.getRowHeight(row) != maxPreferredHeight)
           table.setRowHeight(row, maxPreferredHeight);

       setText(value == null ? "" : value.toString());
       return this;
   }


}
