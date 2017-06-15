package com.gildedrose

class GildedRose(val items: Array[Item]) {

  val conjured = "Conjured"
  val brie = "Aged Brie"
  val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  val sulfuras = "Sulfuras, Hand of Ragnaros"

  def updateQuality() {
    items.filterNot(_.name.matches(`sulfuras`)).map{
      currItem => handleNonLegendaryItems(currItem)
    }
  }

  private def handleNonLegendaryItems(item: Item): Item = {
    return item.name match {
      case `backstagePass` => alterBackstagePassQuality(item)
      case `brie` => alterBrieQuality(item)
      case `conjured` => alterConjuredQuality(item)
      case _ =>

        reduceSellIn(item)

        val reduction = if (item.sellIn > 0) 1 else 2

        decreaseQuality(item, reduction)
    }
  }

  private def decreaseQuality(item: Item, quantity: Int): Item = {
    for (_ <- 1 to quantity) {
      if (item.quality > 0) {
        item.quality = item.quality - 1
      }
    }
    item
  }

  private def reduceSellIn(item: Item): Unit = {
    if (item.sellIn > 0) {
      item.sellIn = item.sellIn - 1
    }
  }

  private def alterConjuredQuality(item: Item): Item = {
    reduceSellIn(item)
    decreaseQuality(item, 2)
    item
  }

  private def alterBrieQuality(item: Item): Item = {
    reduceSellIn(item)
    increaseQuality(item)
    item
  }

  private def alterBackstagePassQuality(item: Item): Item = {
    reduceSellIn(item)
    increaseQuality(item)

    if (item.sellIn < 10) {
      increaseQuality(item)

      if (item.sellIn < 5) {
        increaseQuality(item)
      }

      if (item.sellIn == 0) {
        item.quality = 0
      }
    }
    item
  }

  private def increaseQuality(item: Item) = {
    if (item.quality < 50) {
      item.quality = item.quality + 1
    }
  }
}