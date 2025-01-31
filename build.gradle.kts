plugins {
    base
    id("io.github.gradle-nexus.publish-plugin")
}

version = "3.1.0-SNAPSHOT"

subprojects {
    //Real subproject DSL is located at `buildSrc/src/main/kotlin/dev.3-3.jmccc.gradle.kts`
    apply(plugin = "dev.3-3.jmccc")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(System.getenv("OSSRH_USERNAME"))
            password.set(System.getenv("OSSRH_PASSWORD"))
        }
    }
}