package io.github.mikaojk.day1.part1

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs


fun day1Part1(): String {
    // input is here: https://adventofcode.com/2024/day/1
    val locationIdsInput = getFileAsString("src/main/resources/day1/input.txt")

    val locationIdsLeft = locationIdsInput.split("\\n".toRegex()).map {
        LocationId(it.substringBefore("  ").trim().toInt())
        }

    val locationIdsRigth = locationIdsInput.split("\\n".toRegex()).map {
        LocationId(it.substringAfter("  ").trim().toInt())
    }


    return totaldistance(locationIdsLeft, locationIdsRigth).toString()

}

data class LocationId(
    val locationId: Int
)

data class LocationIdPair(
    val locationIdLeft: Int,
    val locationIdRight: Int
)

fun totaldistance(locationIdLeft: List<LocationId>, locationIdRight: List<LocationId>): Int {

    val locationIdLeftOrdet = locationIdLeft.sortedBy { it.locationId }
    val locationIdRightOrdet = locationIdRight.sortedBy { it.locationId }

    val locationIdPairs = locationIdLeftOrdet.mapIndexed { index, locationId ->
        LocationIdPair(locationIdLeft = locationId.locationId, locationIdRight = locationIdRightOrdet[index].locationId)
    }

    var sum = 0

    locationIdPairs.forEach { locationPair ->
        sum += distance(locationPair.locationIdLeft, locationPair.locationIdRight)
    }

    return sum
}

fun distance(location1: Int, location2: Int): Int {
    return abs(location1 - location2)
}


fun getFileAsString(filePath: String): String {
    try {
        return String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
    } catch (exception: Exception) {
        throw Exception("Could not get file content as string", exception)
    }
}