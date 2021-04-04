plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
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

    //dynamic features
    dynamicFeatures = mutableSetOf(
        BuildModules.Features.GIT_REPOSITORY,
        BuildModules.Features.PULL_REQUEST
    )
}

dependencies {

    implementation(AppDependencies.basicLibraries)

    implementation(AppDependencies.daggerLibrary)
    kapt(AppDependencies.daggerLibraryKapt)

    //common modules
    implementation(project(BuildModules.Common.CORE))
    implementation(project(BuildModules.Common.UI))
    implementation(project(BuildModules.Common.NAVITATION))

    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)

}