public class PhilosopherAsymmetric implements Runnable {
    private final TableAsymmetric table;
    private final int firstFork, secondFork;
    private final int id;

    public PhilosopherAsymmetric(int id, TableAsymmetric table) {
        this.id = id;
        this.table = table;
        int leftFork = id;
        int rightFork = (id + 1) % 5;

        if (id == 4) {
            firstFork = rightFork;
            secondFork = leftFork;
        } else {
            firstFork = leftFork;
            secondFork = rightFork;
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Philosopher " + id + " is thinking.");
            Thread.sleep(500);

            table.getFork(firstFork);
            System.out.println("Philosopher " + id + " picked up first fork " + firstFork);
            table.getFork(secondFork);
            System.out.println("Philosopher " + id + " picked up second fork " + secondFork);

            System.out.println("Philosopher " + id + " is eatting");
            Thread.sleep(1000);

            table.putFork(secondFork);
            System.out.println("Philosopher " + id + " put down second fork " + secondFork);
            table.putFork(firstFork);
            System.out.println("Philosopher " + id + " put down first fork " + firstFork);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}