package mata_uang;

public class MYR extends MataUang {
    @Override
    public double konversiDariIDR(double jumlah) {
        return jumlah / 4;
    }
}
