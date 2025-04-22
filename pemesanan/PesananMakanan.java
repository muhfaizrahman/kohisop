package pemesanan;

import menu.Makanan;
import menu.Menu;

public class PesananMakanan extends MenuPesanan {
    
    // Array nampung kuantitas pesanan item
    private static int[] isiPesanan = new int[Makanan.getDaftarMakanan().length];
    
    public PesananMakanan() {
        super(isiPesanan, 5, 2, "Makanan");
    }

    @Override
    public Menu[] getDaftarMenu() {
        return Makanan.getDaftarMakanan();
    }
    
    // Untuk ambil array int yang nampung "kuantitas" pesanan item
    public static int[] getQTYPesanan() {
        return isiPesanan;
    }

    // Untuk mengecek apakah suatu kode terdapat di dalam daftar minuman yang tersedia
    public static boolean kodeAdaDiMakanan(String kode) {
        for (Makanan makanan : Makanan.getDaftarMakanan()) {
            if (makanan != null && makanan.getKode().equals(kode)) {
                return true;
            }
        }
        return false;
    }

}