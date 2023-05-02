package expo.modules.gyroview

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

const val kOnGyroEvent = "onGyroEvent"

class ExpoGyroViewModule : Module() {
  private var isInitialized: Boolean = false
  private var placeholderText: String? = null
  private var gyroEventListener: GyroEventListener? = null

  override fun definition() = ModuleDefinition {
    Name("ExpoGyroView")

    View(ExpoGyroView::class) {
      Events(kOnGyroEvent)

      Prop("placeholderText") { view: ExpoGyroView, text: String? ->
        view.textView.text = text ?: "START"
        placeholderText = text
      }

      Prop("track") { view: ExpoGyroView, isTracking: Boolean ->
        val activity = appContext.activityProvider?.currentActivity
        val applicationContext = activity?.applicationContext

        if(applicationContext != null && !isInitialized) {
          isInitialized = true
          gyroEventListener = GyroEventListener(applicationContext) {
            view.updateText(it)
            view.onGyroEvent(mapOf("y" to it))
          }
        }
        gyroEventListener?.isTracking = isTracking
        view.textView.text = placeholderText ?: "START"
      }
    }
  }
}
