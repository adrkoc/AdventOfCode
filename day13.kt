import java.io.File
import java.io.InputStream


fun day13Star01(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    lines.add("")
    val t: MutableList<MutableList<String>> = mutableListOf()
    var result = 0

    for (line in lines) {
        if (line != "") {
            t.add(line.split("").filter { it != "" }.toMutableList())
            continue
        }
        result += mirror(t)
        result += 100 * mirror(transpose(t))
        t.clear()
    }

    return result
}

fun day13Star02(file: String): Int {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    lines.add("")
    val t: MutableList<MutableList<String>> = mutableListOf()
    var result = 0

    for (line in lines) {
        if (line != "") {
            t.add(line.split("").filter { it != "" }.toMutableList())
            continue
        }
        result += mirrorWithError(t)
        result += 100 * mirrorWithError(transpose(t))
        t.clear()
    }

    return result
}

fun transpose(t: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val transposed: MutableList<MutableList<String>> = mutableListOf()
    for (i in 0..<t[0].size) {
        transposed.add(mutableListOf())
        for (j in 0..<t.size) {
            transposed[i].add(t[j][i])
        }
    }
    return transposed
}

fun mirror(t: MutableList<MutableList<String>>): Int {
    var result = 0
    for (i in 0..t[0].size - 2) {
        var s1 = i
        var s2 = i + 1
        var valid = true
        while (s1 != -1 && s2 != t[0].size) {
            var checker = true
            for (index in 0..<t.size) {
                if (t[index][s1] != t[index][s2]) {
                    checker = false
                    break
                }
            }
            if (!checker) {
                valid = false
                break
            }
            --s1
            ++s2
        }
        result += if (valid) i + 1 else 0
    }
    return result
}

fun mirrorWithError(t: MutableList<MutableList<String>>): Int {
    var result = 0
    for (i in 0..t[0].size - 2) {
        var s1 = i
        var s2 = i + 1
        var error = 0
        while (s1 != -1 && s2 != t[0].size) {
            for (index in 0..<t.size) {
                if (t[index][s1] != t[index][s2]) {
                    error += 1
                }
            }
            if (error > 1) break
            --s1
            ++s2
        }
        result += if (error == 1) i + 1 else 0
    }
    return result
}


fun main(args: Array<String>) {
    println(day13Star01("src/main/kotlin/day13_input01.txt")) // 30575
    println(day13Star02("src/main/kotlin/day13_input01.txt")) // 37478
}