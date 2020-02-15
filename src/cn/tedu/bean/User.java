package cn.tedu.bean;
/**
 * 用来封装从文件中读取得到的数据
 * 并且可以非常简单的写入在表格
 * @author qianyuhao
 *
 * Comparable 比较器
 */
public class User implements Comparable<User>{
	private String uName;
	private String time;
	private int score;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public int compareTo(User o) {
		return o.score - this.score;
	}
	
}
