package membership;

import java.util.ArrayList;
import java.util.List;

public class MemberService {

    // Database member
    private static List<Member> databaseMember = new ArrayList<>();

    public static void daftarMemberBaru(String nama) {
        String kode = KodeGenerator.generateKode();
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
        if (member.getKode().contains("A")) {
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