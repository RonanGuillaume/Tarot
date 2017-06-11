package model.tarot.tarot4Players;

import model.Participant;
import model.Trick;
import model.tarot.ChelemBonus;
import model.tarot.TrickTarotDescription;

import java.util.List;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class Trick4Players extends Trick{
    private Participant taker;
    private int trickValue;
    private TrickTarotDescription description;

    public Trick4Players(List<Participant> trickParticipants) {
        super(trickParticipants);
    }

    public Trick4Players(List<Participant> trickParticipants, Participant taker, TrickTarotDescription description) {
        super(trickParticipants);
        this.taker = taker;
        this.description = description;
        calculateScore();
    }

    private void calculateTrickValue() {
        int margin = description.getPointsMarkedAttacker() - description.getNumberBoutsTaker().getContractValue();

        if (margin < 0){
            description.setWin(false);
            margin *= -1;
        }
        else {
            description.setWin(true);
        }

        trickValue = ((25 + margin + description.getPetitAuBoutBonus().getValueOfBonus())
                * description.getContract().getValueOfMultiplicator()) + description.getPoigneeBonus().getValueOfBonus();

        if (!description.isWin()){
            trickValue *= -1;
        }

        if (description.getChelemBonus() != ChelemBonus.NONE){
            if (description.getChelemAnnouncer().equals(taker)){
                trickValue += description.getChelemBonus().getValueOfBonus();
            }
            else {
                trickValue -= description.getChelemBonus().getValueOfBonus();
            }
        }
    }

    @Override
    public void calculateScore() {
        calculateTrickValue();
        for (Participant participant : getTrickParticipants()) {
            setScoreOfParticipant(participant, trickValue*-1);
        }
        setScoreOfParticipant(taker, trickValue*(getTrickParticipants().size() - 1));
    }
}
