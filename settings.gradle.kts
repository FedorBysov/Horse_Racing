pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Horse racing"
include(":app")
include(":feature")
include(":core")
include(":core:navigation")
include(":core:navigation:api")
include(":core:navigation:impl")
include(":feature:raiting")
include(":feature:raiting:api")
include(":feature:raiting:impl")
include(":feature:race")
include(":feature:race:api")
include(":feature:race:impl")
include(":core:network")
include(":core:network:api")
include(":core:network:impl")
include(":core:storage")
include(":core:storage:api")
include(":core:storage:impl")
include(":core:utils")
