package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。
 * TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 * チョキを積極的に出してポイントを稼ぐタイプです。
 * 相手がこっちの手を見て二連続グー出してきたらパーを出して回避します。
 * さらに、相手が1,2回目にいきなりチョキ連発時にはグーを出して稼いでおき、その後上記のパターンに戻して勝ちにいきます。
 */
public class YourCode implements JankenPlayer {

    @Override
    public String getName() {
        // TODO
        // プレイヤーの名前を半角英数で入力してください。
        return "Nishioka Yoshihiko";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
        // TODO
        // 今は例として、最初の1回はグー、1つ前に相手が出した手がチョキならパー、
        // それ以外ならチョキを出すように実装されています。
        // このメソッドの実装を変更して勝利を目指してください。
        if (myHands.isEmpty()) {
            return Hand.C;
        } else {
            if (enemyHands.size() >= 2) {
                Hand beforeEnemyHand = enemyHands.get(enemyHands.size()
                        - 1);
                Hand before2EnemyHand = enemyHands.get(enemyHands.size()
                        - 2);
                if (before2EnemyHand == Hand.G
                        && beforeEnemyHand == Hand.G) {
                    return Hand.P;
                } else {
                    return Hand.C;
                }
            } else if (myPoint == enemyPoint) {
                return Hand.G;
            }
        }
        return Hand.C;
    }
}
