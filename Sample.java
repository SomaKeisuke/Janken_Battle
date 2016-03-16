package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;

/**
 * 何戦かを１セットとして、じゃんけんを回すためのクラスです。
 *
 * @author yoshikawa_s
 */
public class Sample {

    private int id;
    private Hand firstHand;
    private Hand secondHand;
    private Hand thirdHand;

    public static final int ROTATION_CYCLE = 3;//自分で決めます

    public Sample(Hand firstHand, Hand secondHand, Hand thirdHand) {
        this.firstHand = firstHand;
        this.secondHand = secondHand;
        this.thirdHand = thirdHand;
    }

    public int getId() {
        return id;
    }

    public Hand getHand(int gameNumber) {
        switch(gameNumber % ROTATION_CYCLE){
            case 1:
                return getFirstHand();
            case 2:
                return getSecondHand();
            case 0:
                return getThirdHand();
            default:
                throw new IllegalArgumentException("!!!!Never throw this Expection!!!!");
        }
    }

    public Hand getFirstHand() {
        return firstHand;
    }

    public Hand getSecondHand() {
        return secondHand;
    }

    public Hand getThirdHand() {
        return thirdHand;
    }


    private static final Hand Rock = Hand.G;
    private static final Hand Paper = Hand.P;
    private static final Hand Scissors = Hand.C;

    /**
     *
     * じゃんけんで戦わせるサンプルのデッキを作成します。
     *
     * @param analysisSamples
     */
    public static void setAnalysisDeck(List<Sample> analysisSamples){
        analysisSamples.add(new Sample(Rock, Rock, Rock));
        analysisSamples.add(new Sample(Paper, Paper, Paper));
        analysisSamples.add(new Sample(Scissors, Scissors, Scissors));
        //適当に並べます
        analysisSamples.add(new Sample(Rock, Paper, Scissors));
        analysisSamples.add(new Sample(Scissors, Paper, Rock));
        analysisSamples.add(new Sample(Scissors, Scissors, Paper));
        analysisSamples.add(new Sample(Paper, Paper, Scissors));
        analysisSamples.add(new Sample(Rock, Rock, Paper));
        analysisSamples.add(new Sample(Scissors, Scissors, Rock));
        analysisSamples.add(new Sample(Paper, Paper, Rock));
        analysisSamples.add(new Sample(Rock, Rock, Scissors));
    }
}
