import kotlin.random.Random

class Card {

    var total = 52
    var carDeck = mutableMapOf("2" to 4, "3" to 4)
    var cards = mutableListOf(2,3,4,5,6,7,8,9,10,"Jack","Queen","King","Ace")
    var occurences = mutableListOf(4,4,4,4,4,4,4,4,4,4,4,4,4)



    fun drawCards(numberOfCards: Int): MutableList<Any>{
        val cardList = mutableListOf<Any>()
        for (i in 1..numberOfCards) {
            var index = cards.indices.random()
            cardList += cards[index]
            occurences[index] -= 1
            if (occurences[index] == 0){
                cards.removeAt(index)
                occurences.removeAt(index)
            }
        }
        return cardList
    }
}

//fun main() {
//    var card = Card()
//    println(card.carDeck["2"]?.minus(1)?.let { card.carDeck.replace("2", it) })
//    println(card.carDeck)
//}