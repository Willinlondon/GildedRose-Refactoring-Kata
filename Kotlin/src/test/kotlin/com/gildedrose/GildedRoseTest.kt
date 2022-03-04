package com.gildedrose
import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test
    fun `Normal item should degrade in sell in days remaining after a daily update`() {
        val items = arrayOf(Item("Morning Glory Dew", 10, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("Morning Glory Dew", app.items[0].name)
        assertEquals(9, app.items[0].sellIn)
    }

    @Test
    fun `Normal item should degrade in quality after a daily update`() {
        val items = arrayOf(Item("Morning Glory Dew", 10, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("Morning Glory Dew", app.items[0].name)
        assertEquals(9, app.items[0].quality)
    }

    @Test
    fun `Normal item should degrade in quality after multiple daily updates`() {
        val items = arrayOf(Item("Morning Glory Dew", 10, 10))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Morning Glory Dew", app.items[0].name)
        assertEquals(5, app.items[0].quality)
    }

    @Test
    fun `Normal item should degrade in sell in days after multiple daily updates`() {
        val items = arrayOf(Item("Morning Glory Dew", 10, 10))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Morning Glory Dew", app.items[0].name)
        assertEquals(5, app.items[0].sellIn)
    }

    @Test
    fun `Normal item should not have negative quality after multiple days`() {
        val items = arrayOf(Item("Morning Glory Dew", 10, 2))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Morning Glory Dew", app.items[0].name)
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Normal item quality decreases twice as fast after sell in date`() {
        val items = arrayOf(Item("Morning Glory Dew", 2, 20))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Morning Glory Dew", app.items[0].name)
        assertEquals(12, app.items[0].quality)
    }

    @Test
    fun `Aged Brie should increase in quality after a daily update`() {
        val items = arrayOf(Item("Aged Brie", 10, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("Aged Brie", app.items[0].name)
        assertEquals(11, app.items[0].quality)
    }

    @Test
    fun `Aged Brie should double in quality after sell in`() {
        val items = arrayOf(Item("Aged Brie", 3, 10))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Aged Brie", app.items[0].name)
        assertEquals(17, app.items[0].quality)
    }

    @Test
    fun `Aged Brie should not exceed quality cap of 50`() {
        val items = arrayOf(Item("Aged Brie", 3, 45))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Aged Brie", app.items[0].name)
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun `Sulfuras, Hand of Ragnaros should not degrade in quality`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 3, 80))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name)
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun `Sulfuras, Hand of Ragnaros should not have a sell in date`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 3, 80))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name)
        assertEquals(3, app.items[0].sellIn)
    }

    @Test
    fun `Back stage passes drop to 0 quality after sell in date`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 30))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun `Back stage passes increase in quality by 1 each day over 20 days remaining for sell in`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 26, 10))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(15, app.items[0].quality)
    }

    @Test
    fun `Back stage passes increase in quality by 2 each day after 10 days remaining for sell in`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 10))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(20, app.items[0].quality)
    }

    @Test
    fun `Back stage passes increase in quality by 3 each day after 5 days remaining for sell in`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 10))
        val app = GildedRose(items)
        for (days in 1..5) {
            app.updateQuality()
        }
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(25, app.items[0].quality)
    }

    @Test
    fun `Back stage passes increase in quality with multiple days`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
        val app = GildedRose(items)
        for (days in 1..15) {
            app.updateQuality()
        }
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(40, app.items[0].quality)
    }

    @Test
    fun `Back stage passes should have quality reduced to 0 after sell in date has passed`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 15, 10))
        val app = GildedRose(items)
        for (days in 1..16) {
            app.updateQuality()
        }
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name)
        assertEquals(0, app.items[0].quality)
    }
}


