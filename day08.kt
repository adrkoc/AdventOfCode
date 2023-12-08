import java.io.File
import java.io.InputStream
import kotlin.math.min

fun day08Star01(file: String): Any {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var directions = ""
    val map: MutableMap<String, Pair<String, String>> = mutableMapOf()

    directions = lines[0].strip()
    for (i in 2..<lines.size) {
        val helper: String =
            lines[i].replace(" ", "").replace("=", "")
                .replace("(", "").replace(",", "").replace(")", "")
        val place: String = helper.substring(0, 3)
        val left: String = helper.substring(3, 6)
        val right: String = helper.substring(6)
        map[place] = Pair(left, right)
    }

    var result: Int = 0
    var counter: Int = 0
    var current: String = "AAA"
    while (current[2] != 'Z') {
        current = if (directions[counter] == 'L') {
            map[current]!!.first
        } else {
            map[current]!!.second
        }
        ++counter
        ++result
        if (counter >= directions.length) counter = 0
    }

    return result
}


fun day08Star02(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var directions = ""
    val map: MutableMap<String, Pair<String, String>> = mutableMapOf()
    val starts: MutableList<String> = mutableListOf()
    val values: MutableList<Int> = mutableListOf()

    directions = lines[0].strip()
    for (i in 2..<lines.size) {
        val helper: String =
            lines[i].replace(" ", "").replace("=", "")
                .replace("(", "").replace(",", "").replace(")", "")
        val place: String = helper.substring(0, 3)
        val left: String = helper.substring(3, 6)
        val right: String = helper.substring(6)
        map[place] = Pair(left, right)

        if (place[2] == 'A') {
            starts.add(place)
            values.add(0)
        }
    }

    var value: Int = 0
    for (i in starts.indices) {
        var counter: Int = 0
        while (starts[i][2] != 'Z') {
            starts[i] = if (directions[counter] == 'L') {
                map[starts[i]]!!.first
            } else {
                map[starts[i]]!!.second
            }
            ++counter
            ++value
            if (counter >= directions.length) counter = 0
        }
        values[i] = value
        value = 0
    }

    return findLCMOfListOfNumbers(values)
}

// https://www.baeldung.com/kotlin/lcm

fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

fun findLCMOfListOfNumbers(numbers: List<Int>): Long {
    var result = numbers[0].toLong()
    for (i in 1..<numbers.size) {
        result = findLCM(result, numbers[i].toLong())
    }
    return result
}


fun main(args: Array<String>) {
    println(day08Star01("src/main/kotlin/day08_input01.txt")) // 17873
    println(day08Star02("src/main/kotlin/day08_input01.txt")) // 15746133679061
}