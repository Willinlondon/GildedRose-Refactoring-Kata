package com.gildedrose

class GildedRose(val inventory: Array<Item>) {

    companion object {
        const val MAX_QUALITY = 50
        const val MIN_QUALITY = 0
        const val TICKET_FIRST_INCREASE_DATE = 10
        const val TICKET_SECOND_INCREASE_DATE = 5
    }

    fun updateQuality() {
        for (item in inventory) {
            applyQualityChanges(item)
            if (isPastSellInDate(item)) {
                applyQualityChanges(item)
            }
            reduceSellInDate(item)
        }
    }

    private fun applyQualityChanges(item: Item) {
        when (item.name){
            "Backstage passes to a TAFKAL80ETC concert" -> { applyTicketQualityIncrease(item) }
            "Aged Brie" -> { increaseQuality(item) }
            else -> { reduceQuality(item) }
        }
    }

    private fun applyTicketQualityIncrease(item: Item) {
        when {
            item.sellIn <= 0 -> { item.quality = 0 }
            item.sellIn <= TICKET_SECOND_INCREASE_DATE -> {
                repeat(3) { increaseQuality(item) }
            }
            item.sellIn <= TICKET_FIRST_INCREASE_DATE -> {
                repeat(2) { increaseQuality(item) }
            }
            else -> { increaseQuality(item) }
        }
    }

    private fun reduceQuality(item: Item) {
        if (isNotSulfuras(item) && isAboveMinQuality(item)) {
            item.quality --
        }
    }

    private fun increaseQuality(item: Item) {
        if (item.isBelowMaxQuality()) {
            item.quality ++
        }
    }

    private fun isPastSellInDate(item: Item): Boolean {
        return item.sellIn <= 0
    }

    private fun Item.isBelowMaxQuality(): Boolean {
        return quality < MAX_QUALITY
    }

    private fun isAboveMinQuality(item: Item): Boolean {
        return item.quality > MIN_QUALITY
    }

    private fun isNotSulfuras(item: Item): Boolean {
        return item.name != "Sulfuras, Hand of Ragnaros"
    }

    private fun reduceSellInDate(item: Item) {
        if (isNotSulfuras(item)) {
            item.sellIn --
        }
    }
}

