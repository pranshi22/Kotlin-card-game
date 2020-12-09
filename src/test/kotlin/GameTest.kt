import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class GameTest {

    lateinit var game: Game
    lateinit var player: Player

    @BeforeEach
    fun `initialize required classes`(){
        player = Player("donald.blake@marvel.com", "hammer", 10, "Thor")
        game = Game(player)

    }

    @Test
    fun `check if correct table list is returned`() {
        var expectedList = listOf("Ego", "Earth", "Asgard", "Vormir", "Titan")
        var receivedList = game.getAvailableTables()
        assertEquals(expectedList, receivedList)
    }

    @Test
    fun `check after starting game user should have 2 cards in his hands exposed and dealer one card exposed`(){

    }

    @Test
    fun `check if after clicking on hit player card list should be increased and in hand should change`(){

    }

}