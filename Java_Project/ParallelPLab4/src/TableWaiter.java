import java.util.concurrent.Semaphore;

public class TableWaiter {
    private final Semaphore[] forks = new Semaphore[5];
    private final Semaphore waiter = new Semaphore(4);

    public TableWaiter() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public boolean canEnterPhilosopher() {
        return waiter.tryAcquire();
    }

    public void leavePhilosopher() {
        waiter.release();
    }

    public void getFork(int id) throws InterruptedException {
        forks[id].acquire();
    }

    public void putFork(int id) {
        forks[id].release();
    }
}