package com.tuanmanh.inmo

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val VersionCatalog.androidCompileSdk: Int
    get() = findVersion("androidCompileSdk").get().requiredVersion.toInt()

val VersionCatalog.androidMinSdk: Int
    get() = findVersion("androidMinSdk").get().requiredVersion.toInt()

val VersionCatalog.androidTargetSdk: Int
    get() = findVersion("androidTargetSdk").get().requiredVersion.toInt()