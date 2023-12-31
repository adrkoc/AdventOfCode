import java.io.File
import java.io.InputStream
import java.text.FieldPosition
import java.util.Queue
import kotlin.math.abs
import kotlin.math.max


fun day11Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Int = 0
    val emptyRows: MutableList<Int> = mutableListOf()
    val emptyCols: MutableList<Int> = mutableListOf()
    val galaxies: MutableList<Pair<Int, Int>> = mutableListOf()

    // find indexes of empty rows
    for (i in lines.indices) {
        if (lines[i].count() { it == '.' } == lines[i].length) {
            emptyRows.add(i)
        }
    }

    // find indexes of empty columns
    for (i in lines[0].indices) {
        var helper: String = ""
        for (j in lines.indices) {
            helper += lines[j][i]

            // find galaxies
            if (lines[j][i] == '#') {
                galaxies.add(Pair(j, i))
            }
        }
        if (helper.count() { it == '.' } == lines.size) {
            emptyCols.add(i)
        }
    }

    // calculate shortest paths
    for (i in galaxies.indices) {
        for (j in i + 1..<galaxies.size) {
            result += abs(galaxies[i].first - galaxies[j].first) + abs(galaxies[i].second - galaxies[j].second)
            result += emptyRows.count() { it in galaxies[i].first..galaxies[j].first || it in galaxies[j].first..galaxies[i].first }
            result += emptyCols.count() { it in galaxies[i].second..galaxies[j].second || it in galaxies[j].second..galaxies[i].second }
        }
    }
    return result
}


fun day11Star02(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Long = 0
    val emptyRows: MutableList<Int> = mutableListOf()
    val emptyCols: MutableList<Int> = mutableListOf()
    val galaxies: MutableList<Pair<Int, Int>> = mutableListOf()

    // find indexes of empty rows
    for (i in lines.indices) {
        if (lines[i].count() { it == '.' } == lines[i].length) {
            emptyRows.add(i)
        }
    }

    // find indexes of empty columns
    for (i in lines[0].indices) {
        var helper: String = ""
        for (j in lines.indices) {
            helper += lines[j][i]

            // find galaxies
            if (lines[j][i] == '#') {
                galaxies.add(Pair(j, i))
            }
        }
        if (helper.count() { it == '.' } == lines.size) {
            emptyCols.add(i)
        }
    }


    // add spaces between columns
    var counter: Int = 0
    while (emptyCols.isNotEmpty()) {
        val col: Int = emptyCols.removeFirst()
        for (i in galaxies.indices) {
            if (galaxies[i].second - counter > col) {
                galaxies[i] = Pair(galaxies[i].first, galaxies[i].second + 1000000 - 1)
            }
        }
        counter += 1000000 - 1
    }

    // calculate shortest paths
    for (i in galaxies.indices) {
        for (j in i + 1..<galaxies.size) {
            result += abs(galaxies[i].first - galaxies[j].first) + abs(galaxies[i].second - galaxies[j].second)
            result += emptyRows.count() { it in galaxies[i].first..galaxies[j].first || it in galaxies[j].first..galaxies[i].first } * 999999
            result += emptyCols.count() { it in galaxies[i].second..galaxies[j].second || it in galaxies[j].second..galaxies[i].second } * 999999
        }
    }
    return result
}

fun main(args: Array<String>) {
    println(day11Star01("src/main/kotlin/day11_input01.txt")) // 9556712
    println(day11Star02("src/main/kotlin/day11_input01.txt")) // 678626199476
}