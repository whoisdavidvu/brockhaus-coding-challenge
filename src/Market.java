import java.util.ArrayList;
import java.util.List;

public class Market {
    private String marketName;
    private List<Product> currentInventory;

    public Market(String newMarketName) {
        this.marketName = newMarketName;
        this.currentInventory = new ArrayList<Product>();

        if (marketName == null || newMarketName.trim().isEmpty()) {
            throw new IllegalArgumentException("Shop must have a name!");
        }
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public List<Product> getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(List<Product> currentInventory) {
        this.currentInventory = currentInventory;
    }

    public void addProduct(Product p) {
        this.currentInventory.add(p);
    }

    @Override
    public String toString() {
        return "\n" + 
                "market name: " + marketName + "\n" +
                "Market's current inventory: " + currentInventory + "\n";
    }
}
