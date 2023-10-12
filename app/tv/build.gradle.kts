import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-app")
}

android {
    namespace = "com.danbam.indistraw.app.tv"
    defaultConfig {
        applicationId = "com.danbam.indi_straw.tv"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        buildConfigField(
            "String",
            "QR_URL",
            gradleLocalProperties(rootDir).getProperty("QR_URL")
        )
    }
}

dependencies {
    implementation(libs.bundles.tv)
    implementation(libs.androix.leanback)
}