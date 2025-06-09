package tim_dapur;

import java.util.*;
import menu.Makanan;
import menu.Minuman;
import membership.Member;

public class TimDapur {
    private PriorityQueue<Makanan> antrianMakanan;
    private Stack<Minuman> antrianMinuman;
    private Map<Makanan, Member> makananPelanggan;
    private Map<Minuman, Member> minumanPelanggan;

    public TimDapur() {
        // Priority queue untuk Makanan - Sorting dari harga tertinggi
        antrianMakanan = new PriorityQueue<>((a, b) -> 
            Double.compare(b.getHarga(), a.getHarga()));
        // Stack untuk Minuman - LIFO
        antrianMinuman = new Stack<>();
        // Untuk menyimpan data pelanggan yang memesan
        makananPelanggan = new HashMap<>();
        minumanPelanggan = new HashMap<>();
    }

    public void tambahkanMakanan(Makanan makanan, Member pelanggan) {
        if (makanan != null && pelanggan != null) {
            antrianMakanan.add(makanan);
            makananPelanggan.put(makanan, pelanggan);
            System.out.println("Makanan " + makanan.getNama() + " ditambahkan ke antrian untuk " + pelanggan.getNama());
        }
    }

    public void tambahkanMinuman(Minuman minuman, Member pelanggan) {
        if (minuman != null && pelanggan != null) {
            antrianMinuman.push(minuman);
            minumanPelanggan.put(minuman, pelanggan);
            System.out.println("Minuman " + minuman.getNama() + " ditambahkan ke antrian untuk " + pelanggan.getNama());
        }
    }

    // Display pesanan yang diproses
    public void prosesPesanan() {
        System.out.println("\n=== Tim Dapur Memproses Pesanan ===");
        
        System.out.println("\nTim Makanan memproses pesanan (dari harga tertinggi):");
        if (antrianMakanan.isEmpty()) {
            System.out.println("Tidak ada pesanan makanan yang perlu diproses.");
        } else {
            while (!antrianMakanan.isEmpty()) {
                Makanan makanan = antrianMakanan.poll();
                Member pelanggan = makananPelanggan.get(makanan);
                System.out.printf("Memproses: %s - %s (Harga: %d) untuk %s\n", 
                    makanan.getKode(), 
                    makanan.getNama(), 
                    makanan.getHarga(),
                    pelanggan.getNama());
            }
        }
        
        System.out.println("\nTim Minuman memproses pesanan (LIFO):");
        if (antrianMinuman.isEmpty()) {
            System.out.println("Tidak ada pesanan minuman yang perlu diproses.");
        } else {
            while (!antrianMinuman.isEmpty()) {
                Minuman minuman = antrianMinuman.pop();
                Member pelanggan = minumanPelanggan.get(minuman);
                System.out.printf("Memproses: %s - %s (Harga: %d) untuk %s\n", 
                    minuman.getKode(), 
                    minuman.getNama(), 
                    minuman.getHarga(),
                    pelanggan.getNama());
            }
        }
    }
}