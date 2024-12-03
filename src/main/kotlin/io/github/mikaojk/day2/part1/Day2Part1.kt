package io.github.mikaojk.day2.part1

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs


fun day2Part1(): String {
    // input is here: https://adventofcode.com/2024/day/2
    val locationIdsInput = getFileAsString("src/main/resources/day2/input.txt")

    val reports = locationIdsInput.split("\\n".toRegex()).map {
        Report(levels = it.split(" ").map{
             it.trim().toInt()
        })
    }

    var sum = 0

    reports.forEach { report ->
        if (isSafe(report)) {
            sum++
        }
    }

    return sum.toString()
}

data class Report(
    val levels: List<Int>
)

fun isSafe(report: Report): Boolean {
    var safe = true
    var isDecreasing = true
    var isIncrease = true

    for(i in 0..<report.levels.size -1) {
        val a = report.levels[i]
        val b = report.levels[i +1]
        safe = safe && diffIsSafe(a,b)
        when {
            a < b -> isDecreasing = false
            b < a -> isIncrease = false
            else -> {
                isIncrease = false
                isDecreasing = false
            }
        }
    }

    return safe && ( isIncrease || isDecreasing)
}



fun diffIsSafe(level1 : Int, level2 : Int) : Boolean {
    val diff = abs(level1 - level2)
    return diff >= 1 && diff <= 3
}


fun getFileAsString(filePath: String): String {
    try {
        return String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
    } catch (exception: Exception) {
        throw Exception("Could not get file content as string", exception)
    }
}