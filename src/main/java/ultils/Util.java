package ultils;

public interface Util {
    static boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++)
            if (number % i == 0)
                sum += i;
        return sum == number;
    }

    static boolean isPrimeNumber(int i) {
        int factors = 0;
        int j = 1;

        while (j <= i) {
            if (i % j == 0) {
                factors++;
            }
            j++;
        }
        return (factors == 2);
    }


}
