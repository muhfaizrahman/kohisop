package channel_pembayaran;

import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;

public abstract class Pembayaran {
    protected double diskon;
    protected double biayaAdmin;

    Pembayaran(double diskon, double biayaAdmin) {
        this.diskon = diskon;
        this.biayaAdmin = biayaAdmin;
    }

    public abstract void bayar(java.util.Scanner input, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan);

    public double getDiskon() {
        return diskon;
    }

    public double getBiayaAdmin() {
        return biayaAdmin;
    }

}
