package janken_battle.players;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;
import janken_battle.info.HandsInfo;

import java.util.List;

/**
 * あなたです。
 * 
 * 困ったらパーを使う。
 */
public class TakahashiTo implements JankenPlayer {

    @Override
    public String getName() {
	return "takahashi_to";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint,
	    int enemyPoint, int gameNumber) {

	return getMyHand(myHands, enemyHands, myPoint, enemyPoint);
    }

    /**
     * @param enemyHands
     * @return
     */
    private Hand getMyHand(List<Hand> myHands, List<Hand> enemyHands,
	    int myPoint, int enemyPoint) {
	HandsInfo myHandsInfo = new HandsInfo(myHands);
	HandsInfo enemyHandsInfo = new HandsInfo(enemyHands);
	Hand winHnd = getWinHand(enemyHandsInfo.getMostUseHand());
	// 自分が買ってる時
	if (myPoint >= enemyPoint) {
	    // グーは点数が低いので考え直す。
	    if (winHnd == Hand.G) {
		winHnd = getWinHand(enemyHandsInfo.getLastHand());
		// グーは点数が低いので考え直す。
		if (winHnd == Hand.G) {
		    winHnd = myHandsInfo.getLeastUseHand();
		}
	    }
	}
	return winHnd;
    }

    /**
     * @param hand
     * @return
     */
    private Hand getWinHand(Hand hand) {
	if (hand == Hand.P) {
	    return Hand.C;
	}
	if (hand == Hand.G) {
	    return Hand.P;
	}
	if (hand == Hand.C) {
	    return Hand.G;
	}
	throw new RuntimeException("勝てない手です。");
    }

}
