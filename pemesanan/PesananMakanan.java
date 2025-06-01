package pemesanan;

import menu.Makanan;

import java.util.ArrayList;
import java.util.List;

public class PesananMakanan extends MenuPesanan {

    private static List<ItemPesanan> jumlahMakanan = new ArrayList<>();
    
    public PesananMakanan() {
        super(jumlahMakanan, 5, 2, "Makanan");
    }

    @Override
    public List<Makanan> getDaftarMenu() {
        return Makanan.getDaftarMakanan();
    }

    public static List<ItemPesanan> getJumlahPesanan() {
        return jumlahMakanan;
    }

}