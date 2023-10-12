plugins {
    id("indistraw-library")
}

android {
    namespace = "com.danbam.indistraw.domain"
}

dependencies {
    implementation(libs.inject)
    implementation(libs.coroutines.core)
    implementation(libs.paging)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
}