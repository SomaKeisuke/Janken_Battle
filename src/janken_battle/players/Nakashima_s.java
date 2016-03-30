package janken_battle.players;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 30回目まで試行して得点の高い打ち方を採用します。
 */
public class Nakashima_s implements JankenPlayer {

	private List<Integer> resultList;

	private int nowPattern;

	private boolean dyingflg;

	private boolean chokiorpa;


    @Override
    public String getName() {
        return "nakashima_s";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {

    	if(!myHands.isEmpty()){
    	judgePrevResult(myHands.get(gameNumber-2), enemyHands.get(gameNumber-2),gameNumber);
    	}else{
    		resultList = new ArrayList<Integer>();
    		dyingflg = false;
    		chokiorpa = false;
    	}

    	if(gameNumber<=3){
    		return Hand.G;
    	}
    	if(gameNumber<=7){
    		return Hand.C;
    	}
    	if(gameNumber<=10){
    		return Hand.P;
    	}

    	if(gameNumber<=15){
    		return doRoyalHand(enemyHands);
    	}
    	if(gameNumber<=20){
    		return doReverseRoyalHand(myHands);
    	}
    	if(gameNumber<=25){
    		return doCleverHand(myHands,enemyHands);
    	}
    	if(gameNumber<=30){
    		return doReverseCleverHand(myHands,enemyHands);
    	}

    	//残試合数×3以上勝っているときCを出し続けて必勝
    	if((100 - gameNumber)* 3 < (myPoint - enemyPoint) && gameNumber > 50){
    		if(!dyingflg){
    		System.out.println("you are already die..");
    		dyingflg = true;
    		}
    		return Hand.C;
    	}
    	//残試合数×6以上負けているとき、どうせ負けるので悪態をつく
    	if((100 - gameNumber)* 6 < (enemyPoint - myPoint) && gameNumber > 50){
    		System.out.println("fuck you!");
    	}
    	if((100 - gameNumber)* 6 > (enemyPoint - myPoint) && (100 - gameNumber)* 3 < (enemyPoint - myPoint)&& gameNumber > 50){
    		chokiorpa = true;
    	}

    	if(gameNumber == 31){
    	nowPattern = judgeBestPattern();
    	System.out.println("patten:" + nowPattern);
    	}

    	if(gameNumber == 50 || gameNumber == 70){
    		reJudge(gameNumber);
    	}

    	switch (nowPattern){
    	case 1: return doRoyalHand(enemyHands);
    	case 2: return doReverseRoyalHand(myHands);
    	case 3: return doCleverHand(myHands,enemyHands);
    	case 4: return doReverseCleverHand(myHands,enemyHands);
    	}

    	return Hand.C;


}

	private void reJudge(int gameNumber) {
		int tempPoint =0;
		for(int j= gameNumber-20; j<gameNumber-1; j++){
			tempPoint = tempPoint + resultList.get(j);
		}
		if(tempPoint < 0){
			if(nowPattern == 1){
    			nowPattern = 2;
    		}else if(nowPattern == 2){
    			nowPattern = 1;
    		}else if(nowPattern == 3){
    			nowPattern = 4;
    		}else if(nowPattern == 4){
    			nowPattern = 3;
    		}
		}

	}

	private int judgeBestPattern() {

		int royalPoint = 0;
		int revRoyalPoint = 0;
		int clevPoint = 0;
		int revClevPoint = 0;
		for(int i= 10;i<=14;i++){
			royalPoint = royalPoint + resultList.get(i);
		}
		for(int i= 15;i<=19;i++){
			revRoyalPoint = revRoyalPoint + resultList.get(i);
		}
		for(int i= 20;i<=24;i++){
			clevPoint = clevPoint + resultList.get(i);
		}
		for(int i= 25;i<=29;i++){
			revClevPoint = revClevPoint + resultList.get(i);
		}

		if(royalPoint >= revRoyalPoint && royalPoint >= clevPoint
				&& royalPoint >= revClevPoint){
			return 1;
		}
		if(revRoyalPoint >= royalPoint && revRoyalPoint >= clevPoint
				&& revRoyalPoint >= revClevPoint){
			return 2;
		}
		if(clevPoint >= revRoyalPoint && clevPoint >= royalPoint
				&& clevPoint >= revClevPoint){
			return 3;
		}
		if(revClevPoint >= royalPoint && revClevPoint >= clevPoint
				&& revClevPoint >= revRoyalPoint){
			return 4;
		}


		return 0;
	}

	private int judgeUsePattern(int gameNumber, List<Hand> myHands,
			List<Hand> enemyHands) {
		int pattern1 = 0;
    	int pattern2 = 0;
    	int pattern3 = 0;



    	for (int i=1;i<= gameNumber-1;i++) {
    		List <Hand> partMyHands = myHands.subList(0, i);
    		List <Hand> partEnemyHands = enemyHands.subList(0, i);

    		int myG = 0;
            int myC = 0;
            int myP = 0;
            for (Hand myHand : partMyHands) {
                if (myHand == Hand.G) {
                    myG++;
                } else if (myHand == Hand.C) {
                    myC++;
                } else if (myHand == Hand.P) {
                    myP++;
                }
            }

            Hand winHandForMaxHand = Hand.G;
            Hand loseHandForMaxHand = Hand.G;
            if(myG > myC){
            	if(myP > myG){
            		//Pが最も多いとき
            		winHandForMaxHand = Hand.C;
            		loseHandForMaxHand = Hand.G;
            	}
            	if(myP == myG){
            		break;
            	}
            	//Gが多い
            	winHandForMaxHand = Hand.P;
            	loseHandForMaxHand = Hand.C;
            }
            if(myC > myP){
            	//Cが多い
            	winHandForMaxHand = Hand.G;
            	loseHandForMaxHand = Hand.P;
            }
            if(myC == myP){
            	break;
            }

            if(enemyHands.get(i-1).equals(winHandForMaxHand)){
            	pattern2++;
            }else if(enemyHands.get(i-1).equals(loseHandForMaxHand)){
            	pattern1++;
            }else{
            	pattern3++;
            }


    	}


    	if(pattern2 > pattern1 && pattern2 > pattern3){
    		return 2;
    	}else if(pattern3 > pattern2 && pattern3 > pattern1){
    		return 3;
    	}else {
    		return 1;
    	}

	}

	private void judgePrevResult(Hand myHand, Hand enemyHand, int gameNumber) {
		if (myHand == Hand.G) {
            if (enemyHand == Hand.C) {
                //status.myPointAdd(3);
            	resultList.add(3);
            } else if (enemyHand == Hand.P) {
                //status.enemyPointAdd(6);
                resultList.add(-6);
            } else {
            	resultList.add(0);
            }
        } else if (myHand == Hand.C) {
            if (enemyHand == Hand.G) {
                //status.enemyPointAdd(3);
            	resultList.add(-3);
            } else if (enemyHand == Hand.P) {
                //status.myPointAdd(6);
            	resultList.add(6);
            }  else {
            	resultList.add(0);
            }
        } else if (myHand == Hand.P) {
            if (enemyHand == Hand.G) {
                //status.myPointAdd(6);
            	resultList.add(6);
            } else if (enemyHand == Hand.C) {
                //status.enemyPointAdd(6);
            	resultList.add(-6);
            }  else {
            	resultList.add(0);
            }
        }



	}

	private Hand doRoyalHand(List<Hand> enemyHands) {
		int enemyG = 0;
        int enemyC = 0;
        int enemyP = 0;
        for (Hand enemyHand : enemyHands) {
            if (enemyHand == Hand.G) {
                enemyG++;
            } else if (enemyHand == Hand.C) {
                enemyC++;
            } else if (enemyHand == Hand.P) {
                enemyP++;
            }
        }
        if (enemyG > enemyC) {
            if (enemyG > enemyP) {
                // 相手はグーが一番多い→自分はパー
                return Hand.P;
            } else {
                // 相手はパーが一番多い→自分はチョキ
                return Hand.C;
            }
        } else {
            if (enemyC > enemyP) {
                // 相手はチョキが一番多い→自分はグー
                return Hand.G;
            } else {
                // 相手はパーが一番多い→自分はチョキ
                return Hand.C;
            }
        }

	}


	private Hand doReverseRoyalHand(List<Hand> myHands) {
		int myG = 0;
        int myC = 0;
        int myP = 0;
        for (Hand myHand : myHands) {
            if (myHand == Hand.G) {
                myG++;
            } else if (myHand == Hand.C) {
                myC++;
            } else if (myHand == Hand.P) {
                myP++;
            }
        }
		if (myG > myC) {
            if (myG > myP) {
                // 自分はグーが一番多い→自分はチョキ
                return Hand.C;
            } else {
                // 自分はパーが一番多い→自分はグー
                return Hand.G;
            }
        } else {
            if (myC > myP) {
                // 自分はチョキが一番多い→自分はパー
                return Hand.P;
            } else {
                // 自分はパーが一番多い→自分はグー
                return Hand.G;
            }

        }

	}

	private Hand doDrawRoyalHand(List<Hand> myHands) {
		int myG = 0;
        int myC = 0;
        int myP = 0;
        for (Hand myHand : myHands) {
            if (myHand == Hand.G) {
                myG++;
            } else if (myHand == Hand.C) {
                myC++;
            } else if (myHand == Hand.P) {
                myP++;
            }
        }
        if (myG > myC) {
            if (myG > myP) {
                // 自分はグーが一番多い→自分はパー
                return Hand.P;
            } else {
                // 自分はパーが一番多い→自分はチョキ
                return Hand.C;
            }
        } else {
            if (myC > myP) {
                // 自分はチョキが一番多い→自分はグー
                return Hand.G;
            } else {
                // 自分はパーが一番多い→自分はチョキ
                return Hand.C;
            }

        }

	}

	private Hand doCleverHand(List<Hand> myHands, List<Hand> enemyHands){
		if (myHands.isEmpty()) {
            // まだ一回も戦っていない→とりあえずグー
            return Hand.G;
        }
        Hand beforeMyHand = myHands.get(myHands.size() - 1);
        Hand beforeEnemyHand = enemyHands.get(enemyHands.size() - 1);
        if ((beforeMyHand == Hand.G && beforeEnemyHand == Hand.C)
                || (beforeMyHand == Hand.C && beforeEnemyHand == Hand.P)
                || (beforeMyHand == Hand.P && beforeEnemyHand == Hand.G)) {
            // 前回勝っている→前回と同じ手
            return beforeMyHand;
        }
        if ((beforeMyHand == Hand.G && beforeEnemyHand == Hand.G)
                || (beforeMyHand == Hand.C && beforeEnemyHand == Hand.C)
                || (beforeMyHand == Hand.P && beforeEnemyHand == Hand.P)) {
            // 前回あいこ→3つの手から選ぶ
            switch (myHands.size() % 3) {
                case 0:
                    return Hand.G;
                case 1:
                    return Hand.C;
                case 2:
                default:
                    return Hand.P;
            }
        }
        // 前回負けている→前回以外の2つの手から選ぶ
        if (beforeMyHand == Hand.G
                && beforeEnemyHand == Hand.P) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.C;
                case 1:
                default:
                    return Hand.P;
            }
        }
        if (beforeMyHand == Hand.C
                && beforeEnemyHand == Hand.G) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.G;
                case 1:
                default:
                    return Hand.P;
            }
        }
        if (beforeMyHand == Hand.P
                && beforeEnemyHand == Hand.C) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.G;
                case 1:
                default:
                    return Hand.C;
            }
        }
		return Hand.C;
	}

	private Hand doReverseCleverHand(List<Hand> myHands, List<Hand> enemyHands){
		if (myHands.isEmpty()) {
            // まだ一回も戦っていない→とりあえずグー
            return Hand.G;
        }
        Hand beforeMyHand = myHands.get(myHands.size() - 1);
        Hand beforeEnemyHand = enemyHands.get(enemyHands.size() - 1);
        if ((beforeMyHand == Hand.G && beforeEnemyHand == Hand.P)) {
            // 前回相手がPで勝っている
            return Hand.C;
        }
        if ((beforeMyHand == Hand.P && beforeEnemyHand == Hand.C)) {
            // 前回相手がCで勝っている
            return Hand.G;
        }
        if ((beforeMyHand == Hand.C && beforeEnemyHand == Hand.G)) {
            // 前回相手がGで勝っている
            return Hand.P;
        }
        if ((beforeMyHand == Hand.G && beforeEnemyHand == Hand.G)
                || (beforeMyHand == Hand.C && beforeEnemyHand == Hand.C)
                || (beforeMyHand == Hand.P && beforeEnemyHand == Hand.P)) {
            // 前回あいこ→cleverのあいこパターンに勝つ
            switch (myHands.size() % 3) {
                case 0:
                    return Hand.P;
                case 1:
                    return Hand.G;
                case 2:
                default:
                    return Hand.C;
            }
        }
        //
        if (beforeMyHand == Hand.G
                && beforeEnemyHand == Hand.C) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.G;
                case 1:
                default:
                    return Hand.C;
            }
        }
        if (beforeMyHand == Hand.C
                && beforeEnemyHand == Hand.P) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.P;
                case 1:
                default:
                    return Hand.G;
            }
        }
        if (beforeMyHand == Hand.P
                && beforeEnemyHand == Hand.G) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.P;
                case 1:
                default:
                    return Hand.G;
            }
        }
		return Hand.C;
	}

}
