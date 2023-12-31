import java.io.File
import java.io.InputStream


fun day19Star01(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    var checker = true
    var result: Long = 0
    val workflows: MutableList<String> = mutableListOf()
    val ratings: MutableList<String> = mutableListOf()
    for (line in lines) {
        if (line == "") {
            checker = false
            continue
        }
        if (checker) workflows.add(line)
        else ratings.add(line)
    }
    val map: MutableMap<String, List<String>> = mutableMapOf()
    for (workflow in workflows) {
        val i = workflow.indexOf("{")
        map[workflow.substring(0..<i)] = workflow.substring(i + 1..workflow.length - 2).split(",")
    }
    for (rating in ratings) {
        val numbers: MutableList<Int> = mutableListOf()
        var number = ""
        for (character in rating) {
            if (character.isDigit()) number += character
            else if (!character.isDigit() && number != "") {
                numbers.add(number.toInt())
                number = ""
            }
        }
        result += compute(map, numbers, "in")
    }

    return result
}

fun compute(map: MutableMap<String, List<String>>, ratings: MutableList<Int>, workflow: String): Long {
    if (workflow == "A") return ratings.sum().toLong()
    if (workflow == "R") return 0
    for (x in map[workflow]!!) {
        if (!x.contains(":")) return compute(map, ratings, x)
        else {
            val value = if (x[0] == 'x') ratings[0]
            else if (x[0] == 'm') ratings[1]
            else if (x[0] == 'a') ratings[2]
            else ratings[3]

            val i = x.indexOf(":")
            val v = x.substring(2..<i).toInt()
            val nextWorkflow = x.substring(i + 1..<x.length)
            if (x[1] == '<' && value < v) return compute(map, ratings, nextWorkflow)
            else if (x[1] == '>' && value > v) return compute(map, ratings, nextWorkflow)
        }
    }
    return 0
}

fun computeRange(map: MutableMap<String, List<String>>, ranges: MutableList<Pair<Int, Int>>, workflow: String): Long {
    if (workflow == "A") {
        var value: Long = 1
        for (range in ranges) {
            value *= (range.second - range.first + 1)
        }
        return value
    }
    if (workflow == "R") return 0
    var result: Long = 0

    for (x in map[workflow]!!) {
        if (!x.contains(":")) {
            result += computeRange(map, ranges, x)
            break
        } else {
            val index = if (x[0] == 'x') 0
            else if (x[0] == 'm') 1
            else if (x[0] == 'a') 2
            else 3

            val i = x.indexOf(":")
            val v = x.substring(2..<i).toInt()
            val nextWorkflow = x.substring(i + 1..<x.length)
            if (v in ranges[index].first + 1..<ranges[index].second) {
                val helper = ranges.toMutableList()
                if (x[1] == '<') {
                    helper[index] = Pair(ranges[index].first, v - 1)
                    ranges[index] = Pair(v, ranges[index].second)
                } else {
                    helper[index] = Pair(v + 1, ranges[index].second)
                    ranges[index] = Pair(ranges[index].first, v)
                }
                result += computeRange(map, helper, nextWorkflow)

            }
        }
    }
    return result
}


fun day19Star02(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val workflows: MutableList<String> = mutableListOf()
    for (line in lines) {
        if (line == "") break
        workflows.add(line)
    }
    val map: MutableMap<String, List<String>> = mutableMapOf()
    for (workflow in workflows) {
        val i = workflow.indexOf("{")
        map[workflow.substring(0..<i)] = workflow.substring(i + 1..workflow.length - 2).split(",")
    }
    map["A"] = listOf("A")
    map["R"] = listOf("R")

    return computeRange(map, mutableListOf(Pair(1, 4000), Pair(1, 4000), Pair(1, 4000), Pair(1, 4000)), "in")
}


fun main(args: Array<String>) {
    println(day19Star01("src/main/kotlin/day19_input01.txt")) // 406849
    println(day19Star02("src/main/kotlin/day19_input01.txt")) // 138615429259072
}