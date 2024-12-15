plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)


}

android {
    namespace = "com.example.slamdunks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.slamdunks"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
    android {
        buildFeatures {
            dataBinding = true
        }
    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation ("androidx.recyclerview:recyclerview:1.2.1")
        implementation ("androidx.appcompat:appcompat:1.3.1")
        implementation ("androidx.constraintlayout:constraintlayout:2.1.1")
        implementation ("com.google.code.gson:gson:2.10.1")
        implementation ("com.github.bumptech.glide:glide:4.15.1")
        implementation(libs.androidx.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)




    }
}
dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}
