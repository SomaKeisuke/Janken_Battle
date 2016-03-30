package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。
 * TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 * 
 * 試合の状況で手のパターンを変えます。
 * 
 * 勝っているとき
 * 相手の一つ前の手を見て、それに勝つ手を出します。
 * 
 * 負けているとき
 * チョキを積極的に出してポイントを稼ぎにいくタイプです。
 * 相手が二連続グーを出してきたらパーを出すことで、こちらの手が読まれるのを回避します。
 * 
 * さらに、相手が初手でいきなりチョキを出してきたときは、グーを出して稼いでおきます。
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
            
            if (enemyHands.size() > 2 && myPoint > enemyPoint) {
                Hand beforeEnemyHand = enemyHands.get(enemyHands.size()
                        - 1);
                    switch (beforeEnemyHand) {
                        case G:
                            return Hand.P;
                        case P:
                            return Hand.C;
                        case C:
                            return Hand.G;
                    }
            } else if (enemyHands.size() > 2 && myPoint <= enemyPoint) {
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
            return Hand.C;
        }
    }    
}
