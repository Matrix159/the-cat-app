import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Sets common values for Android Applications and Libraries
 */
fun org.gradle.api.Project.commonConfiguration(
  extension: CommonExtension<*, *, *, *, *, *>
) = extension.apply {
  compileSdk = versionCatalog.findVersion("compile-sdk").get().requiredVersion.toInt()

  defaultConfig {
    minSdk = versionCatalog.findVersion("min-sdk").get().requiredVersion.toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildFeatures {
    buildConfig = true
  }
}

fun org.gradle.api.Project.composeConfiguration(
  extension: CommonExtension<*, *, *, *, *, *>
) = extension.apply {
  buildFeatures {
    compose = true
  }
  composeOptions {
    val composeCompilerVersion = versionCatalog
      .findVersion("androidx-compose-compiler")
      .get()
      .requiredVersion
    kotlinCompilerExtensionVersion = composeCompilerVersion
  }
}

fun org.gradle.api.Project.configureKotlinAndroid(
  kotlinAndroidProjectExtension: KotlinAndroidProjectExtension
) {
  kotlinAndroidProjectExtension.apply {
    jvmToolchain(17)
  }
}

val org.gradle.api.Project.versionCatalog
  get() = extensions.getByType(VersionCatalogsExtension::class.java)
    .named("libs")