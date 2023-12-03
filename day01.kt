import java.io.File
import java.io.InputStream

fun day01Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var result: Int = 0
    for (line in lines) {
        val digits: String = line.filter { it.isDigit() }
        if (digits.isNotEmpty()) {
            result += (digits[0].digitToInt() * 10 + digits[digits.length - 1].digitToInt())
        }
    }
    return result
}

fun day01Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val correctedLines: MutableList<String> = mutableListOf()
    for (line in lines) {
        correctedLines.add(wordsToDigits(line))
    }

    var result: Int = 0
    for (line in correctedLines) {
        val digits: String = line.filter { it.isDigit() }
        if (digits.isNotEmpty()) {
            result += (digits[0].digitToInt() * 10 + digits[digits.length - 1].digitToInt())
        }
    }
    return result
}

fun wordsToDigits(string: String): String {
    val numbers: MutableList<Pair<String, String>> = mutableListOf(
        Pair("one", "1e"), Pair("two", "2o"), Pair("three", "3e"),
        Pair("four", "4r"), Pair("five", "5e"), Pair("six", "6x"),
        Pair("seven", "7n"), Pair("eight", "8t"), Pair("nine", "9e"),
    )

    var result: String = ""
    for (character in string) {
        result += character
        for (number in numbers) {
            result = result.replace(number.first, number.second)
        }
    }
    return result
}


fun main(args: Array<String>) {
    println(day01Star01("src/main/kotlin/day01_input01.txt")) // 54644
    println(day01Star02("src/main/kotlin/day01_input01.txt")) // 53348
}