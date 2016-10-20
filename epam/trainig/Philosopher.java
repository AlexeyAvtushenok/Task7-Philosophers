package epam.trainig;

/**
 * Created by Алексей on 16.10.2016.
 */
import java.util.concurrent.*;
import java.util.*;
//класс Философ
class Philosopher implements Runnable {

    private Chopstick left; //левая палочка
    private Chopstick right;//правая палочка
    private final int id; //id философа
    private final int ponderFactor; //коэффицент отображающий на сколько быстро философ производит действия
    private Random rand = new Random(50);

    private void pause() throws InterruptedException { //метод быстроты действий философа:
        if (ponderFactor == 0) {                       //чем меньше  ponderFactor тем меньше философ думает или ест
            return;
        }
        TimeUnit.MILLISECONDS.sleep(
                rand.nextInt(ponderFactor * 250));  //геренация времени ожидания/поедания философа
    }

    public Philosopher(Chopstick right, Chopstick left,
                       int ident, int ponder) {
        this.left = left;
        this.right = right;
        id = ident;
        ponderFactor = ponder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {//когда ловим сигнал interrupt, выбрасывается исключение
                System.out.println(this + " " + "thinking"); //все философы начинают с состояния "в раздумьях"
                pause();
                System.out.println(this + " " + "grabbing right");
                right.take();
                System.out.println(this + " " + "grabbing left");
                left.take();
                System.out.println(this + " " + "eating");
                pause(); //после того как философ взял две палочки, он начинает есть
                right.drop(); //бросает палочки
                left.drop(); //

            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }
}
