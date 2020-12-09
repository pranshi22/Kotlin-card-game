class Game(val player: Player) {
    /**
     * This is the main class from where our game will be started. This class will take care of
     * all the rules and classes needed to play game.
     * */
    private var hiddenCard: Any = ""

    /**
     * This function will return us hidden card
     * */
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

    /**
     * This function will return us list of the cards player has
     * */
    fun getPlayerList(): MutableList<Any> {
        return playerList
    }

    private var dealerList = mutableListOf<Any>()

    /**
     * This function will return us cards which dealer has excluding hidden card before stand
     * and including it after stand
     * */
    fun getDealerList(): MutableList<Any> {
        return dealerList
    }

    private var playerTotal = 0

    /**
     * This function will return us total sum available in player's hand
     * */
    fun getPlayerTotal(): Int {
        return playerTotal
    }

    private var tableChoosed: Table = table1

    /**
     * This function will return the user whichever tables are present
     * */
    fun getAvailableTables(): List<String> {
        return listOf(table1.name,table2.name,table3.name,table4.name,table5.name)
    }

    /**
     * This function will allow the user the table on which he wants to play
     * */
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

    /**
     * The game will be started once this is called. Both dealer and Player will be
     * assigned with 2-2 players each
     * */
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

    /**
     * Once user will call this he will get one card in his hand
     * */
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

    /**
     * Once the user calls this means he no more wants to draw the card. Now here dealer wil draw the cards
     * until have sum of 17 or more in his hand. Then accordingly winner will be announced.
     * */
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