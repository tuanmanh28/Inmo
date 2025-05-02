plugins {
    alias(libs.plugins.project.android.feature)
    alias(libs.plugins.project.android.library.compose)
    alias(libs.plugins.project.hilt)
}
android {
    namespace = "com.tuanmanh.inmo.features.habittracker"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
}