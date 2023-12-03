import java.io.File
import java.io.InputStream

fun day03Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val list: MutableList<MutableList<Char>> = mutableListOf()
    for (line in lines) {
        val chars: MutableList<Char> = mutableListOf()
        for (character in line) {
            chars.add(character)
        }
        list.add(chars)
    }

    var result: Int = 0

    fun addNumber(y: Int, x: Int) {
        if (y < 0 || y >= list.size || x >= list[y].size) return
        if (list[y][x].toInt() !in 48..57) return

        var j: Int = x
        while (j >= 0 && list[y][j].toInt() in 48..57) {
            --j
        }
        if (j < 0) ++j
        if (list[y][j].toInt() !in 48..57) ++j

        var number: String = ""
        while (list[y][j].toInt() in 48..57) {
            number += list[y][j].toString()
            list[y][j] = '.'
            ++j
            if (j >= list[y].size) break
        }
        result += number.toInt()
    }

    for (i in list.indices) {
        for (j in list[i].indices) {
            val char: Char = list[i][j]
            if (char.toInt() in 48..57 || char == '.') {
                continue
            }

            addNumber(i, j - 1)
            addNumber(i, j + 1)

            addNumber(i - 1, j - 1)
            addNumber(i - 1, j)
            addNumber(i - 1, j + 1)

            addNumber(i + 1, j - 1)
            addNumber(i + 1, j)
            addNumber(i + 1, j + 1)
        }
    }
    return result
}


fun day03Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val list: MutableList<MutableList<Char>> = mutableListOf()
    for (line in lines) {
        val chars: MutableList<Char> = mutableListOf()
        for (character in line) {
            chars.add(character)
        }
        list.add(chars)
    }

    var result: Int = 0

    fun addNumber(y: Int, x: Int): Int {
        if (y < 0 || y >= list.size || x >= list[y].size) return 0
        if (list[y][x].toInt() !in 48..57) return 0

        var j: Int = x
        while (j >= 0 && list[y][j].toInt() in 48..57) {
            --j
        }
        if (j < 0) ++j
        if (list[y][j].toInt() !in 48..57) ++j

        var number: String = ""
        while (list[y][j].toInt() in 48..57) {
            number += list[y][j].toString()
            list[y][j] = '.'
            ++j
            if (j >= list[y].size) break
        }
        return number.toInt()
    }

    for (i in list.indices) {
        for (j in list[i].indices) {
            val char: Char = list[i][j]
            if (char != '*') {
                continue
            }

            var n: Int = 0
            var number: Int = 1

            var value = addNumber(i, j - 1)
            if (value != 0) {
                ++n
                number *= value
            }

            value = addNumber(i, j + 1)
            if (value != 0) {
                ++n
                number *= value
            }

            value = addNumber(i - 1, j - 1)
            if (value != 0) {
                ++n
                number *= value
            }

            value = addNumber(i - 1, j)
            if (value != 0) {
                ++n
                number *= value
            }

            value = addNumber(i - 1, j + 1)
            if (value != 0) {
                ++n
                number *= value
            }

            value = addNumber(i + 1, j - 1)
            if (value != 0) {
                ++n
                number *= value
            }

            value = addNumber(i + 1, j)
            if (value != 0 ) {
                ++n
                number *= value
            }

            value = addNumber(i + 1, j + 1)
            if (value != 0) {
                ++n
                number *= value
            }

            if (n == 2) {
                result += number
            }
        }
    }
    return result
}


fun main(args: Array<String>) {
    println(day03Star01("src/main/kotlin/day03_input01.txt")) // 533775
    println(day03Star02("src/main/kotlin/day03_input01.txt")) // 78236071
}