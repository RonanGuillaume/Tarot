package model.tarot;

/**
 * Created by Ronan
 * on 16/01/2017.
 */
public enum ContractType {
     /**
     * Prise contract type.
     */
    PRISE(1, "Prise"), /**
     * Garde contract type.
     */
    GARDE(2, "Garde"), /**
     * Garde sans contract type.
     */
    GARDE_SANS(4, "Garde sans le chien"), /**
     * Garde contre contract type.
     */
    GARDE_CONTRE(6, "Garde contre le chien");

    /**
     * The Value of multiplicator.
     */
    int valueOfMultiplicator;

    String description;

    ContractType(int valueOfMultiplicator, String description) {
        this.valueOfMultiplicator = valueOfMultiplicator;
        this.description = description;
    }

    /**
     * Gets value of multiplicator.
     *
     * @return the value of multiplicator
     */
    public int getValueOfMultiplicator() {
        return valueOfMultiplicator;
    }


    @Override
    public String toString() {
        return description;
    }
}
