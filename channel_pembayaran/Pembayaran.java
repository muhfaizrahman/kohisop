package channel_pembayaran;

import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;
import membership.Member;
import java.util.Scanner;

public abstract class Pembayaran {
    protected double diskon;
    protected double biayaAdmin;

    Pembayaran(double diskon, double biayaAdmin) {
        this.diskon = diskon;
        this.biayaAdmin = biayaAdmin;
    }

    public abstract void bayar(Scanner input, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan, Member member);

    public double getDiskon() {
        return diskon;
    }

    public double getBiayaAdmin() {
        return biayaAdmin;
    }

}
