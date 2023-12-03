import java.io.File
import java.io.InputStream

fun day02Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Int = 0
    for (line in lines) {
        val game: List<String> = line.split(":")
        if (isPossible(game[1])) {
            result += game[0].split(" ")[1].toInt()
        }
    }
    return result
}

fun isPossible(string: String): Boolean {
    val grabs = string.split(";")
    val red: Int = 12
    val green: Int = 13
    val blue: Int = 14
    for (grab in grabs) {
        val list: List<String> = grab.replace(",", "").split(" ")
        for (i in list.indices) {
            if (list[i] == "red" && list[i - 1].toInt() > red) return false
            if (list[i] == "green" && list[i - 1].toInt() > green) return false
            if (list[i] == "blue" && list[i - 1].toInt() > blue) return false
        }
    }
    return true
}


fun day02Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Int = 0
    for (line in lines) {
        var r: Int = 0
        var g: Int = 0
        var b: Int = 0

        val list: List<String> = line.replace(";", "").replace(",", "").split(" ")
        for (i in list.indices) {
            if (list[i] == "red" && list[i - 1].toInt() > r) r = list[i - 1].toInt()
            if (list[i] == "green" && list[i - 1].toInt() > g) g = list[i - 1].toInt()
            if (list[i] == "blue" && list[i - 1].toInt() > b) b = list[i - 1].toInt()
        }
        result += r * g * b
    }
    return result
}


fun main(args: Array<String>) {
    println(day02Star01("src/main/kotlin/day02_input01.txt")) // 2331
    println(day02Star02("src/main/kotlin/day02_input01.txt")) // 71585
}