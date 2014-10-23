package com.Sp4beans.wechat.message.resp;

import java.util.List;

/**
 * 
 * 图文消息
 *
 */

public class NewsMessage extends BaseMessage {
	// 图文消息的个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Atricles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getAtricles() {
		return Atricles;
	}

	public void setAtricles(List<Article> atricles) {
		Atricles = atricles;
	}

}
