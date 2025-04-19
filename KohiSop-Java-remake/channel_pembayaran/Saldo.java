package channel_pembayaran;

public class Saldo {
    private double nominal;

    public Saldo() {
        this.nominal = 0;
    }

    public double getSaldo() {
        return nominal;
    }

    public void setSaldo(double nominal) {
        this.nominal += nominal;
    }

}
