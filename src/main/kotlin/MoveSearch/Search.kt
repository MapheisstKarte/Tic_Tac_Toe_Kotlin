package MoveSearch

import Board.Board
import Board.Zobrist
import kotlin.math.max


data class SearchResult(
    var score: Int  = 0,
    var move: Int = -1,
    var calculatedPositions: Int = 0
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


            if (zobristMap.get(board.BoardRepresentation.ZobristKey) != null){
                // println("transposition found: ${board.BoardRepresentation.ZobristKey.toString(16)}")
                return SearchResult(score = zobristMap.get(board.BoardRepresentation.ZobristKey)!!, move = move, calculatedPositions = calculatedPositions)

            }

            calculatedPositions += 1

            if (board.BoardRepresentation.CircleWon) {
                zobristMap.put(board.BoardRepresentation.ZobristKey, 99 * player)

                return SearchResult(score = 99 * player, move = move, calculatedPositions = calculatedPositions)
            }
            if (board.BoardRepresentation.CrossWon) {
                zobristMap.put(board.BoardRepresentation.ZobristKey, -99 * player)
                return SearchResult(score = -99 * player, move = move, calculatedPositions = calculatedPositions)
            }

            return SearchResult(score = 0, move = move, calculatedPositions = calculatedPositions)
            }

        board.BoardRepresentation.EmptyFields.toList().forEach {

            board.PushMove(it)
            val searchResult = MiniMax(board, -player, zobristMap)
            val score = -searchResult.score
            calculatedPositions += searchResult.calculatedPositions

            board.UndoMove()

            if (score >= maxScore){
                maxScore = score
                move = it

            }
        }

        return SearchResult(score = maxScore, move = move, calculatedPositions = calculatedPositions)
    }
}