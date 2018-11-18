package jUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.esi.projeto.form.models.Message;

public class TestMessage {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String time = "time";
		String channel = "channel";
		String sender = "sender";
		String message = "message";
		
		Message msg = new Message();
		
		msg = new Message(time, channel	, sender, message);
		
		time = time + 1;
		channel = channel +1;
		sender = sender + 1;
		message = message +1;
		
		msg.setTime(time);
		msg.setChannel(channel);
		msg.setSender(sender);
		msg.setMessage(message);
		
		int counter = 0;
		
		if(msg.getTime().equals(time)) {
			counter++;
		}
		if(msg.getChannel().equals(channel)) {
			counter++;
		}
		if(msg.getSender().equals(sender)) {
			counter++;
		}
		if(msg.getMessage().equals(message)) {
			counter++;
		}
		
		assertEquals(4, counter);
	}

}
