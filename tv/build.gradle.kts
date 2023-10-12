import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("feature-tv")
}

android {
    namespace = "com.danbam.tv"
    defaultConfig {
        applicationId = "com.danbam.indi_straw.tv"
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME
        buildConfigField(
            "String",
            "QR_URL",
            gradleLocalProperties(rootDir).getProperty("QR_URL")
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
    implementation(project(":di"))
    implementation(Dependency.AndroidX.LEANBACK)
}