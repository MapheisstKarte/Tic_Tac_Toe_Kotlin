import Board.Board
import Board.Player
import MoveSearch.Search

fun main(args: Array<String>) {

    val board = Board()
    val search = Search()
    board.DrawBoard()
    println("Zobrist Key: ${board.BoardRepresentation.ZobristKey}")


    while(!board.BoardRepresentation.GameOver){

        if (board.BoardRepresentation.SideToMove == Player.Circle) {
            val move = readLine()!!.toInt()
            board.PushMove(move)
            board.DrawBoard()
            println("Zobrist Key: ${board.BoardRepresentation.ZobristKey}")

        } else {

            var time = System.currentTimeMillis()
            val move = search.findBestMove(board, -1)
            println("time to search for move: ${System.currentTimeMillis().minus(time)}")
            board.PushMove(move)
            board.DrawBoard()
            println("Zobrist Key: ${board.BoardRepresentation.ZobristKey}")
        }
    }
}


