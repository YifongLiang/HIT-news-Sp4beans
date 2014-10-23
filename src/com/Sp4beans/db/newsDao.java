package com.Sp4beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Sp4beans.Flag.Flag;
import com.Sp4beans.news.moudle.News;

public class newsDao {
	public static void clearDb(Flag flag) throws SQLException {
		Connection con = Connect.getConnection();
		String sql = "";
		if (flag.isFLAG()) {
			sql = "truncate table news1";
		} else {
			sql = "truncate table news2";
		}
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		Connect.closeConnection(con);
	}

	public static void saveToDb(News news, Flag flag) {
		Connection con = Connect.getConnection();
		String insql = "";
		if (flag.isFLAG()) {
			insql = "insert into news1(id, title, content, url, date) values(?,?,?,?,?)";
		} else {
			insql = "insert into news2(id, title, content, url, date) values(?,?,?,?,?)";
		}
		try {
			PreparedStatement ps = con.prepareStatement(insql);
			ps.setString(1, news.getId());
			ps.setString(2, news.getTitle());
			ps.setString(3, news.getContent());
			ps.setString(4, news.getUrl());
			ps.setString(5, news.getDate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connect.closeConnection(con);
	}
}
