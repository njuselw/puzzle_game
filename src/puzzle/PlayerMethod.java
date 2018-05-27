package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Administrator
 * 处理与玩家相关的操作：增加记录、获得排名
 */
public class PlayerMethod {
	private ArrayList<Player> playerList;
	private PlayerTxtHelper pth;
	
	public PlayerMethod() {
		if (playerList == null) {
			pth = new PlayerTxtHelper();
			playerList = pth.readPlayer();
		}
	}
	public void add(Player p) {
		playerList.add(p);
		pth.writePlayer(playerList);
	}
	
	public Player[] getRankedPlayer() {
		ArrayList<Player> rank = new ArrayList<Player>(playerList);
		
		if (rank.size() == 0) { 
			return null;
		}
		
		Player[] playerArray = (Player[]) rank.toArray();
		for (int i = 0; i < playerArray.length - 1; i++) {
			for (int j = 1; j < playerArray.length - i; j++) {
				if (compare(playerArray[j - 1], playerArray[j])) {
					Player temp = new Player(playerArray[j - 1]);
					playerArray[j - 1] = playerArray[j];
					playerArray[j] = temp;
				}
			}
		}
		return playerArray;
	}
	
	public boolean compare(Player p1, Player p2) {
		String[] t1 = p1.getTime().split(":");
		String[] t2 = p2.getTime().split(":");
		int h1 = Integer.parseInt(t1[0]);
		int m1 = Integer.parseInt(t1[1]);
		int s1 = Integer.parseInt(t1[2]);
		int h2 = Integer.parseInt(t2[0]);
		int m2 = Integer.parseInt(t2[1]);
		int s2 = Integer.parseInt(t2[2]);
		if (h1 > h2) {
			return true;
		} else if(m1 > m2) {
			return true;
		} else if(s1 > s2) {
			return true;
		}
		return false;
	}
}
