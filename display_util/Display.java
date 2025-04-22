package display_util;

import channel_pembayaran.Pembayaran;
import mata_uang.IMataUang;
import mata_uang.MataUang;
import menu.Makanan;
import menu.Menu;
import menu.Minuman;
import pemesanan.PesananMakanan;
import pemesanan.PesananMinuman;
import perhitungan.Perhitungan;
import pemesanan.ItemPesanan;
import pemesanan.MenuPesanan;

public class Display {

    public static void displayKategori(MenuPesanan pesanan) {
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %50s%s%51s |\n", "", "Kategori " + pesanan.getKategori(), "");
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-40s | %-10s | %-10s | %-12s | %-10s | %-12s |\n",
        "Kode", "Nama Menu", "Harga", "Jumlah", "Total Harga", "Pajak (%)", "Pajak (IDR)");
        System.out.printf("|%s|%s|%s|%s|%s|%s|%s|\n",
        "-------", "------------------------------------------", "------------", "------------", "--------------", "------------", "--------------");

        if (pesanan instanceof PesananMinuman) {
            ItemPesanan[] rincian = pesanan.getRincianPesanan();
            Menu[] daftar = pesanan.getDaftarMenu();

            for (ItemPesanan item : rincian) {
                String kode = item.getKode();
                int[] jumlahlItem = PesananMinuman.getQTYPesanan();

                for (int i = 0; i < daftar.length; i++) {
                    if (daftar[i].getKode().equals(kode)) {
                        System.out.printf("| %-5s | %-40s | %-10d | %-10d | %-12.2f | %-9.2f%% | %-12.2f |\n",
                            daftar[i].getKode(),
                            daftar[i].getNama(),
                            daftar[i].getHarga(),
                            jumlahlItem[i],
                            Perhitungan.hitungTotalPerMenu(pesanan, kode),
                            Perhitungan.getPersentasePajak(pesanan, kode) * 100,
                            Perhitungan.getBesarPajakIDR(pesanan, kode)
                        );
                    }
                }
            }   
        } else if (pesanan instanceof PesananMakanan) {
            ItemPesanan[] rincian =  pesanan.getRincianPesanan();
            Menu[] daftar =  pesanan.getDaftarMenu();

            for (ItemPesanan item : rincian) {
                String kode = item.getKode();
                int[] jumlahlItem = PesananMakanan.getQTYPesanan();
                
                for (int i = 0; i < daftar.length; i++) {
                    if (daftar[i].getKode().equals(kode)) {
                        System.out.printf("| %-5s | %-40s | %-10d | %-10d | %-12.2f | %-9.2f%% | %-12.2f |\n",
                            daftar[i].getKode(),
                            daftar[i].getNama(),
                            daftar[i].getHarga(),
                            jumlahlItem[i],
                            Perhitungan.hitungTotalPerMenu(pesanan, kode),
                            Perhitungan.getPersentasePajak(pesanan, kode) * 100,
                            Perhitungan.getBesarPajakIDR(pesanan, kode)
                        );
                    }
                }
            }   
        }
        System.out.printf("|%s|%s|%s|%s|%s|%s|%s|\n", "-------", "------------------------------------------", "------------", "------------", "--------------", "------------", "--------------");
        System.out.printf("| Total harga %s di luar pajak: %-83.2f|\n", pesanan.getKategori(), Perhitungan.hitungTagihanKategori(pesanan));
        System.out.printf("| Total harga %s termasuk pajak: %-82.2f|\n", pesanan.getKategori(), Perhitungan.hitungTagihanKategori(pesanan) + Perhitungan.getPajakKategori(pesanan));
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayKuitansi(Pembayaran pembayaran, PesananMinuman pesananMinuman, PesananMakanan pesananMakanan) {
        displayKategori(pesananMakanan);
        displayKategori(pesananMinuman);

        IMataUang mataUang = MataUang.getMataUang();
        String mataUangString = mataUang.getClass().getSimpleName();
        double totalPajakIDR = Perhitungan.getPajakTagihan(pesananMinuman, pesananMakanan);
        double totalPajak = mataUang.konversiDariIDR(totalPajakIDR);

        double hargaSebelumPerhitunganIDR = Perhitungan.hitungTagihanTotal(pesananMinuman, pesananMakanan);
        double totalTagihanSebelumPerhitungan = mataUang.konversiDariIDR(hargaSebelumPerhitunganIDR);
        
        double totalHargaIDR = (hargaSebelumPerhitunganIDR * (1 - pembayaran.getDiskon())) + pembayaran.getBiayaAdmin() + totalPajakIDR;
        double totalTagihanSetelahPerhitungan = mataUang.konversiDariIDR(totalHargaIDR);

        // Total tagihan dengan mata uang pembayaran
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        
        System.out.printf("| %-50s: %10.2f%% %54s|\n", "Diskon channel pembayaran " + pembayaran.getClass().getSimpleName(), pembayaran.getDiskon() * 100, "");

        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Biaya admin channel pembayaran " + pembayaran.getClass().getSimpleName(), mataUang.konversiDariIDR(pembayaran.getBiayaAdmin()), mataUangString, "");

        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Total pajak pesanan", totalPajak, mataUangString, "");

        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Total tagihan pesanan sebelum perhitungan", totalTagihanSebelumPerhitungan, mataUangString, "");
            
        System.out.printf("| %-50s: %10.2f %-10s %44s|\n", "Total tagihan pesanan setelah perhitungan", totalTagihanSetelahPerhitungan, mataUangString, "");

        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %39s%s%38s |\n", "","Terima kasih dan silakan datang kembali.", "");
        System.out.printf("|%-117s|\n", "-----------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayMenu() {
        
        System.out.println("|-------|------------------------------------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s |\n", "Kode", "Menu Minuman", "Harga (Rp)");
        System.out.println("|-------|------------------------------------------|------------|");
        for (Minuman minuman : Minuman.getDaftarMinuman()) {
            minuman.display();
        }
        System.out.println("|-------|------------------------------------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s |\n", "Kode", "Menu Makanan", "Harga (Rp)");
        System.out.println("|-------|------------------------------------------|------------|");
        for (Makanan makanan : Makanan.getDaftarMakanan()) {
            makanan.display();
        }
        System.out.println("|-------|------------------------------------------|------------|");
    }

    public static void displayPesanan() {
        
        int[] makananPesanan = PesananMakanan.getQTYPesanan();
        Makanan[] daftarMakanan = Makanan.getDaftarMakanan();

        boolean adaMakanan = false;
        System.out.println("|-------|------------------------------------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s |\n", "Kode", "Makanan", "Kuantitas");
        System.out.println("|-------|------------------------------------------|------------|");

        for (int i = 0; i < makananPesanan.length; i++) {
            if (makananPesanan[i] > 0) {
                adaMakanan = true;
                System.out.printf("| %-5s | %-40s | %-10d |\n",
                    daftarMakanan[i].getKode(),
                    daftarMakanan[i].getNama(),
                    makananPesanan[i]
                );
            }
        }

        if (!adaMakanan) {
            System.out.println("|                   Tidak ada pesanan makanan.                  |");
        }

        System.out.println("|-------|------------------------------------------|------------|");


        int[] minumanPesanan = PesananMinuman.getQTYPesanan();
        Minuman[] daftarMinuman = Minuman.getDaftarMinuman();

        boolean adaMinuman = false;
        System.out.println("|-------|------------------------------------------|------------|");
        System.out.printf("| %-5s | %-40s | %-10s |\n", "Kode", "Minuman", "Kuantitas");
        System.out.println("|-------|------------------------------------------|------------|");

        for (int i = 0; i < daftarMinuman.length; i++) {
            if (minumanPesanan[i] > 0) {
                adaMinuman = true;
                System.out.printf("| %-5s | %-40s | %-10d |\n",
                    daftarMinuman[i].getKode(),
                    daftarMinuman[i].getNama(),
                    minumanPesanan[i]
                );
            }
        }

        if (!adaMinuman) {
            System.out.println("|                   Tidak ada pesanan minuman.                  |");
        }

        System.out.println("|-------|------------------------------------------|------------|");
    }
}
