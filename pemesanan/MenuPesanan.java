package pemesanan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import menu.*;

public abstract class MenuPesanan {

    protected List<ItemPesanan> pesananList;
    protected int maxJenis;
    protected int maxKuantitas;
    protected String kategori;

    public MenuPesanan(List<ItemPesanan> pesananList, int maxJenis, int maxKuantitas, String kategori) {
        this.pesananList = pesananList;
        this.maxJenis = maxJenis;
        this.maxKuantitas = maxKuantitas;
        this.kategori = kategori;
    }

    public abstract List<? extends Menu> getDaftarMenu();

    public String getKategori() {
        return kategori;
    }

    public List<ItemPesanan> getRincianPesanan() {
        return pesananList;
    };

    public void pesan(String kode) {
        Scanner input = new Scanner(System.in);
        List<? extends Menu> daftarMenu = getDaftarMenu();

        // Cari objek Menu berdasarkan kode
        Menu target = null;
        for (Menu menu : daftarMenu) {
            if (menu.getKode().equalsIgnoreCase(kode)) {
                target = menu;
                break;
            }
        }
        if (target == null) {
            System.out.println("Error: Kode menu tidak valid.");
            return;
        }

        // Kalau item sudah ada di pesananList, kita ambil referensinya:
        ItemPesanan existing = null;
        for (ItemPesanan ip : pesananList) {
            if (ip.getKode().equalsIgnoreCase(kode)) {
                existing = ip;
                break;
            }
        }

        // Cek jumlah jenis item yang sudah dipesan
        if (existing == null && pesananList.size() >= maxJenis) {
            System.out.println("Jumlah maksimal jenis item kategori " + kategori + " adalah " + maxJenis);
            return;
        }

        System.out.println(
            "Ketik 'CC' untuk batal.\n" +
            "Ketik '0' atau 'S' untuk skip.\n" +
            "Enter = 1 porsi.\n" +
            "Masukkan jumlah porsi (max " + maxKuantitas + "): "
        );
        
        // Memasukkan jumlah porsi
        String qty = input.nextLine().trim().toUpperCase();

        // Kondisi ketika memasukkan jumlah porsi
        if (qty.equals("CC")) {
            System.out.println("Pesanan dibatalkan. Program berhenti.");
            System.exit(0);
        }
        if (qty.equals("") || qty.equals("1")) {
            qty = "1";
        }
        if (qty.equals("0") || qty.equals("S")) {
            System.out.println("Melewati pemesanan menu ini...");
            return;
        }

        // Parsing ke Integer
        int jumlah;
        try {
            jumlah = Integer.parseInt(qty);
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus angka.");
            return;
        }

        if (jumlah < 0 || jumlah > maxKuantitas) {
            System.out.println("Error: Kuantitas tidak valid.");
            return;
        }

        if (existing != null) {
            if (existing.getQty() + jumlah > maxKuantitas) {
                System.out.println("Maksimal jumlah porsi untuk " + kategori + " adalah " + maxKuantitas);
                return;
            }
            existing.setQty(existing.getQty() + jumlah);
            System.out.println(target.getNama() + " ditambahkan sebanyak " + jumlah + " porsi. (Total: " + existing.getQty() + ")");
        } else {
            pesananList.add(new ItemPesanan(target.getKode(), target.getNama(), target.getHarga(), jumlah));
            System.out.println(target.getNama() + " ditambahkan sebanyak " + jumlah + " porsi.");
        }
    }

}
