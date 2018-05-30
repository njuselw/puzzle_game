package puzzle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator
 * 存取玩家信息
 */
public class PlayerTxtHelper {
	public Map<String, Player> readPlayer() {
		File file = new File("TxtData/player.txt");
		Map<String, Player> map = new HashMap<String, Player>();
		try {
			InputStreamReader reader=new InputStreamReader(new FileInputStream(file));
			BufferedReader br=new BufferedReader(reader);
			String str=br.readLine();
			
			while (str != null) {
				String[] data = str.split("&");
				String name = data[0];
				int time = Integer.parseInt(data[1]);
				int grade = Integer.parseInt(data[2]);
				Player p = new Player(name, time, grade);
				map.put(p.getId(), p);
				str = br.readLine();
			}
			
			br.close();
			return map;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void writePlayer (Map<String, Player> map) {
		File file = new File("TxtData");
		if (!file.exists()) {
			file.mkdirs();
		}
		File f = new File("TxtData/player.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			
			Iterator<Entry<String, Player>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Player> entry = iterator.next();
				Player p = entry.getValue();
				String str = p.getName() + "&" + String.valueOf(p.getTime()) + "&" + String.valueOf(p.getGrade());
				bw.write(str);
				bw.write("\r\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
