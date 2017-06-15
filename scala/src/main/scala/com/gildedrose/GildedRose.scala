package com.gildedrose

class GildedRose(val items: Array[Item]) {


  val brie = "Aged Brie"
  val backstagePass = "Backstage passes to a TAFKAL80ETC concert"
  val sulfuras = "Sulfuras, Hand of Ragnaros"

  def updateQuality() {
    for (i <- 0 until items.length) {
      if (!items(i).name.equals(brie)
        && !items(i).name.equals(backstagePass)) {
        if (items(i).quality > 0) {
          if (!items(i).name.equals(sulfuras)) {
            items(i).quality = items(i).quality - 1
          }
        }
      } else {
        if (items(i).quality < 50) {
          items(i).quality = items(i).quality + 1

          if (items(i).name.equals(backstagePass)) {
            if (items(i).sellIn < 11) {
              if (items(i).quality < 50) {
                items(i).quality = items(i).quality + 1
              }
            }

            if (items(i).sellIn < 6) {
              if (items(i).quality < 50) {
                items(i).quality = items(i).quality + 1
              }
            }
          }
        }
      }

      if (!items(i).name.equals(sulfuras)) {
        items(i).sellIn = items(i).sellIn - 1
      }

      if (items(i).sellIn < 0) {
        if (!items(i).name.equals(brie)) {
          if (!items(i).name.equals(backstagePass)) {
            if (items(i).quality > 0) {
              if (!items(i).name.equals(sulfuras)) {
                items(i).quality = items(i).quality - 1
              }
            }
          } else {
            items(i).quality = items(i).quality - items(i).quality
          }
        } else {
          if (items(i).quality < 50) {
            items(i).quality = items(i).quality + 1
          }
        }
      }
    }
  }
}