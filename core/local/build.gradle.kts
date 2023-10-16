plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.local"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.room)
    kapt(libs.room.compiler)
}