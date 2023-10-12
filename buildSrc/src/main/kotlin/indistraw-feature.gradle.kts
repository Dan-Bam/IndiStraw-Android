val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("compose").get().requiredVersion
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:design-system"))
    implementation(libs.findLibrary("androidx.core").get())
    implementation(libs.findLibrary("androidx.lifecycle").get())
    implementation(libs.findBundle("coroutine").get())
    implementation(libs.findBundle("compose").get())
    implementation(libs.findLibrary("accompanist.nav").get())
    implementation(libs.findLibrary("hilt").get())
    kapt(libs.findLibrary("hilt.compiler").get())
    implementation(libs.findBundle("orbit").get())
    implementation(libs.findLibrary("coil").get())
    debugImplementation(libs.findBundle("compose.debug").get())
    androidTestImplementation(libs.findLibrary("compose.test").get())
}