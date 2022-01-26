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
            println("Zobrist Key: ${board.BoardRepresentation.ZobristKey.toString(16)}")

        } else {

            val time = System.currentTimeMillis()
            val searchResult = search.findBestMove(board, -1)
            println("time to search for move: ${System.currentTimeMillis().minus(time)}")
            board.PushMove(searchResult.move)
            board.DrawBoard()
            println("Evaluated Score: ${-searchResult.score}")
            println("Zobrist Key: ${board.BoardRepresentation.ZobristKey.toString(16)}")
        }
    }
}


