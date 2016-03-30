package janken_battle.akiyamamember.human;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.akiyamamember.Human;
import janken_battle.akiyamamember.machine.AtodashiMachine;

public class VsMizutaMan extends Human {

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands) {
        return new AtodashiMachine().getHand(myHands.get(myHands.size()
                - 1));
    }
}
