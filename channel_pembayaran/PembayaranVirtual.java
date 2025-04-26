package channel_pembayaran;

import java.util.Scanner;

import mata_uang.IMataUang;
import mata_uang.MataUang;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;
import perhitungan.Perhitungan;

public class PembayaranVirtual extends Pembayaran {
    protected Saldo saldo;

    PembayaranVirtual(double diskon, int biayaAdmin) {
        super(diskon, biayaAdmin);
        this.saldo = new Saldo();
    }

    @Override
    public void bayar(Scanner input, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan) {
        System.out.println("Pembayaran " + this.getClass().getSimpleName());

        IMataUang mataUang = MataUang.getMataUang();
        double hargaSebelumDiskon = Perhitungan.hitungTagihanTotal(pesananMinuman, pesananMakanan);
        double totalHargaIDR = (hargaSebelumDiskon * (1 - diskon)) + biayaAdmin + Perhitungan.getPajakTagihan(pesananMinuman, pesananMakanan);
        double totalHarga = mataUang.konversiDariIDR(totalHargaIDR);
        String namaClassMataUang = mataUang.getClass().getSimpleName();
        
        // Bayar
        while (true) {
            System.out.printf("Total tagihan adalah : %.2f %s\n", totalHarga, namaClassMataUang);
            
            if (saldo.getSaldo() >= totalHargaIDR) {
                saldo.setSaldo(-totalHargaIDR);
                System.out.println("Pembayaran dengan " + this.getClass().getSimpleName() + " berhasil.");
                System.out.printf("Saldo anda sekarang adalah : %.2f IDR // %.2f %s\n", saldo.getSaldo(), mataUang.konversiDariIDR(saldo.getSaldo()), namaClassMataUang);
                System.out.println("Berikut adalah kuitansinya: ");
                break;
            } else {
                System.out.println("Saldo tidak mencukupi. Silakan top up terlebih dahulu.");
                System.out.println("Masukkan nominal untuk top up saldo: ");
                saldo.setSaldo(input.nextDouble());
                System.out.println("Top up saldo berhasil.");
                System.out.printf("Saldo anda sekarang adalah : %.2f IDR // %.2f %s\n", saldo.getSaldo(), mataUang.konversiDariIDR(saldo.getSaldo()), namaClassMataUang);
                continue;
            }
        }

    }
}
