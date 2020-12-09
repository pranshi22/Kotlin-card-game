import  Player
import Table

class Game {

    private var hiddenCard: Any = ""
        get() {
            return hiddenCard
        }

    private val table1 = Table("Ego", 1)
    private val table2 = Table("Earth", 2)
    private val table3 = Table("Asgard", 5)
    private val table4 = Table("Vormir", 20)
    private val table5 = Table("Titan", 100)

    private val initialCards = mutableMapOf("Player" to mutableListOf<Any>(), "Dealer" to mutableListOf<Any>(),
        "PlayerInHAnd" to 0
    )

    private var playerList = mutableListOf<Any>()
    private var dealerList = mutableListOf<Any>()

    private var playerTotal = 0

    fun getAvailableTables(): List<Table> {
        return listOf(table1,table2,table3,table4,table5)
    }
    val deck = Card()

    fun startGame(){

        var distributeCards = deck.drawCards(4)
        hiddenCard = distributeCards[2]

        playerList.add(distributeCards[0])
        playerList.add(distributeCards[1])
        dealerList.add(distributeCards[3])

        playerList?.forEach{
            when(it){
                is Int -> playerTotal += it
                "King","Queen","Jack" -> playerTotal += 10
                "Ace" -> playerTotal += if (playerTotal+11 > 21) 1 else 11
            }
        }
        initialCards.replace("Dealer", dealerList)
        initialCards.replace("Player", playerList)
        initialCards.replace("PlayerInHAnd", playerTotal)
        println(initialCards)

    }

    fun hit(){
        println("Player Choose to hit")
        println("******************************************")
        if (playerTotal>=21){
            println("You cannot hit anymore. Please start a new game or choose to stand")
        }
        else{
            var pullOneCard = deck.drawCards(1)[0]
            when(pullOneCard){
                is Int -> playerTotal += pullOneCard
                "King","Queen","Jack" -> playerTotal += 10
                "Ace" -> playerTotal += if (playerTotal+11 > 21) 1 else 11
            }
            playerList.add(pullOneCard)

            initialCards.replace("Player", playerList)
            initialCards.replace("PlayerInHAnd", playerTotal)
            println(initialCards)
            if (playerTotal > 21){
                println("Dealer Wins!")
            }
        }
    }

    fun stand(){
        println("Player choose to stand")
        println("******************************************")
        var dealerTotal = 0
        dealerList.add(hiddenCard)
        dealerList.forEach{
            when(it){
                is Int -> dealerTotal += it
                "King","Queen","Jack" -> dealerTotal += 10
                "Ace" -> dealerTotal += if (dealerTotal+11 > 21) 1 else 11
            }
        }
        while (playerTotal<=17){
            var drawCard = deck.drawCards(1)[0]
            when(drawCard){
                is Int -> dealerTotal += drawCard
                "King","Queen","Jack" -> dealerTotal += 10
                "Ace" -> dealerTotal += if (dealerTotal+11 > 21) 1 else 11
            }
        }
        if (dealerTotal == playerTotal){
            if (playerTotal == 21){
                println("CheckMate")
            }
            else{
                println("Push")
            }
        }
        if (dealerTotal>playerTotal){
            if (dealerTotal > 21){
                println("Dealer got busted. You win")
            }
            else{
                println("Soryy!! You Lost!!")
            }

        }
        else{
            if (playerTotal > 21){
                println("you got busted!! You Win!")
            }
            else{
                println("You Win!!")
            }
        }
    }

}

fun main() {
    var player = Player("pranshi.garg@hashedin.com", "hgdsg", 1500, "pranshi22")
    var game = Game()
    game.startGame()
}