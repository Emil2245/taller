plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Source: https://mvnrepository.com/artifact/com.knuddels/jtokkit
    implementation("com.knuddels:jtokkit:1.1.0")
    // Source: https://mvnrepository.com/artifact/ai.djl/api
    implementation("ai.djl:api:0.36.0")
    // Source: https://mvnrepository.com/artifact/ai.djl.pytorch/pytorch-engine
    implementation("ai.djl.pytorch:pytorch-engine:0.36.0")

    // Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j
    implementation("dev.langchain4j:langchain4j:1.13.0")

// Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j-embeddings-all-minilm-l6-v2-q
    implementation("dev.langchain4j:langchain4j-embeddings-all-minilm-l6-v2-q:1.13.0-beta23")
}

tasks.test {
    useJUnitPlatform()
}