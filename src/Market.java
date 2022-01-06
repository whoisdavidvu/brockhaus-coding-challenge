import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Market {
    private String marketName;              // name of market
    private List<Product> currentInventory; // list of products on the shelf
    private List<Product> toRemoveList;     // list of products to remove today
    private List<Product> newInventory;     // list of products after removal has been done

    // Sets the german date standard to a variable
    private SimpleDateFormat germanDateFormatter = new SimpleDateFormat("dd.MM.yyyy");

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

    // function which represents a removal of a product from a shelf manually
    public void removeProduct(Product p) {
        this.currentInventory.remove(p);
        this.toRemoveList.add(p);
    }

    // checks current inventory for expired products at a given date (usually at start of current day) and adds them to the toRemove list
    // and updates quality rating of products and changes current prices accordingly 
    public void dailyCheck(String stringTargetDate) {
        try {
            // translates date in string to a format for Date
            Date targetDate = germanDateFormatter.parse(stringTargetDate);

            // copies the current inventory to a temporary one that we can modify
            newInventory = new ArrayList<Product>(this.currentInventory);

            // initialisation of an iterator
            Iterator<Product> itr = this.currentInventory.iterator();
            while(itr.hasNext()) {
                Product product = itr.next();

                // calculate number of days the cheese has been on the shelf and adjusts new quality rating
                if (product.getProductType().equals(ProductType.cheese)) {
                    long cheeseShelfDurationMilliseconds = Math.abs(targetDate.getTime() - product.getDateAdded().getTime());
                    long cheeseShelfDurationDays = TimeUnit.DAYS.convert(cheeseShelfDurationMilliseconds, TimeUnit.MILLISECONDS);
                    product.setQuality(product.getOriginalQuality() - Math.toIntExact(cheeseShelfDurationDays));
                }

                // calculate number of days the wine has been on the shelf and adjusts new quality rating
                if (product.getProductType().equals(ProductType.wine) && product.getExpirationDate().before(targetDate)) {
                    long wineExpirationMilliseconds = Math.abs(targetDate.getTime() - product.getExpirationDate().getTime());
                    long wineExpirationDays = TimeUnit.DAYS.convert(wineExpirationMilliseconds, TimeUnit.MILLISECONDS);
                    int newQuality = product.getOriginalQuality() + Math.toIntExact(wineExpirationDays/10);
                    if (product.getOriginalQuality() < 50 && newQuality > 50){
                        product.setQuality(50);
                    }      
                    else if (product.getOriginalQuality() < 50 && newQuality <= 50) {
                        product.setQuality(product.getOriginalQuality() + Math.toIntExact(wineExpirationDays/10));
                    }
                }

                // checks every product in inventory for expiration or unadaquate quality (except wine)
                if ((product.getProductType().equals(ProductType.other) && product.getExpirationDate().before(targetDate)) || 
                    (product.getProductType().equals(ProductType.cheese) && product.getCurrentQuality() <= 30)) {
                    newInventory.remove(product);
                    this.toRemoveList.add(product);
                }  
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /* simply clears the toRemoveList when physically done removing expired products and 
    overwrites current inventory with the new inventory list */
    public void updateLists() {
        this.currentInventory.clear();
        this.currentInventory.addAll(newInventory);
        newInventory.clear();
        this.toRemoveList.clear();
    }

    // toString method to output to the terminal
    @Override
    public String toString() {
        return "\n" + 
                "market name: " + marketName + "\n" +
                "market's current inventory: " + currentInventory + "\n" + 
                "inventory to remove today: " + toRemoveList;
    }
}
