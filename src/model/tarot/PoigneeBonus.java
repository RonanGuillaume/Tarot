package model.tarot;

/**
 * Created by Ronan
 * on 16/01/2017.
 */
public enum PoigneeBonus {
    /**
     * None poignee bonus.
     */
    NONE(0, "None"), /**
     * Simple poignee bonus.
     */
    SIMPLE(20, "Simple"), /**
     * Double poignee bonus.
     */
    DOUBLE(30, "Double"), /**
     * Triple poignee bonus.
     */
    TRIPLE(40, "Triple");
    /**
     * The Value of bonus.
     */
    int valueOfBonus;

    String description;

    PoigneeBonus(int valueOfBonus, String description) {
        this.valueOfBonus = valueOfBonus;
        this.description = description;
    }

    /**
     * Gets value of bonus.
     *
     * @return the value of bonus
     */
    public int getValueOfBonus() {
        return valueOfBonus;
    }

    @Override
    public String toString() {
        return description;
    }
}
