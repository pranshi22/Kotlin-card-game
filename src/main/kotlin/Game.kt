class Game(val player: Player) {

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

}