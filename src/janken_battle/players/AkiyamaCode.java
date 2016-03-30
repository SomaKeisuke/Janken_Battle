package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;
import janken_battle.akiyamamember.Human;
import janken_battle.akiyamamember.human.BetaBetaMan;
import janken_battle.akiyamamember.human.StrongMan;
import janken_battle.akiyamamember.human.VsMizutaMan;
import janken_battle.akiyamamember.human.WeakMan;

public class AkiyamaCode implements JankenPlayer {

    private static final String PLAYER_NAME = "akiyama連合軍";
    private static final int NUMBER_BETABETA = 30;
    private static final int NUMBER_VS_MIZUTA = 40;

    @Override
    public String getName() {
        return PLAYER_NAME;
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
        try {
            return choiceMember(myPoint, enemyPoint, gameNumber).getHand(myHands, enemyHands);
        } catch (Throwable e) {
//            e.printStackTrace();
            //握りつぶしてパーを出す
            return Hand.P;
        }
    }

    private Human choiceMember(int myPoint, int enemyPoint, int gameNumber) {
        if (gameNumber < NUMBER_BETABETA) {
            return new BetaBetaMan(gameNumber);
        } else if (gameNumber < NUMBER_VS_MIZUTA) {
            return new VsMizutaMan();
        }
        if (myPoint > enemyPoint) {
            return new StrongMan();
        }
        return new WeakMan();
    }
}
