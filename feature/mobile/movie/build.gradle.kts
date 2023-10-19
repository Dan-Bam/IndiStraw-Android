import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.movie"
    defaultConfig {
        buildConfigField(
            "String",
            "VIDEO_PRE_PATH",
            gradleLocalProperties(rootDir).getProperty("VIDEO_PRE_PATH")
        )
    }
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
}