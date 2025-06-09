package channel_pembayaran;

import java.util.Scanner;
import mata_uang.MataUang;
import membership.Member;
import pemesanan.PesananMakanan;
import membership.MemberService;
import pemesanan.PesananMinuman;
import perhitungan.Perhitungan;

public class PembayaranVirtual extends Pembayaran {
    protected Saldo saldo;

    PembayaranVirtual(double diskon, int biayaAdmin) {
        super(diskon, biayaAdmin);
        this.saldo = new Saldo();
    }

    @Override
    public void bayar(Scanner input, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan, Member member) {
        System.out.println("Pembayaran " + this.getClass().getSimpleName());

        MataUang mataUang = MataUang.getMataUang();
        double hargaSebelumDiskon = Perhitungan.hitungTagihanTotal(pesananMinuman, pesananMakanan);
        double totalHargaIDR = (hargaSebelumDiskon * (1 - diskon)) + biayaAdmin + Perhitungan.getPajakTagihan(pesananMinuman, pesananMakanan);
        String namaClassMataUang = mataUang.getClass().getSimpleName();
        
        // Case user pilih IDR
        if (member != null && mataUang instanceof mata_uang.IDR) {
            double poinPakai = MemberService.hitungPoinPakai(totalHargaIDR, member);

            if ((int) poinPakai > totalHargaIDR ) {
                totalHargaIDR -= (int) poinPakai;
                System.out.println("Dikarenakan poin yang anda miliki lebih dari total belanja harga, Anda tidak perlu membayar. Berikut adalah kuitansinya: ");
                return;
            } 
        }

        // Bayar
        while (true) {
            double totalHarga = mataUang.konversiDariIDR(totalHargaIDR);
            System.out.printf("Total tagihan adalah : %.2f %s\n", totalHarga, namaClassMataUang);
            
            if (Saldo.getMemberBalance(member) >= totalHargaIDR) {
                Saldo.addToMemberBalance(member, -totalHargaIDR);
                System.out.printf("Saldo anda sekarang adalah : %.2f IDR // %.2f %s\n", 
                    Saldo.getMemberBalance(member), 
                    mataUang.konversiDariIDR(Saldo.getMemberBalance(member)), 
                    namaClassMataUang);
                System.out.println("Pembayaran dengan " + this.getClass().getSimpleName() + " berhasil.");
                System.out.println("Berikut adalah kuitansinya: ");
                break;
            } else {
                System.out.println("Saldo tidak mencukupi. Silakan top up terlebih dahulu.");
                System.out.println("Masukkan nominal untuk top up saldo: ");
                double topUpAmount = input.nextDouble();
                Saldo.addToMemberBalance(member, topUpAmount);
                System.out.println("Top up saldo berhasil.");
                System.out.printf("Saldo anda sekarang adalah : %.2f IDR // %.2f %s\n", 
                    Saldo.getMemberBalance(member), 
                    mataUang.konversiDariIDR(Saldo.getMemberBalance(member)), 
                    namaClassMataUang);
                continue;
            }
        }
    }
}
