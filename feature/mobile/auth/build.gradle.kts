plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.auth"
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
}