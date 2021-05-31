import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private int price;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    //Edited Code
    public boolean isRestaurantOpen() {
        if (getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime)) {
            return true;
        } else {
            return false;
        }
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    //Edited Code
    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    //Edited Code
    private List<Item> addedItems = new ArrayList<>();
    public List<Item> getAddedItems() {
        return addedItems;
    }
    public List<Item> addToCart(String name) {
        for (Item item : getMenu()) {
            //Checking and Adding Item
            if (item.getName().equals(name)) {
                addedItems.add(item);
            }
        }
        //grandTotal(addedItems);
        //Sending complete list to add amount
        return addedItems;
    }

    //Edited Code
    public int grandTotal(List<Item> addedItems) {
        int cartAmount = 0;
        //Adding price of added items
        for (Item item : addedItems) {
            //Parsing amount and adding in cartAmount
            cartAmount += (int) item.getPrice();
        }
        return cartAmount;
    }
}