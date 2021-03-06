package edu.androidclub;

import edu.androidclub.domain.Item;
import edu.androidclub.domain.ItemBox;

import java.util.Scanner;
import java.util.Stack;

/*
    Главный класс нашей программы - представляет собой само приложение.
    Реализуем интерфейс Runnable - обозачаем, что этот класс (наше приложение) ЗАПУСКАЕМО,
    или имеет метод run()
*/
public class Application implements Runnable {


    /*
        Храним статический экземпляр нашего приложения
    */
    private static Application app;

    /*
        Точка входа в ПРОГРАММУ
    */
    public static void main(String[] args) {
        // Создаем экземпляр приложения в статической переменной и запускаем его
        app = new Application();
        app.run();
    }

    /*
        С помощью этого метода мы в любом участке кода (тк public static) можем получить наше приложение,
        причём полученный экземпляр будет ОДИНАКОВЫМ ДЛЯ КАЖДОГО ВЫЗОВА МЕТОДА

        (паттерн Singleton)
    */
    public static Application getInstance() {
        if (app == null) { // Если не создан - создаём
            app = new Application();
        }
        return app;
    }

    /*
        Метод запуска приложения как объекта. Определён интерфейсом
    */
    @Override
    public void run() {
        test();
    }

    /*
        Метод тестирования нашего приложения.
        Здесь мы создадим случайные данные о продуктах и заполним ими наш "автомат", чтобы протестировать то, что
        уже написано
    */
    private void test() {
        // Создадим схему продуктов (пустую)
        ItemInfoScheme itemScheme = new ItemInfoScheme();
        // Создадим наборы продуктов для ячеек схемы (пустые)
        Stack<Item> colas = new Stack<>(); // 1
        Stack<Item> sprites = new Stack<>(); // 2
        Stack<Item> fantas = new Stack<>(); // 2
        Stack<Item> milkas = new Stack<>(); // 2

        // Заполним наши наборы продуктами
        colas.push(new Cola());
        colas.push(new Cola());
        colas.push(new Cola());

        sprites.push(new Sprite());
        sprites.push(new Sprite());

        fantas.push(new Fanta());
        fantas.push(new Fanta());
        fantas.push(new Fanta());

        milkas.push(new Milka());
        milkas.push(new Milka());
        milkas.push(new Milka());
        milkas.push(new Milka());
        milkas.push(new Milka());
        milkas.push(new Milka());

        // Создадим объекты-координаты наших наборов продуктов в схеме
        Coordinates colaCors = new Coordinates(1, 1);
        Coordinates spriteCors = new Coordinates(1, 2);
        Coordinates fantaCors = new Coordinates(3, 0);
        Coordinates milkaCors = new Coordinates(2, 2);

        // Поместии наборы продуктов в схему
        itemScheme.put(colaCors, colas);
        itemScheme.put(spriteCors, sprites);
        itemScheme.put(fantaCors, fantas);
        itemScheme.put(milkaCors, milkas);
        //itemScheme.put(colaCors, sprites);



        // Создадим нашу витрину (коробку продуктов) на основании схемы
        ItemBox itemBox = new ProductsBox(itemScheme);

        // Протестируем схему - заставим витрину выдавать объекты на заданных координатах

        // Тест выдачи и проверки на пустоту слота
        Scanner s = new Scanner(System.in);
        s.nextLine();
        itemBox.emit(new Coordinates(1, 1));
        s.nextLine();
        itemBox.emit(new Coordinates(1, 1));
        s.nextLine();
        itemBox.emit(new Coordinates(1, 1));
        s.nextLine();
        itemBox.emit(new Coordinates(1, 1));

        // Тест некорректных координат
        s.nextLine();
        itemBox.emit(new Coordinates(25, 25));

        // Тратим другие продукты
        s.nextLine();
        itemBox.emit(new Coordinates(2, 2));
        s.nextLine();
        itemBox.emit(new Coordinates(2, 2));
        s.nextLine();
        itemBox.emit(new Coordinates(2, 2));

        s.nextLine();
        itemBox.emit(new Coordinates(3, 0));
        s.nextLine();
        itemBox.emit(new Coordinates(3, 0));
        s.nextLine();
        itemBox.emit(new Coordinates(3, 0));
        s.nextLine();
        itemBox.emit(new Coordinates(3, 0));


    }

    // Опишем Колу как подкласс Предмета
    public static class Cola extends Item {
        public Cola() {
            super("Cola", 45); // Вызов конструктора класса-родителя (класса Item)
        }
    }

    // Опишем Спрайт как подкласс Предмета
    public static class Sprite extends Item {
        public Sprite() {
            super("Sprite", 65);
        }
    }

    public static class Fanta extends Item {
        public Fanta() {
            super("Fanta", 125);
        }
    }

    public static class Milka extends Item {
        public Milka() {
            super("Milka", 5);
        }
    }
}
