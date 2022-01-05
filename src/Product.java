import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    
    // private variables as part of a product
    private String name;                // name of product
    private int originalQuality;        // original quality rating of product
    private int currentQuality;         // current quality rating of product
    private Date dateAdded;             // date when the product was put on shelf  
    private Date expirationDate;        // expiration date of product
    private BigDecimal basePrice;       // base price of product
    private BigDecimal currentPrice;    // daily/final price of product
    private ProductType productType;    // type of product (cheese, wine, other)

    // Sets the german date standard to a variable
    private SimpleDateFormat germanDateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    // constructor of the object class Product
    public Product(String newName, ProductType newProductType, int newQuality, String stringDate, String newBasePriceAsString) {

        this.name = newName;
        this.productType = newProductType;
        this.originalQuality = newQuality;
        this.currentQuality = newQuality;
        try {
            this.expirationDate = germanDateFormatter.parse(stringDate);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.basePrice = new BigDecimal(newBasePriceAsString);
        this.currentPrice = basePrice.add(new BigDecimal(originalQuality*0.10));
        
        // throwing exceptions for invalid inputs
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is invalid!");
        }
        if (basePrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price can not be negative!");
        }
        if (basePrice.scale() > 2) {
            throw new IllegalArgumentException("Price is invalid!");
        }
        if (originalQuality < 30 && productType == ProductType.cheese) {
            throw new IllegalArgumentException("This cheese has a quality rating of under 30 and cannot be added!");
        }
        if (originalQuality < 0 && productType == ProductType.wine) {
            throw new IllegalArgumentException("This wine has a negative quality rating and cannot be accepted!");
        }
        Date today = new Date();
        if (expirationDate.before(today) && productType != ProductType.wine){
            throw new IllegalArgumentException("Expired products can not be added to shelf!");
        }
        long expirationDifferenceMilliseconds = Math.abs(new Date().getTime() - expirationDate.getTime());
        long expirationDifferenceDays = TimeUnit.DAYS.convert(expirationDifferenceMilliseconds, TimeUnit.MILLISECONDS);
        if (expirationDate.after(today) && 
            productType == ProductType.cheese && 
            (expirationDifferenceDays < 50 || expirationDifferenceDays > 100)) {
            throw new IllegalArgumentException("Expiration date of this cheese is either under 50 days or over 100 days after today");
        }
    }

    // in the lines below are getters for every product property
    public String getName() {
        return name;
    }

    public int getOriginalQuality() {
        return originalQuality;
    }

    public int getCurrentQuality() {
        return currentQuality;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void updatePrice() {
        this.currentPrice = basePrice.add(new BigDecimal(currentQuality*0.1));
        this.currentPrice = this.currentPrice.setScale(2, RoundingMode.FLOOR);
    }

    // method to change quality of a product and updates the price
    public void setQuality(int quality) {
        this.currentQuality = quality;
        if (this.productType.equals(ProductType.cheese)) {
            this.updatePrice();
        }
    }

    // method to set the date where this product has been put on the shelf
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    // method to change date to different date
    public void changeDateAdded(String stringDateAdded) {
        try {
            this.dateAdded = germanDateFormatter.parse(stringDateAdded);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // toString method to output products in a clear manner in the terminal
    @Override
    public String toString() {
        return ("\n" + 
                "name: " + this.getName() + ", " + 
                "product type: " + this.getProductType() + ", " +
                "original quality: " + this.getOriginalQuality() + ", " + 
                "current quality: " + this.getCurrentQuality() + ", " + 
                "date added: " + germanDateFormatter.format(this.getDateAdded()) + ", " +
                "expiration date: " + germanDateFormatter.format(this.getExpirationDate()) + ", " + 
                "base price: " + this.getBasePrice() + ", " + 
                "current price: " + this.getCurrentPrice());
    }
}
