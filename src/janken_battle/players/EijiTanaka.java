package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * ・アルゴリズム
 * 相手の１手前から遡ぼれるだけ遡って一致するパターンを探す。探すパターン長は１手から履歴数－１まで全部。
 * その一致したパターンの次の手に勝てる手にポイントを付ける。
 * 一致したパターンが長いほど高いポイントをつける。短いパターンでも一致回数が多ければポイントになる。
 * 最終的に一番ポイントが高い手を出す。
 * 
 * ・プログラム的な話
 * 長くなるパターン一致を使用するので、一括比較ができる文字列比較を使用することでループ回数削減。
 * （java内部でStringのCompareをループでやってたら無意味だが、一括処理していると思う・・・が、ソース見たらループ使ってた。まじか。）
 */
public class EijiTanaka implements JankenPlayer {
	private final String STR_G = "G";
	private final String STR_C = "C";
	private final String STR_P = "P";

	@Override
	public String getName() {
		return "Eiji Tanaka";
	}

	@Override
	public Hand getHand(List<Hand> myHands, List<Hand> enemyHands, int myPoint, int enemyPoint, int gameNumber) {
		String enemyHistory = getHandsHistoryString(enemyHands);
		int waight_g = 0;
		int waight_c = 0;
		int waight_p = 0;
		int gameCnt = enemyHistory.length();
		
		//一手前から、長さを変えてパターン検出
		for(int predictPos = 1;predictPos < gameCnt - 1;predictPos ++){
			String preHistory = enemyHistory.substring(predictPos, gameCnt);
			int chkLen = preHistory.length();
			int chkCnt = gameCnt - chkLen;
			
			//今までの履歴から一致するパターンを探す
			for(int chkPos = 0;chkPos < chkCnt;chkPos++){
				if(enemyHistory.substring(chkPos,chkPos + chkLen).equals(preHistory)){
					//一致したら、その次の手を調べ、それに勝てる手にポイント加算
					String nextHand = enemyHistory.substring(chkPos + chkLen, chkPos + chkLen + 1);
					if(nextHand.equals(STR_G)){
						waight_g += chkLen;
					}else if(nextHand.equals(STR_C)){
						waight_c += chkLen;
					}else{
						waight_p += chkLen;
					}
				}
			}
		}
		if(waight_g > waight_c && waight_g > waight_p){
			return Hand.P;
		}else if(waight_c > waight_p){
			return Hand.G;
		}else{
			return Hand.C;
		}
	}

	private String getHandsHistoryString(List<Hand> hands) {
		StringBuilder sb = new StringBuilder();
		for(Hand hand : hands){
			sb.append(getHandStr(hand));
		}
		return sb.toString();
	}

	private String getHandStr(Hand hand) {
		if (hand == Hand.G) {
			return STR_G;
		} else if (hand == Hand.C) {
			return STR_C;
		} else {
			return STR_P;
		}
	}
}
