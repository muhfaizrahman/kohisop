package pemesanan;

import menu.Minuman;

import java.util.ArrayList;
import java.util.List;

public class PesananMinuman extends MenuPesanan {

    private static List<ItemPesanan> jumlahMinuman = new ArrayList<>();
    
    public PesananMinuman() {
        super(jumlahMinuman, 5, 3, "Minuman");
    }
    
    @Override
    public List<Minuman> getDaftarMenu() {
        return Minuman.getDaftarMinuman();
    }

    public static List<ItemPesanan> getJumlahPesanan() {
        return jumlahMinuman;
    }

}