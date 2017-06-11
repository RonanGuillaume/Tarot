package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public abstract class CardGame extends Observable{
    private String nameGame;
    private int numberOfParticipants;
    private List<Participant> participants;
    private List<Trick> tricks;

    public CardGame(String nameGame, int numberOfParticipants, List<Participant> participants) throws IllegalArgumentException{
        if (participants.size() != numberOfParticipants){
            throw new IllegalArgumentException("The number of participants doesn't match...");
        }
        this.nameGame = nameGame;
        this.numberOfParticipants = numberOfParticipants;
        this.participants = participants;
        this.tricks = new ArrayList<>();
    }

    public String getNameGame() {
        return nameGame;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public List<Participant> getParticipants() {
        ArrayList<Participant> result = new ArrayList<>();
        for (Participant participant : participants) {
            result.add(participant);
        }
        return result;
    }

    public List<Trick> getTricks() {
        ArrayList<Trick> result = new ArrayList<>();
        for (Trick trick : tricks) {
            result.add(trick);
        }
        return result;
    }

    public void addTrick(Trick trick){
        tricks.add(trick);
        for (Participant participant : trick.getTrickParticipants()) {
            participant.addScore(participant.getScore());
        }
        setChanged();
    }
}
