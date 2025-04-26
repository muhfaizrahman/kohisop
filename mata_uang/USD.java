package mata_uang;

public class USD implements IMataUang {
    private double kurs = 1.0 / 15.0;
    public double konversiDariIDR(double nominal) {
        return nominal * kurs;
    }
}
