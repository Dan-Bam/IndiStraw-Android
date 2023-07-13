import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.danbam.design_system"
    compileSdk = Version.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Version.MIN_SDK_VERSION
        targetSdk = Version.TARGET_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            "String",
            "VIDEO_PRE_PATH",
            gradleLocalProperties(rootDir).getProperty("VIDEO_PRE_PATH")
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
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependency.Compose.ACTIVITY)
    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.VIEW_BINDING)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Compose.MATERIAL)
    implementation(Dependency.Compose.MATERIAL3)
    implementation(Dependency.Compose.COMPOSE_HILT_NAV)
    implementation(Dependency.Compose.FOUNDATION)

    implementation(Dependency.Coil.COIL)

    implementation(Dependency.ExoPlayer.EXO_PLAYER_CORE)
    implementation(Dependency.ExoPlayer.EXO_PLAYER_UI)

    implementation(Dependency.Camera.CAMERA)
    implementation(Dependency.Camera.LIFECYCLE)
    implementation(Dependency.Camera.VIEW)

    implementation(Dependency.Barcode.SCANNING)
    implementation(Dependency.Barcode.DRAWING)

    implementation(Dependency.Tv.MATERIAL)
    implementation(Dependency.Tv.FOUNDATION)

    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)
}