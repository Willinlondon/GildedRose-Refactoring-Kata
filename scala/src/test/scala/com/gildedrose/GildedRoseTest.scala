package com.gildedrose

import org.scalatest._

class GildedRoseTest extends FlatSpec with Matchers {

  it should "degrade twice as fast when SellIn date is passed" in {
    val items = Array[Item](new Item("anyItem", 0, 2))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(0)
  }

  it should "never set the quality to negative" in {
    val items = Array[Item](new Item("anyItem", 1, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(0)
  }

  it should "increase the quality of Aged Brie over time" in {
    val items = Array[Item](new Item("Aged Brie", 1, 2))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(3)
  }

  it should "limit quality to 50" in {
    val items = Array[Item](new Item("Aged Brie", 1, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(50)
  }

  it should "never decrease the quality of Sulfuras" in {
    val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 1, 80))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(80)
  }

  it should "never have to sell Sulfuras" in {
    val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 1, 80))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn shouldBe(1)
  }

  it should "increase the value of backstage passes by 2 or 3 or drop to 0" in {
    val items = Array[Item](
      new Item("Backstage passes to a TAFKAL80ETC concert", 11, 40),
      new Item("Backstage passes to a TAFKAL80ETC concert", 6, 40),
      new Item("Backstage passes to a TAFKAL80ETC concert", 3, 40),
      new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40)
    )
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(41)
    app.items(1).quality shouldBe(42)
    app.items(2).quality shouldBe(43)
    app.items(3).quality shouldBe(0)
  }
}