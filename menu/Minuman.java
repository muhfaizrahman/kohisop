package menu;

public class Minuman extends Menu {

    // Data menu
    private static String[] kodeMinuman = {"A1","A2","E1","E2","E3","E4","E5","B1","B2","B3"};
    private static String[] menuMinuman = {"Caffe Latte", "Cappuccino", "Caffe Americano", "Caffe Mocha", "Caramel Macchiato", "Asian Dolce Latte", "Double Shots Iced Shaken Espresso", "Freshly Brewed Coffee", "Vanilla Sweet Cream Cold Brew", "Cold Brew"};
    private static int[] hargaMinuman = {46, 46, 37, 55, 59, 55, 50, 23, 50, 44};

    // Array buat nampung banyaknya minuman
    private static Minuman[] daftarMinuman = new Minuman[kodeMinuman.length];
    
    Minuman(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }
    
    @Override
    public void display() {
        System.out.printf("| %-5s | %-40s | %-10d |\n", kode, nama, harga);
    }
    
    // Method buat ambil array banyaknya minuman dalam daftar menu
    public static Minuman[] getDaftarMinuman() {
        return daftarMinuman;
    }

    public static void minumanInitialization() {
        for (int i = 0; i < kodeMinuman.length; i++) {
            daftarMinuman[i] = new Minuman(kodeMinuman[i], menuMinuman[i], hargaMinuman[i]);
        }
    }
    
}
