/*
import com.google.protobuf.gradle.*
*/
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5
import org.springframework.cloud.contract.verifier.config.TestMode.EXPLICIT

group = "ferhat"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

val protobufVersion = "3.20.1"
val grpcVersion = "1.45.1"
val kotlinVersion = "1.6.21"

plugins {
    val kotlinVersion = "1.6.21"
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.cloud.contract") version "3.1.2"
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
    id("com.google.protobuf") version "0.8.18"
    kotlin("plugin.spring") version kotlinVersion
    kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
/*
    implementation("org.apache.kafka:kafka-streams")
*/
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-streams")
    implementation("org.springframework.kafka:spring-kafka")

    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:1.2.1")
    implementation("com.google.protobuf:protobuf-java:$protobufVersion")
    compileOnly("io.grpc:grpc-all:${grpcVersion}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:${kotlinVersion}")

    implementation("com.ninja-squad:springmockk:3.1.1")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

contracts {
    testFramework.set(JUNIT5)
    testMode.set(EXPLICIT)
    baseClassForTests.set("billing.service.BillingBase")
    contractsDslDir.set(File("${project.rootDir}/src/contractTest/contracts"))
}