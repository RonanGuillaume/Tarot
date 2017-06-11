package model.tarot;

/**
 * Created by Ronan
 * on 16/01/2017.
 */
public enum PetitAuBoutBonus {
    /**
     * None petit au bout bonus.
     */
    NONE(0), /**
     * Attack petit au bout bonus.
     */
    ATTACK(10), /**
     * Defence petit au bout bonus.
     */
    DEFENCE(-10);
    /**
     * The Value of bonus.
     */
    int valueOfBonus;

    PetitAuBoutBonus(int valueOfBonus) {
        this.valueOfBonus = valueOfBonus;
    }

    /**
     * Gets value of bonus.
     *
     * @return the value of bonus
     */
    public int getValueOfBonus() {
        return valueOfBonus;
    }
}
