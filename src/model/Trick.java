package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public abstract class Trick {
    private Map<Participant,Integer> scores;
    private List<Participant> trickParticipants;
    private CardGame myCardGame;

    public Trick(List<Participant> trickParticipants) {
        this.trickParticipants = trickParticipants;
        this.scores = new HashMap<>();
        for (Participant participant : trickParticipants) {
            scores.put(participant, 0);
        }
    }

    public Map<Participant, Integer> getScores() {
        HashMap<Participant, Integer> result = new HashMap<>();
        for (Participant participant : trickParticipants) {
            result.put(participant, scores.get(participant));
        }
        return result;
    }

    public List<Participant> getTrickParticipants() {
        ArrayList<Participant> result = new ArrayList<>();
        for (Participant participant : trickParticipants) {
            result.add(participant);
        }
        return result;
    }

    public CardGame getMyCardGame() {
        return myCardGame;
    }

    protected void setScoreOfParticipant(Participant participant, int score){
        scores.put(participant, score);
    }

    void setMyCardGame(CardGame myCardGame) {
        this.myCardGame = myCardGame;
    }

    public abstract void calculateScore();
}
