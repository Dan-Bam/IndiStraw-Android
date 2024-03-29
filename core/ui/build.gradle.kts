import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.ui"
    defaultConfig {
        buildConfigField(
            "String",
            "VIDEO_PRE_PATH",
            gradleLocalProperties(rootDir).getProperty("VIDEO_PRE_PATH")
        )
        buildConfigField(
            "String",
            "PAY_KEY",
            gradleLocalProperties(rootDir).getProperty("PAY_KEY")
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:design-system"))
    implementation(libs.bundles.compose)
    implementation(libs.coil)
    implementation(libs.bundles.exoplayer)
    implementation(libs.bundles.camera)
    implementation(libs.bundles.barcode)
    implementation(libs.bundles.tv)
    implementation(libs.bundles.retrofit)
    implementation(libs.bootpay)
}