package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。 TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 *
 */
public class Mizuta implements JankenPlayer {

	@Override
	public String getName() {
		// TODO
		// プレイヤーの名前を半角英数で入力してください。
		return "mizuta";
	}

	@Override
	public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint,
			int enemyPoint, int gameNumber) {
		if (myHands.isEmpty()) {
			return Hand.G;
		} else {
			Hand beforeEnemyHand = enemyHands.get(enemyHands.size() - 1);
			switch (beforeEnemyHand) {
			case C:
				return Hand.C;
			case P:
				return Hand.P;
			case G:
				return Hand.G;
			default:
				return Hand.P;
			}
		}
	}
}
