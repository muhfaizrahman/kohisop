package mata_uang;

public class EUR implements IMataUang {
    private double kurs = 1.0 / 14.0;
    public double konversiDariIDR(double nominal) {
        return nominal * kurs;
    }
}
