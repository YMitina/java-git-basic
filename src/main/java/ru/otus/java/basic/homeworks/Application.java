package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {

        Plate plate = new Plate(100);

        Cat[] arrCat = {new Cat("Korgik", 30),
                new Cat("Barsik", 80),
                new Cat("Kompot", 20)};
        for (Cat cat : arrCat) {
            if (plate.dropFood(cat.getAppetite())) {
                cat.setFullness(true);
                System.out.println(cat.getName() + " покушал!");
            } else {
                System.out.println(cat.getName() + " остался голодным!");
            }
        }
        System.out.println();
        System.out.println("Состояние котов:");
        for (Cat cat : arrCat) {
            cat.info();
        }

        System.out.println();
        System.out.println("Наполненость тарелки: " + plate.getCurrentAmountFood());
        plate.addFood(1000);
        plate.addFood(50);
        System.out.println("Наполненость тарелки: " + plate.getCurrentAmountFood());

        arrCat[1].setAppetite(50);

        System.out.println();
        for (int i = 0; i < arrCat.length; i++) {
            if (plate.dropFood(arrCat[i].getAppetite())) {
                arrCat[i].setFullness(true);
                System.out.println(arrCat[i].getName() + " покушал!");
            } else {
                System.out.println(arrCat[i].getName() + " остался голодным!");
            }
        }
        System.out.println();
        System.out.println("Состояние котов:");
        for (int i = 0; i < arrCat.length; i++) {
            arrCat[i].info();
        }


    }
}
