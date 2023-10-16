import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.data"
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
    }
}

dependencies {
    implementation(project(":core:remote"))
    implementation(project(":core:local"))
    implementation(project(":core:domain"))
    implementation(libs.inject)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    implementation(libs.paging)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
}