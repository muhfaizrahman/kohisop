package pemesanan;

import menu.Minuman;
import menu.Menu;

public class PesananMinuman extends MenuPesanan {

    // Array nampung kuantitas pesanan item
    private static int[] isiPesanan = new int[Minuman.getDaftarMinuman().length];

    public PesananMinuman() {
        super(isiPesanan, 5, 3, "Minuman");
    }
    
    @Override
    public Menu[] getDaftarMenu() {
        return Minuman.getDaftarMinuman();
    }

    // Untuk ambil array int yang nampung "kuantitas" pesanan item
    public static int[] getPesanan() {
        return isiPesanan;
    }

    // Untuk mengecek apakah suatu kode terdapat di dalam daftar minuman yang tersedia
    public static boolean kodeAdaDiMinuman(String kode) {
        for (Minuman minuman : Minuman.getDaftarMinuman()) {
            if (minuman != null && minuman.getKode().equals(kode)) {
                return true;
            }
        }
        return false;
    }

}
