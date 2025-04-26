package mata_uang;

public class MYR implements IMataUang {
    private double kurs = 1.0 / 4.0;
    public double konversiDariIDR(double nominal) {
        return nominal * kurs;
    }
}
