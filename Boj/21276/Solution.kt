import java.io.*

data class FamilyTree(
    private val names: List<String>,
    private val ancestors: List<Pair<String, String>>
) {
    private val graph = mutableMapOf<String, MutableList<String>>()
    private val inDegree = mutableMapOf<String, Int>()
    private val founders: List<String>
    private val immediateChildren: Map<String, List<String>>

    init {
        names.forEach { name ->
            graph[name] = mutableListOf()
            inDegree[name] = 0
        }

        ancestors.forEach { (child, parent) ->
            graph[parent]?.add(child)
            inDegree[child] = inDegree.getOrDefault(child, 0) + 1
        }

        founders = names.filter { name -> inDegree[name] == 0 }.sorted()

        immediateChildren = findImmediateChildren()
    }

    fun generateResult(): List<String> = buildList {
        add(founders.size.toString())
        add(founders.joinToString(" "))

        immediateChildren.toSortedMap().forEach { (parent, children) ->
            add("${parent} ${children.size} ${children.sorted().joinToString(" ")}")
        }
    }

    private fun findImmediateChildren(): Map<String, List<String>> {
        val result = mutableMapOf<String, MutableList<String>>()
        names.forEach { name -> result[name] = mutableListOf() }

        val queue = ArrayDeque<String>()
        val depths = mutableMapOf<String, Int>()
        
        founders.forEach { founder ->
            queue.add(founder)
            depths[founder] = 0
        }

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val currentDepth = depths[current] ?: 0

            graph[current]?.forEach { child ->
                inDegree[child] = inDegree[child]!! - 1
                
                if (inDegree[child] == 0) {
                    queue.add(child)
                    depths[child] = currentDepth + 1
                    result[current]?.add(child)
                }
            }
        }

        return result
    }
}

class Solution {
    fun solve(n: Int, names: List<String>, ancestors: List<Pair<String, String>>): List<String> =
        FamilyTree(names, ancestors).generateResult()
}

fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        BufferedWriter(OutputStreamWriter(System.`out`)).use { bw -> 
            val n = br.readLine().toInt()
            val names = br.readLine().split(" ")
            
            val m = br.readLine().toInt()
            val ancestors = List(m) {
                val (x, y) = br.readLine().split(" ")
                x to y
            }
            
            Solution().solve(n, names, ancestors).forEach { line ->
                bw.write(line)
                bw.newLine()
            }
            
            bw.flush()
        }
    }
}