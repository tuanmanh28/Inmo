plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
}
android {
    namespace = "com.tuanmanh.inmo.core.model"
}

dependencies {
    implementation(projects.core.designsystem)
}