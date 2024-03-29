pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "indi_straw"
include(":app:mobile")
include(":app:tv")
include(":core:local")
include(":core:remote")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:design-system")
include(":feature:mobile:auth")
include(":feature:mobile:search")
include(":feature:mobile:funding")
include(":feature:mobile:movie")
include(":feature:mobile:profile")
include(":feature:mobile:main")
include(":feature:mobile:navigation")
include(":feature:tv:home")
include(":feature:tv:main")
include(":feature:tv:setting")
include(":feature:tv:movie")
include(":feature:tv:search")
include(":feature:tv:navigation")
