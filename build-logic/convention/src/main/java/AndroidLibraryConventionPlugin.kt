import com.android.build.gradle.LibraryExtension
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
                defaultConfig.targetSdk = 35


                // The resource prefix is derived from the module name,
                // so resources inside ":core:module" must be prefixed with "core_module_"
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            dependencies {
                "implementation"(libs.findLibrary("android.tracing.ktx").get())
            }
        }
    }
}