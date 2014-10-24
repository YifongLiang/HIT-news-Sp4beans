package com.Sp4beans.wechat.defaultmessage;

public class defaultmessage {

	public static String getAttention() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎关注工大新闻搜索平台").append("\n\n");
		return buffer.toString();
	}

	public static String getMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好~ 我是Sp4beans， 请回复对应指令选择服务：").append("\n");
		buffer.append("回复 \"1 关键词\" 进行新闻搜索").append("\n");
		buffer.append("(例如： 1 期末考试)").append("\n");
		buffer.append("回复 \"2 评价内容\" 对本平台进行评价").append("\n");
		buffer.append("(例如：2 你们做的太差了！)").append("\n");
		buffer.append("回复 \"？\" 显示此帮助菜单").append("\n");
		return buffer.toString();
	}
	public static String getInvalid() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("无效输入").append("\n");
		buffer.append("回复 \"？\" 以显示帮助菜单").append("\n");
		return buffer.toString();
	}
}
