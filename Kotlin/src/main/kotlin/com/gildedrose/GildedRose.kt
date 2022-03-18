package com.gildedrose

class GildedRose(var inventory: Array<Item>) {

    companion object {
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
        const val DAILY_QUALITY_CHANGE = 1
        const val TICKET_FIRST_INCREASE_DATE = 10
        const val TICKET_SECOND_INCREASE_DATE = 5
    }

    fun updateQuality() {
        for (item in inventory) {
            applyQualityChanges(item)
            reduceSellInDate(item)
            if (item.sellIn < 0) {
                applyQualityChanges(item)
            }
        }
    }

    private fun applyQualityChanges(item: Item) {
        when (item.name){
            "Backstage passes to a TAFKAL80ETC concert" -> {
                if (item.sellIn < 0) {
                    item.quality = MIN_QUALITY
                } else {
                    applyTicketQualityIncrease(item)
                }
            }
            "Aged Brie" -> { increaseQuality(item) }
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

