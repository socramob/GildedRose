import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class GildedRoseTest {

    @Test
    public void sulfurasNeverAges() throws Exception {
        List<ExtendableItem> items = new ArrayList<ExtendableItem>();
        items.add(GildedRose.sulfuras(0, 80));
        TestableGildedRose.setItems(items);

        TestableGildedRose.updateQuality();

        assertThat("Sell in", items.get(0).getSellIn(), is(equalTo(0)));
    }

    @Test
    public void conjuredItemsAgeTwiceAsFastAsNormalItems() {
        assertItemQualityChangesBy(GildedRose.conjuredItem(10, 2), -2);
        assertItemQualityChangesBy(GildedRose.conjuredItem(-10, 4), -4);
        assertItemQualityChangesBy(GildedRose.conjuredItem(-10, 2), -2);
    }

    @Test
    public void dexterityVestAgesOneDayEachDay (){
        List<ExtendableItem> items = new ArrayList<ExtendableItem>();
        items.add(new ExtendableItem("+5 Dexterity Vest", 10, 20));
        TestableGildedRose.setItems(items);

        TestableGildedRose.updateQuality();

        assertThat("Sell in", items.get(0).getSellIn(), is(equalTo(9)));
    }

    @Test
    public void obviouslyBackstagePassesAreWorthlessWhenTheShowIsOver() {
        assertItemQualityChangesBy(GildedRose.backstagePasses(0, 20), -20);
    }

    @Test
    public void itShouldIncreaseTheQualityOfABackstagePassByTwoStartingWithTenDaysLeftIncludingTheZerothDay() {
        assertItemQualityChangesBy(GildedRose.backstagePasses(10, 20), 2);
        assertItemQualityChangesBy(GildedRose.backstagePasses(11, 20), 1);
    }

    @Test
    public void itShouldIncreaseTheQualityOfABackstagePassByThreeStartingWithFiveDaysLeftIncludingTheZerothDay() {
        assertItemQualityChangesBy(GildedRose.backstagePasses(5, 20), 3);
        assertItemQualityChangesBy(GildedRose.backstagePasses(6, 20), 2);
    }



    @Test
    public void dexterityVestDecreasesOneInQualityEachDay (){
        assertItemQualityChangesBy(new ExtendableItem("+5 Dexterity Vest", 10, 20), -1);
    }

    @Test
    public void agedBrieIncreaseOneQualityEachDay (){
        assertItemQualityChangesBy(GildedRose.agedBrie(2, 0), 1);
    }

    @Test
    public void agedBrieIncreasesTwoQualitiesEachDayAfterItsSellInDate() {
        assertItemQualityChangesBy(GildedRose.agedBrie(-2, 5), 2);
    }

    @Test
    public void agedBrieWithQualityFiftyDoesntChangeQuality (){
        assertItemQualityChangesBy(GildedRose.agedBrie(2, 50), 0);
    }

    @Test
    public void dexterityVestDecreasesDoubleInQualityAfterSellInDate (){
        assertItemQualityChangesBy(new ExtendableItem("+5 Dexterity Vest", 0, 20), -2 * 1);
    }


    @Test
    public void backstagePassesLoosesQualityAfterSellInDate (){
        assertItemQualityChangesBy(GildedRose.backstagePasses(0, 20), -20);
    }

    @Test
    public void agedBrieIncreasesQualityByTwoIfBelowFiftyAfterSellInDate (){
        assertItemQualityChangesBy(GildedRose.agedBrie(0, 45), 2);
    }

    private void assertItemQualityChangesBy(final ExtendableItem item, final int qualityDelta) {
        int previousQuality = item.getQuality();

        List<ExtendableItem> items = new ArrayList<ExtendableItem>();
        items.add(item);
        TestableGildedRose.setItems(items);

        TestableGildedRose.updateQuality();

        assertThat("Quality", items.get(0).getQuality(), is(equalTo(previousQuality+ qualityDelta)));
    }
}
