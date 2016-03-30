package janken_battle.kohanawa;

import janken_battle.Main.Hand;

public interface HandsStore {
	/**
	 * 出した手を蓄積します。
	 *
	 * @param hand
	 */
	public void storeHands(Hand myHand, Hand enemyHand);

	/**
	 * 引数として渡した手を出した回数を返します。
	 *
	 * @param hand
	 * @return
	 */
	public int getHandsCount(Hand hand);

	/**
	 * 引数として渡した手を出した現在の割合を取得します。
	 *
	 * @param hand
	 * @param gameNumber
	 * @return
	 */
	public double getCurrentHandsRate(Hand hand, int gameNumber);

	/**
	 * 現状一番勝っている手を返します。
	 *
	 * @return
	 */
	public Hand getPointGetter();
}
