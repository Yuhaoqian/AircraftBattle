package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

/**
 * 子弹对象:x, y, Image, state(true,false), speed
 */
public class Bullet {
	private int x, y;
	private Image img;
	private boolean state = true;	// 子弹的状态,起始状态为有效状态
	private int speed;		// 子弹移动的速度
	private int xdir=0;
	public int getXdir() {
		return xdir;
	}

	public void setXdir(int xdir) {
		this.xdir = xdir;
	}

	// 构造函数, 定义了创建子弹时需要的变量
	// speed为正值时,向下运动
	// speed为负值时,向上运动
	public Bullet(int x, int y, Image img, int speed) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.speed = speed;
	}
	
	// 绘制子弹
	public void paintBullet(Graphics g) {
		if(state) {	// 失效状态的子弹不绘制
			g.drawImage(img, x, y, null);
		}
	}
	
	// 子弹移动的方法
	public void bulletMove() {
		y += speed;
		x += xdir;
		// 只要子弹移动,就可能会出界
		if(y < -img.getHeight(null)) {
			// 子弹出界,将状态修改为失效状态
			state = false;
		}
	}
	public void traceMove(List<Enemy> list) {
		// TODO Auto-generated method stub
		double delta = 0;
		int eSize = list.size();
		if (eSize > 0) {
			Enemy enemy = list.get(0);
			double deltax = enemy.getX() - x;
			double deltay = enemy.getY() - y;
			if (deltax == 0) {
				if (enemy.getY() >= y) // 子弹需要下移
					deltax = 0.0000001;
				else
					deltax = -0.0000001;// 子弹需要上移
			}
			if (deltay == 0) {
				if (enemy.getX() >= x) // 子弹需要右移
					deltay = 0.0000001;
				else
					deltay = -0.0000001;// 子弹需要左移
			}
			if( deltax>0 && deltay>0 )
				delta = Math.atan(fabs(deltay / deltax)); // 第一象限
			else if( deltax<0 && deltay>0 )
				delta = Math.PI - Math.atan(fabs(deltay / deltax)); // 第二象限
			else if( deltax<0 && deltay<0 )  
				delta = Math.PI + Math.atan(fabs(deltay/deltax)); // 第三象限
			else // 第四象限
				delta = 2 * Math.PI - Math.atan(fabs(deltay/deltax));
			System.out.println("delta:"+delta);
			x += fabs(speed) * Math.cos(delta);
			y += fabs(speed) * Math.sin(delta);
		}else {
			y += speed;
			x += xdir;
		}

		// 只要子弹移动,就可能会出界
		if(y < -img.getHeight(null)) {
			// 子弹出界,将状态修改为失效状态
			state = false;
		}
		

	}
	private double fabs(double d) {
		// TODO Auto-generated method stub
		if (d < 0) {
			d = -d;
		}
		return d;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
