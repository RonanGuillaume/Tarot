package model.tarot;

/**
 * Created by Ronan
 * on 16/01/2017.
 */
public enum ChelemBonus {
    /**
     * None chelem bonus.
     */
    NONE(0, "None"),

    /**
     * Annonce realise chelem bonus.
     */
    ANNOUNCED_REALISED(400, "Annoncé et réalisé"),

    /**
     * Not annonce realise chelem bonus.
     */
    NOT_ANNOUNCED_REALISED(200, "Non annoncé mais réalisé"),

    /**
     * Annonce not realise chelem bonus.
     */
    ANNOUNCED_NOT_REALISE(-200, "Annoncé mais pas réalisé");

    /**
     * The Value of bonus.
     */
    int valueOfBonus;

    String description;

    ChelemBonus(int valueOfBonus, String description) {
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
