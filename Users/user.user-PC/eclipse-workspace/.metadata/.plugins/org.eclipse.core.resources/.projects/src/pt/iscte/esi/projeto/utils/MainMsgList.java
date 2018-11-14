package pt.iscte.esi.projeto.utils;

import java.awt.Frame;

import pt.iscte.esi.projeto.form.models.Message;

public class MainMsgList {
	/*101 linesm 4 rows*/
	private String[] headers;
	private String[][] msgMatrix;
	private Frame frame;
	
	public MainMsgList(Frame frame) {
		this.frame = frame;
	}
	
	public Object[][] getMsgMatrix() {
		return msgMatrix;
	}
	
	public void setMsgMatrix(String[][] msgMatrix) {
		this.msgMatrix = msgMatrix;
	}
	
	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void addMessage(Message msg) {
		if(msgMatrix.length == 100) {
			return;
		}
		else {
			for(int i=0; i<=100; i++) {
				if(msgMatrix[i][0]==null) {
					for(int j=0; j<=msgMatrix[i].length;j++) {
						switch (j) {
						case 0:
							msgMatrix[i][j] = msg.getTime();
							break;
						case 1:
							msgMatrix[i][j] = msg.getChannel(); 
							break;
						case 2:
							msgMatrix[i][j] = msg.getSender();
							break;
						case 3:
							msgMatrix[i][j] = msg.getMessage();
							break;
						default:
							break;
						}
					}
					break;
				}
			}
		}
	}

}
