plugins {
  id("android-library-convention")
  id("android-compose-convention")
}

android {
  namespace = "com.matrix159.thecatapp.core.ui"
}

dependencies {
  implementation(platform(libs.compose.bom))
  implementation(libs.core.ktx)
  implementation(libs.material3)
}