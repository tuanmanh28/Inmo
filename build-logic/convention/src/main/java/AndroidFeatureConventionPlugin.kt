import com.tuanmanh.inmo.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "project.android.library")
            apply(plugin = "project.hilt")
            apply(plugin = "org.jetbrains.kotlin.android")

            dependencies {
                implementation(project(":core:designsystem"))

                implementation(libs.findLibrary("androidx.core.ktx").get())
                implementation(libs.findLibrary("androidx.hilt.navigation.compose").get())
                implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                implementation(libs.findLibrary("coil.kt").get())
                implementation(libs.findLibrary("coil.kt.compose").get())

            }
        }
    }
}