package perhitungan;

import pemesanan.*;
import membership.Member;
import menu.*;

public class Perhitungan {
    private static Member memberAktif;

    public static void setMemberAktif(Member member) {
        memberAktif = member;
    }

    // Hitung total harga satu item pesanan (harga * kuantitas)
    public static double hitungTotalPerMenu(MenuPesanan pesanan, String kode) {
        for (ItemPesanan item : pesanan.getRincianPesanan()) {
            if (item.getKode().equals(kode)) {
                double harga = getHargaByKode(pesanan, kode);
                return harga * item.getQty();
            }
        }
        return 0;
    }

    // Hitung total tagihan satu kategori pesanan (di luar pajak)
    public static double hitungTagihanKategori(MenuPesanan pesanan) {
        double total = 0;
        for (ItemPesanan item : pesanan.getRincianPesanan()) {
            double harga = getHargaByKode(pesanan, item.getKode());
            total += harga * item.getQty();
        }
        return total;
    }

    // Hitung total tagihan keseluruhan dari makanan dan minuman
    public static double hitungTagihanTotal(PesananMinuman minuman, PesananMakanan makanan) {
        return hitungTagihanKategori(minuman) + hitungTagihanKategori(makanan);
    }

    // Dapatkan persentase pajak dari suatu item berdasarkan kategori dan harga
    public static double getPersentasePajak(MenuPesanan pesanan, String kode) {
        // Jika ada "A" di kode member, maka tidak ada pajak
        if (memberAktif != null && memberAktif.getKode().contains("A")) {
            return 0;
        }
        
        double harga = getHargaByKode(pesanan, kode);

        if (pesanan instanceof PesananMinuman) {
            if (harga < 50) return 0;
            else if (harga >= 50 && harga <= 55) return 0.08;
            else return 0.11;
        } else if (pesanan instanceof PesananMakanan) {
            if (harga < 50) return 0.11;
            else if (harga > 55) return 0.08;
            else return 0;
        }
        return 0;
    }

    // Hitung besar pajak IDR untuk satu item pesanan
    public static double getBesarPajakIDR(MenuPesanan pesanan, String kode) {
        double totalHarga = hitungTotalPerMenu(pesanan, kode);
        double pajak = getPersentasePajak(pesanan, kode);
        return totalHarga * pajak;
    }

    // Hitung total pajak dalam satu kategori pesanan
    public static double getPajakKategori(MenuPesanan pesanan) {
        double totalPajak = 0;
        for (ItemPesanan item : pesanan.getRincianPesanan()) {
            totalPajak += getBesarPajakIDR(pesanan, item.getKode());
        }
        return totalPajak;
    }

    // Hitung total pajak keseluruhan dari makanan dan minuman
    public static double getPajakTagihan(PesananMinuman minuman, PesananMakanan makanan) {
        return getPajakKategori(minuman) + getPajakKategori(makanan);
    }

    // Helper untuk mendapatkan harga menu berdasarkan kode dan kategori
    private static double getHargaByKode(MenuPesanan pesanan, String kode) {
        if (pesanan instanceof PesananMakanan) {
            Makanan m = Makanan.getMakananByKode(kode);
            return (m != null) ? m.getHarga() : 0;
        } else if (pesanan instanceof PesananMinuman) {
            Minuman m = Minuman.getMinumanByKode(kode);
            return (m != null) ? m.getHarga() : 0;
        }
        return 0;
    }
}
