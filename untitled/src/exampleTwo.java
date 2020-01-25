
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class exampleTwo {

    private static final String findMaxCost = "select * from students where fee <= ?";
    private static String addToSql = "insert into purch (purch_price, purch_name, summ) values (?,?,?)";

    public static void main(String[] args) throws SQLException {
        runApp();

    }

    private static void runApp() {
        int optionId;
        int summa;
        int summDB;
        double maxcost;
        boolean isWorks = true;
        Scanner in = new Scanner(System.in);
        List<Order> orders = new ArrayList<>();


        System.out.print("Ваша сумма: ");
        summa = in.nextInt();
        System.out.println();
        System.out.println();


        while (isWorks) {

            System.out.println();
            System.out.println("Ваш остаток: " + summa);

            System.out.println("Выберите номер опции:");

            System.out.println("1. добавить покупку");
            System.out.println("2. выйти из программы");
            System.out.println("3. показать список");
            System.out.println("4. отсортировать список по цене");
            System.out.println("5. удалить элемент");
            System.out.println("5. редактировать элемент");
            System.out.println(" ");
            System.out.print("Введите номер опции:  ");
            optionId = in.nextInt();
            System.out.println();

            if (summa < (summa * 0.1)) {
                System.out.println("НА СЧЕТУ МЕНЬШЕ 10% ОТ Бюджета");
            }

            switch (optionId) {
                case 1:


                    int purchCost = 0;
                    String purchName = "";

                    try{
                        System.out.print("Введите имя: ");
                        purchName = Input.getString(addToSql);

                        System.out.print("Введите стоимость: ");
                        purchCost = Input.getInt(addToSql);

                    } catch (Exception e){
                        System.err.println(e);
                    }

                    ResultSet rs = null;

                    try {
//                            Class.forName(com.mysql.jdbc.Driver);
                            Connection connection = DBconnection.getConnect();
                            PreparedStatement statement = connection.prepareStatement(addToSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                            statement.setInt(1, purchCost);
                            statement.setString(2, purchName);
                            statement.setInt(3, summa);

                            int rows = statement.executeUpdate();
                            System.out.printf("%d rows added", rows);
                    } catch (SQLException e) {
                        System.err.println(e);
                    }



                    if(purchCost <= 0){
                    System.out.println("Цена не может быть отрицательной.");
                    }

                    orders.add(new Order(purchCost, purchName));

                    Order purchase = new Order(purchCost, purchName);
                    summa = summa - purchase.cost;
                    break;
                case 3:
                    System.out.println("Все покупки:\n");
                    for (Order order : orders) {
                        System.out.println(order.ToString());
                    }
                    System.out.println();
                    break;
                case 4:
                    Collections.sort(orders);

                    for (Order order : orders) {
                        System.out.println(order.ToString());
                    }
                    break;
                case 5:
                    System.out.print("Номер элемента: ");
                    int choose = in.nextInt();
                    choose--;
                    Order getEl = orders.get(choose);
                    orders.remove(choose);
                    System.out.print("Вы удалили элемент: " + getEl.ToString());
                    break;
                case 6:
                    System.out.println("Какой элемент заменяем: ");
                    int setElem = in.nextInt();
                    Order oldelem = orders.get(setElem);
                    int oldCost = oldelem.getCost();
                    summa += oldCost;

                    //setElem--;

                    System.out.print("Новая сумма: ");
                    int newCost = in.nextInt();
                    summa -= newCost;

                    System.out.print("Новое название: ");
                    String newName = in.next();

                    orders.set(setElem, new Order(newCost, newName));
                    break;
//                case 7:
//                    System.out.println(": ");
//                    int setElem = in.nextInt();
//                    Order oldelem = orders.get(setElem);
//                    int oldCost = oldelem.getCost();
//                    summa += oldCost;
//
//                    //setElem--;
//
//                    System.out.print("Новая сумма: ");
//                    int newCost = in.nextInt();
//                    summa -= newCost;
//
//                    System.out.print("Новое название: ");
//                    String newName = in.next();
//
//                    orders.set(setElem, new Order(newCost, newName));
//                    break;
                case 2:
                    isWorks = false;
            }
        }
    }
}