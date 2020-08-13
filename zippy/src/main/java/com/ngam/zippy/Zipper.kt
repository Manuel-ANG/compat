package com.ngam.zippy

import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.model.ZipParameters
import net.lingala.zip4j.model.enums.AesKeyStrength
import net.lingala.zip4j.model.enums.CompressionLevel
import net.lingala.zip4j.model.enums.CompressionMethod
import net.lingala.zip4j.model.enums.EncryptionMethod
import java.io.File

class Zipper {

    companion object {
        private fun parameters(): ZipParameters {
            return ZipParameters().apply {
                compressionMethod = CompressionMethod.DEFLATE
                compressionLevel = CompressionLevel.NORMAL
            }
        }

        fun encryp(params: ZipParameters): ZipParameters {
            return params.apply {
                isEncryptFiles = true
                encryptionMethod = EncryptionMethod.ZIP_STANDARD
                aesKeyStrength = AesKeyStrength.KEY_STRENGTH_128
            }
        }

        fun zip(file: File, destination: File, pwd: String?) {
            val pass = if (pwd.isNullOrEmpty()) {
                ""
            } else {
                pwd
            }
            val parameters = parameters()
            if (!pwd.isNullOrEmpty()) {
                parameters.apply {
                    encryp(this)
                }
            }
            val zipfile = ZipFile(destination, pass.toCharArray())
            zipfile.addFile(file, parameters)
        }

        fun zip(file: List<File>, destination: File, pwd: String?) {
            val pass = if (pwd.isNullOrEmpty()) {
                ""
            } else {
                pwd
            }

            val parameters = parameters()
            if (!pwd.isNullOrEmpty()) {
                parameters.apply {
                    encryp(this)
                }
            }
            val zipfile = ZipFile(destination, pass.toCharArray())
            zipfile.addFiles(file, parameters)
        }

        fun unzip(fileLocation: File, fileDestination: File, pwd: String?) {
            val zip = ZipFile(fileLocation.absolutePath)
            if (zip.isEncrypted) {
                val pass = if (pwd.isNullOrEmpty()) {
                    ""
                } else {
                    pwd
                }
                zip.setPassword(pass.toCharArray())
            }
            zip.extractAll(fileDestination.absolutePath)
        }
    }
}