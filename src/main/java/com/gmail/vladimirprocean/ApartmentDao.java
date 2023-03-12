package com.gmail.vladimirprocean;

import com.gmail.vladimirprocean.shared.Apartment;

import java.util.List;

public interface ApartmentDao {
    void createTable();
    void addApartment(String district, String address, double square, int amountOfRooms, int price);
    List<Apartment> getApartments();
    List<Apartment> getApartmentsByParameters(String parameter);
}
