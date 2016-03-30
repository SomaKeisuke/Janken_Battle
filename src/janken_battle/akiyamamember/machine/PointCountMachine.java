package janken_battle.akiyamamember.machine;

import janken_battle.Main.Hand;
import janken_battle.akiyamamember.Computer;

public class PointCountMachine extends Computer {

    int gCount = 0;
    int cCount = 0;
    int pCount = 0;

    public void count(Hand hand) {
        switch (hand) {
            case P:
                pCount++;
                break;
            case G:
                gCount++;
                break;
            case C:
                cCount++;
                break;
        }
    }

    public Hand getMostCountHand() {
        if (gCount > cCount
                && gCount > pCount) {
            return Hand.G;
        } else if (cCount > gCount
                && cCount > pCount) {
            return Hand.C;
        } else if (pCount > gCount
                && pCount > cCount) {
            return Hand.P;
        }
        return help();
    }

    public Hand getFewestCountHand() {
        if (gCount < cCount
                && gCount < pCount) {
            return Hand.G;
        } else if (cCount < gCount
                && cCount < pCount) {
            return Hand.C;
        } else if (pCount < gCount
                && pCount < cCount) {
            return Hand.P;
        }
        outOfMemory();
        return help3();
    }
}
