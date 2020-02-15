package cn.tedu.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.tedu.bean.User;
import cn.tedu.util.DBUtils;

public class ScoreManagement {
	public static List<User> getScore() {
		QueryRunner run = new QueryRunner(DBUtils.getDataSource());
		
		String sql = "select * from score";
		List<User> list = null;
		// 第一个参数:SQL
		// 第二个参数: 返回的结果集应该封装为什么样的自定义对象
		try {
				// <>中指定了要将每条数据封装的JavaBean类型
			list = run.query(sql, new BeanListHandler<User>(User.class));
			Collections.sort(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void insertScore(User u) {
		QueryRunner run = new QueryRunner(DBUtils.getDataSource());
		
		String sql = "insert into score(uName, score, time) values(?,?,?)";
		// 第一个参数:SQL
		// 第二个参数: 返回的结果集应该封装为什么样的自定义对象
		try {
				// <>中指定了要将每条数据封装的JavaBean类型
			Date d = new Date();
			// 使用SimpleDateFormat类实现对时间格式的转变
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			Object[] params = new Object[]{u.getuName(), u.getScore(), time};
			run.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
