package com.gildedrose

class GildedRose(val inventory: Array<Item>) {

    companion object {
        const val MAX_QUALITY = 50
        const val TICKET_FIRST_INCREASE_DATE = 10
        const val TICKET_SECOND_INCREASE_DATE = 5
    }

    fun updateQuality() {
        for (item in inventory) {
            item.applyQualityChanges()
            item.reduceSellInDate()
        }
    }

    private fun Item.applyQualityChanges() {
        when (this.name){
            "Backstage passes to a TAFKAL80ETC concert" -> this.applyTicketQualityIncrease()
            "Aged Brie" -> this.increaseQuality()
            "Sulfuras, Hand of Ragnaros" -> this.sellIn ++
            else -> this.reduceQuality()
        }
    }

    private fun Item.applyTicketQualityIncrease() {
        when {
            this.sellIn <= 0 -> { this.quality = 0 }
            this.sellIn <= TICKET_SECOND_INCREASE_DATE -> repeat(3) { this.increaseQuality() }
            this.sellIn <= TICKET_FIRST_INCREASE_DATE -> repeat(2) { this.increaseQuality() }
            else -> this.increaseQuality()
        }
    }

    private fun Item.reduceQuality() {
        if (this.isAboveMinQuality()) {
            this.quality --
            if (this.isPastSellInDate()) {
                this.quality --
            }
        }
    }

    private fun Item.increaseQuality() {
        if (this.isBelowMaxQuality()) {
            this.quality ++
            if (this.isPastSellInDate()) {
                this.quality ++
            }
        }
    }

    private fun Item.isPastSellInDate(): Boolean {
        return sellIn <= 0
    }

    private fun Item.isBelowMaxQuality(): Boolean {
        return quality < MAX_QUALITY
    }

    private fun Item.isAboveMinQuality(): Boolean {
        return quality > 0
    }

    private fun Item.reduceSellInDate() {
        this.sellIn --
    }
}

