class PhilosopherAsymmetric(private val id: Int, private val table: Table) : Runnable {
    private val firstFork: Int
    private val secondFork: Int

    init {
        val leftFork = id
        val rightFork = (id + 1) % 5
        if (id == 4) {
            firstFork = rightFork
            secondFork = leftFork
        } else {
            firstFork = leftFork
            secondFork = rightFork
        }
    }

    override fun run() {
        try {
            println("Philosopher $id is thinking.")
            Thread.sleep(500)

            table.getFork(firstFork)
            println("Philosopher $id picked up first fork $firstFork")

            table.getFork(secondFork)
            println("Philosopher $id picked up second fork $secondFork")

            println("Philosopher $id is eating")
            Thread.sleep(1000)

            table.putFork(secondFork)
            println("Philosopher $id put down left fork $secondFork")

            table.putFork(firstFork)
            println("Philosopher $id put down right fork $secondFork")
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}