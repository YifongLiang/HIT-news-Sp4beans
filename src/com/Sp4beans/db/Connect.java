package com.Sp4beans.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Á´½ÓÊý¾Ý¿â
 *
 */

public class Connect {
	private static String dbDriver = "com.mysql.jdbc.Driver";

	// private String dbUrl =
	// "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_wangjixin";
	// private String dbUser = "n3knl5n2zx";
	// private String dbPass = "1m511yh2k2jyhlwkxh0wx1y14w5z0hlk0lm3kx2l";

	public static Connection getConnection() {
		Connection con = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName(dbDriver);
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hitnews",
							"root", "superbeans");
			// con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
