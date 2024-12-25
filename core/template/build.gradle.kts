plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "ide.square.template"
    compileSdk = 34
    
    defaultConfig { 
        targetSdk = 34
        minSdk = 29
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}