package io.github.mikaojk.day3

import io.github.mikaojk.getFileAsString

class Day03 {

    fun part1(): String {
        // input is here: https://adventofcode.com/2024/day/3
        val locationIdsInput = getFileAsString("src/main/resources/day3/test.txt")

        val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""

        val mulInstructions = mulRegex.toRegex().findAll(locationIdsInput).map { matchResult ->
            val (first: String, second: String) = matchResult.destructured
            Multiply(firstNumber = first.toInt(), secondNumber = second.toInt())
        }
        var sum = 0

        mulInstructions.forEach { mulInstructio ->
            sum += (mulInstructio.firstNumber * mulInstructio.secondNumber)
        }

        return sum.toString()
    }

    data class Multiply(
        val firstNumber: Int,
        val secondNumber: Int,
    )


}