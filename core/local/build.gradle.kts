plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.local"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.inject)
    implementation(libs.room)
    kapt(libs.room.compiler)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
}