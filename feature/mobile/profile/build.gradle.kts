plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.profile"
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
}