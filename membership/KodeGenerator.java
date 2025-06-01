package membership;

import java.util.Random;

public class KodeGenerator {
    private static final String CHAR_POOL = "ABCDEF0123456789";
    private static final int PANJANG_KODE = 6;

    public static String generateKode() {
        StringBuilder kode = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < PANJANG_KODE; i++) {
            int index = rand.nextInt(CHAR_POOL.length());
            kode.append(CHAR_POOL.charAt(index));
        }
        return kode.toString();
    }
}
