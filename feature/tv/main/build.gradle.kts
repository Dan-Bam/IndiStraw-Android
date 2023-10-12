import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.tv.main"
    defaultConfig {
        buildConfigField(
            "String",
            "QR_URL",
            gradleLocalProperties(rootDir).getProperty("QR_URL")
        )
    }
}

dependencies {
    implementation(project(":feature:tv:home"))
    implementation(project(":feature:tv:movie"))
    implementation(project(":feature:tv:navigation"))
    implementation(project(":feature:tv:search"))
    implementation(project(":feature:tv:setting"))
    implementation(libs.bundles.tv)
}