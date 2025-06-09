package pemesanan;

import menu.Minuman;

import java.util.ArrayList;
import java.util.List;

public class PesananMinuman extends MenuPesanan {
    
    public PesananMinuman() {
        super(new ArrayList<>(), 5, 3, "Minuman");
    }

    @Override
    public List<Minuman> getDaftarMenu() {
        return Minuman.getDaftarMinuman();
    }
        
}