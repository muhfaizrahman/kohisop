package perhitungan;

import menu.Menu;
import pemesanan.MenuPesanan;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;    

public class Perhitungan {

    // Mekanisme perhitungan menu
    // Method untuk hitung (harga * jumlah) tiap item
    public static double hitungTotalPerMenu(MenuPesanan pesanan, String kode) {
        Menu[] daftarMenu = pesanan.getDaftarMenu();
        int[] QTYItem = pesanan.getQTYItemPesanan();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].getKode().equals(kode)) {
                double harga = daftarMenu[i].getHarga();
                int jumlah = QTYItem[i];
                
                return harga * jumlah;
            }
        }
        return 0;
    }
    
    // Method untuk hitung total tagihan tiap kategori (di luar pajak)
    public static double hitungTagihanKategori(MenuPesanan pesanan) {
        double total = 0;
        Menu[] daftarMenu = pesanan.getDaftarMenu();
        int[] QTYItem = pesanan.getQTYItemPesanan();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (QTYItem[i] > 0) {
                total += daftarMenu[i].getHarga() * QTYItem[i];
            }
        }
        return total;
    }
    
    // Method untuk hitung total tagihan keseluruhan (di luar pajak)
    public static double hitungTagihanTotal(PesananMinuman minuman, PesananMakanan makanan) {
        return hitungTagihanKategori(minuman) + hitungTagihanKategori(makanan);
    }
    

    // Mekanisme Perhitungan Pajak
    // Method untuk mendapatkan persentase pajak suatu item
    public static double getPersentasePajak(MenuPesanan pesanan, String kode) {
        Menu[] daftarMenu = pesanan.getDaftarMenu();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].getKode().equals(kode)) {
                double harga = daftarMenu[i].getHarga();

                if (pesanan instanceof PesananMinuman) {
                    if (harga < 50) return 0;
                    else if (harga >= 50 && harga <= 55) return 0.08; 
                    else return 0.11;
                } else if (pesanan instanceof PesananMakanan) {
                    if (harga < 50) return 0.11;
                    else if (harga > 55) return 0.08;
                    else return 0;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    // Method untuk kalkulasi total harga untuk suatu item * persentase pajak item tersebut
    public static double getBesarPajakIDR(MenuPesanan pesanan, String kode) {
        Menu[] daftarMenu = pesanan.getDaftarMenu();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].getKode().equals(kode)) {
                double persentasePajak = getPersentasePajak(pesanan, kode);
                double hargaTotalPerMenu = hitungTotalPerMenu(pesanan, kode); 

                return persentasePajak * hargaTotalPerMenu;
            }
        }
        return 0;
    }
    
    // Method untuk menghitung total pajak IDR yang terdapat dalam suatu kategori
    public static double getPajakKategori(MenuPesanan pesanan) {
        double total = 0;
        Menu[] daftarMenu = pesanan.getDaftarMenu();
        int[] QTYItem = pesanan.getQTYItemPesanan();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (QTYItem[i] > 0) {
                total += getBesarPajakIDR(pesanan, daftarMenu[i].getKode());
            }
        }
        return total;
    }
    
    // Method untuk hitung total pajak tagihan pelanggan kohisop
    public static double getPajakTagihan(PesananMinuman minuman, PesananMakanan makanan) {
        return getPajakKategori(minuman) + getPajakKategori(makanan);
    }

}