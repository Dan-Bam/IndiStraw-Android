val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
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
    implementation(project(":core:ui"))
    implementation(libs.findLibrary("androidx.core").get())
    implementation(libs.findLibrary("androidx.lifecycle").get())
    implementation(libs.findBundle("coroutine").get())
    implementation(libs.findBundle("compose").get())
    implementation(libs.findLibrary("accompanist.nav").get())
    implementation(libs.findLibrary("hilt").get())
    ksp(libs.findLibrary("hilt.compiler").get())
    implementation(libs.findBundle("orbit").get())
    implementation(libs.findLibrary("coil").get())
    debugImplementation(libs.findBundle("compose.debug").get())
    androidTestImplementation(libs.findLibrary("compose.test").get())
}