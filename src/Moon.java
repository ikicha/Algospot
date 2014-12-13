import java.util.Scanner;

public class Moon {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.printf("%.3f\n", solve(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }
        }
    }

    private static double solve(int moonSize, int shadowSize, int distance) {
        int max = moonSize + shadowSize;
        int min = shadowSize - moonSize;
        double moonSquare = Math.pow(moonSize, 2);
        double shadowSquare = Math.pow(shadowSize, 2);
        double distanceSquare = Math.pow(distance, 2);
        double moonCircleSize = moonSquare * Math.PI;
        if (max <= distance) {
            return moonCircleSize;
        } else if (min < distance && max > distance) {
            double cosMoon = (distanceSquare - (shadowSquare - moonSquare)) / (2 * distance * moonSize);
            double cosShadow = (distanceSquare + (shadowSquare - moonSquare)) / (2 * distance * shadowSize);
            double radMoon = Math.acos(cosMoon);
            double radShadow = Math.acos(cosShadow);

            double rectSize = Math.sin(radMoon) * moonSize * distance;

            double circleTotalSize = (moonSquare * radMoon + shadowSquare * radShadow);

            return moonCircleSize - (circleTotalSize - rectSize);
        } else {
            return 0;
        }
    }
}
