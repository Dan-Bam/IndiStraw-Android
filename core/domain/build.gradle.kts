plugins {
    id("indistraw-core")
}

android {
    namespace = "com.danbam.indistraw.core.domain"
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.paging)
}