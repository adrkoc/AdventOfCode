import java.io.File
import java.io.InputStream
import kotlin.math.min

fun day07Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val cards: MutableMap<String, Int> = mutableMapOf()
    val ranks: MutableMap<String, Int> = mutableMapOf()
    val characters = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

    for (line in lines) {
        val x = line.split(" ")
        cards[x[0]] = x[1].toInt()
        ranks[x[0]] = 0
    }

    fun type(card: String): Int {
        val helper = mutableListOf(0, 0, 0, 0, 0, 0)
        for (character in characters) {
            helper[card.count() { it == character }] += 1
        }

        if (helper[5] == 1) return 1
        if (helper[4] == 1) return 2
        if (helper[3] == 1 && helper[2] == 1) return 3
        if (helper[3] == 1) return 4
        if (helper[2] == 2) return 5
        if (helper[2] == 1) return 6
        return 7
    }

    for ((c1, bid) in cards) {
        for ((c2, rank) in ranks) {
            if (c1 == c2) continue
            val x = type(c1) - type(c2)
            if (x < 0) {
                ranks[c1] = ranks[c1]!! + 1
            } else if (x == 0) {
                for (i in c1.indices) {
                    if (c1[i] == c2[i]) continue
                    if (characters.indexOf(c1[i]) < characters.indexOf(c2[i])) {
                        ranks[c1] = ranks[c1]!! + 1
                    } else ranks[c1] = ranks[c1]!! - 1
                    break
                }
            } else {
                ranks[c1] = ranks[c1]!! - 1
            }
        }
    }
    val sorted = ranks.entries.sortedBy { it.value }
    var result = 0
    var r = 1
    for ((card, rank) in sorted) {
        result += cards[card]!! * r
        ++r
    }

    return result
}


fun day07Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val cards: MutableMap<String, Int> = mutableMapOf()
    val ranks: MutableMap<String, Int> = mutableMapOf()
    val characters = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')

    for (line in lines) {
        val x = line.split(" ")
        cards[x[0]] = x[1].toInt()
        ranks[x[0]] = 1
    }

    fun type(card: String): Int {
        val helper = mutableListOf(0, 0, 0, 0, 0, 0)
        var checker = false
        for (character in characters) {
            if (character == 'J') {
                checker = true
                continue
            }
            helper[card.count() { it == character }] += 1
        }
        if (checker) {

            var index = helper.size - 1
            while (helper[index] == 0) --index
            helper[index] -= 1
            helper[index + card.count() { it == 'J' }] += 1
        }


        if (helper[5] == 1) return 1
        if (helper[4] == 1) return 2
        if (helper[3] == 1 && helper[2] == 1) return 3
        if (helper[3] == 1) return 4
        if (helper[2] == 2) return 5
        if (helper[2] == 1) return 6
        return 7
    }

    for ((c1, bid) in cards) {
        for ((c2, rank) in ranks) {
            if (c1 == c2) continue
            val x = type(c1) - type(c2)
            if (x < 0) {
                ranks[c1] = ranks[c1]!! + 2
            } else if (x == 0) {
                for (i in c1.indices) {
                    if (c1[i] == c2[i]) continue
                    if (characters.indexOf(c1[i]) < characters.indexOf(c2[i])) {
                        ranks[c1] = ranks[c1]!! + 1
                    } else ranks[c1] = ranks[c1]!! - 0
                    break
                }
            } else {
                ranks[c1] = ranks[c1]!! - 2
            }
        }
    }
    val sorted = ranks.entries.sortedBy { it.value }
    var result = 0
    var r = 1
    for ((card, rank) in sorted) {
        result += cards[card]!! * r
        ++r
    }

    return result
}


fun main(args: Array<String>) {
    println(day07Star01("src/main/kotlin/day07_input01.txt")) // 1731600
    println(day07Star02("src/main/kotlin/day07_input01.txt")) // 40087680
}