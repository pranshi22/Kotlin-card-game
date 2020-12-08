import kotlin.random.Random

class Card {

    var total = 52
    var cards = mutableListOf(2,3,4,5,6,7,8,9,10,"Jack","Queen","King","Ace")
    var occurences = mutableListOf(4,4,4,4,4,4,4,4,4,4,4,4,4)

    fun drawCards(numberOfCards: Int): List<Any>{
        val cardList = mutableListOf<Any>()
        for (i in 1..numberOfCards) {
            var index = cards.indices.random()
            cardList += cards[index]
            occurences[index] -= 1
            if (occurences[index] == 0){
                cards.removeAt(index)
            }
        }
        return cardList
    }
}
