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
    // Source: https://mvnrepository.com/artifact/io.github.cdimascio/java-dotenv
    implementation("io.github.cdimascio:java-dotenv:5.2.2")

    // Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j-embeddings
    implementation("dev.langchain4j:langchain4j-embeddings:1.15.0-beta25")
// Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j-embeddings-all-minilm-l6-v2
    implementation("dev.langchain4j:langchain4j-embeddings-all-minilm-l6-v2:1.15.0-beta25")
}

tasks.test {
}