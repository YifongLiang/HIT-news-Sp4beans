package com.Sp4beans.wechat.defaultmessage;

public class retMessage {
	private static StringBuffer buffer;
	public retMessage() {
		buffer = new StringBuffer();
	}
	public void add(String input) {
		buffer.append(input).append("\n");
	}
	public String get() {
		return buffer.toString();
	}
}
