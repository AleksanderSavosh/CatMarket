package savosh.catmarket.model;


import java.io.Serializable;

public class Product implements Serializable {
    private String bread;
    private Double price;
    private String hairType;
    private Integer lifeTime;
    private Integer maxWeight;
    private String imgName;

    public Product() {
    }

    public Product(String bread) {
        this.bread = bread;
    }

    public Product(String bread, Double price, String hairType, Integer lifeTime, Integer maxWeight, String imgName) {
        this.bread = bread;
        this.price = price;
        this.hairType = hairType;
        this.lifeTime = lifeTime;
        this.maxWeight = maxWeight;
        this.imgName = imgName;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (bread != null ? !bread.equals(product.bread) : product.bread != null) return false;
        if (hairType != null ? !hairType.equals(product.hairType) : product.hairType != null) return false;
        if (imgName != null ? !imgName.equals(product.imgName) : product.imgName != null) return false;
        if (lifeTime != null ? !lifeTime.equals(product.lifeTime) : product.lifeTime != null) return false;
        if (maxWeight != null ? !maxWeight.equals(product.maxWeight) : product.maxWeight != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bread != null ? bread.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (hairType != null ? hairType.hashCode() : 0);
        result = 31 * result + (lifeTime != null ? lifeTime.hashCode() : 0);
        result = 31 * result + (maxWeight != null ? maxWeight.hashCode() : 0);
        result = 31 * result + (imgName != null ? imgName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "bread='" + bread + '\'' +
                ", price=" + price +
                ", hairType='" + hairType + '\'' +
                ", lifeTime=" + lifeTime +
                ", maxWeight=" + maxWeight +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
