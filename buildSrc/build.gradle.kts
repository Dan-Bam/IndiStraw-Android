plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
    languageVersion = libs.versions.gradleKotlin.get()
}

dependencies {
    implementation(libs.plugin.android)
    implementation(libs.plugin.hilt)
    implementation(libs.plugin.kotlin)
}