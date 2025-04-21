package menu;

public class Makanan extends Menu {
    
    // Data Menu
    private static String[] kodeMakanan = {"M1","M2","M3","M4","S1","S2","S3","S4",};
    private static String[] menuMakanan = {"Petemania Pizza", "Mie Rebus Super Mario", "Ayam Bakar Goreng Rebus Spesial", "Soto Kambing Iga Guling", "Singkong Bakar A La Carte", "Ubi Cilembu Bakar Arang", "Tempe Mendoan", "Tahu Bakso Extra Telur", };
    private static int[] hargaMakanan = {112, 35, 72, 124, 37, 58, 18, 28};
    
    // Array buat nampung banyaknya makanan
    private static Makanan[] daftarMakanan = new Makanan[kodeMakanan.length];

    Makanan(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }
    
    @Override
    public void display() {
        System.out.printf("| %-5s | %-40s | %-10d |\n", kode, nama, harga);
    }
    
    // Method buat ambil array banyaknya minuman dalam daftar menu
    public static Makanan[] getDaftarMakanan() {
        return daftarMakanan;
    }
    
    public static void makananInitialization() {
        for (int i = 0; i < kodeMakanan.length; i++) {
            daftarMakanan[i] = new Makanan(kodeMakanan[i], menuMakanan[i], hargaMakanan[i]);
        }
    }

}
