package mata_uang;

public class EUR extends MataUang {
    @Override
    public double konversiDariIDR(double jumlah) {
        return jumlah / 14;
    }
}
