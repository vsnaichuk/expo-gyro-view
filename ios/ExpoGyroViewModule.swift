import ExpoModulesCore

public class ExpoGyroViewModule: Module {
  public func definition() -> ModuleDefinition {
    Name("ExpoGyroView")

    // Enables the module to be used as a native view. Definition components that are accepted as part of the
    // view definition: Prop, Events.
    View(ExpoGyroView.self) {
      Prop("placeholderText") { (view: ExpoGyroView, text: String) in
        
      }
    }
  }
}
