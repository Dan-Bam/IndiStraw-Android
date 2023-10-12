plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.main"
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
}