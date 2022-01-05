import java.text.SimpleDateFormat;
//import java.text.ParseException;
import java.util.Date;
import java.math.BigDecimal;

public class Product {
    
    private String name;                // name of product
    private int quality;                // quality rating of product
    private Date expirationDate;        // expiration date of product
    private BigDecimal basePrice;       // base price of product
    private BigDecimal currentPrice;      // daily/final price of product
    private ProductType productType;    // type of product (cheese, wine, other)

    // Sets the german date standard to a variable
    private SimpleDateFormat germanDateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    public Product(String newName, ProductType newProductType, int newQuality, String stringDate, String newBasePriceAsString) {

        this.name = newName;
        this.productType = newProductType;
        this.quality = newQuality;
        try {
            this.expirationDate = germanDateFormatter.parse(stringDate);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.basePrice = new BigDecimal(newBasePriceAsString);
        this.currentPrice = basePrice.add(new BigDecimal(quality*0.10));
        
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is invalid!");
        }
        if (basePrice.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price can not be negative!");
        }
        if (basePrice.scale() > 2) {
            throw new IllegalArgumentException("Price is invalid!");
        }
        if (quality < 30 && productType == ProductType.cheese) {
            throw new IllegalArgumentException("This cheese has a quality rating of under 30 and cannot be added!");
        }
        if (quality < 0 && productType == ProductType.wine) {
            throw new IllegalArgumentException("This wine has a negative quality rating and cannot be accepted!");
        }
        Date today = new Date();
        if (expirationDate.before(today) && productType != ProductType.wine){
            throw new IllegalArgumentException("Expired products can not be added to shelf!");
        }
    }

    public String getName(){
        return name;
    }

    public int getQuality(){
        return quality;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public BigDecimal getBasePrice(){
        return basePrice;
    }

    public BigDecimal getCurrentPrice(){
        return currentPrice;
    }

    public ProductType getProductType(){
        return productType;
    }


    @Override
    public String toString() {
        return ("\n" + 
                "name: " + this.getName() + "\n" + 
                "product type: " + this.getProductType() + "\n"+
                "quality: " + this.getQuality() + "\n" + 
                "expiration date: " + germanDateFormatter.format(this.getExpirationDate()) + "\n" + 
                "base price: " + this.getBasePrice() + "\n" + 
                "current price: " + this.getCurrentPrice());
    }
}
