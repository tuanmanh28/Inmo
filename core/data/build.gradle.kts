plugins {
    alias(libs.plugins.project.android.library)
    alias(libs.plugins.project.hilt)
    id("kotlinx-serialization")
}
android {
    namespace = "com.tuanmanh.inmo.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)
    api(projects.core.database)
    api(projects.core.datastore)
    api(projects.core.network)
}