package janken_battle.akiyamamember.human;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.akiyamamember.Human;
import janken_battle.akiyamamember.machine.PointCountMachine;

public class StrongMan extends Human {

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands) {
        PointCountMachine pcm = new PointCountMachine();
        for (Hand hand : myHands) {
            pcm.count(hand);
        }
        return pcm.getFewestCountHand();
    }
}
