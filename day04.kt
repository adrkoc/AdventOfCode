import java.io.File
import java.io.InputStream
import kotlin.math.pow

fun day04Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Int = 0

    for (line in lines) {
        var counter: Int = 0
        val numbers: List<String> = line.split(":")[1].split("|")
        val winning: List<String> = numbers[0].split(" ")
        val my: List<String> = numbers[1].split(" ")

        for (number1 in winning) {
            val w: String = number1.replace(" ", "")
            if (w.isEmpty()) continue
            for (number2 in my) {
                val m: String = number2.replace(" ", "")
                if (m.isEmpty()) continue
                if (w == m) {
                    ++counter
                }
            }
        }
        result += 2.0.pow((counter - 1).toDouble()).toInt()
    }
    return result
}


fun day04Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val instances: MutableList<Int> = mutableListOf()
    for (i in lines.indices) {
        instances.add(1)
    }

    var index: Int = 0
    for (line in lines) {
        var counter: Int = 0
        val numbers: List<String> = line.split(":")[1].split("|")
        val winning: List<String> = numbers[0].split(" ")
        val my: List<String> = numbers[1].split(" ")

        for (number1 in winning) {
            val w: String = number1.replace(" ", "")
            if (w.isEmpty()) continue
            for (number2 in my) {
                val m: String = number2.replace(" ", "")
                if (w == m) ++counter
            }
        }

        ++index

        if (counter == 0) continue
        for (i in index..<index + counter) {
            if (i < lines.size) {
                instances[i] += instances[index - 1]
            }
        }
    }
    return instances.sum()
}


fun main(args: Array<String>) {
    println(day04Star01("src/main/kotlin/day04_input01.txt")) // 23847
    println(day04Star02("src/main/kotlin/day04_input01.txt")) // 8570000
}