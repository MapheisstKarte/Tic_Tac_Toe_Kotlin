package Board

data class BoardRepresentation(
    val Fields: IntArray = IntArray(9),
    var OccupiedFields: MutableList<Int> = mutableListOf(),
    var EmptyFields: MutableList<Int> = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
    var SideToMove: Player = Player.Circle,
    var ZobristKey: Long = 0L,
    var GameOver: Boolean = false,
    var CircleWon: Boolean = false,
    var CrossWon: Boolean = false,
)