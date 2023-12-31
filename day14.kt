import java.io.File
import java.io.InputStream


fun day14Star01(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val grid: MutableList<MutableList<String>> = mutableListOf()
    for (line in lines) {
        grid.add(line.split("").filter { it != "" }.toMutableList())
    }

    return calculateLoad(north(grid))
}

fun calculateLoad(grid: MutableList<MutableList<String>>): Long {
    var result: Long = 0
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == "O") {
                result += grid.size - i
            }
        }
    }
    return result
}

fun north(grid: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val new: MutableList<MutableList<String>> = mutableListOf()
    val positions: MutableList<Int> = mutableListOf()

    for (element in grid) new.add(element.toMutableList())
    for (i in grid[0].indices) positions.add(-1)

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (new[i][j] == "#") positions[j] = i
            else if (new[i][j] == "O") {
                new[i][j] = "."
                positions[j] += 1
                new[positions[j]][j] = "O"
            }
        }
    }
    return new
}

fun south(grid: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val new: MutableList<MutableList<String>> = mutableListOf()
    val positions: MutableList<Int> = mutableListOf()

    for (element in grid) new.add(element.toMutableList())
    for (i in grid[0].indices) positions.add(grid.size)

    for (i in grid.indices.reversed()) {
        for (j in grid[i].indices) {
            if (new[i][j] == "#") positions[j] = i
            else if (new[i][j] == "O") {
                new[i][j] = "."
                positions[j] -= 1
                new[positions[j]][j] = "O"
            }
        }
    }
    return new
}

fun west(grid: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val new: MutableList<MutableList<String>> = mutableListOf()
    val positions: MutableList<Int> = mutableListOf()

    for (element in grid) new.add(element.toMutableList())
    for (i in grid.indices) positions.add(-1)

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (new[i][j] == "#") positions[i] = j
            else if (new[i][j] == "O") {
                new[i][j] = "."
                positions[i] += 1
                new[i][positions[i]] = "O"
            }
        }
    }
    return new
}

fun east(grid: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val new: MutableList<MutableList<String>> = mutableListOf()
    val positions: MutableList<Int> = mutableListOf()

    for (element in grid) new.add(element.toMutableList())
    for (i in grid.indices) positions.add(grid[0].size)

    for (i in grid.indices) {
        for (j in grid[i].indices.reversed()) {
            if (new[i][j] == "#") positions[i] = j
            else if (new[i][j] == "O") {
                new[i][j] = "."
                positions[i] -= 1
                new[i][positions[i]] = "O"
            }
        }
    }
    return new
}


fun day14Star02(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var grid: MutableList<MutableList<String>> = mutableListOf()
    val storage: MutableList<MutableList<MutableList<String>>> = mutableListOf()
    for (line in lines) {
        grid.add(line.split("").filter { it != "" }.toMutableList())
    }

    for (i in 0..<1000000000) {
        grid = east(south(west(north(grid))))
        if (grid in storage) {
            val index = storage.indexOf(grid)
            return calculateLoad(storage[(1000000000 - i - 1) % (storage.size - index) + index])
        }
        storage.add(grid)
    }

    return 0
}


fun main(args: Array<String>) {
    println(day14Star01("src/main/kotlin/day14_input01.txt")) // 108759
    println(day14Star02("src/main/kotlin/day14_input01.txt")) // 89089
}