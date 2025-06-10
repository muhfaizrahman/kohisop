package membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberService {

    // Kode generator
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

    // Database member
    private static List<Member> databaseMember = new ArrayList<>();

    public static void daftarMemberBaru(String nama) {
        String kode = MemberService.generateKode();
        Member member = new Member(kode, nama);
        databaseMember.add(member);
    }

    public static Member cariMemberByNama(String nama) {
        for (Member m : databaseMember) {
            if (m.getNama().equalsIgnoreCase(nama)) {
                return m;
            }
        }
        return null;
    }
    
    public static List<Member> getDatabaseMember() {
        return databaseMember;
    }

    // Logic poin
    public static int hitungPoinDapat(double totalBelanja, Member member) {
        int poin = (int) totalBelanja / 10;
        // Only apply bonus for registered members (non-TEMP)
        if (!member.getKode().equals("TEMP") && member.getKode().contains("A")) {
            System.out.printf("| Bonus: Poin dilipatgandakan karena member memiliki kode A %-59s |\n", "");
            poin *= 2;
        }
        return poin;
    }

    public static double hitungPoinPakai(double totalHarga, Member member) {
        if (member == null) return 0;
        else return member.getPoin() * 2;
        
    }
    
}