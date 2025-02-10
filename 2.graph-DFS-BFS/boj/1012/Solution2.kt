import java.io.*

data class Position(val y: Int, val x: Int) {

    fun next(direction: Int) = copy(y = y + dy[direction], x = x + dx[direction])
    fun isValid(cols: Int, rows: Int) = y in 0 until cols && x in 0 until rows
    
    companion object {
        private val dy = intArrayOf(-1, 0, 1, 0)
        private val dx = intArrayOf(0, 1, 0, -1)
    }
}

class Field(private val cols: Int, private val rows: Int, private val field: Array<IntArray>) {
    private val visited = Array(cols) { BooleanArray(rows) }

    fun countClusters(): Int =
        field.indices.sumOf { i -> 
            field[i].indices.count { j -> explore(Position(i, j)) }
        }

    private fun explore(pos: Position): Boolean {
        if (!pos.isValid(cols, rows) || visited[pos.y][pos.x] || field[pos.y][pos.x] == 0) return false
        visited[pos.y][pos.x] = true
        (0..3).map { pos.next(it) }.filter { it.isValid(cols, rows) }.forEach { explore(it) }
        return true
    }

    companion object {
        fun fromCabbages(cols: Int, rows: Int, positions: List<Position>): Field {
            val field = Array(cols) { IntArray(rows) }
            positions.forEach { field[it.y][it.x] = 1 }
            return Field(cols, rows, field)
        }
    }
}

class Solution {
    fun solve(testCases: List<Triple<Int, Int, List<Position>>>): List<Int> =
        testCases.map { (m, n, cabbages) -> Field.fromCabbages(n, m, cabbages).countClusters() }
}

fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        BufferedWriter(OutputStreamWriter(System.`out`)).use { bw -> 
            val t = br.readLine().toInt()
            val testCases = List(t) {
                val (m, n, k) = br.readLine().split(" ").map { it.toInt() }
                Triple(m, n, List(k) {
                    val (x, y) = br.readLine().split(" ").map { it.toInt() }
                    Position(y, x)
                }) 
            }

            Solution().solve(testCases).forEach { bw.write("$it\n") }
            bw.flush()
        }
    }
}