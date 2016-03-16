package janken_battle.players;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 *
 * 出す順番が決まっているサンプルを複数セットして、
 * 成績の良いサンプルを残して戦わせる感じです。
 *
 * @author yoshikawa_s
 */
public class YourCode implements JankenPlayer {


    private static List<Sample> analysisList;
    private static Map<Sample, Integer> analysisScores;

    private static int lastMyPoint = 0;
    private static int lastEnemyPoint = 0;

    private static final int ANALYSIS_SAMPLE_CYCLE = 2;//自分で決めます
    private static final int ANALYSIS_GAME_CYCLE = ANALYSIS_SAMPLE_CYCLE * Sample.ROTATION_CYCLE;


    @Override
    public String getName() {
        return "Analyzer";
    }


    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {

        if(gameNumber == 1){
            analysisList = new ArrayList<Sample>();
            analysisScores = new LinkedHashMap<Sample, Integer>();
            setAnalysisDeck(analysisList, analysisScores);

            Sample runningSample = analysisList.get(0);
            return runningSample.getHand(gameNumber);
        }

        if(gameNumber % Sample.ROTATION_CYCLE == 1){
            Sample ranSample = analysisList.get(0);
            setScore(myPoint,lastMyPoint, enemyPoint, lastEnemyPoint,
                    ranSample, analysisList, analysisScores);
        }

        if(gameNumber % ANALYSIS_GAME_CYCLE == 1){
            analyze(analysisList, analysisScores);
        }

        Sample runningSample = analysisList.get(0);
        return runningSample.getHand(gameNumber);
    }



    private void setAnalysisDeck(List<Sample> analysisList, Map<Sample, Integer> analysisScores) {
        Sample.setAnalysisDeck(analysisList);
        for(Sample sample : analysisList){
            analysisScores.put(sample, 0);
        }
    }



    private void setScore(int myPoint ,int lastMyPoint ,int enemyPoint, int lastEnemyPoint ,
            Sample ranSample, List<Sample> analysisList, Map<Sample, Integer> analysisScores){

        int lastScore = analysisScores.get(ranSample);
        int latestScore = (myPoint - lastMyPoint) - (enemyPoint - lastEnemyPoint);

        rotation(analysisList, analysisScores, ranSample, lastScore + latestScore);

        setLastMyPoint(myPoint);
        setLastEnemyPoint(enemyPoint);
    }


    private void rotation(List<Sample> analysisList, Map<Sample, Integer> analysisScores,
            Sample ranSample, int score) {
        analysisScores.remove(ranSample);
        analysisScores.put(ranSample, score);

        analysisList.remove(ranSample);
        analysisList.add(ranSample);
    }

    public static void setLastMyPoint(int lastMyPoint) {
        YourCode.lastMyPoint = lastMyPoint;
    }
    public static void setLastEnemyPoint(int lastEnemyPoint) {
        YourCode.lastEnemyPoint = lastEnemyPoint;
    }



    private void analyze(List<Sample> analysisList, Map<Sample, Integer> analysisScores) {
        sortByScore(analysisList, analysisScores);
        setOptimalSample(analysisList, analysisScores);
    }

    private void sortByScore(List<Sample> analysisList, Map<Sample, Integer> analysisScores) {

        analysisList.clear();
        for(Sample scoredSample : analysisScores.keySet()){
            for(int i = 0; i < analysisList.size(); i++){
                Sample orderingSample = analysisList.get(i);
                if(analysisScores.get(scoredSample) > analysisScores.get(orderingSample)){
                    analysisList.add(i, scoredSample);
                    break;
                }
            }
            if(!analysisList.contains(scoredSample)){
                analysisList.add(scoredSample);
                continue;
            }
        }
    }


    private static final int OPTIMAL_SCORE = 9;//自分で決めます

    /**
     *
     * 成績のいいサンプルがある場合、全部戦わせます。
     *
     * @param analysisList
     * @param analysisScores
     */
    private void setOptimalSample(List<Sample> analysisList, Map<Sample, Integer> analysisScores) {
        Sample highscoreSample = analysisList.get(0);
        int highscore = analysisScores.get(highscoreSample);

        if(highscore >= OPTIMAL_SCORE){
            for(int i=0; i < ANALYSIS_SAMPLE_CYCLE; i++){
                analysisList.add(0, highscoreSample);
            }
        }
    }

}
