package epam.trainig;

/**
 * Created by Алексей on 16.10.2016.
 */
//философы сидят по кругу от id = 0 до id =4 по часовой стрелке; номера палочек лежат тоже по часовой стрелке.
// у каждого философа лежит палочка с номером его ID -справа
//т.е у 0-ого философа - палочка 0 - справа(палочка 1 - слева), у 1 философа - палочка 1 - справа(палочка 2 - слева) и т.д.
//все философы сначала берут правую палочку, а затем левую. и когда все философы возьмут правую палочку - возникает DEADLOCK
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        System.out.println("Press 'Enter' to quit");
        int ponder = 5; //коэффицент "раздумий философов" = 5. если поставить его в 0, то философы быстрее достигнут состояния DEADLOCK т.к.
        int size = 5;   //все быстро могут взять правую палочку


        Chopstick[] sticks = new Chopstick[size]; //массив палочек
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        Thread[] philosophers = new Thread[size]; // массив философов
        for (int i = 0; i < size; i++) {
          philosophers[i] = new Thread(new Philosopher(
                    sticks[i], sticks[(i + 1) % size], i, ponder)); //(i+1)%size - присвоение левой палочки. последнему философу присвается первая палочка как левая
            philosophers[i].start();
        }


            System.in.read(); // ввод любого символа для окончания программы
        for (int i = 0; i < size; i++) {

            philosophers[i].interrupt(); //посылаем сигнал прерывания потоков
        }
    }
}
