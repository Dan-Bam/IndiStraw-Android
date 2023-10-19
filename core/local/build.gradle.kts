plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.local"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.androidx.preference)
    implementation(libs.room)
    ksp(libs.room.compiler)
}