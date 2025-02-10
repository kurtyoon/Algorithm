import java.io.*

data class Position(val y: Int, val x: Int) {
    fun getNextPosition(direction: Int): Position = Position(y + dy[direction], x + dx[direction])

    fun isValid(cols: Int, rows: Int): Boolean = y in 0 until cols && x in 0 until rows

    companion object {
        private val dy = intArrayOf(-1, 0, 1, 0)
        private val dx = intArrayOf(0, 1, 0, -1)
    }
}

class Field(private val cols: Int, private val rows: Int) {
    private val field = Array(cols) {
        IntArray(rows)
    }

    private val visited = Array(cols) {
        BooleanArray(rows)
    }

    fun plantCabbage(position: Position) {
        field[position.y][position.x] = 1
    }

    fun countCabbageClusters(): Int {
        var count = 0

        for (i in until cols) {
            for (j in until rows) {
                if (field[i][j] == 1 && !visited[i][j]) {
                    exploreCabbageCluster(Position(i, j))
                    count++
                }
            }
        }
    }

    private fun exploreCabbageCluster(position: Position) {
        visited[position.y][position.x] = true
        
        repeat(4) {
            direction -> val nextPosition = position.getNextPosition(direction)

            if (nextPosition.isValid(cols, rows) && field[nextPosition.y][nextPosition.x] == 1 && !visited[nextPosition.y][nextPosition.x]) {
                exploreCabbageCluster(nextPosition)
            }
        }
    }
}

class Solution {
    fun solution(t: Int, testCases: List<IntArray>, cabbages: List<List<Position>>): List<Int> {
        return buildList {
            repeat(t) {
                test -> val (m, n, _) = testCases[test]
                val field = Field(n, m)

                cabbages[test].forEach {
                    cabbagePosition -> field.plantCabbage(cabbagePosition)
                }

                add(field.countCabbageClusters())
            }
        }
    }
}

fun main() {
    try {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        val t = br.readLine().toInt()
        val testCases = mutableListOf<IntArray>()
        val cabbages = mutableListOf<List<Position>>()

        repeat(t) {
            val (m, n, k) = br.readLine().split(" ").map { it.toInt() }
            testCases.add(intArrayOf(m, n, k))

            val cabbagePositions = buildList {
                repeat(k) {
                    val (x, y) = br.readLine().split(" ").map { it.toInt() }
                    add(Position(y, x))
                }
            }

            cabbages.add(cabbagePositions)
        }

        val solution = Solution()
        solution.solution(t, testCases, cabbages).forEach {
            result -> bw.write("$result\n")
        }

        bw.flush()
        br.close()
        bw.close()
    } catch (e: IOException) {
        System.err.println()
    }
}