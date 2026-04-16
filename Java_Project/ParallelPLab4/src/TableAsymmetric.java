import java.util.concurrent.Semaphore;

public class TableAsymmetric {
    private final Semaphore[] forks = new Semaphore[5];

    public TableAsymmetric() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public void getFork(int forkId) throws InterruptedException {
        forks[forkId].acquire();
    }

    public void putFork(int forkId) {
        forks[forkId].release();
    }
}