plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.android.room)
    alias(libs.plugins.project.hilt)
}
android {
    namespace = "com.tuanmanh.inmo.core.database"
}

dependencies {
    api(projects.core.model)
}