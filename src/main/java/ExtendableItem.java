public class ExtendableItem extends Item {

    protected final int defaultDegradationRate;

    public ExtendableItem(String name, int sellIn, int quality, int defaultDegradationRate) {
        super(name, sellIn, quality);
        this.defaultDegradationRate = defaultDegradationRate;
    }

    public ExtendableItem(String name, int sellIn, int quality) {
        this(name, sellIn, quality, 1);
    }

    void updateItem() {
        decrementSellIn();
        updateQuality();
    }

    void updateQuality() {
        if (getSellIn() < 0) {
            decrementQualityDownToZeroBy(2 * defaultDegradationRate);
        } else {
            decrementQualityDownToZeroBy(defaultDegradationRate);
        }
    }

    void incrementQualityUpTo50By(final int delta) {
        setQuality(Math.min(50, getQuality() + delta));
    }

    void decrementQualityDownToZeroBy(final int delta) {
        setQuality(Math.max(0, getQuality() - delta));
    }

    void decrementSellIn() {
        this.sellIn--;
    }

}
