package com.gildedrose

class GildedRose(var inventory: Array<Item>) {

    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val TICKETS = "Backstage passes to a TAFKAL80ETC concert"
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
                if (item.quality > MIN_QUALITY) {
                    if (isNotSulfuras(item)) {
                        item.quality -= DAILY_QUALITY_CHANGE
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality += DAILY_QUALITY_CHANGE

                    if (item.name == TICKETS) {
                        if (item.sellIn <= TICKET_FIRST_INCREASE_DATE) {
                            if (item.quality < MAX_QUALITY) {
                                item.quality += DAILY_QUALITY_CHANGE
                            }
                        }

                        if (item.sellIn <= TICKET_SECOND_INCREASE_DATE) {
                            if (item.quality < MAX_QUALITY) {
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
                        if (item.quality > MIN_QUALITY) {
                            if (isNotSulfuras(item)) {
                                item.quality -= DAILY_QUALITY_CHANGE
                            }
                        }
                    } else {
                        item.quality = MIN_QUALITY
                    }
                } else {
                    if (item.quality < MAX_QUALITY) {
                        item.quality += DAILY_QUALITY_CHANGE
                    }
                }
            }
        }
    }

    private fun isNotAgedBrie(item: Item): Boolean {
        return item.name != AGED_BRIE
    }

    private fun isNotTickets(item: Item): Boolean {
        return item.name != TICKETS
    }

    private fun isNotSulfuras(item: Item): Boolean {
        return item.name != SULFURAS
    }

    private fun reduceSellInDate(item: Item) {
        if (item.name != SULFURAS) {
            item.sellIn -= DAY
        }
    }

}

