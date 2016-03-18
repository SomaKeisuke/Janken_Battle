package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * 1回、または2回前に相手が出した手に勝つ手を出します。 対戦相手に手を読まれないために、ラウンドごとに1回前か2回前に勝つ 手を出すかを決めます。
 */
public class YourCode implements JankenPlayer {

	@Override
	public String getName() {
		// プレイヤーの名前を半角英数で入力してください。
		return "Makoto";
	}

	@Override
	public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {

		if (myHands.size() == 0 || myHands.size() == 1) {
			return Hand.C;
		}

	 int gameRound = getRound(gameNumber);

		switch (gameRound) {
		case 0:
		case 3:
		case 4:
		case 6:
		case 8:
			Hand oneBeforeEnemyHand = enemyHands.get(enemyHands.size() - 1);
			if (oneBeforeEnemyHand == Hand.G) {
				return Hand.P;
			} else if (oneBeforeEnemyHand == Hand.C) {
				return Hand.G;
			} else if (oneBeforeEnemyHand == Hand.P) {
				return Hand.C;
			} else {
				return Hand.C;
			}
		case 1:
		case 2:
		case 5:
		case 7:
		case 9:
			Hand twoBeforeEnemyHand = enemyHands.get(enemyHands.size() - 2);
			if (twoBeforeEnemyHand == Hand.G) {
				return Hand.P;
			} else if (twoBeforeEnemyHand == Hand.C) {
				return Hand.G;
			} else if (twoBeforeEnemyHand == Hand.P) {
				return Hand.C;
			} else {
				return Hand.C;
			}
		default:
			return Hand.C;
		}
	}

	private int getRound(int gameNumber) {
		if (gameNumber < 10) {
			return 0;
		} else if (gameNumber < 20) {
			return 1;
		} else if (gameNumber < 30) {
			return 2;
		} else if (gameNumber < 40) {
			return 3;
		} else if (gameNumber < 50) {
			return 4;
		} else if (gameNumber < 60) {
			return 5;
		} else if (gameNumber < 70) {
			return 6;
		} else if (gameNumber < 80) {
			return 7;
		} else if (gameNumber < 90) {
			return 8;
		} else if (gameNumber <= 100) {
			return 9;
		} else {
			return 0;
		}
	}

}
