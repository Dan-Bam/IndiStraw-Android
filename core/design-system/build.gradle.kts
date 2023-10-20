plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.design_system"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.coil)
    implementation(libs.bundles.tv)
    implementation(libs.bundles.camera)
}