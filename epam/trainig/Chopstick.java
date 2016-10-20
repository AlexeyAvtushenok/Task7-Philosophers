package epam.trainig;

/**
 * Created by Алексей on 16.10.2016.
 */
import java.util.concurrent.*;
import java.util.*;
//класс Палочка для еды
class Chopstick {

    private boolean taken = false; //переменная показывает свободна ли палочка

    public synchronized void take() throws InterruptedException { //метод взятия палочки: если палочка занята,
        while (taken) {                                            // то любой филосов обратившийся к ней, ожидает, до тех пор,
            wait();                                                    // пока палочка не станет свободной

        }
        taken = true;

    }

    public synchronized void drop() { //поло;ить палочку на место: сообщается всем философам что палочка свободна
        taken = false;
        notifyAll(); //можно ставить и notify(), тк к одной палочке могут обращаться лишь два философа
    }
}