package com.gmail.sergick6690;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Costumer {
    private String name;
    private String phone;
    private String adress;

    public Costumer(String name, String phone, String adress) {
        this.name = name;
        this.phone = phone;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
    public void addCostumerToDb(Connection connection){
      try(  PreparedStatement pst= connection.prepareStatement("insert into Costumers (name, phone, adress) values (?,?,?)")){
          pst.setString(1,this.name);
          pst.setString(2,this.phone);
          pst.setString(3,this.adress);
            pst.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
