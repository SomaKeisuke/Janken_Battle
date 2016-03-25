package janken_battle.kohanawa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import janken_battle.Main.Hand;

public class EnemyHandsStore extends AbsHandsStore {
	Map<Hand, List<Hand>> enemyHandsStoreMap;

	public EnemyHandsStore() {
		enemyHandsStoreMap = new HashMap<Hand, List<Hand>>();
	}

	@Override
	Map<Hand, List<Hand>> getStoreMap() {
		return enemyHandsStoreMap;
	}
}
