plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.movie"
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
}