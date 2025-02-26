plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "org.riuntul.material"
    compileSdk = 35
    
    defaultConfig {
        minSdk = 29
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
}

dependencies {
    implementation(libs.google.material)
    implementation(project(":core:shared"))
}