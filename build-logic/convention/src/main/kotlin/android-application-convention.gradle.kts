plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  commonConfiguration(this)
  composeConfiguration(this)

  defaultConfig {
    targetSdk = versionCatalog.findVersion("compile-sdk").get().requiredVersion.toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

kotlin {
  configureKotlinAndroid(this)
}