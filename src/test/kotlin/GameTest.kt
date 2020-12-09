import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


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

    }

}