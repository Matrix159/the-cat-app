plugins {
  id("android-application-convention")
  id("hilt-convention")
}

android {
  namespace = "com.matrix159.thecatapp"

  defaultConfig {
    applicationId = "com.matrix159.thecatapp"
    versionCode = 1
    versionName = "0.0.1"
  }

  buildTypes {
    debug {
      applicationIdSuffix = ".debug"
    }

    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(project(":core:domain"))
  implementation(project(":core:data"))
  implementation(project(":core:ui"))
  implementation(project(":feature:catbreeds"))
  implementation(libs.core.ktx)
  implementation(libs.lifecycle.runtime.ktx)

  implementation(libs.activity.compose)
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.lifecycleRuntime)
  implementation(libs.material3)

  implementation(libs.ui)
  implementation(libs.ui.graphics)
  implementation(libs.ui.tooling.preview)
  // Logging
  implementation(libs.timber)


  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(kotlin("test"))
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
  androidTestImplementation(platform(libs.compose.bom))
  androidTestImplementation(libs.ui.test.junit4)
  androidTestImplementation(kotlin("test"))
  debugImplementation(libs.ui.tooling)
  debugImplementation(libs.ui.test.manifest)
}
