dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.34")
    compileOnly("org.jetbrains:annotations:24.0.1")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.jetbrains:annotations:24.0.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}