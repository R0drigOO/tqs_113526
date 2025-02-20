package lab2_2;

public class Product {
    private int id;
    private String image, description, title, category;
    private double price;

    public Product(){}

    public Product(int id, String image, String description, String title, String category, double price) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.title = title;
        this.category = category;
        this.price = price;
    }
}