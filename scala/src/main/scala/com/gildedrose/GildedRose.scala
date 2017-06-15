package com.gildedrose

class GildedRose(val items: Array[Item]) {

  val brie = "Aged Brie"
  val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  val sulfuras = "Sulfuras, Hand of Ragnaros"

  def updateQuality() {
    for (i <- 0 until items.length) {
      items(i).name match {
        case `sulfuras` =>
        case `backstagePass` => alterBackstagePassQuality(i)

        case _ =>


          if (items(i).name.equals(brie)) {
            increaseQuality(i)
          } else {
            if (items(i).quality > 0) {
              items(i).quality = items(i).quality - 1
            }
          }

          items(i).sellIn = items(i).sellIn - 1

          if (items(i).sellIn < 0) {
            if (!items(i).name.equals(brie)) {
              if (items(i).quality > 0) {
                items(i).quality = items(i).quality - 1
              }
            } else {
              increaseQuality(i)
            }
          }
      }
    }
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
      items(i).sellIn = items(i).sellIn - 1
    }
  }

  private def increaseQuality(i: Int) = {
    if (items(i).quality < 50) {
      items(i).quality = items(i).quality + 1
    }
  }
}