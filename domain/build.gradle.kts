plugins {
    id("indistraw-library")
}

android {
    namespace = "com.danbam.domain"
}

dependencies {
    implementation(Dependency.JavaX.INJECT)

    implementation(Dependency.Kotlin.COROUTINES_CORE)

    implementation(Dependency.AndroidX.PAGING)

    testImplementation(Dependency.UnitTest.JUNIT)
    testImplementation(Dependency.UnitTest.MOCKITO_KOTLIN)
    testImplementation(Dependency.UnitTest.MOCKITO_INLINE)
}