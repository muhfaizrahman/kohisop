package channel_pembayaran;

public class QRIS extends PembayaranVirtual {
    Saldo saldo;

    public QRIS() {
        super(0.05, 0);
    }
}