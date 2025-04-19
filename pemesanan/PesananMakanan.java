package pemesanan;

import menu.Makanan;
import menu.Menu;

public class PesananMakanan extends MenuPesanan {
    
    private static int[] isiPesanan = new int[Makanan.getDaftarMakanan().length];
    private static ItemPesanan[] pesananMakanan = new ItemPesanan[10];

    public static ItemPesanan[] getDetailPesanan() {
        return pesananMakanan;
    }

    public PesananMakanan() {
        super(isiPesanan, 5, 2, "Makanan");
    }

    @Override
    public Menu[] getDaftarMenu() {
        return Makanan.getDaftarMakanan();
    }

    public static int[] getPesanan() {
        return isiPesanan;
    }

    public static boolean kodeAdaDiMakanan(String kode) {
        for (Makanan makanan : Makanan.getDaftarMakanan()) {
            if (makanan != null && makanan.getKode().equals(kode)) {
                return true;
            }
        }
        return false;
    }


}