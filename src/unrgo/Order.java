package unrgo;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private String name;
    private String phone;
    private ArrayList<Catalog> chosenCatalog = new ArrayList<Catalog>();

    public Order(String name, String phone, Catalog chosenCatalog) {
        this.name = name;
        this.phone = phone;

        try {
            this.chosenCatalog.add(chosenCatalog);
        } catch (NullPointerException ex) {

            this.chosenCatalog.add(new Catalog());
        }

    }

    public Order() {

    }
    public ArrayList<Catalog> getChosenCatalog(){
        return this.chosenCatalog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //изменить имя заказчика
    public void editName() {
        Scanner sc = new Scanner(System.in);
        String newName = sc.nextLine();
        setName(newName);
        System.out.println("Успешно изменено!");

    }

    //изменить номер заказчика
    public void editPhone() {
        Scanner sc = new Scanner(System.in);
        String newPhone = sc.next();
        setPhone(newPhone);
        System.out.println("Успешно изменено!");

    }

    public void editChosenGoods(ArrayList<Catalog> catalog,ArrayList<Catalog> list, Catalog ct) {
        int chosenOperation = 0;
        int operation=-2;
        System.out.println(list);
        System.out.println("Выберите действие :" + '\n' + "1-Удалить товар" + '\n' + "2-Добавить товар");
        Scanner sc = new Scanner(System.in);
        chosenOperation = sc.nextInt();
        switch (chosenOperation) {
            case 1:


                ct.deleteGood(list);

                break;
            case 2:
                while (operation == -2) {
                    operation = ct.searchGood(catalog);

                }

                list.add(catalog.get(operation));
                break;
            default:
                System.out.println("Выбрана неверная операция!");
                break;
        }
    }

    public Order editAllOrder(ArrayList<Catalog> catalog,ArrayList<Catalog> list ,Catalog ct, Order order) {
        if (order.isEmpty()) {
                System.out.println("У вас ещё нету заказов!");
                return order;
            }

        int chosenOperation = 0;
        System.out.println("Выберите действие :" + '\n' + "1-Изменить имя" + '\n' + "2-Изменить номер" + '\n' + "3-Изменить заказ");
        Scanner sc = new Scanner(System.in);
        chosenOperation = sc.nextInt();
        switch (chosenOperation) {
            case 1:
                order.editName();
                break;
            case 2:
                order.editPhone();
                break;
            case 3:
                order.editChosenGoods(catalog,list, ct);
                break;
            default:
                System.out.println("Выбрана неверная операция!");
                break;
        }
        return order;
    }

    public void showAllOrder(ArrayList<Order> ord,String fname){
        ord = getOrdersfromBase(fname);
        try{
            Order or =ord.get(0);
            System.out.println(ord);
        }catch (IndexOutOfBoundsException ex){
            System.out.println( "Заказов ещё нету!");
        }



    }
    //проверка на пустоту
    public boolean isEmpty() {
        try {
            if (this.name.isEmpty() || this.phone.isEmpty() || this.chosenCatalog == null) {
                return true;
            }
        } catch (NullPointerException ex) {

            return true;
        }


        return false;
    }

    //показать заказ
    public String showMyOrder(Order order) {
        //случай ,если нету заказов
        if(order.isEmpty()){
            return "У вас ещё нету заказов!";
        }


        return order.toString();
    }

    //cоздать закакз
    public Order createOrder(ArrayList<Catalog> list) {
        Catalog ct = new Catalog();
        int operation = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ваше имя:");
        String clientName = sc.nextLine();
        System.out.println("Введите ваш номер телефона:");
        String clientPhone = sc.next();
        while (operation == -1) {
            operation = ct.searchGood(list);

        }

        Catalog newCt = list.get(operation);


        Order newOrder = new Order(clientName, clientPhone, newCt);


        return newOrder;
    }


    //подтверждения заказа
    public boolean pushOrder(ArrayList<Order> orders, Order order) {
        if (order.isEmpty()) {
            System.out.println("У вас ещё нету заказов!");
            return false;
        } else {
            orders.add(order);

            return true;
        }


    }

    //сохранение заказа в базу
    public void saveOrderToBase(ArrayList<Order> orders, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(orders);
            System.out.println("Данные о заказах обновлены!");
        } catch (Exception ex) {

            System.out.println("Ошибка в записи в файл "+ex.getMessage());
        }

    }

    //получить заказы из базы
    public ArrayList<Order> getOrdersfromBase(String fname) {
        ArrayList<Order> ord = new ArrayList<Order>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname))) {

            ord = ((ArrayList<Order>) ois.readObject());
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        return ord;

    }

    public Order clientMode(ArrayList<Catalog> list, ArrayList<Order> orders, Catalog ct, String fname, Order bufOrder) {
        int chosenOperation = 0;
        Order newOrder= new Order();
        int loop = 0;
        while (loop != 1) {
            System.out.println("Выберите действие :" + '\n' + "0-Показать каталог"+'\n' +"1-Создать заказ" + '\n' + "2-Посмотреть заказ" + '\n' + "3-Редактировать заказ" + '\n' + "4-Подтвердить заказ" + '\n' + "5-Завершить работу");
            Scanner sc = new Scanner(System.in);
            try {
                chosenOperation = sc.nextInt();
            }
            catch (Exception ex){
                System.out.println("Ошибка ввода!");
            }
            switch (chosenOperation) {
                case 0:
                    ct.showCatalog(list);
                    break;
                case 1:
                    bufOrder = createOrder(list);
                    break;
                case 2:
                    System.out.println(showMyOrder(bufOrder));
                    break;

                case 3:
                    bufOrder = editAllOrder(list,bufOrder.chosenCatalog, ct, bufOrder);
                    break;
                case 4:
                    for(Catalog catalog : bufOrder.getChosenCatalog()){
                        int countToReduce =1;
                        if(catalog.getCount()<countToReduce){
                            System.out.println(catalog.getName()+" в количестве "+countToReduce+" отсутсвует!");

                        }
                        else{
                            //int mIndex = list.indexOf(catalog);
                            catalog.reduceCount(countToReduce,catalog);
                            //list.add(mIndex,catalog);

                        }

                    }
                    ct.saveCatalogToBase(list,"catalog.dat");


                    if (!(pushOrder(orders, bufOrder))) {
                        System.out.println("Ошибка!");
                        break;
                    }

                    System.out.println("Ваш заказ добавлен в систему!");
                    return bufOrder;
                case 5:
                    loop = 1;
                    break;
                default:
                    System.out.println("Выбрана неверная операция!");
                    break;

            }
        }
        return  bufOrder;
    }

    @Override
    public String toString() {
        return "Name: " + name + " phone: " + phone + " chosen goods: " + chosenCatalog;
    }
}
