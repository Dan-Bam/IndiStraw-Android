import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.remote"
    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("BASE_URL")
        )
        buildConfigField(
            "String",
            "JUSO_KEY",
            gradleLocalProperties(rootDir).getProperty("JUSO_KEY")
        )
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.inject)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    testImplementation(libs.okhttp.sse)
    implementation(libs.paging)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
}