package week16;
import java.util.Random;

@FunctionalInterface
interface DoNotGiveUp<T> {
    T execute();
}

// Tries at most 3 times
class TryThreeTimes implements DoNotGiveUp<String> {

    private Random random = new Random();

    @Override
    public String execute() {

        for (int attempt = 1; attempt <= 3; attempt++) {

            int number = random.nextInt(100) + 1; // 1 to 100

            System.out.println("TryThreeTimes attempt " + attempt + ": number = " + number);

            if (number > 50) {
                return "You succeeded";
            }
        }

        return "Failed";
    }
}

// Tries at most 1000 times
class TryForEver implements DoNotGiveUp<String> {

    @Override
    public String execute() {

        for (int attempt = 1; attempt <= 1000; attempt++) {

            double number = Math.random();

            System.out.println("TryForEver attempt " + attempt + ": number = " + number);

            if (number < 0.4) {
                return "You succeeded";
            }
        }

        return "Failed";
    }
}

public class DoNotGiveUpDemo {

    public static void main(String[] args) {

        DoNotGiveUp<String> tryThreeTimes = new TryThreeTimes();
        DoNotGiveUp<String> tryForEver = new TryForEver();

        System.out.println("Running TryThreeTimes:");
        String result1 = tryThreeTimes.execute();
        System.out.println("Result: " + result1);

        System.out.println();

        System.out.println("Running TryForEver:");
        String result2 = tryForEver.execute();
        System.out.println("Result: " + result2);
    }
}