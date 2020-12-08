import  Player
import Table

class Game {

    private val table1 = Table("Ego", 1)
    private val table2 = Table("Earth", 2)
    private val table3 = Table("Asgard", 5)
    private val table4 = Table("Vormir", 20)
    private val table5 = Table("Titan", 100)

    fun getAvailableTables(): List<Table> {
        return listOf(table1,table2,table3,table4,table5)
    }
    val deck = Card()

    fun playGame(hits: Int){
        var dealerCards: List<Any>
        var playerCards: List<Any>
        var distributeCards = deck.drawCards(4)
        dealerCards = mutableListOf(distributeCards[2], distributeCards[3])
        playerCards = mutableListOf(distributeCards[0], distributeCards[1])
        for (i in 1..hits){
            playerCards.add(deck.drawCards(1)[0])
        }


    }

}

fun main() {
    var player = Player("pranshi.garg@hashedin.com", "hgdsg", 1500, "pranshi22")

}