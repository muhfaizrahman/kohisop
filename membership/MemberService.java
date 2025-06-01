package membership;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private static List<Member> databaseMember = new ArrayList<>();

    public static Member daftarMemberBaru(String nama) {
        String kode = KodeGenerator.generateKode();
        Member member = new Member(kode, nama);
        databaseMember.add(member);
        return member;
    }

    public static Member cariMember(String kode) {
        for (Member m : databaseMember) {
            if (m.getKode().equalsIgnoreCase(kode)) {
                return m;
            }
        }
        return null;
    }

    public static int hitungPoin(double totalBelanja, String kodeMember) {
        int poin = (int) totalBelanja / 10;
        if (kodeMember.contains("A")) {
            poin *= 2;
        }
        return poin;
    }

    public static void prosesTransaksi(Member member, double totalBelanja) {
        int poinTambahan = hitungPoin(totalBelanja, member.getKode());
        member.tambahPoin(poinTambahan);
    }

    public static List<Member> getDatabaseMember() {
        return databaseMember;
    }
}