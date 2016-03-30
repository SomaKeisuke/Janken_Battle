package janken_battle.akiyamamember;

import java.util.List;

import janken_battle.Main.Hand;

public abstract class Human extends Got {

    public abstract Hand getHand(List<Hand> myHands, List<Hand> enemyHands);

    protected void suicide() {
        throw new RuntimeException("自爆・・・");
    }
}
