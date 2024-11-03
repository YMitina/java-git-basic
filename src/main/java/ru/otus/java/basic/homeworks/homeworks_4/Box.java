package ru.otus.java.basic.homeworks.homeworks_4;

public class Box {
    private int height;
    private int width;
    private int depth;
    private String color;
    private String item;
    private boolean isOpened;

    public Box(int height, int width, int depth, String color) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.color = color;
        this.isOpened = true;
    }


    public void printInfoBox() {
        System.out.println("Информация о коробке:");
        System.out.println("Размеры: " + height + "*" + width + "*" + depth);
        System.out.println("Цвет: " + color);
        if (item == null) {
            System.out.println("Коробка пустая.");
        } else {
            System.out.println("В коробке находится : " + item);
        }
    }

    public void open() {
        isOpened = true;
        System.out.println("Открыли коробку!");
    }

    public void close() {
        isOpened = false;
        System.out.println("Закрыли коробку!");
    }

    public void setColor(String color) {
        this.color = color;
        System.out.println("Цвет коробки изменен на " + color);
    }

    public void putItem(String item) {
        if (isOpened &&  this.item == null) {
            this.item = item;
            System.out.println("В коробку поместили предмет " + item);
            return;
        }
        if (!isOpened) {
            System.out.println("Коробка закрыта, в нее нельзя поместить предмет!");
        } else {
            System.out.println("Коробка уже наполнена, в нее нельзя поместить предмет!");
        }

    }

    public void deleteItem() {
        if (isOpened && item != null) {
            item = null;
            System.out.println("Забрали предмет из коробки. Коробка теперь пустая!");
            return;
        }
        if (!isOpened) {
            System.out.println("Коробка закрыта, нельзя забрать из нее предмет!");
        } else {
            System.out.println("Коробка пуста, нельзя забрать из нее предмет!");
        }
    }
}

