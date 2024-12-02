package io.github.mikaojk.day1.part2

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


fun day1Part2(): String {
    // input is here: https://adventofcode.com/2024/day/1
    val locationIdsInput = getFileAsString("src/main/resources/day1/input.txt")

    val locationIdsLeft = locationIdsInput.split("\\n".toRegex()).map {
        LocationId(it.substringBefore("  ").trim().toInt())
    }

    val locationIdsRigth = locationIdsInput.split("\\n".toRegex()).map {
        LocationId(it.substringAfter("  ").trim().toInt())
    }


    return similarityScore(locationIdsLeft, locationIdsRigth).toString()

}

data class LocationId(
    val locationId: Int
)

fun similarityScore(locationIdLeft: List<LocationId>, locationIdRight: List<LocationId>): Int {
    var sum = 0

    locationIdLeft.forEach { location ->
        sum += (location.locationId * locationIdRight.count { it -> it.locationId == location.locationId })
    }

    return sum
}


fun getFileAsString(filePath: String): String {
    try {
        return String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
    } catch (exception: Exception) {
        throw Exception("Could not get file content as string", exception)
    }
}