package jUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.esi.projeto.form.models.Message;
import pt.iscte.esi.projeto.utils.MainMsgList;

class MainMsgListTest {

	@Test
	void test() {

		String[] headers = new String[4];
		String[][] msgMatrix = new String[100][4];
		
		for(int i=0;i<4;i++) {
			headers[i] = "H" + i;
		}
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<4;j++) {
				msgMatrix[i][j] = "element" + j;
			}
		}
		
		MainMsgList mainMsgList = new MainMsgList();
		mainMsgList = new MainMsgList(headers, msgMatrix);
		
		
		for(int i=0;i<4;i++) {
			headers[i] = "Headers" + i;
		}
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<4;j++) {
				msgMatrix[i][j] = "E" + i;
			}
		}
		
		mainMsgList.setHeaders(headers);
		mainMsgList.setMsgMatrix(msgMatrix);
		
		int counterH = 0;
		int counterM = 0;
		
		headers = mainMsgList.getHeaders();
		msgMatrix = mainMsgList.getMsgMatrix();
		
		for(int i=0;i<4;i++) {
			if(headers[i].equals("Headers" + i)) {
				counterH++;
			}
		}
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<4;j++) {
				if(msgMatrix[i][j].equals("E"+i) && j==0) {
					counterM++;
				}
			}
		}
		
		assertEquals(4, counterH);
		assertEquals(100, counterM);
		
		//verifica se retorna caso a lista esteja a 100
		
		mainMsgList.addMessage(new Message());
		msgMatrix = mainMsgList.getMsgMatrix();
		
		assertEquals(100, msgMatrix.length);
		
		assertEquals(100, mainMsgList.getCounter());
		
		// adiciona lista e verifica a adição
		
		msgMatrix = new String[100][4];
		
		for(int i=0;i<4;i++) {
			headers[i] = "Headers" + i;
		}
		
		for(int i=0;i<50;i++) {
			for(int j=0;j<4;j++) {
				msgMatrix[i][j] = "E" + i;
			}
		}
		
		mainMsgList.setHeaders(headers);
		mainMsgList.setMsgMatrix(msgMatrix);
		
		
		mainMsgList.addMessage(new Message("time", null, null, null));

		mainMsgList.addMessage(new Message(null, "channel", null, null));

		mainMsgList.addMessage(new Message(null, null, "sender", null));

		mainMsgList.addMessage(new Message(null, null, null, "message"));
		
	
		assertEquals(54, mainMsgList.getCounter());
				
	}

}
