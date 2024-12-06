package io.github.mikaojk.day2

import io.github.mikaojk.getFileAsString
import kotlin.math.abs

class Day02 {

    fun part1(): String {
        // input is here: https://adventofcode.com/2024/day/2
        val locationIdsInput = getFileAsString("src/main/resources/day2/input.txt")

        val reports = locationIdsInput.split("\\n".toRegex()).map {
            Report(levels = it.split(" ").map {
                it.trim().toInt()
            })
        }

        var sumOfSafeReports = 0

        reports.forEach { report ->
            if (isLevelsSafe(report.levels)) {
                sumOfSafeReports++
            }
        }

        return sumOfSafeReports.toString()
    }


    fun part2(): String {
        // input is here: https://adventofcode.com/2024/day/2
        val locationIdsInput = getFileAsString("src/main/resources/day2/input.txt")

        val reports = locationIdsInput.split("\\n".toRegex()).map {
            Report(levels = it.split(" ").map {
                it.trim().toInt()
            })
        }

        var sumOfSafeReports = 0

        reports.forEach { report ->
            if (isReportSafeWithProblemDamper(report)) {
                sumOfSafeReports++
            }
        }

        return sumOfSafeReports.toString()
    }

    data class Report(
        val levels: List<Int>
    )

    fun isReportSafeWithProblemDamper(report: Report): Boolean {
        var safe = false
        for (i in 0..<report.levels.size - 1) {
            safe = isLevelsSafe(report.levels.toMutableList().apply { removeAt(i) })
            if (safe) break
        }

        return (safe)

    }

    fun isLevelsSafe(levels: List<Int>): Boolean {
        var safe = true
        var isDecreasing = true
        var isIncrease = true

        for (i in 0..<levels.size - 1) {
            val a = levels[i]
            val b = levels[i + 1]
            safe = safe && diffIsSafe(a, b)
            when {
                a < b -> isDecreasing = false
                b < a -> isIncrease = false
                else -> {
                    isIncrease = false
                    isDecreasing = false
                }
            }
        }

        return safe && (isIncrease || isDecreasing)
    }


    fun diffIsSafe(level1: Int, level2: Int): Boolean {
        val diff = abs(level1 - level2)
        return diff >= 1 && diff <= 3
    }


}