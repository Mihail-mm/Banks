rootProject.name = "lab-1"
include("Presentation")
include("Infrastructure")
include("Application")
include("Application:Models")
findProject(":Application:Models")?.name = "Models"
