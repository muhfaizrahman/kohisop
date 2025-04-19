package pemesanan;

import java.util.Scanner;
import menu.*;

public abstract class MenuPesanan {
    protected Scanner input = new Scanner(System.in);

    int[] itemPesanan;
    int maxJenis;
    int maxKuantitas;
    String kategori;
    
    public MenuPesanan(int[] itemPesanan, int maxJenis, int maxKuantitas, String kategori) {
        this.itemPesanan = itemPesanan;
        this.maxJenis = maxJenis;
        this.maxKuantitas = maxKuantitas;
        this.kategori = kategori;
    }
    
    public String getKategori() {
        return kategori;
    }

    public abstract Menu[] getDaftarMenu();

    public int[] getItemPesanan() {
        return itemPesanan;
    }
    
    public void pesan(String kode) {
        Menu[] daftarMenu = getDaftarMenu();

        // Pengecekan kode menu yang diinputkan
        int index = -1;
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && daftarMenu[i].getKode().equals(kode)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Error: Kode menu tidak valid");
            return;
        }

        // Validasi jumlah jenis menu yang dapat dipesan
        int jumlahJenisPesanan = 0;
        for (int i = 0; i < itemPesanan.length; i++) {
            if (itemPesanan[i] > 0) {
                jumlahJenisPesanan++;
            }
        }
        if (itemPesanan[index] == 0 && jumlahJenisPesanan >= maxJenis) {
            System.out.println("Jumlah maksimal pemesanan kategori " + kategori + " adalah " + maxJenis + " jenis item");
            return;
        }

        // User input jumlah porsi
        System.out.println("Ketik 'CC' untuk membatalkan pemesanan.\nKetik '0' atau 'S' untuk melewati pemesanan.\nTekan enter untuk porsi = 1.\nMasukkan jumlah porsi yang diinginkan (max " + maxKuantitas +"): ");
        String inputJumlah = input.nextLine().trim().toUpperCase();

        // Case user "CC"
        if (inputJumlah.equals("CC")) {
            System.out.println("Pesanan dibatalkan oleh user. Program berhenti.");
            System.exit(0);
        }

        // Case user isi kosong
        if (inputJumlah.equals("")) {
            inputJumlah = "1";
        }
        
        // Case user 0 / "S"
        if (inputJumlah.equals("0") || inputJumlah.equals("S")) {
            System.out.println("Melewati pemesanan menu ini...");
            return;
        }
        
        // Parse to Int
        int jumlah;
        try {
            jumlah = Integer.parseInt(inputJumlah);
        } catch (NumberFormatException e) {
            System.out.println("Error: Input tidak valid! Masukkan angka.");
            return;
        }
        
        // Validasi input jumlah
        if (jumlah < 0 || jumlah > maxKuantitas) {
            System.out.println("Error: Jumlah tidak valid");
            return;
        }
        
        // Jumlah maksimal porsi menu dalam 1 pemesanan
        if (itemPesanan[index] + jumlah > maxKuantitas) {
            System.out.println("Maksimal jumlah porsi " + kategori + " adalah " + maxKuantitas);
            return;
        } 

        // Penambahan menu ke dalam pesanan
        itemPesanan[index] += jumlah;
        System.out.println(daftarMenu[index].getNama() + " ditambahkan sebanyak " + jumlah + " porsi.");
    }

    public ItemPesanan[] getRincianPesanan() {
        Menu[] daftarMenu = getDaftarMenu();
        int jumlahItem = 0;

        for (int i = 0; i < itemPesanan.length; i++) {
            if (itemPesanan[i] > 0) {
                jumlahItem++;
            }
        }

        ItemPesanan[] hasil = new ItemPesanan[jumlahItem];
        int index = 0;
        for (int i = 0; i < itemPesanan.length; i++) {
            if (itemPesanan[i] > 0) {
                hasil[index] = new ItemPesanan(daftarMenu[i].getKode(), itemPesanan[i]);
                index++;
            }
        }

        return hasil;
    }
}