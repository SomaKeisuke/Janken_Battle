package janken_battle.akiyamamember.human;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.akiyamamember.Human;

public class BetaBetaMan extends Human {

    int _gameNumber;

    Hand[] _betaHand = new Hand[] { Hand.P, Hand.C, Hand.C, Hand.P, Hand.G, Hand.P, Hand.G, Hand.C, Hand.P, Hand.P,
            Hand.P, Hand.C, Hand.C, Hand.P, Hand.G, Hand.P, Hand.G, Hand.C, Hand.P, Hand.P, Hand.G, Hand.G, Hand.P,
            Hand.G, Hand.C, Hand.P, Hand.G, Hand.P, Hand.G, Hand.C, Hand.P, Hand.G, Hand.P, Hand.G, Hand.C, Hand.P };

    public BetaBetaMan(int gameNumber) {
        this._gameNumber = gameNumber;
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands) {
        if (_gameNumber == 10) {
            suicide();
        }
        return this._betaHand[this._gameNumber
                - 1];
    }
}
