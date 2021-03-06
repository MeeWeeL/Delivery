import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.meeweel.delivery"
    const val compile_sdk = 31
    const val min_sdk = 23
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val model = ":model"
    const val repository = ":repository"
}

object Versions {
    //Design
    const val appcompat = "1.4.1"
    const val material = "1.5.0"

    //Kotlin
    const val core = "1.7.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.5.2"
    const val coroutinesAndroid = "1.5.2"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinAndroid = "3.1.2"
    const val koinViewModel = "3.1.2"


    //Room
    const val roomKtx = "2.3.0"
    const val runtime = "2.3.0"
    const val roomCompiler = "2.3.0"

    //Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.2.0"

    // Glide
    const val compiler = "4.12.0"
    const val glide = "4.12.0"
}

object Glide {

    const val compiler = "com.github.bumptech.glide:compiler:${Versions.compiler}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

}
object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koin_view_model = "org.koin:koin-android-viewmodel:${Versions.koinViewModel}"
    const val koin_insert_core = "io.insert-koin:koin-core:3.1.2"
    const val koin_insert_android = "io.insert-koin:koin-android:3.1.2"
    const val koin_insert_compat = "io.insert-koin:koin-android-compat:3.1.2"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object LifeCycle {
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
    const val viewmodel = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"

}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}