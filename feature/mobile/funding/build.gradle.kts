plugins {
    id("indistraw-feature")
}

android {
    namespace = "com.danbam.indistraw.feature.mobile.funding"
}

dependencies {
    implementation(libs.bootpay)
}