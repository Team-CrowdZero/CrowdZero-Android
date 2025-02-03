plugins {
    id("java-library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    // KotlinDependencies
    implementation(libs.kotlin)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.collections)

    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.javax.inject)
    implementation(libs.hilt.core)
}

kotlin {
    jvmToolchain(17) // Kotlin에서도 JVM 17을 사용하도록 설정
}
