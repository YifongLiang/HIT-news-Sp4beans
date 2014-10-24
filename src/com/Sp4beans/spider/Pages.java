package com.Sp4beans.spider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 提取页面信息
 *
 */

public class Pages {

//	/**
//	 * 计算两个日期间相差的天数
//	 */
//	private static int getIntervalDays(Date startday, Date endday) {
//		if (startday.after(endday)) {
//			Date cal = startday;
//			startday = endday;
//			endday = cal;
//		}
//		long sl = startday.getTime();
//		long el = endday.getTime();
//		long ei = el - sl;
//		return (int) (ei / (1000 * 60 * 60 * 24));
//	}
//
//	/**
//	 * 字符串->日期
//	 */
//	private static Date changeToDate(String str) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		return sdf.parse(str);
//	}

	// 获取某网页中的所有有用链接
	public static Queue getLinks(Document doc, Queue q, Type type, Date now)
			throws ParseException {
		switch (type) {
		case CS:
			Elements links = doc.getElementsByTag("tr");
			for (Element link : links) {
				Element ele = link.getElementsByTag("a").first();
				Element ele2 = link.getElementsByClass("views-field-created-1")
						.first();
				if (ele != null) {
					// System.out.println(ele2.text());
					// Date date = changeToDate(ele2.text());
					// int days = getIntervalDays(date, now);
					// if (days <= 100) {
					String linkHref = ele.attr("href");
					q.enQueue("http://cs.hit.edu.cn" + linkHref);
					// }
				}
			}
			links = doc.getElementsByClass("pager-item");
			for (Element link : links) {
				Element ele = link.getElementsByTag("a").first();
				if (ele != null) {
					String linkHref = ele.attr("href");
					q.enQueue("http://cs.hit.edu.cn" + linkHref);
				}
			}
			break;
		case NO:
			break;
		default:
			break;
		}
		return q;
	}

	// 获取新闻日期
	public static String getDate(Document doc, Type type) {
		String ret = "";
		switch (type) {
		case CS:
			Element ele = doc.getElementsByClass("submitted").first();
			// System.out.println(ele.text());
			ret = ele.text();
			break;
		case NO:
			break;
		default:
			break;
		}
		return ret;
	}

	// 获取新闻标题
	public static String getTitle(Document doc, Type type) {
		String ret = "";
		switch (type) {
		case CS:
			Element ele = doc.getElementsByTag("h2").get(3);
			ret = ele.text();
			break;
		case NO:
			break;
		default:
			break;
		}
		return ret;
	}

	// 获取新闻正文
	public static String getContent(Document doc, Type type) {
		String ret = "";
		switch (type) {
		case CS:
			Element ele = doc.getElementsByClass("field-name-body").first();
			// System.out.println(ele.toString());
			ret = ele.toString();
			break;
		case NO:
			break;
		default:
			break;
		}
		return ret;
	}

	public static void main(String args[]) throws IOException {
		Document doc = Jsoup.connect("http://cs.hit.edu.cn/?q=node/1041").get();
		System.out.println(getContent(doc, Type.CS));
	}
}
