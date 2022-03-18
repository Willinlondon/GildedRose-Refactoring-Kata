package com.gildedrose

class GildedRose(var inventory: Array<Item>) {

    companion object {
        const val TICKETS = "Backstage passes to a TAFKAL80ETC concert"
        const val AGED_BRIE = "Aged Brie"
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
        const val DAILY_QUALITY_CHANGE = 1
        const val TICKET_FIRST_INCREASE_DATE = 10
        const val TICKET_SECOND_INCREASE_DATE = 5
    }

    fun updateQuality() {
        for (item in inventory) {
            when (item.name){
                TICKETS -> { applyTicketQualityIncrease(item) }
                AGED_BRIE -> { increaseQuality(item) }
                else -> { reduceQuality(item) }
            }
            reduceSellInDate(item)
            if (item.sellIn < 0) {
                sellInDateReached(item)
            }
        }
    }

    private fun sellInDateReached(item: Item) {
        when (item.name) {
            TICKETS -> { item.quality = MIN_QUALITY }
            AGED_BRIE -> { increaseQuality(item) }
            else -> { reduceQuality(item) }
        }
    }

    private fun applyTicketQualityIncrease(item: Item) {
        increaseQuality(item)
        if (item.sellIn <= TICKET_FIRST_INCREASE_DATE) {
                increaseQuality(item)
        }
        if (item.sellIn <= TICKET_SECOND_INCREASE_DATE) {
                increaseQuality(item)
        }
    }

    private fun reduceQuality(item: Item) {
        if (isNotSulfuras(item) && isAboveMinQuality(item)) {
                item.quality -= DAILY_QUALITY_CHANGE
        }
    }

    private fun increaseQuality(item: Item) {
        if (isBelowMaxQuality(item)) {
            item.quality += DAILY_QUALITY_CHANGE
        }
    }

    private fun isBelowMaxQuality(item: Item): Boolean {
        return item.quality < MAX_QUALITY
    }

    private fun isAboveMinQuality(item: Item): Boolean {
        return item.quality > MIN_QUALITY
    }

    private fun isNotSulfuras(item: Item): Boolean {
        return item.name != "Sulfuras, Hand of Ragnaros"
    }

    private fun reduceSellInDate(item: Item) {
        if (isNotSulfuras(item)) {
            item.sellIn -= 1
        }
    }

}

