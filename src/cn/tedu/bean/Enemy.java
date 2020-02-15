package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;

public class Enemy {
	private int x, y;
	private Image img;
	private boolean state = true;
	private int speed;
	private int hp=10;
	private int xdir = 0;
	
	public int getXdir() {
		return xdir;
	}

	public void setXdir(int xdir) {
		this.xdir = xdir;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	// 构造函数
	public Enemy(int x, int y, Image img, int speed) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.speed = speed;
	}
	
	// 画敌机方法
	public void paintEnemy(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	// 敌机移动的方法
	public void enemyMove() {
		y += speed;
		// 移动就意味着可能出界
		if(y > (900+img.getHeight(null))) {
			state = false;
		}
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
