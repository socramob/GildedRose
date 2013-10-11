import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    protected static List<ExtendableItem> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        System.out.println("OMGHAI!");
		
        items = new ArrayList<ExtendableItem>();
        items.add(new ExtendableItem(DEXTERITY_VEST, 10, 20));
        items.add(agedBrie(2, 0));
        items.add(new ExtendableItem(ELIXIR_OF_THE_MONGOOSE, 5, 7));
        items.add(sulfuras(0, 80));
        items.add(backstagePasses(15, 20));
        items.add(new ExtendableItem(CONJURED_MANA_CAKE, 3, 6));

        updateQuality();
    }

    public static ExtendableItem conjuredItem(int sellIn, int quality) {
        return new ExtendableItem(CONJURED_MANA_CAKE, sellIn, quality, 2);
    }

    public static ExtendableItem sulfuras(final int sellIn, final int quality) {
        return new ExtendableItem(SULFURAS_HAND_OF_RAGNAROS, sellIn, quality) {
            @Override
            void updateItem() { }
        };
    }

    public static ExtendableItem agedBrie(final int sellIn, final int quality) {
        return new ExtendableItem(AGED_BRIE, sellIn, quality) {
            void updateQuality() {
                if (getSellIn() < 0) {
                    incrementQualityUpTo50By(2);
                } else {
                    incrementQualityUpTo50By(1);
                }
            }
        };
    }

    public static ExtendableItem backstagePasses(final int sellIn, final int quality) {
        return new ExtendableItem(BACKSTAGE_PASSES, sellIn, quality) {
            void updateQuality() {
                if (getSellIn() < 0) {
                    setQuality(0);
                } else if (getSellIn() < 5) {
                    incrementQualityUpTo50By(3);
                } else if (getSellIn() < 10) {
                    incrementQualityUpTo50By(2);
                } else {
                    incrementQualityUpTo50By(1);
                }
            }
        };
    }


    public static void updateQuality()
    {
        for (ExtendableItem currentItem : items) {
            currentItem.updateItem();
        }
    }
}
