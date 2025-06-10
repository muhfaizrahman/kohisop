import java.util.*;

import channel_pembayaran.EMoney;
import channel_pembayaran.Pembayaran;
import channel_pembayaran.QRIS;
import channel_pembayaran.Tunai;
import display_util.Display;
import mata_uang.EUR;
import mata_uang.JPY;
import mata_uang.MYR;
import mata_uang.MataUang;
import mata_uang.USD;
import mata_uang.IDR;
import membership.Member;
import membership.MemberService;
import menu.*;
import pemesanan.*;
import tim_dapur.*;
import perhitungan.Perhitungan;

public class KohiSopApp {
    private static TimDapur timDapur = new TimDapur();
    private static Member memberAktif = null;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jumlahPelanggan = 0;

        // Inisialisasi menu makanan dan minuman
        Makanan.makananInitialization();
        Minuman.minumanInitialization();

        while (jumlahPelanggan < 3) {
            // reset order untuk customer baru
            PesananMakanan pesananMakanan = new PesananMakanan();
            PesananMinuman pesananMinuman = new PesananMinuman();
            
            System.out.println("Selamat datang di KohiSop! Silakan input nama anda: ");
            String namaPelanggan = input.nextLine();

            // Cek apakah customer merupakan member
            memberAktif = MemberService.cariMemberByNama(namaPelanggan);
            if (memberAktif == null) {
                // Membuat temporary member untuk customer pertama kali
                memberAktif = new Member("TEMP", namaPelanggan);
            }
            
            System.out.println("Poin member saat ini: " + memberAktif.getPoin());
            
            // Set member state sekarang (current)
            Perhitungan.setMemberAktif(memberAktif);

            jumlahPelanggan++;

            Display.displayMenu();

            boolean lanjutPesanan = true;

            // Memilih menu makanan / minuman
            while (lanjutPesanan) {
                System.out.println("Ketik \"YES\" untuk menyelesaikan pesanan. Ketik \"CC\" untuk membatalkan pesanan.");
                System.out.print("Masukkan kode pemesanan: ");
                String kode = input.next().toUpperCase();
                
                if (kode.equals("CC")) {
                    System.exit(0);
                }
                
                if (kode.equals("YES")) {
                    break;
                }
                
                Menu menu = Menu.getMenuByKode(kode);
                if (menu != null) {
                    if (menu instanceof Makanan) {
                        pesananMakanan.pesan(kode);
                        timDapur.tambahkanMakanan((Makanan) menu, memberAktif);
                    } else if (menu instanceof Minuman) {
                        pesananMinuman.pesan(kode);
                        timDapur.tambahkanMinuman((Minuman) menu, memberAktif);
                    }
                } else {
                    System.out.println("Error: Kode tidak ditemukan. Coba lagi.");
                }

                Display.displayPesanan(pesananMakanan, pesananMinuman);
            }

            // Memilih mata uang
            boolean valid = false;
            while (!valid) {
                System.out.println("Pilih mata uang pembayaran: \nIDR\nUSD\nEUR\nMYR\nJPY");
                String pilih = input.next().toUpperCase();
                
                switch (pilih) {
                    case "IDR": 
                        MataUang.setMataUang(new IDR()); 
                        valid = true;
                        break;
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
                        continue;
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
                        pembayaran.bayar(input, pesananMinuman, pesananMakanan, memberAktif);
                        break;
                    case "QRIS":
                        pembayaran = new QRIS();
                        pembayaran.bayar(input, pesananMinuman, pesananMakanan, memberAktif);
                        break;
                    case "EMONEY":
                        pembayaran = new EMoney();
                        pembayaran.bayar(input, pesananMinuman, pesananMakanan, memberAktif);
                        break;
                    default:
                        System.out.println("Error: Metode pembayaran tidak ditemukan. Coba lagi.");
                        continue;
                }
            }

            Display.displayKuitansi(pembayaran, pesananMinuman, pesananMakanan, memberAktif);
            
            // Pause sebelum lanjut ke pelanggan berikutnya
            System.out.println("Melanjutkan ke pelanggan berikutnya...");
            input.nextLine();
        }

        // Display proses tim dapur
        timDapur.prosesPesanan();

        input.close();
    }
}