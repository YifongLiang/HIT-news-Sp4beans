package com.Sp4beans.wechat.defaultmessage;

public class defaultmessage {

	public static String getAttention() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��ӭ��ע������������ƽ̨").append("\n\n");
		return buffer.toString();
	}

	public static String getMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("����~ ����Sp4beans�� ��ظ���Ӧָ��ѡ�����:").append("\n");
		buffer.append("�ظ� \"1 �ؼ���\" ������������").append("\n");
		buffer.append("(����: 1 ��ĩ����)").append("\n");
		buffer.append("�ظ� \"2 ��������\" �Ա�ƽ̨��������").append("\n");
		buffer.append("(����: 2 ��������̫���ˣ�)").append("\n");
		buffer.append("�ظ� \"��\" ��ʾ�˰����˵�").append("\n");
		return buffer.toString();
	}
	public static String getInvalid() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("��Ч����").append("\n");
		buffer.append("�ظ� \"��\" ����ʾ�����˵�").append("\n");
		return buffer.toString();
	}
}
