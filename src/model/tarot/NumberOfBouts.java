package model.tarot;

/**
 * Created by Ronan
 * on 29/01/2017.
 */
public enum NumberOfBouts {
    NONE(56),
    ONE(51),
    TWO(41),
    THREE(36);

    int contractValue;

    NumberOfBouts(int contractValue) {
        this.contractValue = contractValue;
    }

    public int getContractValue() {
        return contractValue;
    }
}
