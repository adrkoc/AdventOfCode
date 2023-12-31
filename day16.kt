import java.io.File
import java.io.InputStream


enum class Direction { UP, DOWN, RIGHT, LEFT }

fun day16Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val t: MutableList<MutableList<String>> = mutableListOf()
    for (line in lines) {
        t.add(line.split("").filter { it != "" }.toMutableList())
    }

    return energizingTiles(t, Pair(Pair(0, 0), Direction.LEFT))
}


fun day16Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val t: MutableList<MutableList<String>> = mutableListOf()
    for (line in lines) {
        t.add(line.split("").filter { it != "" }.toMutableList())
    }

    val energized: MutableList<Int> = mutableListOf()

    for (i in 0..<t[0].size) {
        energized.add(energizingTiles(t, Pair(Pair(0, i), Direction.UP)))
        energized.add(energizingTiles(t, Pair(Pair(t.size - 1, i), Direction.DOWN)))
    }

    for (i in 0..<t.size) {
        energized.add(energizingTiles(t, Pair(Pair(i, 0), Direction.LEFT)))
        energized.add(energizingTiles(t, Pair(Pair(i, t[0].size - 1), Direction.RIGHT)))
    }

    return energized.max()
}


fun energizingTiles(t: MutableList<MutableList<String>>, first: Pair<Pair<Int, Int>, Direction>): Int {
    val maxX = t[0].size - 1
    val maxY = t.size - 1

    val q: MutableList<Pair<Pair<Int, Int>, Direction>> = mutableListOf()
    val visited: MutableSet<Pair<Pair<Int, Int>, Direction>> = mutableSetOf()

    q.add(first)

    while (q.isNotEmpty()) {
        val helper = q.removeAt(0)
        val y = helper.first.first
        val x = helper.first.second
        val d = helper.second

        if (helper in visited || x !in 0..maxY || y !in 0..maxX) {
            continue
        }

        visited.add(Pair(Pair(y, x), d))

        if (t[y][x] == ".") {
            when (d) {
                Direction.LEFT -> q.add(Pair(Pair(y, x + 1), Direction.LEFT))
                Direction.RIGHT -> q.add(Pair(Pair(y, x - 1), Direction.RIGHT))
                Direction.UP -> q.add(Pair(Pair(y + 1, x), Direction.UP))
                Direction.DOWN -> q.add(Pair(Pair(y - 1, x), Direction.DOWN))
            }
        } else if (t[y][x] == "|") {
            when (d) {
                Direction.LEFT, Direction.RIGHT -> {
                    q.add(Pair(Pair(y - 1, x), Direction.DOWN))
                    q.add(Pair(Pair(y + 1, x), Direction.UP))
                }

                Direction.UP -> q.add(Pair(Pair(y + 1, x), Direction.UP))
                Direction.DOWN -> q.add(Pair(Pair(y - 1, x), Direction.DOWN))
            }
        } else if (t[y][x] == "-") {
            when (d) {
                Direction.LEFT -> q.add(Pair(Pair(y, x + 1), Direction.LEFT))
                Direction.RIGHT -> q.add(Pair(Pair(y, x - 1), Direction.RIGHT))

                Direction.UP, Direction.DOWN -> {
                    q.add(Pair(Pair(y, x + 1), Direction.LEFT))
                    q.add(Pair(Pair(y, x - 1), Direction.RIGHT))
                }
            }
        } else if (t[y][x] == "/") {
            when (d) {
                Direction.LEFT -> q.add(Pair(Pair(y - 1, x), Direction.DOWN))
                Direction.RIGHT -> q.add(Pair(Pair(y + 1, x), Direction.UP))
                Direction.UP -> q.add(Pair(Pair(y, x - 1), Direction.RIGHT))
                Direction.DOWN -> q.add(Pair(Pair(y, x + 1), Direction.LEFT))
            }
        } else if (t[y][x] == "\\") {
            when (d) {
                Direction.LEFT -> q.add(Pair(Pair(y + 1, x), Direction.UP))
                Direction.RIGHT -> q.add(Pair(Pair(y - 1, x), Direction.DOWN))
                Direction.UP -> q.add(Pair(Pair(y, x + 1), Direction.LEFT))
                Direction.DOWN -> q.add(Pair(Pair(y, x - 1), Direction.RIGHT))
            }
        }
    }

    val energized: MutableSet<Pair<Int, Int>> = mutableSetOf()
    for (element in visited) {
        energized.add(element.first)
    }

    return energized.size
}


fun main(args: Array<String>) {
    println(day16Star01("src/main/kotlin/day16_input01.txt")) // 6605
    println(day16Star02("src/main/kotlin/day16_input01.txt")) //
}