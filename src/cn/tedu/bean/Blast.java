package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Blast {
	private int x, y;
	private Image img;
	private boolean state = true;
	private int dx = 0;
	private Timer t;	// 计时器
	
	public Blast(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
		
		t = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dx += 66;
				if(dx > 66*7) {
					state = false;
					t.stop();
				}
			}
		});
		t.start();
	}
	
	public void paintBlast(Graphics g) {
		if(state) {
			g.drawImage(img, 			// 需要绘制的图片
					x, y, x+66, y+66, 	// 绘制的坐标
					dx, 0, dx+66, 66, 	// 在原图片中的位置
					null);
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
}
