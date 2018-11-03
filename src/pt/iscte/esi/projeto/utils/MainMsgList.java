package pt.iscte.esi.projeto.utils;

import java.awt.Frame;

import pt.iscte.esi.projeto.form.models.Message;

public class MainMsgList {
	
	private String[] headers;
	private Object[][] msgMatrix;
	private Frame frame;
	
	public MainMsgList(Frame frame) {
		this.frame = frame;
	}
	
	public Object[][] getMsgMatrix() {
		return msgMatrix;
	}
	
	public void setMsgMatrix(Object[][] msgMatrix) {
		this.msgMatrix = msgMatrix;
	}
	
	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void addMessage(Message msg) {
		if(msgMatrix == null) {
			msgMatrix = new Object[100][100];
		}
		if(msgMatrix[99] != null) {
			return;
		}
		else {
			for(int i=0; i<=100; i++) {
				if(msgMatrix[i]!=null) {
					for(int j=0; j<=msgMatrix[i].length; j++) {
						if(msgMatrix[i][j] == null) {
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
							return;
						}
						
					}
				}
			}
		}
	}

}
