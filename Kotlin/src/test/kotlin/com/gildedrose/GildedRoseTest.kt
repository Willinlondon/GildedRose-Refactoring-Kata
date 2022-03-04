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

}


