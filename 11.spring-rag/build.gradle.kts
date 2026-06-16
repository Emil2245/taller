plugins {
    java
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.programacion.taller"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

extra["springAiVersion"] = "2.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.ai:spring-ai-starter-model-openai")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // READERS:
    // Source: https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-pdf-document-reader
    implementation("org.springframework.ai:spring-ai-pdf-document-reader")
    // Source: https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-tika-document-reader
    implementation("org.springframework.ai:spring-ai-tika-document-reader")

    //EIPs
    // Source: https://mvnrepository.com/artifact/org.apache.camel.springboot/camel-spring-boot-starter
    implementation("org.apache.camel.springboot:camel-spring-boot-starter:4.20.0")
    // Source: https://mvnrepository.com/artifact/org.apache.camel.springboot/camel-file-starter
    implementation("org.apache.camel.springboot:camel-file-starter:4.20.0")


    // Source: https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-starter-model-transformers
    implementation("org.springframework.ai:spring-ai-starter-model-transformers")
    // Source: https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-qdrant-store
    implementation("org.springframework.ai:spring-ai-qdrant-store:2.0.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
    }
}

