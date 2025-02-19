plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ide.square.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "ide.square.app"
        
        targetSdk = 34
        minSdk = 29
        
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.lsp4j)
    implementation(project(":core:shared"))
    implementation(project(":ui:material"))
}