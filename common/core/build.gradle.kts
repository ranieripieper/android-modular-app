plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    libraryVariants.all {
        val variant = this
        variant.buildConfigField("String", "GITHUB_API_URL", "\"https://api.github.com\"")
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {

    implementation(AppDependencies.basicLibraries)

    implementation(AppDependencies.daggerLibrary)
    kapt(AppDependencies.daggerLibraryKapt)

    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.rxJavaLibraries)

    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}