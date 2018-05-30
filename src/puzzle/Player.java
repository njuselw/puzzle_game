package puzzle;
/**
 * @author Administrator
 * 玩家类
 */
public class Player{
	/*
	 * name: String, 玩家姓名
	 * time: int, 完成游戏需要的时间
	 * grade: int, 游戏等级
	 * id: String, 存入文件时需要的对象id，由name+grade构成
	 */
	private String name;
	private int time;
	private int grade;
	private String id;
	
	public Player() {
		this.name = "";
		this.time = 0;
		this.grade = 0;
		this.setId("");
	}
	
	public Player(Player p) {
		this.name = p.getName();
		this.time = p.getTime();
		this.grade = p.getGrade();
		this.id = p.getId();
	}
	
	public Player(String name, int time, int grade) {
		this.name = name;
		this.time = time;
		this.grade = grade;
		this.id = name + String.valueOf(grade);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
