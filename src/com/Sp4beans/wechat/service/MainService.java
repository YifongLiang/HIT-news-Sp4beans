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
 * 核心服务类
 *
 */

public class MainService {
	/**
	 * 处理微信发来的请求
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候再试~";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方账号(open_id)
			String fromUserName = requestMap.get("FromUserName");
			// 公众账号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String content = requestMap.get("Content");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//retMessage retm = new retMessage();
				
				if(content.charAt(0) == '1') {
					// 加入处理搜索的功能
					//retm.add("搜索");
					//respContent = retm.get();
					respContent = "搜索\n";
				} else if(content.charAt(0) == '2') {
					// 加入处理评价的功能 
					//retm.add("评价");
					//respContent = retm.get();
					respContent = "评价\n";
					
				} else if(content.charAt(0) == '?' || content.charAt(0) == '？') {
					respContent = defaultmessage.getMenu();
				} else {
					respContent = defaultmessage.getInvalid();
				}
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = defaultmessage.getAttention();
					respContent += defaultmessage.getMenu();
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 无
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
