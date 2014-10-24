package com.Sp4beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Sp4beans.news.moudle.News;

public class newsDao {
	public static void clearAll(Connection con) {
		clearNews(con);
		clearHash(con);
	}
	public static void clearNews(Connection con) {
		String sql = "truncate table news";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 清除hash中的信息
	public static void clearHash(Connection con) {
		String sql = "truncate table hash";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// hash表中是否包含id ture 表示hash表中存在该id
	public static boolean isHashed(Connection con, String id) {
		String sql = "select * from hash where id = '" + id + "'";
		ResultSet rs = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				if (rs != null)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// 新闻信息是否被保存过 ture 表示news表中存在该新闻信息
	public static boolean isSaved(Connection con, String id) {
		String sql = "select * from news where id = '" + id + "'";
		ResultSet rs = null;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				if (rs != null)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 将新闻信息存入数据库
	public static void saveToDb(Connection con, News news) {
		String insql = "insert into news(id, title, content, url, date) values(?,?,?,?,?)";
		// insql = "insert into news(id, title, url, date) values(?,?,?,?)";
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
	}
	
	// 将id存入hash表
	public static void saveToHash(Connection con, String id) {
		String insql = "insert into hash(id) values(?)";
		// insql = "insert into news(id, title, url, date) values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
