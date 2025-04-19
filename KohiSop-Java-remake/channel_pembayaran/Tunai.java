package channel_pembayaran;

import java.util.Scanner;

import mata_uang.IMataUang;
import mata_uang.MataUang;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;
import perhitungan.Perhitungan;

public class Tunai extends Pembayaran{

    public Tunai() {
        super(0, 0);
    }

    @Override
    public void bayar(Scanner input, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan) {
        System.out.println("Pembayaran Tunai");

        IMataUang mataUang = MataUang.getMataUang();
        double totalHargaIDR = Perhitungan.hitungTagihanTotal(pesananMinuman, pesananMakanan) + Perhitungan.getPajakTagihan(pesananMinuman, pesananMakanan);
        double totalHargaMataUangLain = mataUang.konversiDariIDR(totalHargaIDR);

        while (true) {
            System.out.printf("Total tagihan adalah : %.2f %s\n", totalHargaMataUangLain, mataUang.getClass().getSimpleName());
            System.out.println("Masukkan jumlah uang tunai untuk membayar: ");

            double uangTunai = input.nextDouble();

            System.out.printf("Uang tunai yang dimasukkan adalah : %.2f IDR // %.2f %s\n", uangTunai, mataUang.konversiDariIDR(uangTunai), mataUang.getClass().getSimpleName());
    
            if (uangTunai >= totalHargaIDR) {
                double kembalian = mataUang.konversiDariIDR(uangTunai - totalHargaIDR);
                System.out.printf("Kembalian adalah : %3.2f %s\n", kembalian, mataUang.getClass().getSimpleName());
                System.out.println("Pembayaran berhasil. Berikut adalah kuitansinya: ");
                break;
            } else {
                System.out.println("Uang tunai tidak mencukupi. Silakan bayar lagi.");
                continue;
            }
    
        }
    }
}
