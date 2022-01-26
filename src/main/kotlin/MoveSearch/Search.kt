package MoveSearch

import Board.Board
import Board.Zobrist



data class SearchResult(
    var score: Int  = 0,
    var move: Int = -1,
)

class Search {



    fun findBestMove(board: Board, player: Int): SearchResult{
        val zobrist = Zobrist()
        val zobristTable = zobrist.createZobristTable()
        val zobristMap = hashMapOf<Long, Int>()
        board.BoardRepresentation.ZobristKey = zobrist.createZobristKey(board.BoardRepresentation, zobristTable)

        return MiniMax(board, player, zobristMap)
    }

    fun MiniMax(board: Board, player: Int, zobristMap: HashMap<Long, Int>): SearchResult {


        var maxScore = -99
        var move = -1
        var calculatedPositions = 0

        if (board.BoardRepresentation.GameOver){

            if (board.BoardRepresentation.CircleWon) {
                return SearchResult(score = 99 * player, move = move)
            }
            if (board.BoardRepresentation.CrossWon) {
                return SearchResult(score = -99 * player, move = move)
            }

            return SearchResult(score = 0, move = move)
            }

        board.BoardRepresentation.EmptyFields.toList().forEach {

            board.PushMove(it)

            var score = 0
            when(zobristMap.get(board.BoardRepresentation.ZobristKey)){
                 null -> {
                     score = -MiniMax(board, -player, zobristMap).score
                     zobristMap.put(board.BoardRepresentation.ZobristKey, score)

                 }
                 else -> {
                     score = zobristMap.get(board.BoardRepresentation.ZobristKey)!!0
                 }
            }

            board.UndoMove()

            if (score >= maxScore){
                maxScore = score
                move = it

            }
        }

        return SearchResult(score = maxScore, move = move)
    }
}