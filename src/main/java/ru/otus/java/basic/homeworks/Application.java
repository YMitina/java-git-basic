package ru.otus.java.basic.homeworks;

import java.util.*;

public class Application {
    public static void main(String[] args) {
      //  System.out.println("Hello world!");
      //  System.out.println("Привет мир!!!");
        System.out.println("Задача 1");
        int min = 2;
        int max = 7;
        System.out.println("Заполнение массива числами от " + min + " до " + max);
        ArrayList<Integer> list1 = fillingArray(min, max);
        System.out.println(list1);
        System.out.println();

        System.out.println("Задача 2");
        int sum = sumMoreFive(list1);
        System.out.println("Сумма элементов массива, которые имеют значение больше 5 равна " + sum);
        System.out.println();

        System.out.println("Задача 3");
        int number = 10;
        System.out.println("Перезапись элементов массива указанным числом " + number);
        arraySetElementNumber(list1, number);
        System.out.println(list1);
        System.out.println();

        System.out.println("Задача 4");
        System.out.println("Входящий массив");
        System.out.println("Заполнение массива числами от " + min + " до " + max);
        ArrayList<Integer> list2 = fillingArray(min, max);
        System.out.println(list2);
        System.out.println("Исходящий массив после увеличения на число " + number);
        arrayPlusNumber(list2,  number);
        System.out.println(list2);
        System.out.println();

        System.out.println("Задача 5");
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Julia", 38));
        employeeList.add(new Employee("Olga", 32));
        employeeList.add(new Employee("Inna", 40));
        employeeList.add(new Employee("Ivan", 31));
        employeeList.add(new Employee("Oleg", 42));
        employeeList.add(new Employee("Petr", 48));

        System.out.println("Employees:");
        for (int i = 0; i < employeeList.size(); i++) {
            System.out.println(employeeList.get(i).toString());
        }

        ArrayList<String> nameList = getNameArrayList(employeeList);
        System.out.println(nameList);

        System.out.println("Задача 6");
        int ageMax = 40;
        ArrayList<Employee> employeeList2 = findEmployeesOlder(employeeList, ageMax);
        System.out.println("Сотрудники в возасте от " + ageMax + " лет:");
        for (int i = 0; i < employeeList2.size(); i++) {
            System.out.println(employeeList2.get(i).toString());
        }

        System.out.println("Задача 7");
        if(checkAverageAge(employeeList,ageMax))
        { System.out.println("Средний возраст сотрудников более " + ageMax + " лет!");}
        else{ System.out.println("Средний возраст сотрудников менее " + ageMax + " лет!");}

        System.out.println("Задача 8");
        System.out.println("Самый юнный сотрудник: " + youngestEmployee(employeeList));

    }

    static ArrayList<Integer> fillingArray(int min, int max){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    static int sumMoreFive(ArrayList<Integer> list){
        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 5)
             sum += list.get(i);
        }
        //Это тоже правильны вариант?
        /*for (Integer i : list ) {
            if (i.intValue()>5) {
                sum += i.intValue();
            }
        }*/
        return sum;
    }

    static void arraySetElementNumber(ArrayList<Integer> list, int number){
        for (int i = 0; i < list.size(); i++) {
            list.set(i,number);
        }
    }

    static void arrayPlusNumber(ArrayList<Integer> list, int number){
        for (int i = 0; i < list.size(); i++) {
                list.set(i,list.get(i)+ number);
        }
    }

    static ArrayList<String> getNameArrayList(ArrayList<Employee> employeeList)
    {
        ArrayList<String> nameList = new ArrayList<>();
        for (Employee employee : employeeList) {
            nameList.add(employee.getName());
        }
        return nameList;
    }

    static ArrayList<Employee> findEmployeesOlder(ArrayList<Employee> employeeListIn, int age)
    {
        ArrayList<Employee> employeeListOut = new ArrayList<>();
        for (Employee employee : employeeListIn) {
            if (employee.getAge() >= age) {
                employeeListOut.add(employee);
            }
        }
        return employeeListOut;
    }

    static boolean checkAverageAge(ArrayList<Employee> employeeListIn, float averageAge)
    {
        float sum =0f;
        float count = (float)employeeListIn.size();
        for (Employee employee : employeeListIn) {
            sum += (float)employee.getAge();
        }
        if (sum/count > averageAge) {
            return true;
        }
        else {
            return false;
        }
    }

    static Employee youngestEmployee(ArrayList<Employee> employeeListIn)
    {
        Employee youngest = employeeListIn.get(0);
        for (int i = 0; i < employeeListIn.size(); i++) {
            if (employeeListIn.get(i).getAge() < youngest.getAge()) {
                youngest = employeeListIn.get(i);
            }
        }
        return youngest;
    }
}
