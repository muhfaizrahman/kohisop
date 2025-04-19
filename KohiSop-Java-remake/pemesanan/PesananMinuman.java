package pemesanan;

import menu.Minuman;
import menu.Menu;

public class PesananMinuman extends MenuPesanan {

    private static int[] isiPesanan = new int[Minuman.getDaftarMinuman().length];
    private static ItemPesanan[] pesananMinuman = new ItemPesanan[10];

    public static ItemPesanan[] getDetailPesanan() {
        return pesananMinuman;
    }

    public PesananMinuman() {
        super(isiPesanan, 5, 3, "Minuman");
    }

    @Override
    public Menu[] getDaftarMenu() {
        return Minuman.getDaftarMinuman();
    }

    public static int[] getPesanan() {
        return isiPesanan;
    }

    public static boolean kodeAdaDiMinuman(String kode) {
        for (Minuman minuman : Minuman.getDaftarMinuman()) {
            if (minuman != null && minuman.getKode().equals(kode)) {
                return true;
            }
        }
        return false;
    }


}
