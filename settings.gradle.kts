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
        maven { 
            url = uri("https://repo.gradle.org/gradle/libs-releases") 
        }
    }
}

rootProject.name = "Square IDE"
include(":core:app", ":core:template", ":core:tooling", ":java:lsp")