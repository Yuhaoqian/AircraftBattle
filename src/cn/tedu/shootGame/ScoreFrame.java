package cn.tedu.shootGame;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.tedu.bean.User;
import cn.tedu.service.ScoreManagement;

/*
 * 显示排行榜的窗体
 * 
 */
public class ScoreFrame {
	public static void main(String[] args) {
		new ScoreFrame().initFrame();
	}
	// 初始化窗体的方法
	public void initFrame() {
		// 获取屏幕的宽高
		int fw = Toolkit.getDefaultToolkit().getScreenSize().width;
		int fh = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("排行榜");
		// 设置窗体的图标
		frame.setIconImage(new ImageIcon("shootgameImg/1.png").getImage());
		// 指定窗体的宽高
		frame.setBounds((fw-600)/2, (fh-900)/2, 600, 900);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 设定窗体大小不可更改
		frame.setResizable(false);
		//  
		
		initTable(frame);
		frame.setVisible(true);
	}
	
	// 初始化表格的方法
	// 参数，指定将来表格显示的窗体显示在哪个窗体
	public void initTable(JFrame frame) {
		// 定义表格对象
		JTable table = new JTable();
		table.setBackground(Color.orange);
		// 定义表头
		String[] colName = {"用户名称", "分数", "时间", "排名"};
		// 通过model对象向表格中插入数据
		// 第一个参数：具体数据
		// 第二个参数：向哪一行插入数据 行是从0开始的
		// 第三个参数：向哪一列插入数据 列是从0开始的.
//		model.setValueAt("张三", 0, 0);
//		model.setValueAt("9000", 0, 1);
//		model.setValueAt("2019-11-25 17:23:00", 0, 2);
//		model.setValueAt("第一名", 0, 3);
		
		List<User> scoreList = ScoreManagement.getScore();
		// 创建表模型 参数：表头，表中数据的行数 	表中的行数可以通过集合.size()实现
		DefaultTableModel model = new DefaultTableModel(colName, scoreList.size());
		// 遍历循环
		for (int i = 0; i < scoreList.size(); i++) {
			// 获取每一条记录
			User user = scoreList.get(i);
			model.setValueAt(user.getuName(), i, 0);
			model.setValueAt(user.getScore(), i, 1);
			model.setValueAt(user.getTime(), i, 2);
			model.setValueAt("第" + (i + 1) + "名", i, 3);
		}
		// 将表模型添加到表格对象
		table.setModel(model);
		// 设置表格内容不可编辑
		table.setEnabled(false);
		// 创建要显示表格的面板对象
		// 使用JScrollPane可以根据表格内的数据判断是否显示滚动条
		JScrollPane pane = new JScrollPane(table);
		// 将面板对象添加到窗体上
		frame.add(pane);
		
	}
}
