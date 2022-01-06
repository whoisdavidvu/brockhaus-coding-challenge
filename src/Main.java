import java.util.Date;

public class Main {
    public static void main(String args[]){
        Date today = new Date();
        
        Market superduper = new Market("superduper");

        Product emmentaler = new Product("Emmentaler", ProductType.cheese, 40, "08.03.2022", "1.99");
        Product gouda = new Product("Gouda", ProductType.cheese, 31, "02.03.2022", "1.49");
        Product granapadano = new Product("Grana Padano", ProductType.cheese, 40,  "15.03.2022", "2.99");
        Product parmesan = new Product("Parmesan", ProductType.cheese, 40, "15.03.2022", "3.49");
        Product redwine = new Product("Rotwein", ProductType.wine, 40, "01.02.2022", "4.99");
        Product whitewine = new Product("Weißwein", ProductType.wine, 49, "01.02.2022", "4.99");
        Product gewurztraminer = new Product("Gewürztraminer", ProductType.wine, 70, "01.02.2022", "4.99");
        Product salmon = new Product("Geräucherter Lachs", ProductType.other, -20, "07.01.2022", "4.99");
        Product salami = new Product("Luftgetrockenete Salami", ProductType.other, 10, "15.05.2022", "2.99");

        superduper.addProduct(emmentaler);
        superduper.addProduct(gouda);
        superduper.addProduct(granapadano);
        superduper.addProduct(parmesan);
        superduper.addProduct(redwine);
        superduper.addProduct(whitewine);
        superduper.addProduct(gewurztraminer);
        superduper.addProduct(salmon);
        superduper.addProduct(salami);

        // See Product.java to see that cheese can only have an expiration date from 50 to 100 days in the future from today and
        // that wine with negative quality rating will not be accepted. Both will throw an exception.
        
        System.out.println(superduper.toString());
        System.out.println();
        System.out.println("Task: fixed price that doesnt change + final price changed by quality rating.");
        System.out.println("--------------------------------------------------");

        salmon.setQuality(-30);
        System.out.println(superduper.toString());
        System.out.println();
        System.out.println("Notice: Changing quality of other food changes price (here: salmon).");
        System.out.println("--------------------------------------------------");

        // Running dailyCheck at this date to simulate a run of the program on that specific date, for real world use current date
        superduper.dailyCheck("08.01.2022");
        System.out.println(superduper.toString());
        System.out.println();
        System.out.println("Task: Cheese (here: gouda) will get removed when it has a quality rating lower than 30.");
        System.out.println("Task: Cheese loses a quality point every day and price gets adjusted accordingly.");
        System.out.println("Notice: Expired food (here: salmon) gets removed.");
        System.out.println("--------------------------------------------------");

        superduper.dailyCheck("21.02.2022");
        System.out.println(superduper.toString());
        System.out.println("Performing list update after expired products have been removed from shelves.");
        System.out.println();
        superduper.updateLists();
        System.out.println(superduper.toString());
        System.out.println();
        System.out.println("Task: Wine gains a quality point for every 10 days after its designated date with a maximum of 50.");
        System.out.println("Task: Wine does not expire and will not get removed.");
        System.out.println("Task: Wine does not change its price after a quality rating change and will not get removed.");
        System.out.println("--------------------------------------------------");
    }
}
