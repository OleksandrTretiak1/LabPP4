using System;
using System.Threading;

namespace ParallelPLab4
{
    public class TableAsymmetric
    {
        private readonly Semaphore[] forks = new Semaphore[5];

        public TableAsymmetric()
        {
            for (int i = 0; i < 5; i++)
            {
                forks[i] = new Semaphore(1, 1);
            }
        }
        public void GetFork(int forkId) => forks[forkId].WaitOne();
        public void PutFork(int forkId) => forks[forkId].Release();
    }

    public class PhilosopherAsymmetric
    {
        private readonly TableAsymmetric table;
        private readonly int firstFork, secondFork;
        private readonly int id;

        public PhilosopherAsymmetric(int id, TableAsymmetric table)
        {
            this.id = id;
            this.table = table;
            int leftFork = id;
            int rightFork = (id + 1) % 5;
            if (id == 4)
            {
                firstFork = rightFork;
                secondFork = leftFork;
            }
            else
            {
                firstFork = leftFork;
                secondFork = rightFork;
            }
        }

        public void Start()
        {
            for (int i = 0; i < 1; i++)
            {
                Console.WriteLine($"Philosopher {id} is thinking.");
                Thread.Sleep(500);

                table.GetFork(firstFork);
                Console.WriteLine($"Philosopher {id} picked up first fork {firstFork}");
                table.GetFork(secondFork);
                Console.WriteLine($"Philosopher {id} picked up second fork {secondFork}");
                Console.WriteLine($"Philosopher {id} is eatting");
                Thread.Sleep(1000);
                table.PutFork(secondFork);
                Console.WriteLine($"Philosopher {id} put down second fork {secondFork}");
                table.PutFork(firstFork);
                Console.WriteLine($"Philosopher {id} put down first fork {firstFork}");
            }
        }
    }
}