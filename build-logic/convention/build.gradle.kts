import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.tuanmanh.inmo.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)

}
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            /**
             * refer to [AndroidApplicationComposeConventionPlugin]
             */
            id = libs.plugins.inmo.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            /**
             * refer to [AndroidApplicationConventionPlugin]
             */
            id = libs.plugins.inmo.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            /**
             * refer to [AndroidLibraryComposeConventionPlugin]
             */
            id = libs.plugins.inmo.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            /**
             * refer to [AndroidLibraryConventionPlugin]
             */
            id = libs.plugins.inmo.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            /**
             * refer to [AndroidFeatureConventionPlugin]
             */
            id = libs.plugins.inmo.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("hilt") {
            /**
             * refer to [HiltConventionPlugin]
             */
            id = libs.plugins.inmo.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("androidRoom") {
            /**
             * refer to [AndroidRoomConventionPlugin]
             */
            id = libs.plugins.inmo.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            /**
             * refer to [JvmLibraryConventionPlugin]
             */
            id = libs.plugins.inmo.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }

}