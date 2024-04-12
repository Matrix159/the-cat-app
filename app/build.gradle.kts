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
//    implementation(projects.core.data)
//    implementation(projects.core.ui)
//    implementation(projects.feature.transactions)
//    implementation(projects.feature.settings)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.lifecycleRuntime)
    implementation(libs.material3)
//  implementation(libs.material3.extendedIcons)

    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    // date time
    implementation(libs.kotlinx.datetime)
    // Logging
    implementation(libs.timber)

    // glance
    // For AppWidgets support
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)

    implementation(libs.androidx.material3.windowSizeClass)

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
