package com.ngam.compat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ngam.zippy.CreateFile
import com.ngam.zippy.Zipper
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        writeFile("dsds")
    }

    private fun writeFile(data: String?): File? {
        try {
            if (data.isNullOrEmpty()) {
                return null
            } else {
                val dir = filesDir
                val directory = File(dir, "AQUI")
                if (dir.isDirectory && directory.exists()) {
                    directory.deleteRecursively()
                }
                directory.mkdir()
                CreateFile.apply {
                    rootPath = directory
                }
                val lista: ArrayList<File> = arrayListOf(
                    CreateFile.create("holaaaaa"),
                    CreateFile.create("YAAAAA"),
                    CreateFile.create("SDDJKSKJDHSDD"),
                    CreateFile.create("SDJFSDFDH")
                )
                val fileZip = File(directory, "SINGLEFILE.zip")
                //ZipUtils().zipFile(file.absolutePath, fileZip.absolutePath, "")
                Zipper.zip(CreateFile.create("holaaaaa"), fileZip, "12345")
                lista.forEach {
                    it.delete()
                }
                //Zipper.unzip(fileZip,directory,"MANUEL")
                return fileZip
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return null
        }
    }
}