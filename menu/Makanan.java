package menu;

import java.util.ArrayList;
import java.util.List;

public class Makanan extends Menu {

    // Konstruktor
    Makanan(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    private static List<Makanan> daftarMakanan = new ArrayList<>();

    // Inisialisasi makanan dari daftar statik
    public static void makananInitialization() {
        daftarMakanan.add(new Makanan("M1", "Petemania Pizza", 112));
        daftarMakanan.add(new Makanan("M2", "Mie Rebus Super Mario", 35));
        daftarMakanan.add(new Makanan("M3", "Ayam Bakar Goreng Rebus Spesial", 72));
        daftarMakanan.add(new Makanan("M4", "Soto Kambing Iga Guling", 124));
        daftarMakanan.add(new Makanan("S1", "Singkong Bakar A La Carte", 37));
        daftarMakanan.add(new Makanan("S2", "Ubi Cilembu Bakar Arang", 58));
        daftarMakanan.add(new Makanan("S3", "Tempe Mendoan", 18));
        daftarMakanan.add(new Makanan("S4", "Tahu Bakso Extra Telur", 28));
    }

    // Getter untuk daftar makanan
    public static List<Makanan> getDaftarMakanan() {
        return daftarMakanan;
    }

    public static Makanan getMakananByKode(String kode) {
        for (Makanan makanan : Makanan.getDaftarMakanan()) {
            if (makanan != null && makanan.getKode().equals(kode)) {
                return makanan;
            }
        }
        return null;
    }
}
