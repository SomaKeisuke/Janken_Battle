package janken_battle.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * 相手の出す手の確率から期待値を算出し、最も期待値の高い手を繰り出します。
 * パターンが相手に読まれないよう相手の出した手の統計を3分割し、収束速度を遅くしています。
 */
public class Soma implements JankenPlayer {

    @Override
    public String getName() {
        return "Soma";
    }

    private List<Hand> variableHands = new ArrayList<Hand>(Arrays.asList(new Hand[] {Hand.C, Hand.P, Hand.G}));
    private int denominator;
//    private Map<Hand, Integer> hand1by3Map = new HashMap<Hand, Integer>() {{put(Hand.G, 0);put(Hand.C, 0);put(Hand.P, 0);}};
//    private Map<Hand, Integer> hand2by3Map = new HashMap<Hand, Integer>() {{put(Hand.G, 0);put(Hand.C, 0);put(Hand.P, 0);}};
//    private Map<Hand, Integer> hand3by3Map = new HashMap<Hand, Integer>() {{put(Hand.G, 0);put(Hand.C, 0);put(Hand.P, 0);}};
    private Map<Hand, Integer> hand1by3Map = new HashMap<Hand, Integer>();
    private Map<Hand, Integer> hand2by3Map = new HashMap<Hand, Integer>();
    private Map<Hand, Integer> hand3by3Map = new HashMap<Hand, Integer>();

    private double ecpectedValueHandG;
    private double ecpectedValueHandC;
    private double ecpectedValueHandP;

    private double enemyHandProbalityG;
    private double enemyHandProbalityC;
    private double enemyHandProbalityP;


    // コンストラクタ
    // 匿名クラスがリフレクションの関係でエラーになるためこちらで初期値を入れる
    @SuppressWarnings("unchecked")
    public Soma() {
//        hand1by3Map.put(Hand.G, 0);
//        hand1by3Map.put(Hand.C, 0);
//        hand1by3Map.put(Hand.P, 0);
//        hand2by3Map.put(Hand.G, 0);
//        hand2by3Map.put(Hand.C, 0);
//        hand2by3Map.put(Hand.P, 0);
//        hand3by3Map.put(Hand.G, 0);
//        hand3by3Map.put(Hand.C, 0);
//        hand3by3Map.put(Hand.P, 0);
        init(hand1by3Map,hand2by3Map,hand3by3Map);
    }

    /**
     * 初期処理
     */
    private void init(Map<Hand, Integer>... args){
        for(Map<Hand, Integer> handMap : args) {
            for (Hand hand : Hand.values()) {
                handMap.put(hand ,0);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        try {
            // 残り試合全て相手に+3入ったとしても負けない場合、ひたすらチョキを出す
            if (myPoint - enemyPoint > (100 - gameNumber + 1) * 3) {
                return Hand.C;
            }

            // 3G毎に期待値の高い手に切り替える
            if (gameNumber % 3 == 1 && myHands.size() > 0) {
                denominator = gameNumber / 3;
                //直近の3試合分の結果をストック
                hand1by3Map.put(enemyHands.get(gameNumber - 4), hand1by3Map.get(enemyHands.get(gameNumber - 4)) + 1);
                hand2by3Map.put(enemyHands.get(gameNumber - 3), hand2by3Map.get(enemyHands.get(gameNumber - 3)) + 1);
                hand3by3Map.put(enemyHands.get(gameNumber - 2), hand3by3Map.get(enemyHands.get(gameNumber - 2)) + 1);

                changeHighExpectedValueHand(hand1by3Map,hand2by3Map,hand3by3Map);
            };
            return variableHands.get((gameNumber + 2) % 3) != null ? variableHands.get((gameNumber + 2) % 3) : Hand.C;
        } catch (Exception e) {
            return Hand.C;
        }
    }

    /**
     * 相手の手の確率から期待値を算出し、もっとも期待値の高い手にvariableHandsを書き換える
     */
    private void changeHighExpectedValueHand(Map<Hand, Integer>... args){

        int index = 0;
        for (Map<Hand, Integer> handMap : args) {
            enemyHandProbalityG = (double)handMap.get(Hand.G) / denominator;
            enemyHandProbalityC = (double)handMap.get(Hand.C) / denominator;
            enemyHandProbalityP = (double)handMap.get(Hand.P) / denominator;

            ecpectedValueHandG = enemyHandProbalityG * 0 + enemyHandProbalityC * 3 + enemyHandProbalityP * -6;
            ecpectedValueHandC = enemyHandProbalityG * -3 + enemyHandProbalityC * 0 + enemyHandProbalityP * 6;
            ecpectedValueHandP = enemyHandProbalityG * 6 + enemyHandProbalityC * -6 + enemyHandProbalityP * 0;

            if (ecpectedValueHandG < ecpectedValueHandC) {
                if (ecpectedValueHandP < ecpectedValueHandC) {
                    variableHands.set(index, Hand.C);
                } else {
                    variableHands.set(index, Hand.P);
                }
            } else {
                if (ecpectedValueHandP < ecpectedValueHandG) {
                    variableHands.set(index, Hand.G);
                } else {
                    variableHands.set(index, Hand.P);
                }
            }
            index++;
        }
    }
}
