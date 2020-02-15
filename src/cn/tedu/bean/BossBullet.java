package cn.tedu.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BossBullet {
	private int x,y;
	private Image img;
	private boolean state=true;//子弹状态，起始状态为有效状态
	private int speed;                   //子弹移动的速度
	private int atk= 1 ;

	
	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

		//构造函数，定义了创建子弹时需要的变量
		//speed为正值是子弹向下移动，speed为负值是子弹向上移动
		public BossBullet(int x,int y,Image img, int speed ){
			this.x=x;
			this.y=y;
			this.img=img;
			this.speed=speed;
			}
		
		public void paintBossBullet(Graphics g){
			if (state){           //失效状态的子弹不参与绘制
				g.drawImage(img, x, y, null);
			}
		}
		
		 public void logic() {
		            //Boss子弹
		                y += speed+8;
		                if (y < 0) {
		                    state= true;
		                }
		    }
		//子弹移动的方法
		public void BossBulletMove(){
			y -= speed;
			//只要子弹移动就可能有出界
			if(y<-img.getHeight(null)){
				//子弹出界，将状态改为失效状态
				state=false;
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
