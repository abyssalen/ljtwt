package rt.ljtwt

import javafx.application.Application
import rt.ljtwt.ui.ParentView
import tornadofx.App

/**
 * Created on 11/10/16.
 */
class LJTWTApp : App(ParentView::class)

fun main(args: Array<String>) {
    Application.launch(LJTWTApp::class.java)
}