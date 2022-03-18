package com.gildedrose

class GildedRose(var inventory: Array<Item>) {

    companion object {
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
        const val DAILY_QUALITY_CHANGE = 1
        const val DAY = 1
        const val TICKET_FIRST_INCREASE_DATE = 10
        const val TICKET_SECOND_INCREASE_DATE = 5
    }

    fun updateQuality() {
        for (item in inventory) {
            if (isNotAgedBrie(item) && isNotTickets(item)) {
                if (isAboveMinQuality(item)) {
                    if (isNotSulfuras(item)) {
                        item.quality -= DAILY_QUALITY_CHANGE
                    }
                }
            } else {
                if (isBelowMaxQuality(item)) {
                    item.quality += DAILY_QUALITY_CHANGE

                    if (!isNotTickets(item)) {
                        if (item.sellIn <= TICKET_FIRST_INCREASE_DATE) {
                            if (isBelowMaxQuality(item)) {
                                item.quality += DAILY_QUALITY_CHANGE
                            }
                        }

                        if (item.sellIn <= TICKET_SECOND_INCREASE_DATE) {
                            if (isBelowMaxQuality(item)) {
                                item.quality += DAILY_QUALITY_CHANGE
                            }
                        }
                    }
                }
            }
            reduceSellInDate(item)

            if (item.sellIn < 0) {
                if (isNotAgedBrie(item)) {
                    if (isNotTickets(item)) {
                        if (isAboveMinQuality(item)) {
                            if (isNotSulfuras(item)) {
                                item.quality -= DAILY_QUALITY_CHANGE
                            }
                        }
                    } else {
                        item.quality = MIN_QUALITY
                    }
                } else {
                    if (isBelowMaxQuality(item)) {
                        item.quality += DAILY_QUALITY_CHANGE
                    }
                }
            }
        }
    }

    private fun isBelowMaxQuality(item: Item): Boolean {
        return item.quality < MAX_QUALITY
    }

    private fun isAboveMinQuality(item: Item): Boolean {
        return item.quality > MIN_QUALITY
    }

    private fun isNotAgedBrie(item: Item): Boolean {
        return item.name != "Aged Brie"
    }

    private fun isNotTickets(item: Item): Boolean {
        return item.name != "Backstage passes to a TAFKAL80ETC concert"
    }

    private fun isNotSulfuras(item: Item): Boolean {
        return item.name != "Sulfuras, Hand of Ragnaros"
    }

    private fun reduceSellInDate(item: Item) {
        if (isNotSulfuras(item)) {
            item.sellIn -= DAY
        }
    }

}

