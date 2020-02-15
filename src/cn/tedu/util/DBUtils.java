package cn.tedu.util;
/**
 * 数据库工具类
 * 	c3p0连接池来获取数据库资源连接
 */

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	// 创建连接池对象
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	// 返回连接对象的方法
	public static Connection getConn() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 返回连接池的方法
	public static DataSource getDataSource() {
		
		return dataSource;
	}
	
}
