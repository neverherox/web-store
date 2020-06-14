package entity;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int userId;
    private int productId;
    private OrderStatus status;
    public Order()
    {

    }
    public Order(int id, int userId,int productId,OrderStatus status)
    {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }
    public Order(int userId,int productId,OrderStatus status)
    {
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getProductId()
    {
        return productId;
    }
    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public OrderStatus getStatus(){return status;}
    public void setStatus(OrderStatus status){this.status = status;}
}
