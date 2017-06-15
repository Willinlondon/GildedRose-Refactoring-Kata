package com.gildedrose

class GildedRose(val items: Array[Item]) {

  val conjured = "Conjured"
  val brie = "Aged Brie"
  val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  val sulfuras = "Sulfuras, Hand of Ragnaros"

  def updateQuality() {
    for (i <- 0 until items.length) {
      items(i).name match {
        case `sulfuras` =>
        case `backstagePass` => alterBackstagePassQuality(i)
        case `brie` => alterBrieQuality(i)
        case `conjured` => alterConjuredQuality(i)
        case _ =>

          reduceSellIn(i)

          val reduction = if (items(i).sellIn > 0) 1 else 2

          decreaseQuality(i, reduction)
      }
    }
  }

  private def decreaseQuality(i: Int, quantity: Int) = {
    for (_ <- 1 to quantity) {
      if (items(i).quality > 0) {
        items(i).quality = items(i).quality - 1
      }
    }
  }

  def reduceSellIn(i: Int) = {
    if (items(i).sellIn > 0) {
      items(i).sellIn = items(i).sellIn - 1
    }
  }

  private def alterConjuredQuality(i: Int) = {
    reduceSellIn(i)
    decreaseQuality(i, 2)
  }

  private def alterBrieQuality(i: Int) = {
    reduceSellIn(i)
    increaseQuality(i)
  }

  private def alterBackstagePassQuality(i: Int) = {
    increaseQuality(i)

    if (items(i).sellIn < 11) {
      increaseQuality(i)

      if (items(i).sellIn < 6) {
        increaseQuality(i)
      }

      if (items(i).sellIn < 1) {
        items(i).quality = 0
      }

      reduceSellIn(i)
    }
  }

  private def increaseQuality(i: Int) = {
    if (items(i).quality < 50) {
      items(i).quality = items(i).quality + 1
    }
  }
}