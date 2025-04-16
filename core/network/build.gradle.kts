plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
    id("kotlinx-serialization")
}
android {
    namespace = "com.tuanmanh.inmo.core.network"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}