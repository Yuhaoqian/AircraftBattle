package cn.tedu.shootGame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameFrame implements ActionListener  {
	// 定义菜单栏对象
	static JMenuBar bar;
	static JMenu game; // 游戏菜单
	static JMenu help; // 帮助菜单
	static JMenuItem score; // 分数菜单项
	
	public static void main(String[] args) {
		new GameFrame();
	}
	public GameFrame() {
		initFrame();
	}
	/**
	 * 初始化窗体方法
	 * 
	 * @param frame
	 */
	public void initFrame() {
		

		
		
		// 获取屏幕的宽高
		int fw = Toolkit.getDefaultToolkit().getScreenSize().width;
		int fh = Toolkit.getDefaultToolkit().getScreenSize().height;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("飞机大战");
		// 设置窗体的图标
		frame.setIconImage(new ImageIcon("shootgameImg/1.png").getImage());
		// 指定窗体的宽高
		frame.setBounds((fw - 600) / 2, (fh - 900) / 2, 600, 900);
		// 创建Panel对象
		GamePanel panel = new GamePanel();
		// 调用move方法
		panel.move();
		// 将panel添加到frame上
		frame.add(panel);
		initJMenu(frame);
		// 设定窗体大小不可更改
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// 初始化菜单的方法
	public void initJMenu(JFrame frame) {
		bar = new JMenuBar(); // 为菜单栏对象赋值
		game = new JMenu("游戏");
		help = new JMenu("帮助");
		// 给help菜单添加鼠标点击事件
		help.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("给帮助菜单添加鼠标点击事件");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		score = new JMenuItem("排行榜");
		// 为排行榜菜单项添加事件
		score.addActionListener(this);
		game.add(score); // 将排行榜菜单项添加到游戏菜单上

		bar.add(game); // 将游戏、排行榜菜单添加到菜单栏上
		bar.add(help);
		frame.setJMenuBar(bar); // 将菜单栏添加到窗体上
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 当点击排行榜菜单项时，显示排行榜窗体。
		new ScoreFrame().initFrame();
	}
}
