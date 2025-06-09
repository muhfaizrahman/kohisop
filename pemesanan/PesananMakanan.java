package pemesanan;

import java.util.ArrayList;
import java.util.List;

import menu.Makanan;

public class PesananMakanan extends MenuPesanan {

    public PesananMakanan() {
        super(new ArrayList<>(), 5, 2, "Makanan");
    }

    @Override
    public List<Makanan> getDaftarMenu() {
        return Makanan.getDaftarMakanan();
    }
}