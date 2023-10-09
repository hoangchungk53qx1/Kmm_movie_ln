plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform.plugin)
    alias(libs.plugins.com.android.library.plugin)
    alias(libs.plugins.org.jetbrains.compose.plugin)
    alias(libs.plugins.org.jetbrains.kotlin.serialization.plugin)
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.squareup.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        sourceSets {
            getByName("androidMain").kotlin.srcDirs("build/generated/moko/androidMain/src")
        }

    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(libs.dev.icerock.moko.mvvm.core.get())
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export(libs.dev.icerock.moko.resources)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.animation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.bundles.sqldelight)
//                implementation(libs.org.jetbrains.kotlinx.datetime)
                implementation(libs.bundles.ktor)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.media.kamel.image)
                implementation(libs.bundles.datastore)
                implementation(libs.io.insert.koin.core)
                api(libs.bundles.moko.mvvm)
                api(libs.bundles.moko.resources)
                api(libs.bundles.moko.permissions)

                api(libs.moe.tlaster.precompose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.android.driver)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activity.compose)
                implementation(libs.io.ktor.client.android)
                implementation(libs.io.insert.koin.android)
                implementation(libs.com.google.android.gms.play.services.location)
            }
        }
    }
}

android {
    namespace = "com.chungha.team.survey"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}