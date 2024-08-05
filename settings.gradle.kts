rootProject.name = "demo"
include("spring:spring-data")
include("hibernate")
findProject(":spring:spring-data")?.name = "spring-data"
