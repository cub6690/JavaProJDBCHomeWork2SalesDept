package com.gmail.sergick6690;



import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbProperties props = new DbProperties();
        try (Connection con = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            try (Statement st = con.createStatement()) {
                st.execute("drop table  if exists Costumers, Products, Orders");
                st.execute("create  table Costumers(id int not null AUTO_INCREMENT   primary key, name  varchar (100) not null,phone  varchar (13) not null,adress varchar (150))");
                st.execute("create table Products(id int not null AUTO_INCREMENT primary key , Description varchar (100), Detalis text, Price decimal (6,2))");
                st.execute("create table Orders(id int not null AUTO_INCREMENT primary key ,date dateВалюти ,product_id int not null,qty int,customer_id int not null ,FOREIGN KEY (product_id) REFERENCES Products (id), foreign key (customer_id) references Costumers(id))");
            }
            Scanner sc = new Scanner(System.in);
            boolean end = false;
            for (; end == false; ) {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Press 1 for add new cosrumer");
                System.out.println("Press 2 for add new product");
                System.out.println("Press 3 for add new order");
                System.out.println("Press 4 for check ordes");
                System.out.println("Press 0 for exit");
                System.out.print("->");
                int unswer = sc.nextInt();
                if (unswer == 1) {
                    System.out.println("Enter name");
                    String name = sc1.nextLine();
                    System.out.println("Enter Phone");
                    String phone = sc1.nextLine();
                    System.out.println("Enter adress");
                    String adress = sc1.nextLine();
                    Costumer costumer = new Costumer(name, phone, adress);
                    costumer.addCostumerToDb(con);
                } else if (unswer == 2) {
                    System.out.println("Enter description of product");
                    String description = sc1.nextLine();
                    System.out.println("Enter detalis of product");
                    String detalis = sc1.nextLine();
                    System.out.println("Enter price");
                    double price = sc1.nextDouble();
                    Products product = new Products(description, detalis, price);
                    product.addProductToDb(con);
                } else if (unswer == 3) {
                    System.out.println("Enter product id");
                    int productId = sc1.nextInt();
                    System.out.println("Enter qti");
                    int qti = sc1.nextInt();
                    System.out.println("Enter costumer id");
                    int costumerId = sc1.nextInt();
                    Order order = new Order(productId, qti, costumerId);
                    order.addNewOrder(con);
                } else  if(unswer==4){
                    try( PreparedStatement pst= con.prepareStatement("select * from orders")){
                        ResultSet res = pst.executeQuery();
                        ResultSetMetaData md = res.getMetaData();
                        for (int i=1;i<=md.getColumnCount();i++)
                            System.out.printf("%-15s", md.getColumnName(i), "%11s");
                        System.out.println();
                        for (;res.next();){
                            for(int i =1; i<=md.getColumnCount();i++){
                                System.out.printf("%-15s",res.getString(i),"%11s");
                            }
                            System.out.println();
                        }
                    }

                }
                else if (unswer==0){
                    end=true;
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    }
