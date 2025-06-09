package mata_uang;

public class USD extends MataUang {
    @Override
    public double konversiDariIDR(double jumlah) {
        return jumlah / 15;
    }
}
