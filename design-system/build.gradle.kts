import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-library")
}

android {
    namespace = "com.danbam.design_system"
    defaultConfig {
        buildConfigField(
            "String",
            "VIDEO_PRE_PATH",
            gradleLocalProperties(rootDir).getProperty("VIDEO_PRE_PATH")
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependency.Compose.ACTIVITY)
    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Compose.MATERIAL)
    implementation(Dependency.Compose.MATERIAL3)
    implementation(Dependency.Compose.COMPOSE_HILT_NAV)
    implementation(Dependency.Compose.FOUNDATION)

    implementation(Dependency.Coil.COIL)

    implementation(Dependency.ExoPlayer.EXO_PLAYER_CORE)
    implementation(Dependency.ExoPlayer.EXO_PLAYER_HLS)
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