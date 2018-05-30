package puzzle;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RankUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JTextArea rankArea;
	private PlayerMethod pm;
	
	public RankUI() {
		super("玩家排行榜");
		panel = new JPanel();
		rankArea = new JTextArea();
		this.setBounds(200, 200, 380, 380);
		panel.setBounds(20, 20, 370, 370);
		this.setContentPane(panel);
		panel.add(rankArea);
		
		pm = new PlayerMethod();
		ArrayList<Player> ranked = pm.getSortedPlayer();
		String t = "玩家姓名\t等级\t时间\t\n";
		if (ranked.size() == 0) {
			
		} else {
			for (int i = 0; i < ranked.size(); i++) {
				Player p = ranked.get(i);
				t = t + p.getName() + "\t" + String.valueOf(p.getGrade()) + "\t" + getStringTime(p.getTime()) +"\n";
			}
		}
		rankArea.setText(t);
		rankArea.setEditable(false);
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	public String getStringTime(int time) {
		int second = time % 60;
		int m = time / 60;
		int minute = m % 60;
		int hour = m / 60;
		String se = "", mi = "", ho = "";
		if (second >= 10) {
			se = se + String.valueOf(second);
		} else {
			se = se + "0" + String.valueOf(second);
		}
		if (minute >= 10) {
			mi = mi + String.valueOf(minute);
		} else {
			mi = mi + "0" + String.valueOf(minute);
		}
		if (hour >= 10) {
			ho = ho + String.valueOf(hour);
		} else {
			ho = ho + "0" + String.valueOf(hour);
		}
		return ho+":"+mi+":"+se;
	}

}
