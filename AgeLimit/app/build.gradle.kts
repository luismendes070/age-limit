plugins {
    alias(libs.plugins.android.application)
    // id("com.android.application") version "7.4.2" // Make sure the version is correct
    kotlin("android") version "1.8.0" // Adjust based on your Kotlin version
}

android {
    namespace = "com.luismendes070.agelimit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luismendes070.agelimit"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("com.google.android.gms:play-services-auth:20.5.0") // Google Sign-In
    implementation("com.google.android.gms:play-services-games:22.0.0") // Play Games Services
    implementation("com.google.android.gms:play-services-games-client:22.0.0") // Client API
    implementation("com.google.android.gms:play-services-base:18.0.1") // Base services
    implementation("com.google.android.gms:play-services-tasks:18.0.1") // Task API

    implementation("com.android.tools.build:gradle:7.4.2") // Update this as per your project
    implementation("com.google.gms:google-services:4.3.15") // Google services classpath

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

