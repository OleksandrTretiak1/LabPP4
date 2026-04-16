import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    println("Select way:")
    println("1 - AsymmetricWay")
    println("2 - WaiterWay")

    val choice = scanner.nextLine()
    val table = Table()

    if (choice == "1") {
        for (i in 0 until 5) {
            Thread(PhilosopherAsymmetric(i, table)).start()
        }
    } else if (choice == "2") {
        for (i in 0 until 5) {
            Thread(PhilosopherWaiter(i, table)).start()
        }
    }
    scanner.close()
}