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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Player> getRankedPlayer() {
		ArrayList<Player> rank = new ArrayList<Player>(playerList);
		Collections.sort(rank);
		return rank;
	}
}
