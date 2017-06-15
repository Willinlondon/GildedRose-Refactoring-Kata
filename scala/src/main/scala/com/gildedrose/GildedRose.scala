package com.gildedrose

class GildedRose(val items: Array[Item]) {

  val conjured = "Conjured"
  val brie = "Aged Brie"
  val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  val sulfuras = "Sulfuras, Hand of Ragnaros"

  def updateQuality() {
    items.filterNot(_.name.matches(`sulfuras`)).map {
      currItem => handleNonLegendaryItems(currItem)
    }
  }

  private def handleNonLegendaryItems(item: Item): Item = {
    val updatedItem = reduceSellIn(item)

    updatedItem.name match {
      case `backstagePass` => alterBackstagePassQuality(updatedItem)
      case `brie` => alterQuality(updatedItem, 1)
      case `conjured` => alterQuality(updatedItem, -2)
      case _ => alterQuality(updatedItem, if (updatedItem.sellIn > 0) -1 else -2)
    }
  }

  private def alterBackstagePassQuality(item: Item): Item = {

    val sellIn = item.sellIn
    val value = if (sellIn / 5d >= 2) 1 else if (sellIn / 5d >= 1) 2 else 3

    if (sellIn == 0) {
      item.quality = 0
      item
    } else
      alterQuality(item, value)

  }

  private def alterQuality(item: Item, quantity: Int): Item = {

    for (_ <- 1 to Math.abs(quantity)) {
      if (item.quality > 0 && item.quality < 50) {
        item.quality = item.quality + 1 * Math.signum(quantity).toInt
      }
    }

    item
  }

  private def reduceSellIn(item: Item): Item = {
    if (item.sellIn > 0) {
      item.sellIn = item.sellIn - 1
    }
    item
  }
}