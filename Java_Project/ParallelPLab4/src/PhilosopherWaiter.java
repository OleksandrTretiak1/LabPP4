public class PhilosopherWaiter implements Runnable {
    private final TableWaiter table;
    private final int leftFork, rightFork;
    private final int id;

    public PhilosopherWaiter(int id, TableWaiter table) {
        this.id = id;
        this.table = table;
        this.leftFork = id;
        this.rightFork = (id + 1) % 5;
    }

    @Override
    public void run() {
        boolean hasEaten = false;
        try {
            while (!hasEaten) {
                System.out.println("Philosopher " + id + " is thinking.");
                Thread.sleep(500);

                if (table.canEnterPhilosopher()) {
                    try {
                        table.getFork(leftFork);
                        System.out.println("Philosopher " + id + " picked up left fork " + leftFork);
                        table.getFork(rightFork);
                        System.out.println("Philosopher " + id + " picked up right fork " + rightFork);

                        System.out.println("Philosopher " + id + " is eating");
                        Thread.sleep(1000);

                        table.putFork(leftFork);
                        System.out.println("Philosopher " + id + " put down left fork " + leftFork);
                        table.putFork(rightFork);
                        System.out.println("Philosopher " + id + " put down right fork " + rightFork);
                        hasEaten = true;
                    } finally {
                        table.leavePhilosopher();
                    }
                } else {
                    System.out.println("Philosopher " + id + " couldn't enter the table and will try again later.");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}