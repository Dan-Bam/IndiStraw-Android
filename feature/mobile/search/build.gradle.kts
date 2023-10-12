plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.search"
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
}