import java.util.concurrent.Semaphore

class Table {
    private val forks = Array(5) { Semaphore(1) }
    private val waiter = Semaphore(4)

    fun canEnterPhilosopher(): Boolean = waiter.tryAcquire()

    fun leavePhilosopher() = waiter.release()

    fun getFork(id: Int) = forks[id].acquire()

    fun putFork(id: Int) = forks[id].release()
}