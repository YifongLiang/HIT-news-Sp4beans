package com.Sp4beans.spider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.Sp4beans.db.Connect;
import com.Sp4beans.db.newsDao;
import com.Sp4beans.news.moudle.News;

public class GetInfo {
	public static void getInfoCs(Connection con) throws ParseException, SQLException {
		String seed = "http://cs.hit.edu.cn/?q=notice";
		Queue q = new Queue();
		q.enQueue(seed);
		Date now = new Date();
		while (!q.isQueueEmpty()) {
			String tmp = q.deQueue();
			String md5 = MD5.getMD5(tmp);
			Type type = Filter.istype(tmp);
//			System.out.println(tmp);
//			System.out.println(newsDao.isSaved(con, md5));
			if (!newsDao.isHashed(con, md5)) {
				try {
					System.out.println(tmp);
					Document doc = Jsoup.connect(tmp).get();
					if (Filter.isnews(tmp, type) && !newsDao.isSaved(con, md5)) {
						News news = new News();
						news.setId(md5);
						news.setUrl(tmp);
						news.setTitle(Pages.getTitle(doc, type));
						news.setDate(Pages.getDate(doc, type));
						news.setContent(Pages.getContent(doc, type));
						newsDao.saveToDb(con, news);
					} else if (Filter.islist(tmp, type)) {
						q = Pages.getLinks(doc, q, type, now);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				//map.put(md5, tmp);
				newsDao.saveToHash(con, md5);
			}
		}
	}

	public static void main(String args[]) throws ParseException, SQLException {
		Connection con = Connect.getConnection();
		newsDao.clearAll(con);
//		newsDao.clearHash(con);
//		getInfoCs(con);
		Connect.closeConnection(con);
	}

	// public static void getInfoCs_News(Flag flag) {
	//
	// }
}
