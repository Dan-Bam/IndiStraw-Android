import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-library")
}

android {
    namespace = "com.danbam.design_system"
    defaultConfig {
        buildConfigField(
            "String",
            "VIDEO_PRE_PATH",
            gradleLocalProperties(rootDir).getProperty("VIDEO_PRE_PATH")
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.bundles.compose)
    implementation(libs.coil)
    implementation(libs.bundles.exoplayer)
    implementation(libs.bundles.camera)
    implementation(libs.bundles.barcode)
    implementation(libs.bundles.tv)

}