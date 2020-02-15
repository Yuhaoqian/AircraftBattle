package cn.tedu.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.tedu.bean.User;
/**
 * 飞机大战排行榜IO读写工具类
 * @author qianyuhao
 *
 */
public class FileOperation {
	/*
	 * 将用户名、分数、时间写入在指定文件中
	 */
	public static void writeToTxt(String name, int score, String time) {
		FileWriter fw = null;
		BufferedWriter bfw = null;
		try {
			// 创建输出流对象时：输出的文件对象/路径，是否追加
			// 第二个参数：true相加，false覆盖
			fw = new FileWriter("score.txt", true);
			bfw = new BufferedWriter(fw);
			String line = name + "," + score + "," + time;
			bfw.write(line);
			bfw.newLine();
			bfw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeWriter(bfw);
			closeWriter(fw);
		}
	}
	/*
	 * 读取文件中所有数据，并把每一条数据转换为user对象
	 * 之后将所有的User对象存储在一个List集合中
	 */
	
	public static List<User> readScore() {
		FileReader fr = null;
		BufferedReader bfr = null;
		List<User> scoreList = new ArrayList<User>();
		// 将从文件中得到数据保存在list集合
		try {
			fr = new FileReader("score.txt");
			bfr = new BufferedReader(fr);
			// 读取第一行数据
			String line = bfr.readLine();
			// 如果line不为空，那么表示文件没有读完
			while(line != null) {
				// 将line中数据转变为user对象
				// 2,59,2019-11-25 16:44:42
				String[] str = line.split(",");
				User user = new User();
				user.setuName(str[0]);
				// Integer.parseInt(str[1]) 将String强转为int类型的值  
				user.setScore(Integer.parseInt(str[1]));
				user.setTime(str[2]);
				// 将user对象存储在list集合中
				scoreList.add(user);
				line = bfr.readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeReader(bfr);
			closeReader(fr);
		}
		// 对集合中的数据进行排序
		Collections.sort(scoreList);
		return scoreList;
	}
	/*
	 * 统一关闭字符输出流
	 */
	public static void closeWriter(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				writer = null;
			}
		}
	}
	/*
	 * 统一关闭字符输入流
	 */
	public static void closeReader(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				reader = null;
			}
		}
	}

}
