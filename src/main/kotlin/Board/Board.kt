package Board

import java.lang.Exception

class Board {

    var BoardRepresentation: BoardRepresentation = BoardRepresentation()
    var MoveStack: MutableList<Int> = mutableListOf()
    val zobrist = Zobrist()
    val zobristTable = zobrist.createZobristTable()

    fun PushMove(field: Int) {

        if (field !in BoardRepresentation.EmptyFields) {
            throw Exception("Illegal Move: Board.Field ${field} is occupied")
        }
        if (BoardRepresentation.GameOver) {
            throw Exception("Illegal Move: Game is Over")
        }


        // decides by which player the square will be occupied
        // changes the side to move

        when (BoardRepresentation.SideToMove) {
            Player.Circle -> {
                BoardRepresentation.Fields[field] = Field.Circle.FieldValue
                BoardRepresentation.ZobristKey = BoardRepresentation.ZobristKey xor zobristTable[0][field]
                BoardRepresentation.SideToMove = Player.Cross
            }
            Player.Cross -> {
                BoardRepresentation.Fields[field] = Field.Cross.FieldValue
                BoardRepresentation.ZobristKey = BoardRepresentation.ZobristKey xor zobristTable[1][field]
                BoardRepresentation.SideToMove = Player.Circle
            }

        }

        // adds the given field to the occupied fields
        // and removes the field from the empty fields

        BoardRepresentation.OccupiedFields.add(field)
        BoardRepresentation.EmptyFields.remove(field)
        MoveStack.add(field)
        CheckResult()

    }

    fun UndoMove() {
        if (MoveStack.isEmpty()) {
            throw Exception("No move has been made!")
        }

        // looks up the last move made and clears the field (undoing the move)
        // adds the field back to the empty fields and removes it from the occupied fields
        if (BoardRepresentation.Fields[MoveStack.last()] == Field.Circle.FieldValue){
            BoardRepresentation.ZobristKey = BoardRepresentation.ZobristKey xor zobristTable[0][MoveStack.last()]
        } else if (BoardRepresentation.Fields[MoveStack.last()] == Field.Cross.FieldValue){
            BoardRepresentation.ZobristKey = BoardRepresentation.ZobristKey xor zobristTable[1][MoveStack.last()]
        }

        BoardRepresentation.Fields[MoveStack.last()] = Field.Empty.FieldValue


        BoardRepresentation.OccupiedFields.remove(MoveStack.last())
        BoardRepresentation.EmptyFields.add(MoveStack.last())
        MoveStack.remove(MoveStack.last())


        // changes the side to move

        when (BoardRepresentation.SideToMove) {
            Player.Circle -> BoardRepresentation.SideToMove = Player.Cross
            Player.Cross -> BoardRepresentation.SideToMove = Player.Circle
        }

        CheckResult()

    }

    fun CheckResult() {

        BoardRepresentation.GameOver = false
        BoardRepresentation.CircleWon = false
        BoardRepresentation.CrossWon = false

        // Circle Won Check
        if (
            BoardRepresentation.Fields[0] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[1] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[2] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[3] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[4] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[5] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[6] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[7] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[8] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[0] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[3] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[6] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[1] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[4] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[7] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[2] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[5] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[8] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[0] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[4] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[8] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        if (
            BoardRepresentation.Fields[2] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[4] == Field.Circle.FieldValue
            && BoardRepresentation.Fields[6] == Field.Circle.FieldValue
        ) {
            BoardRepresentation.CircleWon = true
        }

        // Cross Won Check

        if (
            BoardRepresentation.Fields[0] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[1] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[2] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[3] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[4] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[5] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[6] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[7] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[8] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[0] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[3] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[6] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[1] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[4] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[7] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[2] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[5] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[8] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[0] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[4] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[8] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (
            BoardRepresentation.Fields[2] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[4] == Field.Cross.FieldValue
            && BoardRepresentation.Fields[6] == Field.Cross.FieldValue
        ) {
            BoardRepresentation.CrossWon = true
        }

        if (BoardRepresentation.CrossWon or BoardRepresentation.CircleWon or BoardRepresentation.EmptyFields.isEmpty()) {
            BoardRepresentation.GameOver = true

        }

    }

    fun DrawBoard() {

        println(
            SymbolFromFieldType(BoardRepresentation.Fields[0]) + " | " +
                    SymbolFromFieldType(BoardRepresentation.Fields[1]) + " | " +
                    SymbolFromFieldType(BoardRepresentation.Fields[2])
        )

        println("---------")

        println(
            SymbolFromFieldType(BoardRepresentation.Fields[3]) + " | " +
                    SymbolFromFieldType(BoardRepresentation.Fields[4]) + " | " +
                    SymbolFromFieldType(BoardRepresentation.Fields[5])
        )

        println("---------")

        println(
            SymbolFromFieldType(BoardRepresentation.Fields[6]) + " | " +
                    SymbolFromFieldType(BoardRepresentation.Fields[7]) + " | " +
                    SymbolFromFieldType(BoardRepresentation.Fields[8])
        )

        println("Side to Move: ${BoardRepresentation.SideToMove}")
        println("Game Over: ${BoardRepresentation.GameOver}")
        println("Circle Won: ${BoardRepresentation.CircleWon}")
        println("Cross Won: ${BoardRepresentation.CrossWon}")

    }


    fun SymbolFromFieldType(field: Int): Char {
        return when (field) {
            Field.Circle.FieldValue -> 'O'
            Field.Cross.FieldValue -> 'X'
            Field.Empty.FieldValue -> '.'
            else -> throw Exception("Unidentifiable Square Type")
        }
    }

    fun CountPositions(): Int {

        if (BoardRepresentation.GameOver) {
            return 1
        }

        var numPositions = 0

        BoardRepresentation.EmptyFields.toList().forEach {

            PushMove(it)
            numPositions += CountPositions()
            UndoMove()
        }
        return numPositions
    }
}
