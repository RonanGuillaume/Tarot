package model;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class Participant {
    private String name;
    private int score;

    public Participant(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    void addScore(int score) {
        this.score += score;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equalsIgnoreCase(((Participant)obj).getName()) && obj.getClass() == Participant.class;
    }

    @Override
    public String toString() {
        return name;
    }
}
