package cn.tedu.util;

import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;

public class MusicPlayer {
	/* 类的成员变量：音乐 */
	public AudioClip bg;
	public AudioClip dadada;
	public AudioClip bomb;
	public AudioClip award;
	public AudioClip kill;
	public AudioClip sicko;
	public AudioClip bgm;

	/* 类的成员变量：音频文件的路径 */
	public URL bgUrl;
	public URL dadadaUrl;
	public URL bombUrl;
	public URL awardUrl;
	public URL killUrl;
	public URL sickoUrl;
	public URL bgmUrl;


	// 通过构造方法，加载所有的音频文件
	public MusicPlayer() {
		File f1 = new File("music/game_music.wav");
		File f2 = new File("music/se_dadada.wav");
		File f3 = new File("music/se_boom.wav");
		File f4 = new File("music/se_award.wav");
		File f5 = new File("music/kill.wav");
		File f6 = new File("music/sicko.wav");
		File f7 = new File("music/bgm.wav");


		try {
			bgUrl = f1.toURL();
			dadadaUrl = f2.toURL();
			bombUrl = f3.toURL();
			awardUrl = f4.toURL();
			killUrl = f5.toURL();
			sickoUrl = f6.toURL();
			bgmUrl = f7.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		bg = JApplet.newAudioClip(bgUrl);
		dadada = JApplet.newAudioClip(dadadaUrl);
		bomb = JApplet.newAudioClip(bombUrl);
		award = JApplet.newAudioClip(awardUrl);
		kill = JApplet.newAudioClip(killUrl);
		sicko = JApplet.newAudioClip(sickoUrl);
		bgm = JApplet.newAudioClip(bgmUrl);

	}

	public void playBg() {
		bg.loop();// 循环播放
	}

	public void stopBg() {
		bg.stop();// 停止音乐
	}

	public void playDadada() {
		dadada.loop();
	}

	public void stopDadada() {
		dadada.stop();
	}

	public void playBomb() {
		bomb.loop();
	}

	public void stopBomb() {
		bomb.stop();
	}

	public void playAward() {
		award.loop();
	}

	public void stopAward() {
		award.stop();
	}

	public void playKill() {
		kill.play();
	}

	public void stopKill() {
		kill.stop();
	}
	public void playSicko() {
		sicko.play();
	}

	public void stopSicko() {
		sicko.stop();
	}
	public void playBgm() {
		bgm.play();
	}

	public void stopBgm() {
		bgm.stop();
	}
	public static void main(String[] args) {
		MusicPlayer play = new MusicPlayer();
		play.playKill();

	}

}
