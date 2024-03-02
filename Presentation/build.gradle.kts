plugins {
    id("java")
}

group = "M3208.MihailMM"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/info.picocli/picocli
    implementation(project(":Application"))
    implementation("info.picocli:picocli:4.7.5")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}