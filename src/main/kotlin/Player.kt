data class Player(
    val email: String,
    val password: String,
    var bankRoll: Int,
    val userName: String,
    val avatar: String? = null
) {

    fun deductBankRoll(points: Int){
        bankRoll -= points
    }
}
