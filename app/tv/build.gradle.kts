plugins {
    id("indistraw-app")
}

android {
    namespace = "com.danbam.indistraw.app.tv"
    defaultConfig {
        applicationId = "com.danbam.indi_straw.tv"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }
}

dependencies {
    implementation(project(":feature:tv:main"))
    implementation(project(":feature:tv:navigation"))
    implementation(libs.bundles.tv)
    implementation(libs.androix.leanback)
}