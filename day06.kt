import java.io.File
import java.io.InputStream
import kotlin.math.min

fun day06Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val time: MutableList<Int> = mutableListOf()
    val distance: MutableList<Int> = mutableListOf()

    time.addAll(lines[0].split(" ").filter { it.toIntOrNull() != null }.map { it.toInt() })
    distance.addAll(lines[1].split(" ").filter { it.toIntOrNull() != null }.map { it.toInt() })

    var result: Int = 1
    for (i in time.indices) {
        var counter: Int = 0
        for (t in 0..time[i]) {
            val d = t * (time[i] - t)
            if (d > distance[i]) {
                ++counter
            }
        }
        if (counter != 0) {
            result *= counter
        }
    }
    if (result == 1) return 0
    return result
}


fun day06Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val time: Long = lines[0].split(":")[1].replace(" ", "").toLong()
    val distance: Long = lines[1].split(":")[1].replace(" ", "").toLong()

    var result: Int = 0
    for (t in 0..time) {
        val d = t * (time - t)
        if (t * (time - t) > distance) {
            ++result
        }
    }
    return result
}


fun main(args: Array<String>) {
    println(day06Star01("src/main/kotlin/day06_input01.txt")) // 1731600
    println(day06Star02("src/main/kotlin/day06_input01.txt")) // 40087680
}