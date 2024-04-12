import java.util.Properties

plugins {
    id("android-library-convention")
    id("hilt-convention")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.matrix159.thecatapp.data"

    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("secrets.properties").inputStream())
        buildConfigField("String", "CATS_API_KEY", properties.getProperty("CATS_API_KEY"))
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.timber)
}