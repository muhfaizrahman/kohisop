package pemesanan;

public class ItemPesanan {
        private String kode;
        private int qty;
    
        public ItemPesanan(String kode, int qty) {
            this.kode = kode;
            this.qty = qty;
        }
    
        public String getKode() {
            return kode;
        }
    
        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

}
