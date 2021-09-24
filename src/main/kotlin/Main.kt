import Board.Board

fun main(args: Array<String>) {

    val board = Board()
    println(board.CountPositions())
    board.DrawBoard()

    while(!board.BoardRepresentation.GameOver){
        val move = readLine()!!.toInt()
        board.PushMove(move)
        board.DrawBoard()

    }




}


