import ext.libs

plugins {
  id("com.google.devtools.ksp")
  id("dagger.hilt.android.plugin")
}

dependencies {
  "implementation"(libs.findLibrary("hilt").get())
  "ksp"(libs.findLibrary("hilt.android.compiler").get())
  "implementation"(libs.findLibrary("hilt.viewmodel").get())
}
