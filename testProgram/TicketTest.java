package test;

//import static org.junit.Assert.*;


import org.junit.Test;

public class TicketTest {

	@Test
	public void testCreate() {
		Ticket a = new Ticket("");
		String name = a.getName();
		String[] seatNum = a.getSeatNum();
	}

}
