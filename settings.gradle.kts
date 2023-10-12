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
include(":core:data")
include(":core:domain")
include(":core:di")
include(":core:design-system")
include(":feature:mobile:auth")
include(":feature:mobile:search")
include(":feature:mobile:funding")
