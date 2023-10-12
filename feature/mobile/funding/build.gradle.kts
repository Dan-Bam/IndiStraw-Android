plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.funding"
}

dependencies {
    implementation(project(":feature:mobile:navigation"))
    implementation(libs.bootpay)
}