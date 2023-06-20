plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.danbam.tv"
    compileSdk = Version.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.danbam.indi_straw.tv"
        minSdk = Version.MIN_SDK_VERSION
        targetSdk = Version.TARGET_SDK_VERSION
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
    implementation(project(":di"))
    implementation(project(":design-system"))

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.leanback:leanback:1.0.0")

    implementation(Dependency.Hilt.HILT_ANDROID)
    kapt(Dependency.Hilt.HILT_ANDROID_COMPILER)
}