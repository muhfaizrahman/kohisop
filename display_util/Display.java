package display_util;

import java.util.Comparator;
import java.util.List;

import channel_pembayaran.Pembayaran;
import mata_uang.MataUang;
import menu.Makanan;
import menu.Minuman;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;
import perhitungan.Perhitungan;
import pemesanan.ItemPesanan;
import pemesanan.MenuPesanan;
import membership.*;

public class Display {

    public static void displayKategori(MenuPesanan pesanan) {
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %50s%s%51s |\n", "", "Kategori " + pesanan.getKategori(), "");
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-40s | %-10s | %-10s | %-12s | %-10s | %-12s |\n",
            "Kode", "Nama Menu", "Harga", "Jumlah", "Total Harga", "Pajak (%)", "Pajak (IDR)");
        System.out.printf("|%s|%s|%s|%s|%s|%s|%s|\n",
            "-------", "------------------------------------------", "------------", "------------", "--------------", "------------", "--------------");

        for (ItemPesanan item : pesanan.getRincianPesanan()) {
            String kode = item.getKode();
            int qty = item.getQty();
            String nama = "";
            int harga = 0;

            // Ambil detail menu dari kode
            if (pesanan instanceof PesananMakanan) {
                Makanan makanan = Makanan.getMakananByKode(kode);
                if (makanan != null) {
                    nama = makanan.getNama();
                    harga = makanan.getHarga();
                }
            } else if (pesanan instanceof PesananMinuman) {
                Minuman minuman = Minuman.getMinumanByKode(kode);
                if (minuman != null) {
                    nama = minuman.getNama();
                    harga = minuman.getHarga();
                }
            }

            double total = Perhitungan.hitungTotalPerMenu(pesanan, kode);
            double pajakPersen = Perhitungan.getPersentasePajak(pesanan, kode) * 100;
            double pajakIDR = Perhitungan.getBesarPajakIDR(pesanan, kode);

            System.out.printf("| %-5s | %-40s | %-10d | %-10d | %-12.2f | %-9.2f%% | %-12.2f |\n",
                kode, nama, harga, qty, total, pajakPersen, pajakIDR);
        }

        System.out.printf("|%s|%s|%s|%s|%s|%s|%s|\n",
            "-------", "------------------------------------------", "------------", "------------", "--------------", "------------", "--------------");
        System.out.printf("| Total harga %s di luar pajak: %-83.2f|\n", pesanan.getKategori(), Perhitungan.hitungTagihanKategori(pesanan));
        System.out.printf("| Total harga %s termasuk pajak: %-82.2f|\n", pesanan.getKategori(), Perhitungan.hitungTagihanKategori(pesanan) + Perhitungan.getPajakKategori(pesanan));
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayKuitansi(Pembayaran pembayaran, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan, Member member) {

        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %40s%s%39s |\n", "","Kuitansi Pembayaran Kohisop Restaurant", "");
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s: %10s %84s |\n", "Nama Customer", member.getNama(), "");
        System.out.printf("| %-20s: %10s %84s |\n", "Kode Member", member.getKode(), "");

        displayKategori(pesananMakanan);
        displayKategori(pesananMinuman);
        
        MataUang mataUang = MataUang.getMataUang();
        String mataUangString = mataUang.getClass().getSimpleName();
        double totalPajakIDR = Perhitungan.getPajakTagihan(pesananMinuman, pesananMakanan);
        double totalPajak = mataUang.konversiDariIDR(totalPajakIDR);

        double hargaSebelumPerhitunganIDR = Perhitungan.hitungTagihanTotal(pesananMinuman, pesananMakanan);
        double totalTagihanSebelumPerhitungan = mataUang.konversiDariIDR(hargaSebelumPerhitunganIDR);
        
        double totalHargaIDR = (hargaSebelumPerhitunganIDR * (1 - pembayaran.getDiskon())) + pembayaran.getBiayaAdmin() + totalPajakIDR;
        double totalTagihanSetelahPerhitungan = mataUang.konversiDariIDR(totalHargaIDR);

        // Total tagihan dengan mata uang pembayaran
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");

        if (member != null) {
            System.out.printf("| %-50s: %10d %54s |\n", "Poin sebelum transaksi", member.getPoin(), "");
            
            // Calculate points before member registration
            double poinDapat = 0;
            poinDapat = MemberService.hitungPoinDapat(totalHargaIDR, member);
            if (mataUang instanceof mata_uang.IDR) {
                double poinPakai = MemberService.hitungPoinPakai(totalHargaIDR, member);
                if (poinPakai > totalHargaIDR) 
                    member.tambahPoin(-(int) (poinPakai / 2)); // Deduct points used
            }

            // Handle member registration
            if (member.getKode().equals("TEMP")) {
                // Register new member
                MemberService.daftarMemberBaru(member.getNama());
                // Get new member reference
                Member newMember = MemberService.cariMemberByNama(member.getNama());
                // Transfer existing points and add new points
                newMember.tambahPoin(member.getPoin() + (int)poinDapat);
                // Update member reference
                member = newMember;
            } else {
                // Add points for existing member
                member.tambahPoin((int)poinDapat);
            }

            System.out.printf("| %-50s: %10d %54s |\n", "Poin setelah transaksi", member.getPoin(), "");

        }
        
        System.out.printf("| %-50s: %10.2f%% %54s|\n", "Diskon channel pembayaran " + pembayaran.getClass().getSimpleName(), pembayaran.getDiskon() * 100, "");

        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Biaya admin channel pembayaran " + pembayaran.getClass().getSimpleName(), mataUang.konversiDariIDR(pembayaran.getBiayaAdmin()), mataUangString, "");

        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Total pajak pesanan", totalPajak, mataUangString, "");

        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Total tagihan pesanan sebelum perhitungan", totalTagihanSebelumPerhitungan, mataUangString, "");
            
        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Total tagihan pesanan setelah perhitungan", totalTagihanSetelahPerhitungan, mataUangString, "");

        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %39s%s%38s |\n", "","Terima kasih dan silakan datang kembali.", "");
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        
    }

    public static void displayMenu() {
        System.out.println("|-------|------------------------------------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s |\n", "Kode", "Menu Minuman", "Harga (Rp)");
        System.out.println("|-------|------------------------------------------|------------|");
        for (Minuman minuman : Minuman.getDaftarMinuman()) {
            minuman.display();
        }
        System.out.println("|-------|------------------------------------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s |\n", "Kode", "Menu Makanan", "Harga (Rp)");
        System.out.println("|-------|------------------------------------------|------------|");
        for (Makanan makanan : Makanan.getDaftarMakanan()) {
            makanan.display();
        }
        System.out.println("|-------|------------------------------------------|------------|");
    }

    public static void displayPesanan(PesananMakanan pesananMakanan, PesananMinuman pesananMinuman) {
        List<ItemPesanan> rincianMakanan = pesananMakanan.getRincianPesanan();
        List<ItemPesanan> rincianMinuman = pesananMinuman.getRincianPesanan();

        rincianMakanan.sort(Comparator.comparingInt(ItemPesanan::getHarga));
        rincianMinuman.sort(Comparator.comparingInt(ItemPesanan::getHarga));

        System.out.println("|-------|------------------------------------------|------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s | %-10s |\n", "Kode", "Makanan", "Harga", "Kuantitas");
        System.out.println("|-------|------------------------------------------|------------|------------|");

        if (rincianMakanan.isEmpty()) {
            System.out.println("|                          Tidak ada pesanan makanan.                        |");
        } else {
            for (ItemPesanan item : rincianMakanan) {
                System.out.printf("| %-5s | %-40s | %-10d | %-10d |\n",
                    item.getKode(), 
                    item.getNama(), 
                    item.getHarga(), 
                    item.getQty()
                );
            }

            System.out.println("|-------|------------------------------------------|------------|------------|");
        }

        System.out.println("|-------|------------------------------------------|------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s | %-10s |\n", "Kode", "Minuman", "Harga", "Kuantitas");
        System.out.println("|-------|------------------------------------------|------------|------------|");

        if (rincianMinuman.isEmpty()) {
            System.out.println("|                          Tidak ada pesanan minuman.                        |");
        } else {
            for (ItemPesanan item : rincianMinuman) {
                System.out.printf("| %-5s | %-40s | %-10d | %-10d |\n",
                    item.getKode(), 
                    item.getNama(), 
                    item.getHarga(), 
                    item.getQty()
                );
            }

            System.out.println("|-------|------------------------------------------|------------|------------|");
        }
    }

}
