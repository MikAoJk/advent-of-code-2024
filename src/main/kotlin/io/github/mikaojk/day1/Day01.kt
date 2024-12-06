package io.github.mikaojk.day1

import io.github.mikaojk.getFileAsString
import kotlin.math.abs

class Day01 {

    fun part1(): String {
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

    fun part2(): String {
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

    fun similarityScore(locationIdLeft: List<LocationId>, locationIdRight: List<LocationId>): Int {
        var sum = 0

        locationIdLeft.forEach { location ->
            sum += (location.locationId * locationIdRight.count { it -> it.locationId == location.locationId })
        }

        return sum
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
            LocationIdPair(
                locationIdLeft = locationId.locationId,
                locationIdRight = locationIdRightOrdet[index].locationId
            )
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


}