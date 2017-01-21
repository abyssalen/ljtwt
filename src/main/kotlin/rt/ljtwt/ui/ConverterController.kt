package rt.ljtwt.ui

import rt.ljtwt.util.EOLUtils
import tornadofx.Controller
import java.io.File
import java.nio.file.Files
import java.util.function.BiPredicate

/**
 * Created on 11/10/16.
 */
class ConverterController : Controller() {

    fun writeTextFiles(fromDirectory: File, toDirectory: File) {
        Files.find(fromDirectory.toPath(), 99, BiPredicate { path, basicFileAttributes ->
            path.fileName.toString().endsWith(".java").and(basicFileAttributes.isRegularFile)
        }).forEach {
            writeTextFile(fileToRead = it.toFile(), toDirectory = toDirectory)
        }
    }

    private fun writeTextFile(fileToRead: File, toDirectory: File) {
        val newFile = File(toDirectory, "${fileToRead.nameWithoutExtension}_code.txt")
        newFile.bufferedWriter().use { writer ->
            writer.appendln("/* Auto generated text file using LinuxJavaToWindowsTxt (LJTWT) */")
            writer.appendln()
            fileToRead.bufferedReader().copyTo(writer)
        }
        EOLUtils.convertToWindowsEOL(newFile);
    }
}