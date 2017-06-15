package com.gildedrose

import org.scalatest._

class GildedRoseTest extends FlatSpec with Matchers {

  it should "degrade twice as fast when SellIn date is passed" in {
    val items = Array[Item](new Item("anyItem", 0, 2))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality shouldBe(0)
  }
}