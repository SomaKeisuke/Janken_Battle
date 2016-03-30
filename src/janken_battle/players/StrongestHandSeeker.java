package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;
import janken_battle.kohanawa.EnemyHandsStore;
import janken_battle.kohanawa.HandsStore;
import janken_battle.kohanawa.MyHandsStore;

/**
 * 最強の手を極めんとする探求者です。
 *
 */
public class StrongestHandSeeker implements JankenPlayer {
	private HandsStore myHandsStore;
	private HandsStore enemyHandsStore;

	public StrongestHandSeeker() {
		myHandsStore = new MyHandsStore();
		enemyHandsStore = new EnemyHandsStore();
	}

	@Override
	public String getName() {
		return "強き手を極めんとするもの";
	}

	@Override
	public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
		if (gameNumber != 1) {
			myHandsStore.storeHands(myHands.get(myHands.size() - 1), enemyHands.get(enemyHands.size() - 1));
			enemyHandsStore.storeHands(enemyHands.get(enemyHands.size() - 1), myHands.get(myHands.size() - 1));
		}

		return myHandsStore.getPointGetter();
	}
}
