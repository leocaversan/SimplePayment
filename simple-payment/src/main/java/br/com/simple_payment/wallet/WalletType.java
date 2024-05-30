package br.com.simple_payment.wallet;

public enum WalletType {
    COMUM(1), PAYEE(2);

    private int value;
    private WalletType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
