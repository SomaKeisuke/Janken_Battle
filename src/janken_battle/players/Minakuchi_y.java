package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 *
 * 相手が出した手を4回ごとに集計し、一番多く出した手に勝つ手を数回だす。
 * 集計した次の対戦で負けたら、その相手の手に勝つ手を出す。
 * 最初はグーを出す。
 */
public class Minakuchi_y implements JankenPlayer {

    @Override
    public String getName() {
        return "Minakuchi_y";
    }

    private static int enemyGCount;
    private static int enemyCCount;
    private static int enemyPCount;
    private static Hand myHand;

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
        //最初はグー
        if (myHands.isEmpty()) {
            myHand = Hand.G;
            return Hand.G;
        }

        Hand beforeEnemyHand = enemyHands.get(enemyHands.size()-1);
        Hand beforeMyHand = myHands.get(myHands.size()-1);
        if (beforeEnemyHand == Hand.G) {
            enemyGCount++;
        } else if (beforeEnemyHand == Hand.C) {
            enemyCCount++;
        } else if (beforeEnemyHand == Hand.P) {
            enemyPCount++;
        }
        //試合数が4の倍数の時、集計する
        if (gameNumber % 4== 0) {
            myHand=analysis();
            return myHand;
        }
        //集計した結果出した手が勝つ場合そのまま出しつづけ
        //集計した結果出した手が負けた場合、勝つ手に変える。
        if (beforeMyHand == Hand.G && beforeEnemyHand == Hand.P) {
            myHand = Hand.C;
        } else if (beforeMyHand== Hand.C && beforeEnemyHand == Hand.G) {
            myHand = Hand.P;
        } else if (beforeMyHand== Hand.P && beforeEnemyHand == Hand.C) {
            myHand = Hand.G;
        }
        return myHand;
    }

    public Hand analysis(){
        if (enemyGCount > enemyCCount && enemyGCount > enemyPCount) {
            myHand = Hand.P;
        } else if (enemyCCount > enemyGCount && enemyCCount > enemyPCount) {
            myHand = Hand.G;
        } else if (enemyPCount > enemyGCount && enemyPCount > enemyCCount) {
            myHand = Hand.C;
        }else if(enemyGCount == enemyCCount){
            myHand = Hand.G;
        }else if(enemyCCount == enemyPCount){
            myHand = Hand.C;
        }else if(enemyPCount == enemyGCount){
            myHand = Hand.P;
        }
        enemyGCount = 0;
        enemyCCount = 0;
        enemyPCount = 0;
        return myHand;
    }
}
