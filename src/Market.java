import java.util.Date;
import java.util.ArrayList;
import java.util.List;
public class Market {
    private String marketName;              // name of market
    private List<Product> currentInventory; // list of products on the shelf
    private List<Product> toRemoveList;     // list of products to remove today

    public Market(String newMarketName) {
        this.marketName = newMarketName;
        this.currentInventory = new ArrayList<Product>();
        this.toRemoveList = new ArrayList<Product>();

        if (marketName == null || newMarketName.trim().isEmpty()) {
            throw new IllegalArgumentException("Shop must have a name!");
        }
    }

    // getters and setters for properties
    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public List<Product> getToRemoveList() {
        return toRemoveList;
    }

    public void setToRemoveList(List<Product> toRemoveList) {
        this.toRemoveList = toRemoveList;
    }

    public List<Product> getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(List<Product> currentInventory) {
        this.currentInventory = currentInventory;
    }

    // adds a product to a shelf with today's date as its dateAdded property
    public void addProduct(Product p) {
        this.currentInventory.add(p);
        p.setDateAdded(new Date());
    }

    // adds an expired or a product that has to be removed from shelves to a toRemove list
    public void toRemoveList(Product p) {
        this.toRemoveList.add(p);
    }

    // function which represents a removal of a product from a shelf
    public void removeProduct(Product p) {
        this.currentInventory.remove(p);
    }

    // toString method to output to the terminal
    @Override
    public String toString() {
        return "\n" + 
                "market name: " + marketName + "\n" +
                "Market's current inventory: " + currentInventory + "\n";
    }
}
