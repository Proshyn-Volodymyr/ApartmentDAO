package com.gmail.vladimirprocean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    static String url = resourceBundle.getString("db.url");
    static String user = resourceBundle.getString("db.user");
    static String password = resourceBundle.getString("db.password");

    public static void main(String[] args) {

        boolean flag = true;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            ApartmentDao apartmentDao = new ApartmentDaoImpl(connection);

//            Creating a new table
            apartmentDao.createTable();

//            Adding rows to the existing table
            apartmentDao.addApartment("Prydniprovskyi", "Novoprechystenska 555, ap. 10", 55.55, 3, 50000);
            apartmentDao.addApartment("Sosnovskiy", "Gogolya 500, ap. 100", 35.01, 1, 20000);
            apartmentDao.addApartment("Pivdennozahidnuy", "Shevchenka 390, ap. 183", 45.12, 2, 40000);
            apartmentDao.addApartment("Prydniprovskyi", "Ambrosa 35, ap. 10", 85.10, 3, 70000);
            apartmentDao.addApartment("Prydniprovskyi", "Novoprechystenska 52, ap. 15", 42.20, 1, 35000);
            apartmentDao.addApartment("Pivdennozahidnuy", "Heroiv Maidanu 5, ap. 150", 100.00, 2, 100000);

//            Getting a list of apartments by definite parameter
//            apartmentDao.getApartments("id");
            while (flag) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Show the table");
                System.out.println("2. Add the new apartment");
                System.out.println("3. Search apartment by parameter");
                System.out.println("4. Quit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        apartmentDao.getApartments();
                        break;
                    case 2:
                        App.addNewApartment();
                        break;
                    case 3:
                        break;
                    case 4:
                        flag = false;
                        break;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addNewApartment() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            ApartmentDao apartmentDao = new ApartmentDaoImpl(connection);
            boolean flag = true;
            String district = "";
            String address = "";
            double square = 0.0;
            int amountOfRooms = 0;
            int price = 0;

            while (flag) {
                System.out.println("====== ADDING A NEW APARTMENT ======");
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Enter the name district: ");
                district = scanner.nextLine();

                System.out.println("2. Enter the address: ");
                address = scanner.nextLine();

                System.out.println("3. Enter the square of apartment: ");
                square = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("4. Enter the quantity of rooms");
                amountOfRooms = scanner.nextInt();
                scanner.nextLine();

                System.out.println("5. Enter the price of apartment");
                price = scanner.nextInt();
                scanner.nextLine();

                System.out.println("1. Submit");
                System.out.println("0. Back");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        apartmentDao.addApartment(district, address, square, amountOfRooms, price);
                        flag = false;
                        break;
                    case 0:
                        flag = false;
                        break;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

}
