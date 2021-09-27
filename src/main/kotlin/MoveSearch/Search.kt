package MoveSearch

import Board.Board
import Board.Zobrist
import kotlin.math.max

class Search {

    fun findBestMove(board: Board, player: Int): Int{
        val zobrist = Zobrist()
        val zobristTable = zobrist.createZobristTable()
        val zobristMap = hashMapOf<Long, Int>()
        board.BoardRepresentation.ZobristKey = zobrist.createZobristKey(board.BoardRepresentation, zobristTable)

        return MiniMax(board, player, zobristMap)
    }

    fun MiniMax(board: Board, player: Int, zobristMap: HashMap<Long, Int>): Int {

        if (board.BoardRepresentation.GameOver){

            if (zobristMap.get(board.BoardRepresentation.ZobristKey) != null){
                // println("transposition found: ${board.BoardRepresentation.ZobristKey.toString(16)}")
                return zobristMap.get(board.BoardRepresentation.ZobristKey)!!

            }

            if (board.BoardRepresentation.CircleWon) {
                zobristMap.put(board.BoardRepresentation.ZobristKey, 99 * player)
                return 99 * player
            }
            if (board.BoardRepresentation.CrossWon) {
                zobristMap.put(board.BoardRepresentation.ZobristKey, -99 * player)
                return -99 * player
            }

            return 0
            }

        var maxScore = -99
        var move = -1


        board.BoardRepresentation.EmptyFields.toList().forEach {

            board.PushMove(it)
            val score = -MiniMax(board, -player, zobristMap)
            board.UndoMove()

            if (score >= maxScore){
                maxScore = score
                move = it

            }
        }

        return move
    }
}