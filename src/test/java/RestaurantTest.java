import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    //Edited Code
    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant=new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    //Edited Code
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant SpiedRestaurant=Mockito.spy(restaurant);
        Mockito.when(SpiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));

        boolean statusOfRestaurant=SpiedRestaurant.isRestaurantOpen();
        assertEquals(true,statusOfRestaurant);
    }

    //Edited Code
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant SpiedRestaurant=Mockito.spy(restaurant);
        Mockito.when(SpiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:29:00"));

        boolean statusOfRestaurant=SpiedRestaurant.isRestaurantOpen();
        assertEquals(false,statusOfRestaurant);

    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    /*@Test
    public void If_added_119Rs_SweetCornSoup_addedToCart_Only_119Rs_should_be_Returned(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);

        restaurant.addToCart("Sweet corn soup");

        int grandTotal=restaurant.grandTotal(restaurant.getAddedItems());
        assertEquals(119,grandTotal);
    }

    @Test
    public void If_added_119Rs_SweetCornSoup_and_269Rs_VegetableLasagne_addedToCart_388Rs_should_be_Returned(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);

        restaurant.addToCart("Sweet corn soup");
        restaurant.addToCart("Vegetable lasagne");

        int grandTotal=restaurant.grandTotal(restaurant.getAddedItems());
        assertEquals(388,grandTotal);
    }*/

    /*@Test
    public void check_menu_assigned_to_each_restaurant_individual_AND_not_generic_menu_for_all_restaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant RealVadaPav=new Restaurant("Real VadaPav","Chennai",openingTime,closingTime);
        Restaurant CafeBahar=new Restaurant("Cafe Bahar", "Hyderabad",openingTime,closingTime);

        RealVadaPav.addToMenu("Vada pav",119);
        RealVadaPav.addToMenu("Samosa", 269);
        RealVadaPav.addToMenu("Bhel",319);

        CafeBahar.addToMenu("Veg Biriyani",120);
        CafeBahar.addToMenu("Chicken Biriyani",150);
        CafeBahar.addToMenu("Mutton Biriyani",180);

        assertEquals("Vada pav",RealVadaPav.getMenu().get(0).getName());
        assertEquals("Samosa",RealVadaPav.getMenu().get(1).getName());
        assertEquals("Bhel",RealVadaPav.getMenu().get(2).getName());

        assertEquals("Veg Biriyani",CafeBahar.getMenu().get(0).getName());
        assertEquals("Chicken Biriyani",CafeBahar.getMenu().get(1).getName());
        assertEquals("Mutton Biriyani",CafeBahar.getMenu().get(2).getName());

    }*/

}