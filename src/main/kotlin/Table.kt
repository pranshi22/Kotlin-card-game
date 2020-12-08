import Card
import Player


class Table(val name: String, val betSize: Int) {

    fun enterTable(bet: Int){
        if (bet <= betSize){
            println("Welcome to the table")
        }
        else{
            println("You cannot enter this table as you don't have enough bet to play")
        }
    }
}