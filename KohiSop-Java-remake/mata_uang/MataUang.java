package mata_uang;

public class MataUang {
    private static IMataUang mataUangDipilih;

    public static void setMataUang(IMataUang mataUang) {
        mataUangDipilih = mataUang;
    }

    public static IMataUang getMataUang() {
        return mataUangDipilih;
    }
}