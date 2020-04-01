import java.util.Random;

public class Main {

    public static int s = 1;
    public static int r = 2;
    public static int c = 3;
    public static double P00 = 0.3, P01 = 0.4, P02 = 0.3;
    public static double P10 = 0.5, P11 = 0,   P12 = 0.5;
    public static double P20 = 0.1, P21 = 0.7, P22 = 0.2;

    public static void main(String args[]) {

        int curr = s;
        int countR = 0, countS = 0, countC = 0;
        double normCountR = 0, normCountS = 0, normCountC = 0;

        Random random = new Random();

        for (int i = 0; i < 100000; i++) {

            if (i % 500 == 0 && i > 1000) {

                double newNormCountR = (double) countR / i;
                double newNormCountS = (double) countS / i;
                double newNormCountC = (double) countC / i;

                double epsilon = 0.0001;

                if ((Math.abs(newNormCountR - normCountR) < epsilon) &&
                        (Math.abs(newNormCountS - normCountS) < epsilon) &&
                        (Math.abs(newNormCountC - normCountC) < epsilon)) {

                    System.out.println("Found at step: " + i);
                    break;
                } else {

                    normCountC = newNormCountC;
                    normCountR = newNormCountR;
                    normCountS = newNormCountS;
                }
            }
            double rand = random.nextFloat();

            if (curr == s) {
                countS++;
                if (rand < P00) {
                    curr = s;
                } else if (rand < P00 + P01) {
                    curr = c;
                } else {
                    curr = r;
                }
                continue;
            }
            if (curr == r) {
                countR++;
                if (rand < P11) {
                    curr = r;
                } else if (rand < P11 + P10) {
                    curr = s;
                } else {
                    curr = c;
                }
                continue;
            }
            if (curr == c) {
                countC++;
                if (rand < P22) {
                    curr = c;
                } else if (rand < P22 + P21) {
                    curr = r;
                } else {
                    curr = s;
                }
                continue;
            }

        }

        System.out.println("normCountC: " + normCountC);
        System.out.println("normCountR: " + normCountR);
        System.out.println("normCountS: " + normCountS);
    }
}
