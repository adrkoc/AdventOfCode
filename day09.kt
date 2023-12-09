import java.io.File
import java.io.InputStream


fun day09Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val list: MutableList<List<Int>> = mutableListOf()
    var result = 0

    for (line in lines) {
        list.add(line.split(" ").map { it.toInt() })
        var index = list.size - 1
        while (list[index].count() { it == 0 } != list[index].size) {
            val helper: MutableList<Int> = mutableListOf()
            for (i in 1..<list[index].size) {
                helper.add(list[index][i] - list[index][i - 1])
            }
            list.add(helper)
            ++index
        }
        var value = 0
        for (x in list) {
            value += x[x.size - 1]
        }
        result += value
        list.clear()
    }
    return result
}


fun day09Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val list: MutableList<List<Int>> = mutableListOf()
    var result = 0

    for (line in lines) {
        list.add(line.split(" ").map { it.toInt() })
        var index = list.size - 1
        while (list[index].count() { it == 0 } != list[index].size) {
            val helper: MutableList<Int> = mutableListOf()
            for (i in 1..<list[index].size) {
                helper.add(list[index][i] - list[index][i - 1])
            }
            list.add(helper)
            ++index
        }
        var value = 0
        for (x in list.reversed()) {
            value = x[0] - value
        }
        result += value
        list.clear()
    }

    return result
}

fun main(args: Array<String>) {
    println(day09Star01("src/main/kotlin/day09_input01.txt")) // 1743490457
    println(day09Star02("src/main/kotlin/day09_input01.txt")) // 1053
}