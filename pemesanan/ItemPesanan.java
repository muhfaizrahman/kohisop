package pemesanan;

public class ItemPesanan {
        private String kode;
        private String nama;
        private int harga;
        private int qty;
    
        public ItemPesanan(String kode, String nama, int harga, int qty) {
            this.kode = kode;
            this.nama = nama;
            this.harga = harga;
            this.qty = qty;
        }
    
        public String getKode() {
            return kode;
        }
        
        public String getNama() {
            return nama;
        }
        
        public int getHarga() {
            return harga;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

}
