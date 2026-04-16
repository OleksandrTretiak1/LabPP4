class PhilosopherWaiter(private val id: Int, private val table: Table) : Runnable {
    override fun run() {
        var i = 0
        try {
            while (i < 1) {
                println("Philosopher $id is thinking.")
                Thread.sleep(500)

                if (table.canEnterPhilosopher()) {
                    try {
                        val leftFork = id
                        val rightFork = (id + 1) % 5

                        table.getFork(leftFork)
                        println("Philosopher $id picked up left fork $leftFork")

                        table.getFork(rightFork)
                        println("Philosopher $id picked up right fork $rightFork")

                        println("Philosopher $id is eating")
                        Thread.sleep(1000)

                        table.putFork(leftFork)
                        println("Philosopher $id put down left fork $leftFork")

                        table.putFork(rightFork)
                        println("Philosopher $id put down right fork $rightFork")

                        i++
                    } finally {
                        table.leavePhilosopher()
                    }
                } else {
                    println("Philosopher $id couldn't enter the table and will try again later.")
                    Thread.sleep(1000)
                }
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}