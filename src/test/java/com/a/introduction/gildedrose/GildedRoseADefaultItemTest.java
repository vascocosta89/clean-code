package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GildedRoseADefaultItemTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final int MAXIMUM_BRIE_QUALITY = 50;

    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final int DEFAULT_QUALITY = 4;
    private static final int NOT_EXPIRED_SELLIN = 16;
    private static final int EXPIRED_SELLIN = -1;
    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 1 when the item is not expired
     * and sell in should decrease by 1.
     */
    @Test
    public void unexpiredDefaultItem_qualityDecreasesBy1() {

        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expected = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELLIN - 1,DEFAULT_QUALITY - 1);

        assertItem(expected, app.items[0]);
    }

    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
     */
    @Test
    public void expiredDefaultItem_qualityDecreasesBy2() {
        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM,EXPIRED_SELLIN,DEFAULT_QUALITY);
        app.updateQuality();
        Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1,DEFAULT_QUALITY - 2);
        assertItem(expected,app.items[0]);
    }

    @Test
    public void unexpiredAgedBrie_qualityIncreasesBy1() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
        app.updateQuality();
        Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);
        assertItem(expected,app.items[0]);
    }
    @Test
    public void expiredAgedBrie_qualityIncreasesBy2() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);
        app.updateQuality();
        Item expected = new Item(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);
        assertItem(expected,app.items[0]);
    }

    @Test
    public void unexpiredAgedBrie_qualityDoesNotGoBeyondMaximum() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, MAXIMUM_BRIE_QUALITY);
        app.updateQuality();
        Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, MAXIMUM_BRIE_QUALITY);
        assertItem(expected,app.items[0]);
    }

    private void assertItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

    private GildedRose createGildedRoseWithOneItem(String itemType, int sellin, int quality) {
        Item item = new Item(itemType, sellin, quality);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        return app;
    }
}
