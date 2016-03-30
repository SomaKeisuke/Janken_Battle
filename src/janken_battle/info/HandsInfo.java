package janken_battle.info;

import janken_battle.Main.Hand;

import java.util.List;

/**
 * 手の情報
 * 
 * @author takahashi_to
 *
 */
public class HandsInfo {

    private Hand lastHand = Hand.P;
    private int paCount = 0;
    private int guCount = 0;
    private int chokiCount = 0;

    public HandsInfo(List<Hand> hands) {
	if (hands.size() == 0) {
	    return;
	}
	this.lastHand = hands.get(hands.size() - 1);
	for (Hand hand : hands) {
	    if (hand == Hand.P) {
		this.paCount++;
	    } else if (hand == Hand.G) {
		this.guCount++;
	    } else {
		this.chokiCount++;
	    }
	}
    }

    /**
     * 一番使われてる手。わからなかったら、パーを返す。
     * 
     * @return
     */
    public Hand getMostUseHand() {
	if (paCount >= guCount && paCount >= chokiCount) {
	    return Hand.P;
	} else if (chokiCount >= paCount && chokiCount >= guCount) {
	    return Hand.C;
	} else {
	    return Hand.G;
	}
    }

    /**
     * 一番使われてないて。わからなかったらパーを返す。
     * 
     * @return
     */
    public Hand getLeastUseHand() {
	if (paCount <= guCount && paCount <= chokiCount) {
	    return Hand.P;
	} else if (chokiCount <= paCount && chokiCount <= guCount) {
	    return Hand.C;
	} else {
	    return Hand.G;
	}
    }

    /**
     * 最後に使われた手。
     * 
     * @return
     */
    public Hand getLastHand() {
	return lastHand;
    }
}