package de.xyndra.wikwriv

import java.nio.file.FileSystemNotFoundException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object ResourceUtil {
    fun copyResourceToDir(resource: String, folder: String, dir: Path) {
        val uri = ResourceUtil::class.java.getResource(folder + resource)?.toURI()?: throw IllegalArgumentException("Resource not found: $resource")
        if (Files.notExists(dir)) {
            Files.createDirectories(dir)
        }
        val resourcePath = try {
            Paths.get(uri)
        } catch (_: FileSystemNotFoundException) {
            // If this is thrown, then it means that we are running the JAR directly (example: not from an IDE)
            val env = mutableMapOf<String, String>()
            FileSystems.newFileSystem(uri, env).getPath(resource)
        }
        // Create the necessary directories
        Files.createDirectories(dir.resolve(resource - -1).parent)
        Files.write(dir.resolve((resource - -1)), Files.readAllBytes(resourcePath))
    }

    fun getFilesInResourceDir(dir: String): List<String> {
        val uri = ResourceUtil::class.java.getResource(dir)?.toURI()?: throw IllegalArgumentException("Resource not found: $dir")
        val dirPath = try {
            Paths.get(uri)
        } catch (_: FileSystemNotFoundException) {
            // If this is thrown, then it means that we are running the JAR directly (example: not from an IDE)
            val env = mutableMapOf<String, String>()
            FileSystems.newFileSystem(uri, env).getPath(dir)
        }
        return getFilesRecursive(dirPath).map { it.toString() - dirPath.toString() }
    }

    fun getFilesRecursive(path: Path): List<Path> {
        val files = mutableListOf<Path>()
        Files.list(path).forEach {
            if (Files.isDirectory(it)) {
                files.addAll(getFilesRecursive(it))
            } else {
                files.add(it)
            }
        }
        return files
    }
}