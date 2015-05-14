package savosh.catmarket.model;


import java.util.HashSet;
import java.util.Set;

public class Order {
    private Integer id;
    private String fio;
    private String phone;
    private String email;
    private String deliveryAddress;
    private String paymentDescription;
    private Double totalPrice;
    private String status;

    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Order() {
    }

    public Order(String fio, String phone, String email, String deliveryAddress,
                 String paymentDescription, Double totalPrice, String status) {
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.paymentDescription = paymentDescription;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(Integer id, String fio, String phone, String email, String deliveryAddress,
                 String paymentDescription, Double totalPrice, String status) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.paymentDescription = paymentDescription;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (deliveryAddress != null ? !deliveryAddress.equals(order.deliveryAddress) : order.deliveryAddress != null)
            return false;
        if (email != null ? !email.equals(order.email) : order.email != null) return false;
        if (fio != null ? !fio.equals(order.fio) : order.fio != null) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (orderProducts != null ? !orderProducts.equals(order.orderProducts) : order.orderProducts != null)
            return false;
        if (paymentDescription != null ? !paymentDescription.equals(order.paymentDescription) : order.paymentDescription != null)
            return false;
        if (phone != null ? !phone.equals(order.phone) : order.phone != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (paymentDescription != null ? paymentDescription.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderProducts != null ? orderProducts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", paymentDescription='" + paymentDescription + '\'' +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", orderProducts=" + orderProducts +
                '}';
    }
}
