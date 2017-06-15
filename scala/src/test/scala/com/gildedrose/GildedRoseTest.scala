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
}