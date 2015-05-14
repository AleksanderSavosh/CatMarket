package savosh.catmarket.model;


public class OrderProduct {

    private Product product;
    private Integer count;

    public OrderProduct() {
    }

    public OrderProduct(Integer count) {
        this.count = count;
    }

    public OrderProduct(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
