package unrgo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    int ID = 0;


    public static void main(String[] args) {

        ArrayList<Catalog> goods = new ArrayList<Catalog>();
        ArrayList<Order> orders = new ArrayList<Order>();
        Catalog start = new Catalog();
        Order ord = new Order();

        String catalogBase = "catalog.dat";
        String orderBase = "orders.dat";

        goods = start.getCatalogFromBase(catalogBase);

        int chosenOperation = 0;
        System.out.println('\t' + "Добро пожаловать в тест-версию Simple Shop");
        System.out.println("Выберите режим:" + '\n' + "1-Админисратор магазину" + '\n' + "2-Покупатель");
        Scanner sc = new Scanner(System.in);
        try {
            chosenOperation = sc.nextInt();
        }
        catch (Exception ex){
            System.out.println("Ошибка ввода!");
        }
        switch (chosenOperation) {
            case 1:
                start.adminMode(goods,catalogBase,orderBase,orders);
                break;
            case 2:
                ord.clientMode(goods,start, orderBase, ord);
                break;

            default:
                System.out.println("Выбрана неверная операция!");
                break;
        }

        System.out.println("See you!");


    }
}