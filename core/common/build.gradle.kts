plugins {
    alias(libs.plugins.inmo.jvm.library)
    alias(libs.plugins.inmo.hilt)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
}