import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select way:");
        System.out.println("1 - AsymmetricWay");
        System.out.println("2 - WaiterWay");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            TableAsymmetric table = new TableAsymmetric();
            for (int i = 0; i < 5; i++) {
                new Thread(new PhilosopherAsymmetric(i, table)).start();
            }
        } else if (choice.equals("2")) {
            TableWaiter table = new TableWaiter();
            for (int i = 0; i < 5; i++) {
                new Thread(new PhilosopherWaiter(i, table)).start();
            }
        }
        scanner.close();
    }
}