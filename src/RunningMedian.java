import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RunningMedian {
    private static class MiddleSet {
        ArrayList<Long> set = new ArrayList<Long>();
        long balance = 0;

        long getMiddleValue() {
            if (set.size() == 0) {
                return Long.MIN_VALUE;
            }
            return set.get((set.size() - 1) / 2);
        }

        void put(long data) {
            long middle = getMiddleValue();
            set.add(data);

            Collections.sort(set);

            if (set.size() <= 3) {
                return;
            }
            if (middle > data) {
                balance--;
            } else {
                balance++;
            }
            if (balance > 1) {
                set.remove(0);
                balance -= 2;

            } else if (balance < 0) {
                set.remove(set.size() - 1);
                balance += 2;
            }
        }
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.println(solve(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }
        }
    }

    private static long solve(int length, int a, int b) {
        long result = 0;
        MiddleSet set = new MiddleSet();
        int last = 1983;
        for (int i = 0; i < length; i++) {
            set.put(last);
            System.out.println(set.getMiddleValue());
            result += set.getMiddleValue() % 20090711;
            result %= 20090711;
            last = (last * a + b) % 20090711;
        }

        return result;
    }
}
