import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.danbam.mobile"
    compileSdk = Version.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.danbam.indi_straw.mobile"
        minSdk = Version.MIN_SDK_VERSION
        targetSdk = Version.TARGET_SDK_VERSION
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            "String",
            "QR_URL",
            gradleLocalProperties(rootDir).getProperty("QR_URL")
        )
        buildConfigField(
            "String",
            "PAY_KEY",
            gradleLocalProperties(rootDir).getProperty("PAY_KEY")
        )
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
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
    hilt {
        enableAggregatingTask = true
    }
}
dependencies {
    implementation(project(":domain"))
    implementation(project(":di"))
    implementation(project(":design-system"))

    implementation(Dependency.AndroidX.CORE_KTX)
    implementation(Dependency.AndroidX.LIFECYCLE)
    implementation(Dependency.AndroidX.PAGING_COMPOSE)

    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.RETROFIT_CONVERTER_GSON)

    implementation(Dependency.Compose.ACTIVITY)
    implementation(Dependency.Compose.UI)
    implementation(Dependency.Compose.PREVIEW)
    implementation(Dependency.Compose.MATERIAL)
    implementation(Dependency.Compose.COMPOSE_HILT_NAV)

    implementation(Dependency.Accompanist.ANIMATE_NAVIGATION)

    implementation(Dependency.Kotlin.COROUTINES_CORE)
    implementation(Dependency.Kotlin.COROUTINES_ANDROID)

    implementation(Dependency.Hilt.HILT_ANDROID)
    kapt(Dependency.Hilt.HILT_ANDROID_COMPILER)

    implementation(Dependency.Mvi.ORBIT_CORE)
    implementation(Dependency.Mvi.ORBIT_VIEW_MODEL)
    implementation(Dependency.Mvi.ORBIT_TEST)

    implementation(Dependency.Coil.COIL)

    implementation(Dependency.BootPay.BOOT_PAY)

    debugImplementation(Dependency.AndroidTest.COMPOSE_TOOL)
    debugImplementation(Dependency.AndroidTest.COMPOSE_MANIFEST)
    androidTestImplementation(Dependency.AndroidTest.COMPOSE_TEST)
}
