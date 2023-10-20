plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.plugin.android)
    implementation(libs.plugin.hilt)
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.ksp)
}