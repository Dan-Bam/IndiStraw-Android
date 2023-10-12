dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "indi_straw"
include(":mobile")
include(":core:data")
include(":core:domain")
include(":core:di")
include(":core:design-system")
include(":tv")