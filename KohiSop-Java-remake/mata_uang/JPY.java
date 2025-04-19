package mata_uang;

public class JPY extends MataUang implements IMataUang {
    private double kurs = 10.0;
    public double konversiDariIDR(double nominal) {
        return nominal * kurs;
    }
}
