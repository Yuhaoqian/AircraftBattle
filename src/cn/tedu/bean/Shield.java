package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;

public class Shield {
	private int x,y;          //x，y坐标
	private Image img;   //保护罩显示图片
	private boolean state=false;
	//构造函数
	public Shield(int x, int y, Image img){
		this.x=x;
		this.y=y;
		this.img=img;
	}
	//绘制保护罩
	public void paintShield(Graphics g){
		if (state == true)
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
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
