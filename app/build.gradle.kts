plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.com.google.devtools.ksp)
	alias(libs.plugins.kotlin.parcelize)
	kotlin("kapt") version "2.1.0"
}

android {
	namespace = "com.kzerk.cryptoapp"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.kzerk.cryptoapp"
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

	buildFeatures {
		viewBinding {
			enable = true
		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

	implementation(libs.io.reactivex.rxjava2.rxjava)
	implementation(libs.io.reactivex.rxjava2.rxandroid)

	implementation(libs.squareup.retrofit2.retrofit)
	implementation(libs.squareup.retrofit2.converter)

	kapt(libs.androidx.lifecycle.compiler)
	implementation(libs.androidx.lifecycle.reactivestreams.ktx)

	implementation(libs.androidx.room.runtime)
	implementation(libs.androidx.room.ktx)
	ksp(libs.room.compiler)

	implementation(libs.squareup.picasso)
	implementation(libs.work.runtime.ktx)
	implementation (libs.kotlinx.coroutines.core)
	implementation (libs.kotlinx.coroutines.android)
	implementation (libs.lifecycle.viewmodel.ktx)
}