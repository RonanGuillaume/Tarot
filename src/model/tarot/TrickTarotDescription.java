package model.tarot;

import model.Participant;

/**
 * Created by Ronan
 * on 14/02/2017.
 */
public class TrickTarotDescription {
    private PoigneeBonus poigneeBonus;
    private PetitAuBoutBonus petitAuBoutBonus;
    private int pointsMarkedAttacker;
    private int pointsMarkedDefenser;
    private ContractType contract;
    private ChelemBonus chelemBonus;
    private Participant chelemAnnouncer;
    private NumberOfBouts numberBoutsTaker;
    private boolean win;

    public TrickTarotDescription(PoigneeBonus poigneeBonus, PetitAuBoutBonus petitAuBoutBonus, int pointsMarkedAttacker, int pointsMarkedDefenser, ContractType contract, ChelemBonus chelemBonus, Participant chelemAnnouncer, NumberOfBouts numberBoutsTaker) {
        this.poigneeBonus = poigneeBonus;
        this.petitAuBoutBonus = petitAuBoutBonus;
        this.pointsMarkedAttacker = pointsMarkedAttacker;
        this.pointsMarkedDefenser = pointsMarkedDefenser;
        this.contract = contract;
        this.chelemBonus = chelemBonus;
        this.chelemAnnouncer = chelemAnnouncer;
        this.numberBoutsTaker = numberBoutsTaker;
    }

    public PoigneeBonus getPoigneeBonus() {
        return poigneeBonus;
    }

    public PetitAuBoutBonus getPetitAuBoutBonus() {
        return petitAuBoutBonus;
    }

    public int getPointsMarkedAttacker() {
        return pointsMarkedAttacker;
    }

    public int getPointsMarkedDefenser() {
        return pointsMarkedDefenser;
    }

    public ContractType getContract() {
        return contract;
    }

    public ChelemBonus getChelemBonus() {
        return chelemBonus;
    }

    public Participant getChelemAnnouncer() {
        return chelemAnnouncer;
    }

    public NumberOfBouts getNumberBoutsTaker() {
        return numberBoutsTaker;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
