package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;

/*
 * 英雄机类
 */
public class Hero {
	private int x,y;          //x，y坐标
	private Image img;   //英雄机显示图片
	private int hp=100;          //英雄机的生命值
	private boolean state=true;
	private int firePower = 1;
	
	public int getFirePower() {
		return firePower;
	}
	public void setFirePower(int firePower) {
		this.firePower = firePower;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	//构造函数
	public Hero(int x, int y, Image img){
		this.x=x;
		this.y=y;
		this.img=img;
	}
	//绘制英雄机
	public void paintHero(Graphics g){
		if (isState())
			g.drawImage(img, x, y, null);
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
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
}
