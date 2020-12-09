import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


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
        game.startGame()
        var playeCardAfterStart = game.getPlayerList()
        var dealerCardAfterStart = game.getDealerList()

        assertAll(
            {assertEquals(2, playeCardAfterStart.size)},
            {assertEquals(1, dealerCardAfterStart.size)}
        )
    }

    @Test
    fun `check if after clicking on hit player card list should be increased and in hand should change`(){
        var inHandBefore = game.getPlayerTotal()
        game.hit()
        var inHandAfter = game.getPlayerTotal()

        assertNotEquals(inHandAfter, inHandBefore)
    }

}