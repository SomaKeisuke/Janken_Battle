package janken_battle.kohanawa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import janken_battle.Main.Hand;

public class MyHandsStore extends AbsHandsStore {
	Map<Hand, List<Hand>> myHandsStoreMap;
	BattleResult battleResult;

	public MyHandsStore() {
		myHandsStoreMap = new HashMap<Hand, List<Hand>>();
		battleResult = new BattleResult();
	}

	@Override
	Map<Hand, List<Hand>> getStoreMap() {
		return myHandsStoreMap;
	}

	@Override
	BattleResult getBattleResult() {
		return battleResult;
	}

}
