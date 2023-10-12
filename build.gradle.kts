buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.plugin.android)
        classpath(libs.plugin.kotlin)
        classpath(libs.plugin.hilt)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
