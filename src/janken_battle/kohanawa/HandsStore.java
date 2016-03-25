package janken_battle.kohanawa;

import janken_battle.Main.Hand;

public interface HandsStore {
	/**
	 * 出した手を蓄積します。
	 *
	 * @param hand
	 */
	public void storeHands(Hand hand);

	/**
	 * 引数として渡した手を出した回数を返します。
	 *
	 * @param hand
	 * @return
	 */
	public int getHandsCount(Hand hand);
}
