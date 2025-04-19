import java.util.*;

import channel_pembayaran.EMoney;
import channel_pembayaran.Pembayaran;
import channel_pembayaran.QRIS;
import channel_pembayaran.Tunai;
import display_util.Display;
import mata_uang.EUR;
import mata_uang.IMataUang;
import mata_uang.JPY;
import mata_uang.MYR;
import mata_uang.MataUang;
import mata_uang.USD;
import menu.*;
import pemesanan.*;

public class KohiSopApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Inisialisasi menu makanan dan minuman
        Makanan.makananInitialization();
        Minuman.minumanInitialization();

        // Pesanan pesanan = new Pesanan();
        PesananMakanan pesananMakanan = new PesananMakanan();
        PesananMinuman pesananMinuman = new PesananMinuman();

        Display.displayMenu();
        
        // Memilih menu makanan / minuman
        while (true) {
            System.out.println("Ketik \"YES\" untuk menyeselaikan pesanan. Ketik \"CC\" untuk membatalkan pesanan.");
            System.out.println("Masukkan kode pemesanan: ");
            String kode = input.next().toUpperCase();
            
            // Case user "CC"
            if (kode.equals("CC")) {
                System.out.println("Pesanan dibatalkan oleh user. Program berhenti.");
                System.exit(0);
            }
            
            // Case user "yes"
            if (kode.equals("YES")) {
                break;
            }
            
            if (PesananMakanan.kodeAdaDiMakanan(kode)) {
                pesananMakanan.pesan(kode);
            } else if (PesananMinuman.kodeAdaDiMinuman(kode)) {
                pesananMinuman.pesan(kode);
            } else {
                System.out.println("Error: Kode tidak ditemukan. Coba lagi.");
            }
            Display.displayPesanan();
        }

        // Memilih mata uang
        boolean valid = false;
        while (!valid) {
            System.out.println("Pilih mata uang pembayaran: \nUSD\nEUR\nMYR\nJPY");
            String pilih = input.next().toUpperCase();
            
            switch (pilih) {
                case "USD": 
                    MataUang.setMataUang(new USD()); 
                    valid = true;
                    break;
                case "EUR": 
                    MataUang.setMataUang(new EUR()); 
                    valid = true;
                    break;
                case "MYR": 
                    MataUang.setMataUang(new MYR());
                    valid = true;
                    break;
                case "JPY": 
                    MataUang.setMataUang(new JPY()); 
                    valid = true;
                    break;
                default:
                    System.out.println("Error: Mata uang tidak ditemukan. Coba lagi.");
                break;
            }
        }

        // Memilih metode pembayaran
        Pembayaran pembayaran = null;

        while (pembayaran == null) {
            System.out.println("Pilih metode pembayaran: \nTunai\nQRIS\nEMoney");
            String pilih = input.next().toUpperCase();
    
            switch (pilih) {
                case "TUNAI":
                    pembayaran = new Tunai();
                    pembayaran.bayar(input, pesananMinuman, pesananMakanan);
                    break;
                case "QRIS":
                    pembayaran = new QRIS();
                    pembayaran.bayar(input, pesananMinuman, pesananMakanan);
                    break;
                case "EMONEY":
                    pembayaran = new EMoney();
                    pembayaran.bayar(input, pesananMinuman, pesananMakanan);
                    break;
                default:
                    System.out.println("Error: Metode pembayaran tidak ditemukan. Coba lagi.");
                    continue;
            }
            
        }

        Display.displayKuitansi(pembayaran, pesananMinuman, pesananMakanan);

        input.close();
    }
}