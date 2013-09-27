import java.util.List;

public class TestableGildedRose extends GildedRose {
    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(List<Item> newItems){
        items = newItems;
    }
}
