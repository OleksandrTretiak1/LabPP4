using System;
using System.Threading;

namespace ParallelPLab4
{
    public class TableWaiter
    {
        private readonly Semaphore[] forks = new Semaphore[5];
        private readonly Semaphore waiter = new Semaphore(4, 4);

        public TableWaiter()
        {
            for (int i = 0; i < 5; i++)
            {
                forks[i] = new Semaphore(1, 1);
            }
        }

        public bool CanEnterPhilosopher() => waiter.WaitOne(0);

        public void LeavePhilosopher() => waiter.Release();

        public void GetFork(int id) => forks[id].WaitOne();
        public void PutFork(int id) => forks[id].Release();
    }

    public class PhilosopherWaiter
    {
        private readonly TableWaiter table;
        private readonly int leftFork, rightFork;
        private readonly int id;

        public PhilosopherWaiter(int id, TableWaiter table)
        {
            this.id = id;
            this.table = table;
            leftFork = id;
            rightFork = (id + 1) % 5;
        }

        public void Start()
        {
            int i = 0;
            while (i < 1)
            {
                Console.WriteLine($"Philosopher {id} is thinking.");
                Thread.Sleep(500);

                if (table.CanEnterPhilosopher())
                {
                    try
                    {
                        table.GetFork(leftFork);
                        Console.WriteLine($"Philosopher {id} picked up left fork {leftFork}");
                        table.GetFork(rightFork);
                        Console.WriteLine($"Philosopher {id} picked up right fork {rightFork}");
                        Console.WriteLine($"Philosopher {id} is eating");
                        Thread.Sleep(1000);
                        table.PutFork(leftFork);
                        Console.WriteLine($"Philosopher {id} put down left fork {leftFork}");
                        table.PutFork(rightFork);
                        Console.WriteLine($"Philosopher {id} put down right fork {rightFork}");
                        i++;
                    }
                    finally
                    {
                        table.LeavePhilosopher();
                    }
                }
                else
                {
                    Console.WriteLine($"Philosopher {id} couldn't enter the table and will try again later.");
                    Thread.Sleep(1000);
                }
            }
        }
    }
}