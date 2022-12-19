import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "me.le.tuan.anh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.ruimo:csvparser_3:1.3")
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.apache.commons:commons-csv:1.5")
    implementation ("dev.inmo:tgbotapi:4.2.1")
    implementation("org.postgresql:postgresql:42.3.1")
    implementation("com.microsoft.sqlserver:mssql-jdbc:11.2.1.jre18")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}