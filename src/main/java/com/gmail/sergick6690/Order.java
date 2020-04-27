package com.gmail.sergick6690;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Order {
    private int id;
    private int productId;
    private int qti;
    private int costumerId;
private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Order(int productId, int qti, int costumerId) {
        this.productId = productId;
        this.qti = qti;
        this.costumerId = costumerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQti() {
        return qti;
    }

    public void setQti(int qti) {
        this.qti = qti;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public void addNewOrder(Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.execute("insert into orders (date, product_id, qty, customer_id ) values ('"+sdf.format(new Date())+"',"+productId+","+qti+","+costumerId+" )");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", qti=" + qti +
                ", costumerId=" + costumerId +
                '}';
    }
}