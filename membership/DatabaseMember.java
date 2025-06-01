package membership;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMember {
    private static List<Member> daftarMember = new ArrayList<>();

    public static Member cariMember(String nama) {
        for (Member m : daftarMember) {
            if (m.getNama().equalsIgnoreCase(nama)) {
                return m;
            }
        }
        return null;
    }

    public static void tambahMember(Member member) {
        daftarMember.add(member);
    }

    public static List<Member> getDaftarMember() {
        return daftarMember;
    }
}
