plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.petfinder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.petfinder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    val navigationVersion = "2.7.4"
    val retrofitVersion = "2.9.0"
    val okHttpVersion = "4.9.1"
    val rxJavaVersion = "3.1.3"
    val rxJavaAdapterVersion = "3.0.0"
    val koinVersion = "3.2.3"
    val okHttpLoggingVersion = "4.9.1"
    val pagingVersion = "3.2.1"
    val pagingRxJava3Version = "3.2.1"
    val rxAndroidVersion = "3.0.0"

    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    // Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

    // RxJava and RxAndroid
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVersion")
    implementation("io.reactivex.rxjava3:rxandroid:$rxAndroidVersion")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:$rxJavaAdapterVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpLoggingVersion")

    implementation("io.insert-koin:koin-android:$koinVersion")

    implementation("androidx.paging:paging-common-ktx:$pagingVersion")
    implementation("androidx.paging:paging-rxjava3:$pagingRxJava3Version")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}