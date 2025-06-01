package menu;

import java.util.ArrayList;
import java.util.List;

public class Makanan extends Menu {

    // Data Menu
    private static final List<String> kodeMakanan = List.of("M1","M2","M3","M4","S1","S2","S3","S4");
    private static final List<String> namaMakanan = List.of(
        "Petemania Pizza", "Mie Rebus Super Mario", "Ayam Bakar Goreng Rebus Spesial",
        "Soto Kambing Iga Guling", "Singkong Bakar A La Carte", "Ubi Cilembu Bakar Arang",
        "Tempe Mendoan", "Tahu Bakso Extra Telur"
    );
    private static final List<Integer> hargaMakanan = List.of(112, 35, 72, 124, 37, 58, 18, 28);

    private static final List<Makanan> daftarMakanan = new ArrayList<>();

    // Konstruktor
    Makanan(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    // Getter untuk daftar makanan
    public static List<Makanan> getDaftarMakanan() {
        return daftarMakanan;
    }

    // Inisialisasi makanan dari daftar statik
    public static void makananInitialization() {
        for (int i = 0; i < kodeMakanan.size(); i++) {
            daftarMakanan.add(new Makanan(kodeMakanan.get(i), namaMakanan.get(i), hargaMakanan.get(i)));
        }
    }

    public static Makanan getMakananByKode(String kode) {
        for (Makanan makanan : Makanan.getDaftarMakanan()) {
            if (makanan != null && makanan.getKode().equals(kode)) {
                return makanan;
            }
        }
        return null; // kalau gak ketemu
    }
}
