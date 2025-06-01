package menu;

import java.util.ArrayList;
import java.util.List;

public class Minuman extends Menu {

    // Data menu
    private static final List<String> kodeMinuman = List.of("A1","A2","E1","E2","E3","E4","E5","B1","B2","B3");
    private static final List<String> namaMinuman = List.of(
        "Caffe Latte", "Cappuccino", "Caffe Americano", 
        "Caffe Mocha", "Caramel Macchiato", "Asian Dolce Latte", 
        "Double Shots Iced Shaken Espresso", "Freshly Brewed Coffee", "Vanilla Sweet Cream Cold Brew", 
        "Cold Brew"
    );
    private static final List<Integer> hargaMinuman = List.of(46, 46, 37, 55, 59, 55, 50, 23, 50, 44);

    private static final List<Minuman> daftarMinuman = new ArrayList<>();
    
    // Konstruktor
    Minuman(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    // Getter untuk daftar minuman
    public static List<Minuman> getDaftarMinuman() {
        return daftarMinuman;
    }

    // Inisialisasi minuman dari daftar
    public static void minumanInitialization() {
        for (int i = 0; i < kodeMinuman.size(); i++) {
            daftarMinuman.add(new Minuman(kodeMinuman.get(i), namaMinuman.get(i), hargaMinuman.get(i)));
        }
    }

    public static Minuman getMinumanByKode(String kode) {
        for (Minuman minuman : Minuman.getDaftarMinuman()) {
            if (minuman != null && minuman.getKode().equals(kode)) {
                return minuman;
            }
        }
        return null; // kalau gak ketemu
    }
    
}
