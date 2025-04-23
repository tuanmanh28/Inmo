plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
    id("kotlinx-serialization")
}
android {
    namespace = "com.tuanmanh.inmo.core.common"
}

dependencies {
    api(libs.kotlinx.serialization.json)
}