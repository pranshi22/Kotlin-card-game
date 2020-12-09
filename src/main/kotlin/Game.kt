import java.lang.Exception

class Game(val player: Player) {

    private var hiddenCard: Any = ""

    fun getHiddenCard(): Any {
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

    fun getPlayerList(): MutableList<Any> {
        return playerList
    }

    private var dealerList = mutableListOf<Any>()

    fun getDealerList(): MutableList<Any> {
        return dealerList
    }

    private var playerTotal = 0

    fun getPlayerTotal(): Int {
        return playerTotal
    }

    private var tableChoosed: Table = table1

    fun getAvailableTables(): List<String> {
        return listOf(table1.name,table2.name,table3.name,table4.name,table5.name)
    }

    fun chooseTable(name: String){
        when(name){
            "Ego" -> tableChoosed = table1
            "Earth" -> tableChoosed = table2
            "Asgard" -> tableChoosed = table3
            "Vormir" -> tableChoosed = table4
            "Titan" -> tableChoosed = table5
        }

        tableChoosed.enterTable(player)
    }
    val deck = Card()

    fun startGame(){

        var distributeCards = deck.drawCards(4)
        hiddenCard = distributeCards[2]
        println(hiddenCard)

        playerList.add(distributeCards[0])
        playerList.add(distributeCards[1])
        dealerList.add(distributeCards[3])

        playerList.forEach{
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
        println("******************************************")
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
            if (playerTotal > 21){
                println("Dealer Wins!")
                dealerList.add(getHiddenCard())
                initialCards.replace("Dealer", dealerList)
            }
            println(initialCards)
        }
    }

    fun stand(){
        println("******************************************")
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
        while (dealerTotal<=17){
            var drawCard = deck.drawCards(1)[0]
            when(drawCard){
                is Int -> dealerTotal += drawCard
                "King","Queen","Jack" -> dealerTotal += 10
                "Ace" -> dealerTotal += if (dealerTotal+11 > 21) 1 else 11
            }
            dealerList.add(drawCard)
            initialCards.replace("Dealer", dealerList)
        }
        println(initialCards)
        if (dealerTotal == playerTotal && playerTotal < 21){
            if (playerTotal == 21){
                println("CheckMate")
            }
            else{
                println("Push")
            }
        }
        if (dealerTotal>playerTotal){
            if (dealerTotal > 21){
                println("Dealer got busted. You win, Your winning amount is added to your bank roll")
                tableChoosed.returnBet(player)
            }
            else{
                println("Sorry!! You Lost!!")
            }

        }
        else{
            if (playerTotal > 21){
                println("you got busted!! Dealer Win!")
            }
            else{
                println("You Win!! Your winning amount is added to your bank roll")
                tableChoosed.returnBet(player)
            }
        }
    }

}

fun main() {
    var player = Player("tony.stark@marvel.com", "edward", 1500, "Iron Man")
    var game = Game(player)
    val tables = game.getAvailableTables()
    println(tables)
    game.chooseTable(tables[2])
    game.startGame()
    game.hit()
    game.stand()
    println(player.bankRoll)

}