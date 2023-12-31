import java.io.File
import java.io.InputStream


fun day15Star01(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Long = 0
    val strings: List<String> = lines[0].split(",")
    for (string in strings) {
        result += hash(string)
    }

    return result
}

fun hash(string: String): Long {
    var value: Long = 0
    for (character in string) {
        value += character.toInt()
        value *= 17
        value %= 256
    }
    return value
}


fun day15Star02(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Long = 0
    val strings: List<String> = lines[0].split(",")
    val boxes: MutableMap<Long, MutableList<Pair<String, Int>>> = mutableMapOf()

    for (i in 0..255) {
        boxes[i.toLong()] = mutableListOf()
    }

    for (string in strings) {
        if (string.contains('-')) {
            val x = string.split('-')
            val name = x[0]
            val value = hash(name)

            for ((index, lens) in boxes[value]!!.withIndex()) {
                if (lens.first == name) {
                    boxes[value]!!.removeAt(index)
                    break
                }
            }
        } else if (string.contains('=')) {
            val x = string.split('=')
            val name = x[0]
            val value = hash(name)
            var checker = false

            for ((index, lens) in boxes[value]!!.withIndex()) {
                if (lens.first == name) {
                    boxes[value]!![index] = Pair(name, x[1].toInt())
                    checker = true
                    break
                }
            }
            if (!checker) {
                boxes[value]!!.add(Pair(name, x[1].toInt()))
            }
        }
    }

    for ((value, lenses) in boxes) {
        for ((index, lens) in lenses.withIndex()) {
            result += (value + 1) * (index + 1) * lens.second
        }
    }

    return result
}


fun main(args: Array<String>) {
    println(day15Star01("src/main/kotlin/day15_input01.txt")) // 495972
    println(day15Star02("src/main/kotlin/day15_input01.txt")) // 245223
}