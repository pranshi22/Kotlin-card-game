import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class CardTest {

    val cards = listOf(2,3,4,5,6,7,8,9,10,"Jack","Queen","King","Ace")

    lateinit var cardObj : Card

    @BeforeEach
    fun `initialize card`(){
        cardObj = Card()
    }
    
    @Test
    fun `check if proper number of cards are drawn`(){
        var cardsDrawn = cardObj.drawCards(4)
        assertEquals(4,cardsDrawn.size)
    }

    @Test
    fun `check if cards are appropriate`() {
        var cardsDrawn = cardObj.drawCards(2)
        cardsDrawn.forEach{
            assert(cards.contains(it))
        }
    }
}