import java.io.File
import java.io.InputStream
import kotlin.math.min

fun day05Star01(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val seeds: MutableList<Long> = mutableListOf()
    val ss: MutableList<Long> = mutableListOf()
    val sf: MutableList<Long> = mutableListOf()
    val fw: MutableList<Long> = mutableListOf()
    val wl: MutableList<Long> = mutableListOf()
    val lt: MutableList<Long> = mutableListOf()
    val th: MutableList<Long> = mutableListOf()
    val hl: MutableList<Long> = mutableListOf()


    var counter = 0

    for (line in lines) {
        if (line.contains("-")) continue
        if (line.strip().isEmpty()) {
            ++counter
            continue
        }
        val values = line.split(" ").filter { it.toLongOrNull() != null }.map { it.toLong() }
        when (counter) {
            0 -> seeds.addAll(values)
            1 -> ss.addAll(values)
            2 -> sf.addAll(values)
            3 -> fw.addAll(values)
            4 -> wl.addAll(values)
            5 -> lt.addAll(values)
            6 -> th.addAll(values)
            7 -> hl.addAll(values)
        }
    }

    val locations: MutableList<Long> = mutableListOf()

    fun helper(number: Long, list: MutableList<Long>): Long {
        for (i in list.indices step 3) {
            if (number >= list[i + 1] && number < list[i + 1] + list[i + 2]) {
                return list[i] + number - list[i + 1]
            }
        }
        return number
    }

    for (seed in seeds) {
        var x = seed
        x = helper(x, ss)
        x = helper(x, sf)
        x = helper(x, fw)
        x = helper(x, wl)
        x = helper(x, lt)
        x = helper(x, th)
        locations.add(helper(x, hl))
    }

    return locations.min()
}


fun day05Star02(file: String): Long {
    val input: InputStream = File(file).inputStream()
    val lines: MutableList<String> = mutableListOf()
    input.bufferedReader().forEachLine { line -> lines.add(line) }

    val seeds: MutableList<Long> = mutableListOf()
    val ss: MutableList<Long> = mutableListOf()
    val sf: MutableList<Long> = mutableListOf()
    val fw: MutableList<Long> = mutableListOf()
    val wl: MutableList<Long> = mutableListOf()
    val lt: MutableList<Long> = mutableListOf()
    val th: MutableList<Long> = mutableListOf()
    val hl: MutableList<Long> = mutableListOf()

    var counter = 0

    for (line in lines) {
        if (line.contains("-")) continue
        if (line.strip().isEmpty()) {
            ++counter
            continue
        }
        val values = line.split(" ").filter { it.toLongOrNull() != null }.map { it.toLong() }
        when (counter) {
            0 -> seeds.addAll(values)
            1 -> ss.addAll(values)
            2 -> sf.addAll(values)
            3 -> fw.addAll(values)
            4 -> wl.addAll(values)
            5 -> lt.addAll(values)
            6 -> th.addAll(values)
            7 -> hl.addAll(values)
        }
    }

    val locations: MutableList<Long> = mutableListOf()

    fun helper(number: Long, list: MutableList<Long>): Long {
        for (i in list.indices step 3) {
            if (number >= list[i + 1] && number < list[i + 1] + list[i + 2]) {
                return list[i] + number - list[i + 1]
            }
        }
        return number
    }

    fun ranges(intervals: MutableList<Long>, list: MutableList<Long>): MutableList<Long> {
        val x = intervals[0]
        val y = intervals[1]

        for (i in list.indices step 3) {
            val a = list[i + 1]
            val b = list[i + 1] + list[i + 2] - 1
            if (a in x..y && a !in intervals) intervals.add(a)
            if (a + 1 in x..y && a + 1 !in intervals) intervals.add(a + 1)
            if (b in x..y && b !in intervals) intervals.add(b)
            if (b + 1 in x..y && b + 1 !in intervals) intervals.add(b + 1)
        }

        val computed: MutableList<Long> = mutableListOf()
        for (element in intervals) {
            computed.add(helper(element, list))
        }
        return computed
    }


    for (i in seeds.indices step 2) {
        var intervals = mutableListOf(seeds[i], seeds[i] + seeds[i + 1] - 1)
        intervals = ranges(intervals, ss)
        intervals = ranges(intervals, sf)
        intervals = ranges(intervals, fw)
        intervals = ranges(intervals, wl)
        intervals = ranges(intervals, lt)
        intervals = ranges(intervals, th)
        intervals = ranges(intervals, hl)
        locations.add(intervals.min())
    }
    return locations.min()
}


fun main(args: Array<String>) {
    println(day05Star01("src/main/kotlin/day05_input01.txt")) // 165788812
    println(day05Star02("src/main/kotlin/day05_input01.txt")) // 1928058
}