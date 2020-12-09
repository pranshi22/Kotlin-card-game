import Card
import Player


data class Table(val name: String, val betSize: Int) {

    private var betHolded = 0
    fun enterTable(player: Player){
        if (player.bankRoll <= betSize){
            player.deductBankRoll(betSize)
            betHolded += betSize
            println("You have successfully joined the table and bet points of $betSize is deducted from your bankRoll")
        }
        else{
            println("You cannot enter this table as you don't have enough bet to play. Please choose any other table")
        }
    }

}