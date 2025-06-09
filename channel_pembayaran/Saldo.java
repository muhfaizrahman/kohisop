package channel_pembayaran;

import java.util.HashMap;
import java.util.Map;
import membership.Member;

public class Saldo {
    private static Map<Member, Double> memberBalances = new HashMap<>();
    private double nominal;

    public Saldo() {
        this.nominal = 0;
    }

    public static double getMemberBalance(Member member) {
        return memberBalances.getOrDefault(member, 0.0);
    }

    public static void setMemberBalance(Member member, double amount) {
        memberBalances.put(member, amount);
    }

    public static void addToMemberBalance(Member member, double amount) {
        double currentBalance = getMemberBalance(member);
        setMemberBalance(member, currentBalance + amount);
    }
}
