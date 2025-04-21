package perhitungan;

import menu.Menu;
import pemesanan.MenuPesanan;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;    

public class Perhitungan {

    // Kode Refactor
    public static double hitungTotalPerMenu(MenuPesanan pesanan, String kode) {
        Menu[] daftarMenu = pesanan.getDaftarMenu();
        int[] itemPesanan = pesanan.getItemPesanan();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && daftarMenu[i].getKode().equals(kode)) {
                double harga = daftarMenu[i].getHarga();
                int jumlah = itemPesanan[i];
                return harga * jumlah;
            }
        }
        return 0;
    }
    
    public static double hitungTagihanKategori(MenuPesanan pesanan) {
        double total = 0;
        Menu[] daftarMenu = pesanan.getDaftarMenu();
        int[] itemPesanan = pesanan.getItemPesanan();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && itemPesanan[i] > 0) {
                total += daftarMenu[i].getHarga() * itemPesanan[i];
            }
        }
        return total;
    }
    
    public static double hitungTagihanTotal(PesananMinuman minuman, PesananMakanan makanan) {
        return hitungTagihanKategori(minuman) + hitungTagihanKategori(makanan);
    }
    
    // Hitung Pajak
    public static double getPajak(MenuPesanan pesanan, String kode) {
        Menu[] daftarMenu = pesanan.getDaftarMenu();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && daftarMenu[i].getKode().equals(kode)) {
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

    public static double getPajakPerJenis(MenuPesanan pesanan) {
        double total = 0;
        Menu[] daftarMenu = pesanan.getDaftarMenu();
        int[] itemPesanan = pesanan.getItemPesanan();

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && itemPesanan[i] > 0) {
                double pajakPerItem = getPajak(pesanan, daftarMenu[i].getKode());
                total = pajakPerItem * hitungTotalPerMenu(pesanan, daftarMenu[i].getKode());
            }
        }
        return total;
    }
    
    public static double getPajakKategori(MenuPesanan pesanan) {
        return getPajakPerJenis(pesanan);
    }
    
    public static double getPajakTagihan(PesananMinuman minuman, PesananMakanan makanan) {
        return getPajakKategori(minuman) + getPajakKategori(makanan);
    }

}