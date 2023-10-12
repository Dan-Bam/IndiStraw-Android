val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

plugins {
    id("feature-mobile")
}

dependencies {
    implementation(libs.findBundle("tv").get())
}