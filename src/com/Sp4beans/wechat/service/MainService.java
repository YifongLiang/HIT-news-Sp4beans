package com.Sp4beans.wechat.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.Sp4beans.wechat.defaultmessage.defaultmessage;
import com.Sp4beans.wechat.defaultmessage.retMessage;
import com.Sp4beans.wechat.message.resp.TextMessage;
import com.Sp4beans.wechat.util.MessageUtil;

/**
 * 
 * ���ķ�����
 *
 */

public class MainService {
	/**
	 * ����΢�ŷ���������
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ�����~";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// ���ͷ��˺�(open_id)
			String fromUserName = requestMap.get("FromUserName");
			// �����˺�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// ��Ϣ����
			String content = requestMap.get("Content");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//retMessage retm = new retMessage();
				
				if(content.charAt(0) == '1') {
					// ���봦�������Ĺ���
					//retm.add("����");
					//respContent = retm.get();
					respContent = "����\n";
				} else if(content.charAt(0) == '2') {
					// ���봦�����۵Ĺ��� 
					//retm.add("����");
					//respContent = retm.get();
					respContent = "����\n";
					
				} else if(content.charAt(0) == '?' || content.charAt(0) == '��') {
					respContent = defaultmessage.getMenu();
				} else {
					respContent = defaultmessage.getInvalid();
				}
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = defaultmessage.getAttention();
					respContent += defaultmessage.getMenu();
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// ��
				}
			} else {
				respContent = defaultmessage.getInvalid();
			}
			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}
