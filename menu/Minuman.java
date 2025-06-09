package menu;

import java.util.ArrayList;
import java.util.List;

public class Minuman extends Menu {

    // Konstruktor
    Minuman(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    private static List<Minuman> daftarMinuman = new ArrayList<>();

    // Inisialisasi minuman dari daftar
    public static void minumanInitialization() {
        daftarMinuman.add(new Minuman("A1", "Caffe Latte", 46));
        daftarMinuman.add(new Minuman("A2", "Cappuccino", 46));
        daftarMinuman.add(new Minuman("E1", "Caffe Americano", 37));
        daftarMinuman.add(new Minuman("E2", "Caffe Mocha", 55));
        daftarMinuman.add(new Minuman("E3", "Caramel Macchiato", 59));
        daftarMinuman.add(new Minuman("E4", "Asian Dolce Latte", 55));
        daftarMinuman.add(new Minuman("E5", "Double Shots Iced Shaken Espresso", 50));
        daftarMinuman.add(new Minuman("B1", "Freshly Brewed Coffee", 23));
        daftarMinuman.add(new Minuman("B2", "Vanilla Sweet Cream Cold Brew", 50));
        daftarMinuman.add(new Minuman("B3", "Cold Brew", 44));
    }

    // Getter untuk daftar minuman
    public static List<Minuman> getDaftarMinuman() {
        return daftarMinuman;
    }

    public static Minuman getMinumanByKode(String kode) {
        for (Minuman minuman : Minuman.getDaftarMinuman()) {
            if (minuman != null && minuman.getKode().equals(kode)) {
                return minuman;
            }
        }
        return null;
    }
    
}