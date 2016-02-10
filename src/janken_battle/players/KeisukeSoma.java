package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。
 * TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 *
 */
public class KeisukeSoma implements JankenPlayer {

    @Override
    public String getName() {
        return "KeisukeSoma";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {


        if (gameNumber == 50) {
            throw new RuntimeException();
        }



        return Hand.G;
    }




}
