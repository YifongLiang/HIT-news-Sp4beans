package com.Sp4beans.wechat.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "文本消息~\n";
				respContent += fromUserName + "\n";
				respContent += toUserName + "\n";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "图片消息~";
			}
			// 地理位置消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "地理位置消息~";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "链接消息~";
			}
			// 音頻消息
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "音频消息~";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注~";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 无
				}
			}
			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}
