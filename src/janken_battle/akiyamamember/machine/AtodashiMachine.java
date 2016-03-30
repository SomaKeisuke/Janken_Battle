package janken_battle.akiyamamember.machine;

import janken_battle.Main.Hand;
import janken_battle.akiyamamember.Computer;

public class AtodashiMachine extends Computer {

    public Hand getHand(Hand hand) {
        switch (hand) {
            case P:
                return Hand.C;
            case G:
                return Hand.P;
            case C:
                return Hand.G;
            default:
                return help2();
        }
    }
}
