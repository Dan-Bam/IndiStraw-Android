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
    implementation(Dependency.Compose.COMPOSE_HILT_NAV)
    implementation(Dependency.Compose.VIEW_PAGER)

    implementation(Dependency.Coil.COIL)

    implementation(Dependency.ExoPlayer.EXO_PLAYER_CORE)
    implementation(Dependency.ExoPlayer.EXO_PLAYER_UI)

    implementation(Dependency.Camera.CAMERA)
    implementation(Dependency.Camera.LIFECYCLE)
    implementation(Dependency.Camera.VIEW)

    implementation(Dependency.Barcode.BARCODE)

    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)
}