package pemesanan;

public class ItemPesanan {
        private String kode;
        private int jumlah;
    
        public ItemPesanan(String kode, int jumlah) {
            this.kode = kode;
            this.jumlah = jumlah;
        }
    
        public String getKode() {
            return kode;
        }
    
        public int getJumlah() {
            return jumlah;
        }
}
