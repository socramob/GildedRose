import java.util.List;

public class TestableGildedRose extends GildedRose {
    public static List<ExtendableItem> getItems() {
        return items;
    }

    public static void setItems(List<ExtendableItem> newItems){
        items = newItems;
    }
}
