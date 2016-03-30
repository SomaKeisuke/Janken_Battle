package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。
 * TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 * 
 * 基本、相手の手の変化に合わせて直前に出した相手の手を出していきます。
 * あいこの時は直前の相手の手に勝てる手を出します。
 * 
 */
public class HibiCode implements JankenPlayer {

    @Override
    public String getName() {
        // TODO
        // プレイヤーの名前を半角英数で入力してください。
        return "HIBI SHINICHIRO";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        // TODO
        // 今は例として、最初の1回はグー、1つ前に相手が出した手がチョキならパー、
        // それ以外ならチョキを出すように実装されています。
        // このメソッドの実装を変更して勝利を目指してください。
        

        if (myHands.isEmpty()) {
            //まずはグーを出します。
            return Hand.G;
            
        } else {
            
            int currentTurn = enemyHands.size();
            Hand beforeEnemyHand = enemyHands.get(currentTurn - 1);

            Hand returnHand;//返す手です。
            
            if (beforeEnemyHand == Hand.C) {
                returnHand = Hand.C;
            } else if (beforeEnemyHand == Hand.P){
                returnHand = Hand.P;
            } else if (beforeEnemyHand == Hand.G){
                returnHand = Hand.G;
            } else {
                returnHand = Hand.G;
            }

            Hand beforeMyHand = myHands.get(currentTurn - 1);
            
            if(beforeMyHand.equals(beforeEnemyHand)){
              
              if (beforeEnemyHand == Hand.C) {
                  returnHand = Hand.G;
              } else if (beforeEnemyHand == Hand.P){
                  returnHand = Hand.G;
              } else if (beforeEnemyHand == Hand.G){
                  returnHand = Hand.P;
              } else {
                  returnHand = Hand.G;
              }
          }
            return returnHand;
        }
    }
}
