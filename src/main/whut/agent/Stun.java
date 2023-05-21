package whut.agent;

import whut.player.AgentUsable;
import whut.material.Packet;

public class Stun extends Agent {

    private int effectTime;

    //be?ll?tja a kezd? hat?s?rt?ket
    public Stun() {
        effectTime = 3;
    }

    @Override
    public boolean startTurnEffect(AgentUsable au) {
        //cs?kkenti a hat?sid?t, ?s ha lej?rt, akkor kit?rli a virol?gust?l
        if(--effectTime < 0) {
            au.removeAgensOnMe(this);
            //ha lej?rt akkor m?r tud mozogni
            return true;
        }

        //ha nem j?rt le m?g akkor nem tud mozogni
        return false;
    }

    //stunnolt, teh?t lehet lopni t?le
    @Override
    public boolean canStealEffect() {
        return true;
    }

    @Override
    public boolean check(String s) {
        return s.equals("stun");
    }

    public String toString() {
        return "stun";
    }

    @Override
    public void destroyEffect(Packet p) {
        //abstracct oszt?lyt val?s?t meg ez?rt kell
    }
}