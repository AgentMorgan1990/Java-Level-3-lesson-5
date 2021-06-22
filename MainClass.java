package Lesson3_5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainClass {

    static int start = 0;
    static int win = 0;
    static int finish = 0;

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    cars[index].prepare();
                    cyclicBarrier.await();
                    if (start == 0) {
                        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                        start++;
                    }
                    cars[index].launch();
                    if (win == 0) {
                        win++;
                        System.out.println(cars[index].getName() + " победил");
                    }
                    cyclicBarrier.await();
                    if (finish == 0) {
                        finish++;
                        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
