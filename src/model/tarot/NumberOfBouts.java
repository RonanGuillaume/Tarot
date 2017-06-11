package model.tarot;

/**
 * Created by Ronan
 * on 29/01/2017.
 */
public enum NumberOfBouts {
    NONE(56, "0"),
    ONE(51, "1"),
    TWO(41, "2"),
    THREE(36, "3");

    int contractValue;
    String description;

    NumberOfBouts(int contractValue, String description) {
        this.contractValue = contractValue;
        this.description = description;
    }

    public int getContractValue() {
        return contractValue;
    }


    @Override
    public String toString() {
        return description;
    }
}
