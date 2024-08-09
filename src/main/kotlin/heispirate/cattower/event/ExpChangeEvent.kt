package heispirate.cattower.event

data class ExpChangeEvent(
    val userId: Long,
    val exp: Int,
    val type: ExpChangeType
)

enum class ExpChangeType {
    GAIN, LOSE
}