import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.google.protobuf") version "0.8.18"

}

group = "ferhat"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

val protobufVersion = "3.20.1"
val grpcVersion = "1.45.1"

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.apache.kafka:kafka-streams")
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
	compileOnly( "io.grpc:grpc-all:${grpcVersion}")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
	testImplementation("org.springframework.kafka:spring-kafka-test")
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


//TODO fix protoc gen
protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:${protobufVersion}"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
		}
		id("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.2.1:jdk7@jar"
		}
	}

	generateProtoTasks {
		ofSourceSet("main").forEach {
			it.generateDescriptorSet = true
			it.descriptorSetOptions.includeImports = true
			it.plugins {
				id("grpc")
				id("grpckt")
			}
		}
	}
}

