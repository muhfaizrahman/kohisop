package pemesanan;

import menu.Makanan;
import java.util.List;

public class PesananMakanan extends MenuPesanan {
    
    public PesananMakanan() {
        super(5, 2, "Makanan");
    }

    @Override
    public List<Makanan> getDaftarMenu() {
        return Makanan.getDaftarMakanan();
    }

}