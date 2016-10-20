package epam.trainig;

/**
 * Created by Алексей on 16.10.2016.
 */
import java.util.concurrent.*;
//решение проблемы: последний филосов берет палочки в другом порядке: сначала левую, потом правую.
// т.е. когда все философы кроме последнего возьмут правую палочку, последний филосов попытается взять левую палочку,
//но она будет заблокирована первым философом. и тогда последний филосов будет ждать пока освободится левая палочка, и не будет
//трогать правую палочку. и тогда предпоследний сможет поесть. и т.д.
public class FixedDiningPhilosophers {

    public static void main(String[] args) throws Exception {
        System.out.println("Press 'Enter' to quit");
        int ponder = 5;// аналогично как в DeadlockingDiningPhilosophers
        int size = 5;

        ExecutorService exec = Executors.newFixedThreadPool(size); //создаем пул потоков с фиксированным количеством философов
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {                               // всем философам кроме последнего присваиваем палочки как обычно:
                exec.execute(new Philosopher(                   //сначала правую потом левую
                        sticks[i], sticks[i + 1], i, ponder));
            } else {
                exec.execute(new Philosopher(                      // у последнего философа меняем палочки
                        sticks[0], sticks[i], i, ponder));
            }
        }

            System.in.read();

        exec.shutdownNow(); //посылаем сигнал прерывания все потокам в пуле
    }
}