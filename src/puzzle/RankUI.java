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
		this.setBounds(200, 200, 280, 280);
		panel.setBounds(20, 20, 270, 270);
		this.setContentPane(panel);
		panel.add(rankArea);
		
		pm = new PlayerMethod();
		Player[] ranked = pm.getRankedPlayer();
		String t = "玩家姓名                    等级                    时间           \n";
		if (ranked.length == 0) {
			
		} else {
			for (int i = 0; i < ranked.length; i++) {
				Player p = ranked[i];
				t = t + p.getName() + "          " + String.valueOf(p.getGrade()) + "          " + p.getTime() +"\n";
			}
		}
		rankArea.setText(t);
		rankArea.setEditable(false);
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}

}
