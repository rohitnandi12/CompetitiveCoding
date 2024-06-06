package com.interview;

/**
 # Requirements Specification

 We buy and sell only the finest goods, but our goods are constantly degrading
 in `Quality` as they approach their sell by date.

 We have a system in place that updates our inventory for us. Your task is to
 refactor the code to make it easier to add new features to our system so that
 we can begin selling new categories of items. These are the current rules:

 - All `items` have a `Quality` value which denotes how valuable the item is
 - The `Quality` of an item is never negative
 - The `Quality` of an item is never more than `50` [0 to 50]

 - All `items` have a `SellBy` value which denotes the number of days we have to sell the `items`
 - Once the sell by date has passed, `Quality` degrades twice as fast

 - At the end of each day our system lowers both values for every item

 - "Aged Brie" actually increases in `Quality` the older it gets. Increase by 1 when sellBy is non-negative, and 2 when negative

 - "Sulfuras", being a legendary item, never has to be sold or decreases in `Quality`
 - "Sulfuras" quality is always 80, which is an exception to the max 50 quality rule.

 - "Backstage passes", like aged brie, increases in `Quality` as its `SellIn` value approaches;
 - `Quality` increases by `2` when there are `10` days or less
 - `Quality` increases by `3` when there are `5` days or less; but
 - `Quality` drops to `0` after the concert
 */

// CANNOT CHANGE THIS!
class Item {
    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}

class Store {
    ItemWrapper[] itemWrappers;

    public Store(ItemWrapper[] itemWrappers) {
        this.itemWrappers = itemWrappers;
    }

    public void updateQuality() {
        for (ItemWrapper itemWrapper : itemWrappers) {
            itemWrapper.updateItem();
        }
    }
}

final class ApplicationConstants {
    public final static int MIN_POSSIBLE_QUALITY = 0;
    public final static int MAX_POSSIBLE_QUALITY = 50;
}
interface ItemUpdateStrategy {

    default boolean updateSellIn(Item item) {
        item.sellIn -= 1;
        return true;
    }
    boolean updateQuality(Item item);
}

class AgedBrieItemUpdateStrategy implements ItemUpdateStrategy {
    // - "Aged Brie" actually increases in `Quality` the older it gets. Increase by 1 when sellBy is non-negative, and 2 when negative
    public boolean updateQuality(Item item) {
        int value = 1;
        if(item.sellIn < 0) {
            value = 2;
        }
        item.quality = Math.max(ApplicationConstants.MIN_POSSIBLE_QUALITY, item.quality + value);
        return true;
    }
}

class SulfurasUpdateStrategy implements ItemUpdateStrategy {
    // - "Sulfuras", being a legendary item, never has to be sold or decreases in `Quality`
    // - "Sulfuras" quality is always 80, which is an exception to the max 50 quality rule.
    public boolean updateSellIn(Item item) {
        return true;
    }
    public boolean updateQuality(Item item) {
        return true;
    }
}

class PerishableItemUpdateStrategy implements ItemUpdateStrategy {
    // - At the end of each day our system lowers both values for every item
    public boolean updateQuality(Item item) {
        item.quality = Math.max(ApplicationConstants.MIN_POSSIBLE_QUALITY, item.quality - 1);
        return true;
    }
}

class BackstagePassesItemUpdateStrategy implements ItemUpdateStrategy {
    /*
    - "Backstage passes", like aged brie, increases in `Quality` as its `SellIn` value approaches;
    - `Quality` increases by `2` when there are `10` days or less
    - `Quality` increases by `3` when there are `5` days or less; but
    - `Quality` drops to `0` after the concert
  */
    public boolean updateQuality(Item item) {
        int value = 0;
        if(10 < item.sellIn) {
            value = 1;
        } else if(5 < item.sellIn) {
            value = 2;
        } else if(0 < item.sellIn) {
            value = 3;
        }

        item.quality = Math.min(ApplicationConstants.MAX_POSSIBLE_QUALITY, item.quality + value);
        return true;
    }
}

class ItemWrapper {
    Item item;
    ItemUpdateStrategy updateStrategy;
    ItemWrapper(Item item, ItemUpdateStrategy updateStrategy) {
        this.item = item;
        this.updateStrategy = updateStrategy;
    }

    public void updateItem() {
        // validate(item) // null check, data checks and all
        if(!updateStrategy.updateSellIn(item)) {
            throw new RuntimeException("SellIn didn't update");
        }

        if(!updateStrategy.updateQuality(item)) {
            throw new RuntimeException("Quality didn't update");
        }

        // replace with logger
        System.out.println(item.name + " updated successfully!!");
    }
}

public class BeerRiver {
    public static void main(String[] args) {
        // Sample products
        ItemWrapper[] items = {
                new ItemWrapper(new Item("Aged Brie", 5, 30), new AgedBrieItemUpdateStrategy()),
                new ItemWrapper(new Item("Sulfuras", 42, 80), new SulfurasUpdateStrategy()),
                new ItemWrapper(new Item("Aged Brie", 0, 10), new AgedBrieItemUpdateStrategy()),
                new ItemWrapper(new Item("The Lord of the Rings", 30, 7), new PerishableItemUpdateStrategy()),
                new ItemWrapper(new Item("Backstage passes", 12, 15), new BackstagePassesItemUpdateStrategy()),
        };

        Store store = new Store(items);
        store.updateQuality();
        for (ItemWrapper itemWrapper : items) {
            System.out.println(itemWrapper.item);
        }

        assertItem(items[0].item, 4, 31); // Aged Brie
        assertItem(items[1].item, 42, 80); // Sulfuras
        assertItem(items[2].item, -1, 12); // "Aged Brie"
        assertItem(items[3].item, 29, 6); // "The Lord of the Rings"
        assertItem(items[4].item, 11, 16); // "Backstage passes"
    }

    // private static void testStoreItems(Item[] items) {
    //   assertItem(items[0], 4, 31); // Aged Brie
    //   assertItem(items[1], 42, 80); // Sulfuras
    //   assertItem(items[2], -1, 12); // "Aged Brie"
    //   assertItem(items[3], 29, 6); // "The Lord of the Rings"
    //   assertItem(items[4], 11, 16); // "Backstage passes"
    // }

    private static void assertItem(Item item, int expectedSellIn, int expectedQuality) {
        assertSellIn(item, expectedSellIn);
        assertQuality(item, expectedQuality);
    }

    private static void assertQuality(Item item, int expectedQuality) {
        assert item.quality == expectedQuality;
    }

    private static void assertSellIn(Item item, int expectedSellIn) {
        assert item.sellIn == expectedSellIn;
    }
}