plugins {
    id("java")
}


group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

val lanchain4jVersion = "1.14.0"

dependencies {
    // Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j-open-ai
    implementation("dev.langchain4j:langchain4j:${lanchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-open-ai:${lanchain4jVersion}")

}

tasks.test {
}