plugins {
    id("java")
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.12.0")
    implementation("org.jobrunr:jobrunr:7.2.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}