plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.app_s9"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.app_s9"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {
    // KTX (Extensiones de Kotlin para facilitar la escritura)
    implementation(libs.androidx.core.ktx)

    // AppCompat (Compatibilidad con vistas y temas de Android)
    implementation(libs.androidx.appcompat)

    // Material Design (Componentes modernos de UI como botones, Switches, etc.)
    implementation(libs.material)

    // Activity KTX (Extensiones para Activity, como funciones para iniciar actividades)
    implementation(libs.androidx.activity)

    // ConstraintLayout (Para layouts avanzados)
    implementation(libs.androidx.constraintlayout)

    // Dependencias para pruebas unitarias
    testImplementation(libs.junit)

    // Dependencias para pruebas UI
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

}
