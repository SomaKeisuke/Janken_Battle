package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。
 * TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 *
 */
public class Toyota implements JankenPlayer {

    @Override
    public String getName() {
        return "Toyota";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands ,int myPoint ,int enemyPoint ,int gameNumber) {
        // 初手
        if (myHands.isEmpty()) {
            return Hand.G;
        // 二手目
        } else if (myHands.size() == 1) {
            return Hand.C;
        } else {
            Hand lastMyHand = myHands.get(myHands.size() - 1);
            Hand secondLastMyHand = myHands.get(myHands.size() - 2);
            Hand lastEnemyHand = enemyHands.get(enemyHands.size() - 1);
            Hand secondLastEnemyHand = enemyHands.get(enemyHands.size() - 2);
            // 前回あいこだった場合には、それに勝てる手を出す
            if (lastMyHand.equals(lastEnemyHand)) {
                return winHand(lastMyHand);
            }
            // 自分の前回、前々回が同じ手の場合は、それに負ける手を出す(カウンター)
            if (lastMyHand.equals(secondLastMyHand)) {
                return loseHand(lastMyHand);
            }
            // 敵の前回、前々回が同じ手の場合は、それに勝てる手を出す
            if (lastEnemyHand.equals(secondLastEnemyHand)) {
                return winHand(lastEnemyHand);
            }
            // 前回負けた場合にはその自分の手に負ける手を出す(カウンター)
            if (lastMyHand.equals(loseHand(lastEnemyHand))) {
                return loseHand(lastMyHand);
            }
            // 前回勝った場合にはその自分の手に負ける手を出す(カウンター)
            if (lastMyHand.equals(winHand(lastEnemyHand))) {
                return loseHand(lastMyHand);
            }
        }
        return Hand.C;
    }

    // 引数で渡ってくる手に勝てる手を返す
    private static Hand winHand(Hand hand) {
        if (hand.equals(Hand.G)) {
            return Hand.P;
        } else if (hand.equals(Hand.C)) {
            return Hand.G;
        } else if (hand.equals(Hand.P)) {
            return Hand.C;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // 引数で渡ってくる手に負ける手を返す
    private static Hand loseHand(Hand hand) {
        if (hand.equals(Hand.G)) {
            return Hand.C;
        } else if (hand.equals(Hand.C)) {
            return Hand.P;
        } else if (hand.equals(Hand.P)) {
            return Hand.G;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
