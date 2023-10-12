import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-app")
}

android {
    namespace = "com.danbam.indistraw.app.mobile"
    defaultConfig {
        applicationId = "com.danbam.indi_straw.mobile"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        buildConfigField(
            "String",
            "QR_URL",
            gradleLocalProperties(rootDir).getProperty("QR_URL")
        )
    }
}