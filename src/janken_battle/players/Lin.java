package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。 TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 *
 */
public class Lin implements JankenPlayer {

	boolean glover;
	boolean plover;
	boolean clover;

	int count_g = 0;
	int count_p = 0;
	int count_c = 0;

	@Override
	public String getName() {
		// TODO
		// プレイヤーの名前を半角英数で入力してください。
		return "Lin";
	}

	@Override
	public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
		if (myHands.isEmpty()) {
			return Hand.P;
		} else {
			count_hands(enemyHands);
			is_whichlover();
			if (glover && !plover && !clover) {
				return Hand.P;
			} else if (plover && !glover && !clover) {
				return Hand.C;
			} else if (clover && !plover && !glover) {
				return Hand.G;
			} else if (plover && glover && !clover) {
				return Hand.P;
			} else if (clover && plover && !glover) {
				return Hand.C;
			} else if (!clover && plover && glover) {
				return Hand.P;
			} else {
				return Hand.P;
			}
		}
	}

	private void count_hands(List<Hand> enemyHands) {
		for (Hand hand : enemyHands) {
			if (hand == Hand.G) {
				count_g = count_g++;
			} else if (hand == Hand.C) {
				count_c = count_c+2;
			} else if (hand == Hand.P) {
				count_p = count_p + 2;
			}
		}
	}

	private boolean is_whichlover() {
		if (count_g > 5) {
			return glover = true;
		}
		if (count_c > 8) {
			return clover = true;
		}
		if (count_p > 2) {
			return plover = true;
		}
		return false;
	}

}
