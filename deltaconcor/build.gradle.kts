plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "uk.gov.justice.laadces"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	//implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("jakarta.xml.bind:jakarta.xml.bind-api")
	runtimeOnly("org.glassfish.jaxb:jaxb-runtime")
	implementation("com.opencsv:opencsv:5.9")

	/* Oracle is implementation rather than runtimeOnly because XMLType is needed at compile-time */
	implementation("com.oracle.database.jdbc:ojdbc11-production")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testRuntimeOnly("com.h2database:h2") /* allow tests to run without Oracle DB */
}

tasks.withType<Test> {
	useJUnitPlatform()
}
