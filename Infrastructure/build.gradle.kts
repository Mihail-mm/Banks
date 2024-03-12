plugins {
    id("java")
}

group = "M3208.MihailMM"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":Application"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.springframework:spring-jdbc:6.1.3")
}

tasks.test {
    useJUnitPlatform()
}