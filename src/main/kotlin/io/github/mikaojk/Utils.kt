package io.github.mikaojk

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

fun getFileAsString(filePath: String): String {
    try {
        return String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
    } catch (exception: Exception) {
        throw Exception("Could not get file content as string", exception)
    }
}