plugins {
    alias(libs.plugins.com.android.application.plugin)
    kotlin("android")
    alias(libs.plugins.org.jetbrains.compose.plugin)
}

android {
    namespace = "com.chungha.team.survey.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.chungha.team.survey.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.androidx.compose.animation)
    implementation(libs.io.insert.koin.android)
}