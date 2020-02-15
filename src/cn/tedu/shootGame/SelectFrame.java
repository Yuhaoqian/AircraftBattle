package cn.tedu.shootGame;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectFrame {
	public static void main(String[] args) {
		new SelectFrame().initFrame();
	}

	// 初始化窗体的方法
	public void initFrame() {
		// 获取屏幕的宽高
		int fw = Toolkit.getDefaultToolkit().getScreenSize().width;
		int fh = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("选择");
		// 设置窗体的图标
		frame.setIconImage(new ImageIcon("shootgameImg/1.png").getImage());
		// 指定窗体的宽高
		frame.setBounds((fw-600)/2, (fh-900)/2, 600, 900);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane=new JPanel();
		frame.setContentPane(contentPane);
		//创建两个按钮，并且将按钮添加到内容面板中
		JButton b1=new JButton("确定");
		JButton b2=new JButton("取消");
		contentPane.add(b1);
		contentPane.add(b2);
		// 设定窗体大小不可更改
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}
