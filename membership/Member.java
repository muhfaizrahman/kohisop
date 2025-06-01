package membership;

public class Member {
    private String kode;
    private String nama;
    private int poin;
    
    public Member(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
        this.poin = 0;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getPoin() {
        return poin;
    }

    public void tambahPoin(int tambahan) {
        this.poin += tambahan;
    }
}
