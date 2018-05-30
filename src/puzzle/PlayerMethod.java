package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator
 * 处理与玩家相关的操作：增加记录、获得排名
 */
public class PlayerMethod {
	private PlayerTxtHelper pth;
	
	private Map<String, Player> map;
	
	private static PlayerMethod pm;
	
	public static PlayerMethod getInstance() {
		if (pm == null) {
			pm = new PlayerMethod();
		}
		return pm;
	}
	
	public PlayerMethod() {
		if (map == null) {
			pth = new PlayerTxtHelper();
			map = pth.readPlayer();
		}
	}
	
	public void add(Player p) {
		String id = p.getId();
		if (map.get(id) != null) {
			Player t = map.get(id);
			if (t.getTime() > p.getTime()) {
				map.put(id, p);
			}
		} else {
			map.put(id, p);
		}
		pth.writePlayer(map);
	}
	
	public ArrayList<Player> getSortedPlayer() {
		ArrayList<Player> pList = new ArrayList<Player>();
		Iterator<Map.Entry<String, Player>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, Player> entry = entries.next();
			pList.add(entry.getValue());
		}
		Collections.sort(pList, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				Player p1 = (Player)o1;
				Player p2 = (Player)o2;
				if (p1.getTime() > p2.getTime()) {
					return 1;
				} else if (p1.getTime() < p2.getTime()) {
					return -1;
				} else {
					if (p1.getGrade() < p2.getGrade()) {
						return 1;
					} else if (p1.getGrade() > p2.getGrade()) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		
		});
		return pList;
	}
}
