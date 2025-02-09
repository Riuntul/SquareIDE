plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "ide.square.shared"
    compileSdk = 34
    
    defaultConfig {
        targetSdk = 34
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
}