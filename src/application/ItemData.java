package application;

public class ItemData {
    private int quantity;
    private String item;
    private int price;

    public ItemData(String item, int quantity, int price) {
        this.quantity = quantity;
        this.item = item;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
