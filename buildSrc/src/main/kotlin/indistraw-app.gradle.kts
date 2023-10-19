import org.gradle.kotlin.dsl.project

val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
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
    packagingOptions.resources.excludes += setOf(
        "META-INF/DEPENDENCIES",
        "META-INF/LICENSE",
        "META-INF/LICENSE.txt",
        "META-INF/license.txt",
        "META-INF/NOTICE",
        "META-INF/NOTICE.txt",
        "META-INF/INDEX.LIST",
        "META-INF/notice.txt",
        "META-INF/ASL2.0",
        "META-INF/gradle/incremental.annotation.processors"
    )
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:remote"))
    implementation(project(":core:design-system"))
    implementation(libs.findLibrary("androidx.core").get())
    implementation(libs.findBundle("compose").get())
    implementation(libs.findLibrary("accompanist.nav").get())
    implementation(libs.findLibrary("hilt").get())
    ksp(libs.findLibrary("hilt.compiler").get())
    debugImplementation(libs.findBundle("compose.debug").get())
    androidTestImplementation(libs.findLibrary("compose.test").get())
}