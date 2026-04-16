using System;
using System.Threading;

namespace ParallelPLab4
{
    public class Program
    {
        public static void Main(string[] args)
        {

            Console.WriteLine("Select way:");
            Console.WriteLine("1 - AsymmetricWay");
            Console.WriteLine("2 - WaiterWay");

            string choice = Console.ReadLine() ?? "";

            if (choice == "1")
            {
                TableAsymmetric table = new TableAsymmetric();
                Thread[] tasks = new Thread[5];
                for (int i = 0; i < 5; i++)
                {
                    int id = i;
                    tasks[i] = new Thread(() => new PhilosopherAsymmetric(id, table).Start());
                    tasks[i].Start();
                }
            }
            else if (choice == "2")
            {
                TableWaiter table = new TableWaiter();
                Thread[] tasks = new Thread[5];
                for (int i = 0; i < 5; i++)
                {
                    int id = i;
                    tasks[i] = new Thread(() => new PhilosopherWaiter(id, table).Start());
                    tasks[i].Start();
                }
            }
        }
    }
}