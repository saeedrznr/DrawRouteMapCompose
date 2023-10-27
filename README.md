# DrawRouteMapCompose

<img src="https://github.com/saeedrznr/DrawRouteMapCompose/blob/master/app/src/main/res/drawable/example_img.jpg">

# Usage
Make sure your app have allready enable Google Map API and Google Direction API. Then you can use this library and follow this task to integrate DrawRouteMaps into your project.

Add support jitpact repository in root build.gradle at the end of repositories:

allprojects {
   repositories {
	maven { url "https://jitpack.io" }
   }
}
Add dependencies :

dependencies {
    implementation 'com.github.saeedrznr:DrawRouteMapCompose:latest-version'
}
# In Your Composable

   GoogleMap(modifier=Modifier.fillMaxSize()) {
                       DrawRoutes(
                           key = "API_KEY",
                           origin = LatLng(23.173093, -102.868270),
                           destination = LatLng(60.005543, -111.884948),
                           originMarker = true,
                           destinationMarker = true
                       )
                   }
