plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.tv.movie"
}

dependencies {
    implementation(project(":feature:tv:navigation"))
    implementation(libs.bundles.tv)
}