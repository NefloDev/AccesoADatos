plugins {
    id("java")
}

group = "org.ficheros"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    implementation("org.mockito:mockito-core:5.5.0")
    implementation("org.mockito:mockito-junit-jupiter:5.5.0")
    compileOnly("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}