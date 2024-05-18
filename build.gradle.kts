plugins {
    id("java")
    `maven-publish`
}

group = "cn.taskeren"
version = "1.0-SNAPSHOT"

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8

    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.2.0-jre")
    implementation("org.slf4j:slf4j-api:2.0.13")

    testImplementation("org.slf4j:slf4j-simple:2.0.13")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("maven") {
            artifactId = "minecraft-forge-config-legacy"
            from(components["java"])
        }
    }
}