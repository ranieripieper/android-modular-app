plugins {
    id("com.android.dynamic-feature")
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

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}

dependencies {

    //main project
    implementation(project(BuildModules.APP))

    //common modules
    implementation(project(BuildModules.Common.CORE))
    implementation(project(BuildModules.Common.UI))
    implementation(project(BuildModules.Common.NAVITATION))

    implementation(AppDependencies.basicLibraries)

    //glide
    implementation(AppDependencies.glideLibrary)

    //retrofit
    implementation(AppDependencies.retrofitLibraries)

    //dagger
    implementation(AppDependencies.daggerLibrary)
    kapt(AppDependencies.daggerLibraryKapt)

    //rx java
    implementation(AppDependencies.rxJavaLibraries)

    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}