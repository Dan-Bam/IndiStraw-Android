import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-library")
}

android {
    namespace = "com.danbam.indistraw.data"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
        buildConfigField(
            "String",
            "ALL_IGNORE_PATH",
            gradleLocalProperties(rootDir).getProperty("ALL_IGNORE_PATH")
        )
        buildConfigField(
            "String",
            "POST_IGNORE_PATH",
            gradleLocalProperties(rootDir).getProperty("POST_IGNORE_PATH")
        )
        buildConfigField(
            "String",
            "GET_IGNORE_PATH",
            gradleLocalProperties(rootDir).getProperty("GET_IGNORE_PATH")
        )
        buildConfigField(
            "String",
            "PATCH_IGNORE_PATH",
            gradleLocalProperties(rootDir).getProperty("PATCH_IGNORE_PATH")
        )
        buildConfigField(
            "String",
            "JUSO_KEY",
            gradleLocalProperties(rootDir).getProperty("JUSO_KEY")
        )
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.inject)
    implementation(libs.room)
    kapt(libs.room.compiler)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    testImplementation(libs.okhttp.sse)
    implementation(libs.paging)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
}