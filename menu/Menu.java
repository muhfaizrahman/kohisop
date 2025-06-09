package menu;

public abstract class Menu {
    protected String kode;
    protected String nama;
    protected int harga;

    Menu(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    public void display() {
        System.out.printf("| %-5s | %-40s | %-10d |\n", kode, nama, harga);
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }
    
    public static Menu getMenuByKode(String kode) {
        Makanan mkn = Makanan.getMakananByKode(kode);
        if (mkn != null) return mkn;
    
        Minuman mnm = Minuman.getMinumanByKode(kode);
        if (mnm != null) return mnm; 

        return null;
    }
    
}