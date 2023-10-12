import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-library")
    id("kotlin-android")
}

android {
    namespace = "com.danbam.indistraw.core.di"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.preference)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.room)
    kapt(libs.room.compiler)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
}