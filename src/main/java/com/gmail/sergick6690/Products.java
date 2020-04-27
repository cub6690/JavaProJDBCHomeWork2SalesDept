package com.gmail.sergick6690;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Products {
   private int id;
    private String description;
    private String detalis;
    private double price;

    public Products(String description, String detalis, double price) {
        this.description = description;
        this.detalis = detalis;
        this.price = price;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetalis() {
        return detalis;
    }

    public void setDetalis(String detalis) {
        this.detalis = detalis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void addProductToDb(Connection connection){
        try(  PreparedStatement pst= connection.prepareStatement("insert into products (Description , Detalis, Price) values (?,?,?)")){
            pst.setString(1,this.description);
            pst.setString(2,this.detalis);
            pst.setDouble(3,this.price);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", detalis='" + detalis + '\'' +
                ", price=" + price +
                '}';
    }
}
