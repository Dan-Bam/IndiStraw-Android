plugins {
    id("indistraw-app")
}

android {
    namespace = "com.danbam.indistraw.app.mobile"
    defaultConfig {
        applicationId = "com.danbam.indi_straw.mobile"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }
}

dependencies {
    implementation(project(":feature:mobile:auth"))
    implementation(project(":feature:mobile:funding"))
    implementation(project(":feature:mobile:main"))
    implementation(project(":feature:mobile:movie"))
    implementation(project(":feature:mobile:navigation"))
    implementation(project(":feature:mobile:profile"))
    implementation(project(":feature:mobile:search"))
}