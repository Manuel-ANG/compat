package com.ngam.zippy

import java.io.File
import java.io.FileWriter
import java.util.*

class CreateFile {
    companion object {
        var rootPath: File? = null
        var extension = "json"

        fun nameFile() = "${UUID.randomUUID()}"

        fun create(rootPath: File, data: String, nameFile: String, extension: String): File {
            val file = File(rootPath, "$nameFile.$extension")
            file.createNewFile()
            val writer = FileWriter(file, false)
            writer.write(data)
            writer.flush()
            writer.close()
            return file
        }

        fun create(data: String, nameFile: String?, extension: String?): File {
            if (rootPath == null) {
                throw IllegalArgumentException("NOT FOUND rootPath")
            } else {
                val file = File(rootPath, "${nameFile ?: nameFile()}.${extension ?: this.extension}")
                file.createNewFile()
                val writer = FileWriter(file, false)
                writer.write(data)
                writer.flush()
                writer.close()
                return file
            }
        }

        fun create(data: String, extension: String?): File {
            if (rootPath == null) {
                throw IllegalArgumentException("NOT FOUND rootPath")
            } else {
                val file = File(rootPath, "${nameFile()}.${extension ?: this.extension}")
                file.createNewFile()
                val writer = FileWriter(file, false)
                writer.write(data)
                writer.flush()
                writer.close()
                return file
            }
        }

        fun create(data: String): File {
            if (rootPath == null) {
                throw IllegalArgumentException("NOT FOUND rootPath")
            } else {
                val file = File(rootPath, "${nameFile()}.$extension")
                file.createNewFile()
                val writer = FileWriter(file, false)
                writer.write(data)
                writer.flush()
                writer.close()
                return file
            }
        }
    }
}