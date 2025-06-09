package mata_uang;

public abstract class MataUang {
    private static MataUang mataUang;

    public static void setMataUang(MataUang mataUang) {
        MataUang.mataUang = mataUang;
    }

    public static MataUang getMataUang() {
        return mataUang;
    }

    public abstract double konversiDariIDR(double jumlah);

}