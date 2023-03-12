package com.gmail.vladimirprocean;

import com.gmail.vladimirprocean.shared.Apartment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApartmentDaoImpl implements ApartmentDao {
    private Connection connection;

    public ApartmentDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS Apartments");
            statement.execute("CREATE TABLE Apartments(id INT NOT NULL AUTO_INCREMENT, district VARCHAR(30) NOT NULL, address VARCHAR(100), square DECIMAL(6,2), amountOfRooms INT NOT NULL , price INT NOT NULL, PRIMARY KEY (id))");
            System.out.println("The table successfully created");

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addApartment(String district, String address, double square, int amountOfRooms, int price) {
        String sql = "INSERT INTO Apartments(district, address, square, amountOfRooms, price) " + "VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, district);
            preparedStatement.setString(2, address);
            preparedStatement.setDouble(3, square);
            preparedStatement.setInt(4, amountOfRooms);
            preparedStatement.setInt(5, price);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Apartment> getApartments() {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM Apartments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Apartment apartment = new Apartment();
                apartment.setId(resultSet.getInt(1));
                apartment.setDistrict(resultSet.getString(2));
                apartment.setAddress(resultSet.getString(3));
                apartment.setSquare(resultSet.getDouble(4));
                apartment.setAmountOfRooms(resultSet.getInt(5));
                apartment.setPrice(resultSet.getInt(6));

                apartments.add(apartment);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        System.out.println(apartments);
        return apartments;
    }
//    private <T> List<T>  filterList(List<Apartment> initList, T param){
//        List<T> tList = new ArrayList<>();
//        String parameterType = param.getClass().getTypeName();
//        for (Apartment listItem:initList
//             ) {
//            if()
//        }
//
//        return tList;
//    }

}
