plugins {
  id("android-library-convention")
  id("android-compose-convention")
  id("hilt-convention")
}

android {
  namespace = "com.matrix159.thecatapp.feature.catbreeds"
}

dependencies {
  implementation(project(":core:ui"))
  implementation(project(":core:domain"))
  implementation(platform(libs.compose.bom))
  implementation(libs.core.ktx)
  implementation(libs.lifecycle.runtime.ktx)
  implementation(libs.compose.lifecycleRuntime)
  implementation(libs.material3)
  implementation(libs.coil)

  testImplementation(project(":core:domain"))
  testImplementation(project(":core:data"))
  debugImplementation(libs.ui.tooling)
  debugImplementation(libs.ui.test.manifest)
}