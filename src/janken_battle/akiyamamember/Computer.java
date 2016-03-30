package janken_battle.akiyamamember;

public class Computer extends Got {

    protected void outOfMemory() {
        throw new RuntimeException("メモリリーク・・・");
    }
}
