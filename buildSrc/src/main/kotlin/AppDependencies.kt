import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    //Basic libs
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"

    //test libs
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    private const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    private const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"

    //dagger
    private const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    private const val daggerKapt = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    //retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitAdapterRxJava =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    private const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    private const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    private const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // Glide
    private const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    private const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //rxJava
    private const val rxJava = "io.reactivex.rxjava2:rxandroid:${Versions.rxjava}"

    //basic Libraries
    val basicLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(recyclerview)
    }

    //dagger
    val daggerLibrary = arrayListOf<String>().apply {
        add(dagger)
    }

    val daggerLibraryKapt = arrayListOf<String>().apply {
        add(daggerKapt)
    }

    //glide
    val glideLibrary = arrayListOf<String>().apply {
        add(glide)
    }

    val glideLibraryKapt = arrayListOf<String>().apply {
        add(glideKapt)
    }

    //retrofit
    val retrofitLibraries = arrayListOf<String>().apply {
        add(retrofit)
        add(retrofitAdapterRxJava)
        add(loggingInterceptor)
        add(okhttp)
        add(retrofitGson)
    }

    //rxJava
    val rxJavaLibraries = arrayListOf<String>().apply {
        add(rxJava)
    }

    //test Libraries
    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mockitoCore)
        add(mockitoInline)
        add(coreTesting)
    }

}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.api(list: List<String>) {
    list.forEach { dependency ->
        add("api", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}