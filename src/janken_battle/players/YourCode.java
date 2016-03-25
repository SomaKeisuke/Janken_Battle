package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;
import janken_battle.kohanawa.EnemyHandsStore;
import janken_battle.kohanawa.HandsStore;
import janken_battle.kohanawa.MyHandsStore;

/**
 * あなたです。 TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 *
 */
public class YourCode implements JankenPlayer {
	private HandsStore myHandsStore;
	private HandsStore enemyHandsStore;

	public YourCode() {
		myHandsStore = new MyHandsStore();
		enemyHandsStore = new EnemyHandsStore();
	}

	@Override
	public String getName() {
		return "YourCode";
	}

	@Override
	public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
		Hand hand;
		if (gameNumber != 1) {
			enemyHandsStore.storeHands(enemyHands.get(enemyHands.size() - 1));
		}

		hand = Hand.G;

		myHandsStore.storeHands(hand);
		return hand;
	}

}
