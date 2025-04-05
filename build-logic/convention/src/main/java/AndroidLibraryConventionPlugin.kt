import com.android.build.gradle.LibraryExtension
import com.tuanmanh.inmo.androidTargetSdk
import com.tuanmanh.inmo.configureKotlinAndroid
import com.tuanmanh.inmo.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.androidTargetSdk
            }

            dependencies {}
        }
    }
}