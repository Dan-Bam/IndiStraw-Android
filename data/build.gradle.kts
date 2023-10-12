import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-library")
}

android {
    namespace = "com.danbam.data"
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

    implementation(Dependency.JavaX.INJECT)

    implementation(Dependency.Room.ROOM)
    kapt(Dependency.Room.ROOM_COMPILER)

    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.RETROFIT_CONVERTER_GSON)
    implementation(Dependency.Retrofit.OKHTTP)
    implementation(Dependency.Retrofit.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Dependency.Retrofit.SSE)
    testImplementation(Dependency.Retrofit.SSE)

    implementation(Dependency.AndroidX.PAGING)

    testImplementation(Dependency.UnitTest.JUNIT)
    testImplementation(Dependency.UnitTest.MOCKITO_KOTLIN)
    testImplementation(Dependency.UnitTest.MOCKITO_INLINE)
}