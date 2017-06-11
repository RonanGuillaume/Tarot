package model.tarot.tarot4Players;

import model.CardGame;
import model.Participant;

import java.util.List;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class Tarot4PlayersGame extends CardGame{
    public Tarot4PlayersGame(List<Participant> participants) {
        super("Tarot", 4, participants);
    }
}
