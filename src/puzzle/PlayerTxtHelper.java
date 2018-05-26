package puzzle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Administrator
 * 存取玩家信息
 */
public class PlayerTxtHelper {
	public void writePlayer(ArrayList<Player> playerList) {
		int size = playerList.size();
		File file = new File("TxtData/player.txt");
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileWriter fw=new FileWriter(file.getAbsolutePath());
			BufferedWriter bw=new BufferedWriter(fw);
			
			for (int i = 0; i < size; i++) {
				Player p = playerList.get(i);
				String info = p.getName() + "&" + p.getTime() + "&" + String.valueOf(p.getGrade());
				bw.write(info);
				bw.write("\r\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Player> readPlayer() {
		File file = new File("TxtData/player.txt");
		ArrayList<Player> playerList = new ArrayList<Player>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String[] info = str.split("&");
				String name = info[0];
				String time = info[1];
				int grade = Integer.parseInt(info[2]);
				Player p = new Player(name, time, grade);
				playerList.add(p);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return playerList;
	}
}
