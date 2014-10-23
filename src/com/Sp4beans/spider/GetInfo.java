package com.Sp4beans.spider;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.Sp4beans.Flag.Flag;
import com.Sp4beans.db.newsDao;
import com.Sp4beans.news.moudle.News;

public class GetInfo {
	public static void getInfoCs(Flag flag) throws ParseException {
		HashMap<String, String> map = new HashMap<String, String>();
		String seed = "http://cs.hit.edu.cn/?q=notice";
		Queue q = new Queue();
		q.enQueue(seed);
		Date now = new Date();
		while (!q.isQueueEmpty()) {
			String tmp = q.deQueue();
			String md5 = MD5.getMD5(tmp);
			Type type = Filter.istype(tmp);
			if (!map.containsKey(md5)) {
				try {
					Document doc = Jsoup.connect(tmp).get();
					if (Filter.isnews(tmp, type)) {
						News news = new News();
						news.setId(md5);
						news.setUrl(tmp);
						news.setTitle(Pages.getTitle(doc, type));
						news.setDate(Pages.getDate(doc, type));
						news.setContent(Pages.getContent(doc, type));
						newsDao.saveToDb(news, flag);
					} else if (Filter.islist(tmp, type)) {
						q = Pages.getLinks(doc, q, type, now);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				map.put(md5, tmp);
			}
		}
	}

//	public static void getInfoCs_News(Flag flag) {
//
//	}
}
