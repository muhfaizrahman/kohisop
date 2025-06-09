package channel_pembayaran;

import java.util.Scanner;
import mata_uang.MataUang;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;
import perhitungan.Perhitungan;
import membership.Member;
import membership.MemberService;
public class Tunai extends Pembayaran {

    public Tunai() {
        super(0, 0);
    }

    @Override
    public void bayar(Scanner input, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan, Member member) {
        System.out.println("Pembayaran Tunai");

        MataUang mataUang = MataUang.getMataUang();
        double totalHargaIDR = Perhitungan.hitungTagihanTotal(pesananMinuman, pesananMakanan) + Perhitungan.getPajakTagihan(pesananMinuman, pesananMakanan);

        // Case user pilih IDR
        if (member != null && mataUang instanceof mata_uang.IDR) {
            double poinPakai = MemberService.hitungPoinPakai(totalHargaIDR, member);

            if ((int) poinPakai > totalHargaIDR ) {
                totalHargaIDR -= (int) poinPakai;
                System.out.println("Dikarenakan poin yang anda miliki lebih dari total belanja harga, Anda tidak perlu membayar. Berikut adalah kuitansinya: ");
                return;
            } 
        }

        // lanjut ke proses input uang tunai
        while (true) {
            double totalHargaMataUangLain = mataUang.konversiDariIDR(totalHargaIDR);
            System.out.printf("Total tagihan adalah : %.2f %s\n", totalHargaMataUangLain, mataUang.getClass().getSimpleName());
            System.out.println("Masukkan jumlah uang tunai untuk membayar: ");

            double uangTunai = input.nextDouble();

            System.out.printf("Uang tunai yang dimasukkan adalah : %.2f IDR // %.2f %s\n",
                uangTunai, mataUang.konversiDariIDR(uangTunai), mataUang.getClass().getSimpleName());

            if (uangTunai >= totalHargaIDR) {
                double kembalian = mataUang.konversiDariIDR(uangTunai - totalHargaIDR);
                System.out.printf("Kembalian adalah : %3.2f %s\n", kembalian, mataUang.getClass().getSimpleName());
                System.out.println("Pembayaran berhasil. Berikut adalah kuitansinya: ");
                break;
            } else {
                System.out.println("Uang tunai tidak mencukupi. Silakan bayar lagi.");
            }
        }

    }
}

