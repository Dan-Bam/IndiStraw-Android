import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.danbam.data"
    compileSdk = Version.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Version.MIN_SDK_VERSION
        targetSdk = Version.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Version.JAVA_VERSION
        targetCompatibility = Version.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Version.JAVA_VERSION.toString()
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