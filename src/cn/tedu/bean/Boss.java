package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/*
 * 定义boss对象
 */
public class Boss {
	int x, y;
	private Image img;
	private boolean state = true;
	private int speed;
	private int hp = 10;

	// 构造函数
	public Boss(int x, int y, Image img, int speed) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.speed = speed;
	}

	// 画boss的方法
	public void paintBoss(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	// boss移动的方法
	public void bossMove() {
		y += speed;
		// 移动就意味着可能出界
		if (y > (600 + img.getHeight(null))) {
			state = false;
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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

}
