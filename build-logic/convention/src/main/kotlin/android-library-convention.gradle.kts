import ext.libs

plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  commonConfiguration(this)
}

kotlin {
  configureKotlinAndroid(this)
}

dependencies {
  testImplementation(kotlin("test"))
  "testImplementation"(libs.findLibrary("kotlinx.coroutines.test").get())

  "androidTestImplementation"(libs.findLibrary("androidx.test.ext.junit").get())
  "androidTestImplementation"(libs.findLibrary("kotlinx.coroutines.test").get())
  "androidTestImplementation"(libs.findLibrary("espresso.core").get())
  androidTestImplementation(kotlin("test"))
}