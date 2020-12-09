import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class PlayerTest {

    lateinit var player: Player

    @BeforeEach
    fun `initializing player object`(){
        player = Player("bruce.banner@marvel.com", "anger", 500, "Hulk")
    }

    @Test
    fun `check if bankroll is reduced after deducting the amount`(){
        var amountBeforeDeduction = player.bankRoll
        player.deductBankRoll(200)
        var amountAfterDeduction = player.bankRoll

        assertAll(
            { assertNotEquals(amountBeforeDeduction, amountAfterDeduction)},
            { assertEquals(300, amountAfterDeduction)}
        )
    }

    @Test
    fun `check if bankroll is increased after adding the amount`(){
        var amountBeforeAdd = player.bankRoll
        player.addAmountToBankRoll(200)
        var amountAfterAdd = player.bankRoll

        assertAll(
            { assertNotEquals(amountBeforeAdd, amountAfterAdd)},
            { assertEquals(700, amountAfterAdd)}
        )
    }

}