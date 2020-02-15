package cn.tedu.shootGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.tedu.bean.Blast;
import cn.tedu.bean.Boss;
import cn.tedu.bean.BossBullet;
import cn.tedu.bean.Bullet;
import cn.tedu.bean.Enemy;
import cn.tedu.bean.Hero;
import cn.tedu.bean.Shield;
import cn.tedu.bean.User;
import cn.tedu.service.ScoreManagement;
import cn.tedu.util.MusicPlayer;

class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	// 定义背景图片对象
	static Image bg, bg1, bg2, bg3;
	static {
		try {
			bg = ImageIO.read(new File("shootgameImg/background/background_1.png"));
			bg1 = ImageIO.read(new File("shootgameImg/background/background_2.png"));
			bg2 = ImageIO.read(new File("shootgameImg/background/background_3.png"));
			bg3 = ImageIO.read(new File("shootgameImg/background/background_4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 定义背景图片的坐标
	int bgX = 0;
	int bgY = -5400;
	int bgX1 = 0;
	int bgY1 = -11400;
	int bgX2 = 0;
	int bgY2 = -11400 - 6000;
	int bgX3 = 0;
	int bgY3 = -11400 - 12000;

	// 定义游戏状态
	public static final int RUNNING = 1;
	public static final int STOP = 2;
	public static final int START = 3;
	public static final int OVER = 4;
	public static final int SELECT = 5;

	// 射击模式
	int shootMode = NORMAL;
	public static final int NORMAL = 1;
	public static final int SICKO = 2;
	// 定义表示当前游戏所处的状态
	int state = START;
	// 定义分数
	int score = 0;
	// 定义子弹生成控制器
	int index;
	// 定义开始图片
	static Image startImg;
	// 定义暂停图片
	static Image startImg1;
	// 定义结束图片
	static Image startImg2;
	// 定义选择英雄机界面图片
	static Image selectImg;
	// 定义开始游戏按钮图片
	static Image startGame;
	// 定义logo
	static Image logo;
	// 定义英雄机图片
	static Image heroImg;
	static Image heroImg2;
	static Image heroImg3;
	static Image huluwa;
	// 定义左箭头
	static Image leftArrow;
	// 定义右箭头
	static Image rightArrow;
	// 定义英雄机保护罩图片
	static Image shieldImg;
	// 定义子弹图片
	static Image heroBulletImg;
	static Image heroBulletImg1;
	static Image heroBulletImg2;
	static Image heroBulletImg3;
	static Image heroBulletLaserImg;
	static Image heroBulletTemp; // 用来记录增加火力前的子弹图片

	static Image killAllImg;

	static Image upgradeImg;

	static ImageIcon saiyanImg;

	static Image huluwaBulletImg;
	// 定义敌机子弹图片
	static Image enemyBulletImg;
	// 定义boss子弹图片
	static Image bossBulletImg;
	// 定义静态图片类型的数组，保存五张敌机图片的数组
	static Image[] enemys = new Image[9];
	// 定义英雄机静态图片的数组，保存n张英雄机图片
	static Image[] heros = new Image[4];
	// 定义英雄机大图片
	static Image[] herosBig = new Image[4];

	// 定义BOSS图片
	static Image[] bosses = new Image[3];

	static int heroIndex = 1; // 英雄机的索引
	// 定义爆炸效果
	static Image blastImg;
	// 定义
	static boolean flag = false;
	// 定义大招是否开启
	static boolean killFlag = false;
	//
	static int timeCount = 0;
	// 在静态代码块中为静态属性赋值
	static int bossIndex = 0;

	static boolean bossFlag = true;
	
	static boolean bgmFlag = true;
	static {
		try {
			startImg = ImageIO.read(new File("shootgameImg/GameInterface/interface_1.png"));
			startImg1 = ImageIO.read(new File("shootgameImg/GameInterface/pause.png"));
			startImg2 = ImageIO.read(new File("shootgameImg/GameInterface/jeimian_2.png"));

			selectImg = ImageIO.read(new File("shootgameImg/GameInterface/bgtp.png"));
			startGame = ImageIO.read(new File("shootgameImg/GameInterface/start.png"));
			logo = ImageIO.read(new File("shootgameImg/GameInterface/logo.jpg"));

			heroImg = ImageIO.read(new File("shootgameImg/HeroPlane/plane_1.png"));
			heroImg2 = ImageIO.read(new File("shootgameImg/HeroPlane/plane_2.png"));
			heroImg3 = ImageIO.read(new File("shootgameImg/HeroPlane/plane_3.png"));
			huluwa = ImageIO.read(new File("shootgameImg/HeroPlane/huluwa.png"));

			killAllImg = ImageIO.read(new File("shootgameImg/kill.jpg"));
			upgradeImg = ImageIO.read(new File("shootgameImg/upgrade.png"));
			saiyanImg = new ImageIcon("shootgameImg/saiyan.gif");

			shieldImg = ImageIO.read(new File("shootgameImg/shield.png"));
			heroBulletImg1 = ImageIO.read(new File("shootgameImg/bullet/bullet.png"));
			heroBulletImg2 = ImageIO.read(new File("shootgameImg/bullet/25.png"));
			heroBulletImg3 = ImageIO.read(new File("shootgameImg/bullet/23.png"));
			heroBulletImg = heroBulletImg1;
			heroBulletTemp = heroBulletImg;
			heroBulletLaserImg = ImageIO.read(new File("shootgameImg/bullet/bullet_3.png"));

			bossBulletImg = ImageIO.read(new File("shootgameImg/bullet/bullet_5.png"));

			enemyBulletImg = ImageIO.read(new File("shootgameImg/bullet/27.png"));

			huluwaBulletImg = ImageIO.read(new File("shootgameImg/bullet/Bullet_huluwa.png"));

			leftArrow = ImageIO.read(new File("shootgameImg/left.png"));
			rightArrow = ImageIO.read(new File("shootgameImg/right.png"));

			blastImg = ImageIO.read(new File("shootgameImg/bang1.png"));
			for (int i = 0; i < 9; i++) { // 通过for循环为数组赋值
				enemys[i] = ImageIO.read(new File("shootgameImg/LittlePlane/enemy_" + (i + 1) + ".png"));

			}
			for (int i = 1; i <= 3; i++) {
				heros[i] = ImageIO.read(new File("shootgameImg/HeroPlane/plane_" + i + ".png"));
			}
			for (int i = 1; i <= 3; i++) {
				herosBig[i] = ImageIO.read(new File("shootgameImg/HeroPlane/select/plane_" + i + ".png"));
			}
			for (int i = 0; i < 3; i++) {
				bosses[i] = ImageIO.read(new File("shootgameImg/BossPlane/plane_" + (i + 4) + ".png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 创建英雄机对象
	Hero hero;
	Boolean heroFlag = false;
	Hero helper1 = new Hero(100, 300, huluwa);
	Hero helper2 = new Hero(400, 300, huluwa);
	Hero hero3;
	// 创建boss对象
	ArrayList<Boss> bossList = new ArrayList<>(); // 创建英雄机保护罩对象
	Shield shield = new Shield(150, 450, shieldImg);
	// 创建英雄机子弹集合
	ArrayList<Bullet> heroList = new ArrayList<>();
	// 创建boss子弹集合
	ArrayList<BossBullet> bossbulletList = new ArrayList<>();
	// 创建敌机集合
	ArrayList<Enemy> enemyList = new ArrayList<>();

	Enemy boss;
	// 创建敌机子弹集合
	ArrayList<Bullet> enemyBulletList = new ArrayList<>();
	// 创建爆炸效果集合
	ArrayList<Blast> blastList = new ArrayList<>();

	// 创建Music Player
	public static MusicPlayer mp = new MusicPlayer();

	// 鼠标事件
	public GamePanel() {
		// 添加鼠标事件
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setFocusable(true);
		// 添加键盘事件
		this.addKeyListener(this);
	}

	// 画方法--重写父类方法
	@Override
	public void paint(Graphics g) {
		super.paint(g);

//		根据游戏状态绘制不同内容
		if (state == START) {
			if (bgmFlag == true) {
				mp.playBgm();
				bgmFlag = false;

			}

			// 绘制开始界面
			g.drawImage(startImg, 0, 0, null);

		} else if (state == SELECT) {
			g.drawImage(selectImg, 0, 0, null);
			g.drawImage(startGame, 90, 656, null);
			g.drawImage(logo, 0, 0, null);

			g.setColor(Color.CYAN);
//			g.fillRect(200, 300, 220, 300);
			g.fillRect(461, 364, 99, 145);
			g.fillRect(53, 364, 95, 145);

			g.drawImage(leftArrow, 50, 360, null);
			g.drawImage(rightArrow, 460, 360, null);
			g.drawImage(herosBig[heroIndex], 165, 300, null);
		} else if (state == STOP || state == RUNNING) {

			// 绘制运行界面
			g.drawImage(bg, bgX, bgY, null);
			g.drawImage(bg1, bgX1, bgY1, null);
			g.drawImage(bg2, bgX2, bgY2, null);
			g.drawImage(bg3, bgX3, bgY3, null);
			// 绘制英雄机
			hero.paintHero(g);
			// 绘制帮机
			helper1.paintHero(g);
			helper2.paintHero(g);
//			if(hero2.isState() == true)
//				hero2.paintHero(g);
			shield.paintShield(g);
			// 绘制英雄机生命条
			// 画英雄机的生命值
			g.setColor(Color.cyan);
			g.setFont(new Font("楷体", Font.BOLD, 20));
			g.drawString("HP:", 10, 56);
			g.setColor(Color.cyan);

			g.setColor(Color.green);
			// 畫筆進行升級
			Graphics2D g2 = (Graphics2D) g;
			// 設置g2畫筆的綫條厚度
			g2.setStroke(new BasicStroke(20));
			int hpx = 55;
			if (hero.getHp() < 100) {
				g2.setColor(Color.green);
				g2.drawLine(hpx, 50, hpx + hero.getHp(), 50);
				g2.setColor(Color.red);
				g2.drawLine(hpx + hero.getHp(), 50, hpx + 100, 50);
			} else if (hero.getHp() == 100) {
				g2.setColor(Color.green);
				g2.drawLine(hpx, 50, hpx + 100, 50);
			}
			// 绘制敌机生命值
			g2.setStroke(new BasicStroke(5));

			for (int i = 0; i < enemyList.size(); i++) {
				Enemy e = enemyList.get(i);
				if (e.getHp() < 10) {
					g2.setColor(Color.green);
					g2.drawLine(e.getX(), e.getY() + e.getImg().getHeight(null), e.getX() + e.getHp() * 5,
							e.getY() + e.getImg().getHeight(null));
					g2.setColor(Color.red);
					g2.drawLine(e.getX() + e.getHp() * 5, e.getY() + e.getImg().getHeight(null), e.getX() + 50,
							e.getY() + e.getImg().getHeight(null));
				} else if (e.getHp() == 10) {
					g2.setColor(Color.green);
					g2.drawLine(e.getX(), e.getY() + e.getImg().getHeight(null), e.getX() + 50,
							e.getY() + e.getImg().getHeight(null));
				}
			}
			// 绘制英雄机子弹
			for (int i = 0; i < heroList.size(); i++) {
				// 获取每一个子弹对象，并调用绘制子弹的方法
				heroList.get(i).paintBullet(g);
			}

			// 绘制敌机
			for (int i = 0; i < enemyList.size(); i++) {
				enemyList.get(i).paintEnemy(g);
			}

			// 绘制敌机子弹
			for (int i = 0; i < enemyBulletList.size(); i++) {
				enemyBulletList.get(i).paintBullet(g);
			}
			// 绘制boss
			for (int i = 0; i < bossList.size(); i++) {
				bossList.get(i).paintBoss(g);
			}
			// 绘制boss子弹
			for (int i = 0; i < bossbulletList.size(); i++) {
				bossbulletList.get(i).paintBossBullet(g);
			}
			// 绘制爆炸效果
			for (int i = 0; i < blastList.size(); i++) {
				blastList.get(i).paintBlast(g);
			}
			// 绘制分数
			g.setColor(Color.cyan);
			g.setFont(new Font("楷体", Font.BOLD, 20));
			g.drawString("SCORE: " + score + "", 10, 106);

			if (bossFlag == false && boss.isState()) {
				boss.paintEnemy(g);
				g.setFont(new Font("楷体", Font.BOLD, 50));
				g.drawString("BOSS HP：" + boss.getHp(), 200, 50);
			}
			if (killFlag == true) {
				g.drawImage(killAllImg, 200, 200, null);
				g.setColor(Color.red);
				g.setFont(new Font("楷体", Font.BOLD, 50));
				g.drawString("龙神の剣を喰らえ！！！", 80, 200);
				g.drawImage(upgradeImg, 100, 800, null);
				g.drawImage(saiyanImg.getImage(), 0, 475, null);

			}

		}
		if (state == STOP) {
			// 绘制暂停图片
			g.drawImage(startImg1, 0, 0, null);
		} else if (state == OVER) {
			// 绘制结束图片
			g.drawImage(startImg2, 0, 0, null);
		}
	}

	// 移动方法
	public void move() {
		// 创建线程并且启动线程
		new Thread() {
			public void run() {
				while (true) {
					// 业务逻辑
					if (state == START) {
						
						// 绘制开始界面
						
					} else if (state == RUNNING) {
						if (heroFlag == false) {
							hero = new Hero(150, 250, heros[heroIndex]);
							helper1.setState(false);
							helper2.setState(false);
							heroFlag = true;
						}
						if (score >= 500 && bossFlag) {
							int ex = 80;
							int ey = 100;
							boss = new Enemy(ex, ey, bosses[0], 0);
							boss.setHp(10000);
							bossFlag = false;
						}
						// 将flag标记设置为true
						flag = true;
						index++;
						if (hero.getHp() <= 0)
							state = OVER;
						// 绘制运行界面
						bgmove(); // 背景移动
						createHeroBullet(); // 创建英雄机子弹
						createHelperBullet(helper1);
						createHelperBullet(helper2);
						createEnemy(); // 生成敌机
						createEnemyBullet(); // 生成敌机子弹
						action(); // 英雄机子弹和敌机发生碰撞
						haction(); // 英雄机和敌机发生碰撞
						hebAction(); // 敌机子弹和英雄机发生碰撞
						sebAction();
						if (timeCount <= 200 && killFlag == true) {
							timeCount++;
						}
						if (timeCount == 100) {
							shootMode = SICKO;
							helper1.setState(true);
							helper2.setState(true);
							shield.setState(true);
							hero.setFirePower(5);
						}
						if (timeCount == 200) {
							killFlag = false;
							timeCount = 0;
							mp.stopBgm();
							mp.playSicko();
						}
//						 子弹移动
						for (int i = 0; i < heroList.size(); i++) {
							if (shootMode == NORMAL)
								heroList.get(i).bulletMove();
							else
								heroList.get(i).traceMove(enemyList);
						}
						// 敌机移动
						for (int i = 0; i < enemyList.size(); i++) {
							enemyList.get(i).enemyMove();
						}
						// 敌机子弹移动
						for (int i = 0; i < enemyBulletList.size(); i++) {
							enemyBulletList.get(i).bulletMove();
						}
						// boss的移动
						for (int i = 0; i < bossList.size(); i++) {
							bossList.get(i).bossMove();
						}
						// 移除失效的敌机
						removeEnemy();
						// 移除失效的子弹
						removeHeroBulleet();
						// 移除失效爆炸效果
						removeblast();
//						System.out.println(heroList.size()+"+=======+"+enemyList.size());

					} else if (state == STOP) {
						// 绘制暂停界面
					} else if (state == OVER && flag) {
						// 产生一个输入框，可以输入用户的名称
						String userName = JOptionPane.showInputDialog(null, "请输入玩家名称！", JOptionPane.OK_CANCEL_OPTION);
						flag = false;
						// 绘制当前的事件
						User u = new User();
						u.setScore(score);
						u.setuName(userName);
						ScoreManagement.insertScore(u);
					}
					// 重绘
					repaint();
					// 线程休眠--毫秒级别
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	// 判断所有敌机和英雄机的碰撞处理
	public void haction() {
		for (int i = 0; i < enemyList.size(); i++) {
			// 获取每一个敌机对象
			Enemy e = enemyList.get(i);
			// 设置敌机和英雄机的状态为false
			// 判断是否发生碰撞
			if (isCollision(hero, e)) {
				if (shield.isState() == false) {
					// 英雄机生命值减5(有护盾就不扣血）
					hero.setHp(hero.getHp() - 5);
				}
				// 分数增加
				score += e.getSpeed();
				// 创建爆炸效果对象
				Blast b = new Blast(e.getX(), e.getY(), blastImg);
				// 将爆炸效果添加到集合中
				blastList.add(b);
				// 如果发生碰撞将英雄机、敌机状态设置为失效
				e.setState(false);
			}
		}
	}

	// 判断所有敌机和所有英雄机子弹的碰撞处理
	public void action() {
		if (bossFlag == false) {
			for (int j = 0; j < heroList.size(); j++) {
				// 获取每一个英雄机子弹的对象
				Bullet bullet = heroList.get(j);
				// 设置敌机和英雄机子弹的状态为false
				// 判断是否发生碰撞
				if (isCollision(bullet, boss)) {
					
					// 如果发生碰撞将子弹、敌机状态设置为失效
					if (boss.getHp() - hero.getFirePower() <= 0) {
						// 创建爆炸效果对象
						Blast b = new Blast(boss.getX(), boss.getY(), blastImg);
						// 将爆炸效果添加到集合中
						blastList.add(b);
						boss.setState(false);
					}
					boss.setHp(boss.getHp() - hero.getFirePower() * 2);
					bullet.setState(false);

					// 分数增加
					score += boss.getSpeed();

				}
			}
		}

		for (int i = 0; i < enemyList.size(); i++) {
			// 获取每一个敌机对象
			Enemy e = enemyList.get(i);
			for (int j = 0; j < heroList.size(); j++) {
				// 获取每一个英雄机子弹的对象
				Bullet bullet = heroList.get(j);
				// 设置敌机和英雄机子弹的状态为false
				// 判断是否发生碰撞
				if (isCollision(bullet, e)) {
					// 如果发生碰撞将子弹、敌机状态设置为失效

					if (e.getHp() - hero.getFirePower() <= 0) {
						// 创建爆炸效果对象
						Blast b = new Blast(e.getX(), e.getY(), blastImg);
						// 将爆炸效果添加到集合中
						blastList.add(b);
						e.setState(false);
					}
					e.setHp(e.getHp() - hero.getFirePower() * 2);
					bullet.setState(false);

					// 分数增加
					score += e.getSpeed();

				}
				// break,return,continue----终止for循环
			}
		}
	}

	// 判断所有敌机子弹和英雄机的碰撞处理
	public void hebAction() {
		for (int i = 0; i < enemyBulletList.size(); i++) {
			// 获取每一个敌机对象
			Bullet eb = enemyBulletList.get(i);
			// 判断是否发生碰撞
			if (isCollision(hero, eb) && shield.isState() == false) {
				// 如果发生碰撞将子弹、敌机状态设置为失效
				eb.setState(false);
				hero.setHp(hero.getHp() - 3);
			}
		}
	}

	// 护盾和敌机子弹的碰撞
	public void sebAction() {
		for (int i = 0; i < enemyBulletList.size(); i++) {
			// 获取每一个敌机对象
			Bullet eb = enemyBulletList.get(i);
			// 判断是否发生碰撞
			if (isCollision(shield, eb)) {
				// 如果发生碰撞将敌机子弹状态设置为失效
				eb.setState(false);
			}
		}
	}

	// 判断敌机和英雄机子弹是否发生碰撞
	// 当地调用该方法，返回true，表示发生碰撞，返回false，表示没有碰撞
	public boolean isCollision(Bullet b, Enemy e) {
		if (b.isState() && e.isState()) {
			// 获取敌机x，y坐标以及敌机图片的、宽高
			int ex = e.getX();
			int ey = e.getY();
			int ew = e.getImg().getWidth(null);
			int eh = e.getImg().getHeight(null);

			// 获取英雄机子弹的x,y坐标以及子弹图片的宽高
			int bx = b.getX();
			int by = b.getY();
			int bw = b.getImg().getWidth(null);
			int bh = b.getImg().getHeight(null);
			// 判断英雄机子弹的x坐标是否在敌机所在的图片范围内
			boolean fx = bx >= (ex - bw) && bx <= (ex + ew);
			boolean fy = by >= (ey - bh) && by <= (ey + eh);
			return fx && fy;
		}
		return false;
	}

	// 判断敌机和英雄机是否发生碰撞
	// 当地调用该方法，返回true，表示发生碰撞，返回false，表示没有碰撞
	public boolean isCollision(Hero h, Enemy e) {
		if (h.isState() && e.isState()) {
			// 获取敌机x，y坐标以及敌机图片的、宽高
			int ex = e.getX();
			int ey = e.getY();
			int ew = e.getImg().getWidth(null);
			int eh = e.getImg().getHeight(null);

			// 获取英雄机的x,y坐标
			int hx = h.getX() + (h.getImg().getWidth(null)) / 2;
			int hy = h.getY() + (h.getImg().getHeight(null)) / 2;
//		int hw = h.getImg().getWidth(null);
//		int hh = h.getImg().getHeight(null);
			// 判断英雄机的x坐标是否在敌机所在的图片范围内
			boolean fx = hx >= (ex) && hx <= (ex + ew);
			boolean fy = hy >= (ey) && hy <= (ey + eh);
			return fx && fy;
		}
		return false;
	}

	// 判断敌机子弹和英雄机是否发生碰撞
	public boolean isCollision(Hero h, Bullet eb) {
		if (eb.isState() && h.isState()) {
			// 获取敌机子弹x，y坐标以及敌机图片的、宽高
			int ebx = eb.getX();
			int eby = eb.getY();
			int ebw = eb.getImg().getWidth(null);
			int ebh = eb.getImg().getHeight(null);

			// 获取英雄机的x,y坐标以及图片的宽高
			int hx = h.getX();
			int hy = h.getY();
			int hw = h.getImg().getWidth(null);
			int hh = h.getImg().getHeight(null);
			// 判断英雄机子弹的x坐标是否在敌机所在的图片范围内
			boolean fx = ebx >= hx && ebx <= (hx + hw);
			boolean fy = eby >= hy && eby <= (hy + hh);
			return fx && fy;
		}
		return false;
	}

	// 判断敌机子弹和英雄机护盾是否发生碰撞
	public boolean isCollision(Shield s, Bullet eb) {
		if (eb.isState() && s.isState()) {
			// 获取敌机子弹x，y坐标以及敌机图片的、宽高
			int ebx = eb.getX();
			int eby = eb.getY();
			int ebw = eb.getImg().getWidth(null);
			int ebh = eb.getImg().getHeight(null);

			// 获取英雄机护盾的x,y坐标以及图片的宽高
			int sx = s.getX();
			int sy = s.getY();
			int sw = s.getImg().getWidth(null);
			int sh = s.getImg().getHeight(null);
			// 判断敌机子弹的x坐标是否在护盾所在的图片范围内
			boolean fx = ebx >= sx && ebx <= (sx + sw);
			boolean fy = eby >= (sy - 30) && eby <= (sy + sh);
			return fx && fy;
		}
		return false;
	}

	// 生成敌机的方法
	public void createEnemy() {
		// 线程每循环20次产生一个敌机
		if (index % 20 == 0) {
			// 创建敌机
			// 指定生成敌机的样式、图片
			int num = (int) (Math.random() * 9);
			int ex = (int) (Math.random() * (600 - enemys[num].getHeight(null)));
			int ey = 0;
			Enemy e = new Enemy(ex, ey, enemys[num], 2);
			enemyList.add(e);
		}
	}

	// 生成帮机子弹
	public void createHelperBullet(Hero helper) {
		if (index % 10 == 0 && helper.isState()) {
			int hx = helper.getX();
			int hy = helper.getY();
			int bx = hx + (helper.getImg().getWidth(null) - helper.getImg().getWidth(null)) / 2 + 5;
			int by = hy + (helper.getImg().getHeight(null) - helper.getImg().getHeight(null)) / 2;
			Bullet bMid;
			bMid = new Bullet(bx, by, huluwaBulletImg, -20);
			heroList.add(bMid);
		}
	}

	// 生成英雄机子弹的方法
	public void createHeroBullet() {
		// 英雄机产生子弹
		if (index % 10 == 0) {
			int hx = hero.getX();
			int hy = hero.getY();
			int bx = hx + (hero.getImg().getWidth(null) - heroBulletImg.getWidth(null)) / 2;
			int by = hy + (hero.getImg().getHeight(null) - heroBulletImg.getHeight(null)) / 2 - 50;
			Bullet bLeft2;
			Bullet bLeft;
			Bullet bMid;
			Bullet bRight;
			Bullet bRight2;
			switch (hero.getFirePower()) {
			case 1:
				bMid = new Bullet(bx, by, heroBulletImg, -5);
				heroList.add(bMid);
				break;
			case 2:
				bLeft = new Bullet(bx, by, heroBulletImg, -5);
				bLeft.setXdir(-1);

				bRight = new Bullet(bx, by, heroBulletImg, -5);
				bRight.setXdir(1);

				heroList.add(bLeft);
				heroList.add(bRight);
				break;
			case 3:
				bLeft = new Bullet(bx, by, heroBulletImg, -5);
				bLeft.setXdir(-1);
				bMid = new Bullet(bx, by, heroBulletImg, -5);
				bRight = new Bullet(bx, by, heroBulletImg, -5);
				bRight.setXdir(1);
				// 将子弹添加到子弹集合中
				heroList.add(bLeft);
				heroList.add(bMid);
				heroList.add(bRight);
				break;
			case 4:
				bLeft = new Bullet(bx, by, heroBulletImg, -5);
				bLeft.setXdir(-2);
				bMid = new Bullet(bx - 15, by, heroBulletImg, -5);
				heroList.add(bMid);
				bMid = new Bullet(bx + 15, by, heroBulletImg, -5);
				heroList.add(bMid);
				bRight = new Bullet(bx, by, heroBulletImg, -5);
				bRight.setXdir(2);
				// 将子弹添加到子弹集合中
				heroList.add(bLeft);
				heroList.add(bRight);
				break;
			case 5:
				heroBulletImg = heroBulletTemp;
				bLeft = new Bullet(bx, by, heroBulletImg, -5);
				bLeft.setXdir(-2);
				bMid = new Bullet(bx - 15, by, heroBulletImg, -5);
				heroList.add(bMid);
				bMid = new Bullet(bx, by, heroBulletImg, -5);
				heroList.add(bMid);
				bMid = new Bullet(bx + 15, by, heroBulletImg, -5);
				heroList.add(bMid);
				bRight = new Bullet(bx, by, heroBulletImg, -5);
				bRight.setXdir(2);
				// 将子弹添加到子弹集合中
				heroList.add(bLeft);
				heroList.add(bRight);
				break;
			case 6:
				heroBulletImg = heroBulletLaserImg;
				bMid = new Bullet(bx, by, heroBulletImg, -100);
				heroList.add(bMid);
				break;
			}

		}

	}

//	//生成Boss的方法
//	public void createBoss(){
//			//创建boss
//			//指定生成敌机的样式、图片
//		if(index%1000==0){
//			int num=(int)(Math.random()*3);
//			int bx=150;
//			int by=50;
//			Boss b=new Boss(bx,by,bosses[num],0);
//			bossList.add(b);
//		
//		}
//	}
//	//移除boss的方法
//	public void removeBoss(){
//		//从敌机集合中获取每一个敌机对象---循环遍历
//		//判断该对象的状态是否失效----if判断
//		//如果失效，从集合中移除敌机
//		for(int i=0;i<bossList.size();i++){
////			enemyList.get(i);
//			Boss b=bossList.get(i);
//			if(!b.isState()){
////				if(!e.isState())
//				bossList.remove(i);
//			}
//		}
//	}
	// 生成敌机子弹的方法
	public void createEnemyBullet() {
		// 敌机产生子弹

		for (int i = 0; i < enemyList.size(); i++) {
			int random = (int) (Math.random() * 30 + 30);
			if (index % random == 0) {
				Enemy e = enemyList.get(i);
				int ebx = e.getX();
				int eby = e.getY();
				int bx = ebx + (e.getImg().getWidth(null) - enemyBulletImg.getWidth(null)) / 2;
				int by = eby + (e.getImg().getHeight(null) + enemyBulletImg.getHeight(null)) / 2;

				Bullet b = new Bullet(bx, by, enemyBulletImg, 5);
				enemyBulletList.add(b);
			}
		}
		if (bossFlag == false) {
			int random = (int) (Math.random() * 10 + 10);
			if (index % random == 0) {

				int ebx = boss.getX();
				int eby = boss.getY();
				int bx = ebx + (boss.getImg().getWidth(null) - enemyBulletImg.getWidth(null)) / 2;
				int by = eby + (boss.getImg().getHeight(null) + enemyBulletImg.getHeight(null)) / 2;
				Bullet bLeft2;
				Bullet bLeft;
				Bullet bMid;
				Bullet bRight;
				Bullet bRight2;
				bLeft = new Bullet(bx, by, bossBulletImg, 10);
				bLeft.setXdir(-2);
				bMid = new Bullet(bx - 20, by, bossBulletImg, 10);
				enemyBulletList.add(bMid);
				bMid = new Bullet(bx, by, bossBulletImg, 10);
				enemyBulletList.add(bMid);
				bMid = new Bullet(bx + 20, by, bossBulletImg, 10);
				enemyBulletList.add(bMid);
				bRight = new Bullet(bx, by, bossBulletImg, 10);
				bRight.setXdir(2);
				enemyBulletList.add(bLeft);
				enemyBulletList.add(bRight);
				
			}
		}

	}

	// 移除爆炸效果的方法
	public void removeblast() {
		// 遍历爆炸效果集合
		for (int i = 0; i < blastList.size(); i++) {
			if (!blastList.get(i).isState()) {
				// 如果状态为false,移除该对象
				blastList.remove(i);
			}
		}
	}

	// 移除敌机的方法
	public void removeEnemy() {
		// 从敌机集合中获取每一个敌机对象---循环遍历
		// 判断该对象的状态是否失效----if判断
		// 如果失效，从集合中移除敌机

		for (int i = 0; i < enemyList.size(); i++) {
//			enemyList.get(i);
			Enemy e = enemyList.get(i);
			if (!e.isState()) {
//				if(!e.isState())
				enemyList.remove(i);
			}
		}
	}

	// 移出英雄机子弹的方法
	public void removeHeroBulleet() {
		// 遍历英雄机子弹集合
		for (int i = 0; i < heroList.size(); i++) {
			// 如果子弹失效就从该集合中移除该子弹对象
			if (!heroList.get(i).isState()) {
				// 调用移除方法
				heroList.remove(i);
			}
		}
	}

	// 背景移动的方法
	public void bgmove() {
		bgY += 5;
		bgY1 += 5;
		bgY2 += 5;
		bgY3 += 5;
		if (bgY3 >= 0) {
			bgY = -8100;
			bgY1 = -8100 - 9000;
			bgY2 = -8100 - 18000;
			bgY3 = -8100 - 27000;
		}
	}

	// 鼠标拖拽
	@Override
	public void mouseDragged(MouseEvent e) {
		// 测试当鼠标发生拖拽，将游戏结束
		if (state == RUNNING) {
			state = OVER;
		}
	}

	// 鼠标移动
	@Override
	public void mouseMoved(MouseEvent e) {
		// 获取鼠标的xy坐标
		int mouseX = e.getX();
		int mouseY = e.getY();
		System.out.println(mouseX + "\t:" + mouseY);
		if (state == START) {
			if (mouseX >= 130 && mouseX <= 260 && mouseY >= 390 && mouseY <= 430) {
				// 当鼠标在开始界面的时候鼠标移入有效点击区域，将鼠标的样式修改
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				// 鼠标移出点击有效区域，还原鼠标样式
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		} else if (state != SELECT) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			// 将鼠标的x,y坐标赋值给英雄机
			hero.setX(mouseX - hero.getImg().getWidth(null) / 2);
			hero.setY(mouseY - hero.getImg().getHeight(null) / 2);
			helper1.setX(hero.getX() - 50);
			helper1.setY(hero.getY() + 30);
			helper2.setX(hero.getX() + 80);
			helper2.setY(hero.getY() + 30);
//			if(hero2.isState() == true) {
//				hero2.setX(hero.getX()-200);
//				hero2.setY(hero.getY());
//			}
			if (shield.isState() == true) {
				shield.setX(mouseX - shield.getImg().getWidth(null) / 2);
				shield.setY(mouseY - shield.getImg().getHeight(null) / 2);
			} else {
				shield.setX(0);
				shield.setY(0);
			}
//			System.out.println("shieldx:" + shield.getX() + "\tshieldy:" + shield.getY());
		}

	}

	// 鼠标点击
	@Override
	public void mouseClicked(MouseEvent e) {
		// 获取鼠标的xy坐标
		int mouseX = e.getX();
		int mouseY = e.getY();
		if (state == START) {
			// 130,390 260,430
			// 指定鼠标点击的有效区域
			if (mouseX >= 193 && mouseX <= 391 && mouseY >= 588 && mouseY <= 658) {
				state = SELECT;// 如果状态为开始，点击鼠标，修改为运行状态
			}

		} else if (state == SELECT) {
			if (mouseX >= 53 && mouseX <= 147 && mouseY >= 365 && mouseY <= 510) {
				heroIndex--;
				if (heroIndex == 0)
					heroIndex = 3;
				System.out.println(heroIndex);
			}
			if (mouseX >= 462 && mouseX <= 560 && mouseY >= 365 && mouseY <= 510) {
				heroIndex++;
				if (heroIndex % 4 == 0)
					heroIndex = 1;
				System.out.println(heroIndex);
			}
			if (mouseX >= 90 && mouseX <= 517 && mouseY >= 656 && mouseY <= 812) {
				state = RUNNING;
			}

		} else if (state == OVER) {
			state = RUNNING;
			score = 0;
			hero.setHp(100);
			hero.setFirePower(1);
			heroBulletImg = heroBulletImg1;
			hero.setImg(heroImg);

			shield.setState(false);
			bgY = 0;
			bgY1 = 0;
			bgY2 = 0;
			bgY3 = 0;

		}
	}

	// 鼠标进入
	@Override
	public void mouseEntered(MouseEvent e) {
		if (state == STOP) {
			state = RUNNING;// 如果状态为STOP，鼠标进入窗体，修改为RUNNING
		}
	}

	// 鼠标移出
	@Override
	public void mouseExited(MouseEvent e) {
		if (state == RUNNING) {
			state = STOP;// 如果状态为RUNNING，鼠标移出窗体，修改为STOP
		}
	}

	// 鼠标按下
	@Override
	public void mousePressed(MouseEvent e) {
//     	if (state==STOP){
//			state=RUNNING;//如果状态为STOP，鼠标点击，修改为RUNNING
//		}else {
//			state=STOP;
//		}
	}

	// 鼠标释放
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A && state == RUNNING) {
			hero.setImg(heroImg);
			System.out.println("hi");
			heroBulletImg = heroBulletImg1;
			heroBulletTemp = heroBulletImg;
		} else if (e.getKeyCode() == KeyEvent.VK_S && state == RUNNING) {
			hero.setImg(heroImg2);
			heroBulletImg = heroBulletImg3;
			heroBulletTemp = heroBulletImg;

		} else if (e.getKeyCode() == KeyEvent.VK_D && state == RUNNING) {
			hero.setImg(heroImg3);
			heroBulletImg = heroBulletImg2;
			heroBulletTemp = heroBulletImg;

		} else if (e.getKeyCode() == KeyEvent.VK_Z && state == RUNNING) {
			if (hero.getFirePower() <= 5) {
				hero.setFirePower(hero.getFirePower() + 1);
				if (hero.getFirePower() == 6) {
					heroBulletTemp = heroBulletImg;
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_X && state == RUNNING) {
			if (hero.getFirePower() == 6) {
				heroBulletImg = heroBulletTemp;
			}
			if (hero.getFirePower() >= 2)
				hero.setFirePower(hero.getFirePower() - 1);
		} else if (e.getKeyCode() == KeyEvent.VK_C && state == RUNNING) {
			if (shield.isState() == true)
				shield.setState(false);
			else
				shield.setState(true);
		} else if (e.getKeyCode() == KeyEvent.VK_V && state == RUNNING) {
			if (helper1.isState() == false) {
				helper1.setState(true);
				helper2.setState(true);
			} else {
				helper1.setState(false);
				helper2.setState(false);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_B && state == RUNNING) {
			if (shootMode == NORMAL) {
				shootMode = SICKO;
			} else {
				shootMode = NORMAL;
			}

		} else if (e.getKeyCode() == KeyEvent.VK_N && state == RUNNING) {
			killFlag = true;
			mp.playKill();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
