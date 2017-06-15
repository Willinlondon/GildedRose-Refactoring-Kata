package com.gildedrose

import org.scalatest._

class GildedRoseTest extends FlatSpec with Matchers {

  private val anyItem = new Item("anyItem", 0, 2)
  private val brie = new Item("Aged Brie", 1, 2)
  private val highQualityBrie = new Item("Aged Brie", 1, 50)
  private val sulfuras = new Item("Sulfuras, Hand of Ragnaros", 1, 80)

  private def backstagePassInDays(days: Int) = {
    new Item("Backstage passes to a TAFKAL80ETC concert", days, 40)
  }

  private def checkQuality(item: Item, expectedValue: Int) = {
    item.quality shouldBe expectedValue
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
}