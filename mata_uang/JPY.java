package mata_uang;

public class JPY extends MataUang {
    @Override
    public double konversiDariIDR(double jumlah) {
        return jumlah * 10;
    }
}
