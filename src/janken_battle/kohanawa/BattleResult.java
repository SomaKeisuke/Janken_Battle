package janken_battle.kohanawa;

import java.util.ArrayList;
import java.util.List;

import janken_battle.Main.Hand;

public class BattleResult {
	private int PwinPoint = 0;
	private int GwinPoint = 0;
	private int CwinPoint = 0;
	private List<Hand> myHands = new ArrayList<Hand>();
	private List<Hand> enemyHands = new ArrayList<Hand>();

	public int getPwinPoint() {
		return PwinPoint;
	}

	public void addPwinPoint(int point) {
		PwinPoint = PwinPoint + point;
	}

	public int getGwinPoint() {
		return GwinPoint;
	}

	public void addGwinPoint(int point) {
		GwinPoint = GwinPoint + point;
	}

	public int getCwinPoint() {
		return CwinPoint;
	}

	public void addCwinPoint(int point) {
		CwinPoint = CwinPoint + point;
	}

	public List<Hand> getMyHands() {
		return myHands;
	}

	public void addMyHand(Hand myHand) {
		myHands.add(myHand);
	}

	public List<Hand> getEnemyHands() {
		return enemyHands;
	}

	public void addEnemyHand(Hand enemyHand) {
		enemyHands.add(enemyHand);
	}

	public void storeResultPoint() {
		Hand myHand = getMyHands().get(getMyHands().size() - 1);
		Hand enemyHand = getEnemyHands().get(getEnemyHands().size() - 1);

		if (myHand == Hand.G) {
			if (enemyHand == Hand.C) {
				addGwinPoint(3);
			} else if (enemyHand == Hand.P) {
				addGwinPoint(-8);
			} else {
				addGwinPoint(-2);
			}
		} else if (myHand == Hand.C) {
			if (enemyHand == Hand.G) {
				addCwinPoint(-8);
			} else if (enemyHand == Hand.P) {
				addCwinPoint(6);
			} else {
				addCwinPoint(-2);
			}
		} else if (myHand == Hand.P) {
			if (enemyHand == Hand.G) {
				addPwinPoint(6);
			} else if (enemyHand == Hand.C) {
				addPwinPoint(-8);
			} else {
				addPwinPoint(-2);
			}
		}
	}

}
