package Board

import kotlin.math.pow
import kotlin.random.Random

class Zobrist {

    fun createZobristTable(): Array<Array<Long>>{

        val table = Array(2) { Array(9) { 0L } }
        for (i in 0..1){
            for (j in 0..8){
                table[i][j] = Random.nextLong(2F.pow(64).toLong())
            }
        }

        return table
    }

    fun createZobristKey(boardRepresentation: BoardRepresentation, table: Array<Array<Long>>): Long {

        var key = 0L

        for (field in 0..8){
            if (boardRepresentation.Fields[field] == Field.Circle.FieldValue){
                key = key xor table[0][field]
            }

            if (boardRepresentation.Fields[field] == Field.Cross.FieldValue){
                key = key xor table[1][field]
            }
        }

        return key
    }
}