package com.gildedrose

import org.scalatest._

class GildedRoseTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  var anyItem, brie, freshBrie, highQualityBrie, sulfuras: Item = null

  override def beforeEach(): Unit = {
    anyItem = new Item("anyItem", 0, 2)
    brie = new Item("Aged Brie", 1, 2)
    freshBrie = new Item("Aged Brie", 0, 0)
    highQualityBrie = new Item("Aged Brie", 1, 50)
    sulfuras = new Item("Sulfuras, Hand of Ragnaros", 1, 80)
  }

  private def backstagePassInDays(days: Int) = {
    new Item("Backstage passes to a TAFKAL80ETC concert", days, 40)
  }

  private def checkQuality(item: Item, expectedValue: Int) = {
    item.quality shouldBe expectedValue
  }

  private def checkSellIn(item: Item, expectedValue: Int): Any = {
    item.sellIn shouldBe(expectedValue)
  }

  it should "always decrease sellIn unless it is zero or sulfuras" in {
    val items = Array[Item](anyItem, brie, freshBrie, sulfuras)
    val app = new GildedRose(items)
    app.updateQuality()
    checkSellIn(app.items(0), 0)
    checkSellIn(app.items(1), 0)
    checkSellIn(app.items(2), 0)
    checkSellIn(app.items(3), 1)
  }

  it should "degrade twice as fast when SellIn date is passed" in {
    val items = Array[Item](anyItem)
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 0)
  }

  it should "never set the quality to negative" in {
    val items = Array[Item](anyItem)
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 0)
  }

  it should "increase the quality of Aged Brie over time" in {
    val items = Array[Item](brie)
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 3)
  }

  it should "limit quality to 50" in {
    val items = Array[Item](highQualityBrie)
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 50)
  }

  it should "never decrease the quality of Sulfuras" in {
    val items = Array[Item](sulfuras)
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 80)
  }

  it should "never have to sell Sulfuras" in {
    val items = Array[Item](sulfuras)
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn shouldBe 1
  }

  it should "increase the value of backstage passes by 2 or 3 or drop to 0" in {
    val items = Array[Item](
      backstagePassInDays(11),
      backstagePassInDays(6),
      backstagePassInDays(3),
      backstagePassInDays(0)
    )
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 41)
    checkQuality(app.items(1), 42)
    checkQuality(app.items(2), 43)
    checkQuality(app.items(3), 0)
  }

  it should "decrease the value of conjured items twice as fast" in {
    val items = Array[Item](
      new Item("Conjured", 2, 4)
    )
    val app = new GildedRose(items)
    app.updateQuality()
    checkQuality(app.items(0), 2)
  }
}