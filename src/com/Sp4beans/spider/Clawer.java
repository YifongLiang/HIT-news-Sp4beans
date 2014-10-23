package com.Sp4beans.spider;

import java.sql.SQLException;
import java.text.ParseException;

import com.Sp4beans.Flag.Flag;
import com.Sp4beans.db.newsDao;

/**
 * 爬虫，将爬取的信息存储到数据库
 */

public class Clawer {
	public static void getInfo(Flag flag) throws ParseException, SQLException {
		// 清除原有表中信息
		newsDao.clearDb(flag);
		
		GetInfo.getInfoCs(flag);
		//GetInfo.getInfoCs_news();
		
		flag.changeFLAG();
	}

	public static void main(String args[]) throws ParseException, SQLException {
		Flag flag = new Flag();
		getInfo(flag);
		
		getInfo(flag);
	}
}
