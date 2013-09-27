import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class GildedRoseTest {

    @Test
    public void dexterityVestAgesOneDayEachDay (){
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        TestableGildedRose.setItems(items);

        TestableGildedRose.updateQuality();

        assertThat("Sell in", items.get(0).getSellIn(), is(equalTo(9)));
    }

    @Test
    public void dexterityVestDecreasesOneInQualityEachDay (){
        assertItemQualityChangesBy(new Item("+5 Dexterity Vest", 10, 20), -1);
    }

    @Test
    public void agedBrieIncreaseOneQualityEachDay (){
        assertItemQualityChangesBy(new Item("Aged Brie", 2, 0), 1);
    }

    @Test
    public void agedBrieWithQualityFiftyDoesntChangeQuality (){
        assertItemQualityChangesBy(new Item("Aged Brie", 2, 50), 0);
    }

    @Test
    public void dexterityVestDecreasesDoubleInQualityAfterSellInDate (){
        assertItemQualityChangesBy(new Item("+5 Dexterity Vest", 0, 20), -2 * 1);
    }


    @Test
    public void BackstagepassesLoosesQualityAfterSellInDate (){
        assertItemQualityChangesBy(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20), -20);
    }

    @Test
    public void agedBrieIncreasesQualityByTwoIfBelowFiftyAfterSellInDate (){
        assertItemQualityChangesBy(new Item("Aged Brie", 0, 45), 2);
    }

    private void assertItemQualityChangesBy(final Item item, final int qualityDelta) {
        int previousQuality = item.getQuality();

        List<Item> items = new ArrayList<Item>();
        items.add(item);
        TestableGildedRose.setItems(items);

        TestableGildedRose.updateQuality();

        assertThat("Quality", items.get(0).getQuality(), is(equalTo(previousQuality+ qualityDelta)));
    }
}
