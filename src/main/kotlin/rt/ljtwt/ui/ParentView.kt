package rt.ljtwt.ui

import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.stage.DirectoryChooser
import tornadofx.View
import java.io.File
import java.nio.file.Paths

/**
 * Created on 11/10/16.
 */
class ParentView : View("Linux Java To Windows Text") {

    override val root: GridPane by fxml()
    val converterController: ConverterController by inject()
    val fromDirectoryTextField: TextField by fxid()
    val toDirectoryTextField: TextField by fxid()
    private var lastFromDirectory: File = File(System.getProperty("user.home"))
    private var lastToDirectory: File = File(System.getProperty("user.home"))

    @FXML
    fun fromAction() {
        val directoryChooser = DirectoryChooser()
        directoryChooser.title = "Choose Your Directory"
        directoryChooser.initialDirectory = lastFromDirectory
        val file: File? = directoryChooser.showDialog(root.scene.window)
        if (file != null) {
            lastFromDirectory = file
            fromDirectoryTextField.text = file.toPath().toString()
        }
    }


    @FXML
    fun toAction() {
        val directoryChooser = DirectoryChooser()
        directoryChooser.initialDirectory = lastToDirectory
        directoryChooser.title = "Choose Your Directory"
        val file: File? = directoryChooser.showDialog(root.scene.window)
        if (file != null) {
            lastToDirectory = file
            toDirectoryTextField.text = file.toPath().toString()
        }
    }


    @FXML
    fun finishAction() {
        val fromDirectory = Paths.get(fromDirectoryTextField.text).toFile()
        val toDirectory = Paths.get(toDirectoryTextField.text).toFile()
        converterController.writeTextFiles(fromDirectory, toDirectory)
    }
}
