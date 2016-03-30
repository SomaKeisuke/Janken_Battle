package janken_battle.kohanawa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import janken_battle.Main.Hand;

public abstract class AbsHandsStore implements HandsStore {
	@Override
	public int getHandsCount(Hand hand) {
		return getStoreMap().get(hand).size();
	}

	@Override
	public void storeHands(Hand myHand, Hand enemyHand) {
		Map<Hand, List<Hand>> targetStoreMap = getStoreMap();
		if (!targetStoreMap.containsKey(myHand)) {
			targetStoreMap.put(myHand, new ArrayList<Hand>());
		}

		targetStoreMap.get(myHand).add(myHand);

		BattleResult result = getBattleResult();
		result.addMyHand(myHand);
		result.addEnemyHand(enemyHand);
		result.storeResultPoint();
	}

	@Override
	public Hand getPointGetter() {
		Hand result = null;
		Map<Hand, Integer> battleResultMap = new HashMap<Hand, Integer>();
		BattleResult battleResult = getBattleResult();
		battleResultMap.put(Hand.P, battleResult.getPwinPoint());
		battleResultMap.put(Hand.G, battleResult.getGwinPoint());
		battleResultMap.put(Hand.C, battleResult.getCwinPoint());

		int max = 0;
		for (Entry<Hand, Integer> entry : battleResultMap.entrySet()) {
			if (result == null) {
				result = entry.getKey();
				max = entry.getValue();
			} else {
				if (max < entry.getValue()) {
					result = entry.getKey();
					max = entry.getValue();
				} else if (max == entry.getValue()) {
					if (result == Hand.G) {
						result = entry.getKey();
					}
				}
			}
		}
		return result;
	}

	@Override
	public double getCurrentHandsRate(Hand hand, int gameNumber) {
		return (getStoreMap().get(hand).size() / gameNumber) * 100;
	}

	/**
	 * Handを蓄積するためのMapを返します。
	 *
	 * @return
	 */
	abstract Map<Hand, List<Hand>> getStoreMap();

	/**
	 * BattleResultを蓄積するためのListを返します。
	 *
	 * @return
	 */
	abstract BattleResult getBattleResult();

}
