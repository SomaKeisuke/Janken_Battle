package janken_battle.kohanawa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import janken_battle.Main.Hand;

public abstract class AbsHandsStore implements HandsStore {
	@Override
	public int getHandsCount(Hand hand) {
		return getStoreMap().get(hand).size();
	}

	@Override
	public void storeHands(Hand hand) {
		Map<Hand, List<Hand>> targetStoreMap = getStoreMap();
		if (!targetStoreMap.containsKey(hand)) {
			targetStoreMap.put(hand, new ArrayList<Hand>());
		}
		targetStoreMap.get(hand).add(hand);
	}

	/**
	 * Handを蓄積するためのMapを返します。
	 *
	 * @return
	 */
	abstract Map<Hand, List<Hand>> getStoreMap();

}
