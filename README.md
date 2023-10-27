# DrawRouteMapCompose

<img style="width=5px;height=5px" src="https://github.com/saeedrznr/DrawRouteMapCompose/blob/master/app/src/main/res/drawable/example_img.jpg">

# Usage
Make sure your app have allready enable Google Map API and Google Direction API. Then you can use this library and follow this task to integrate DrawRouteMaps into your project.

Add support jitpact repository in root build.gradle at the end of repositories:

<!--START_SECTION:Code-->
```text
allprojects {
   repositories {
	maven { url "https://jitpack.io" }
   }
}
```
<!--END_SECTION:Code-->

Add dependencies :

<!--START_SECTION:Code-->
```text
dependencies {
    implementation("com.google.maps.android:maps-compose:2.15.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.github.saeedrznr:DrawRouteMapCompose:0.0.2")
}
```
<!--END_SECTION:Code-->

# In Your Composable

<!--START_SECTION:Code-->
```text
   GoogleMap(modifier=Modifier.fillMaxSize()) {
                       DrawRoutes(
                           key = "API_KEY",
                           origin = LatLng(23.173093, -102.868270),
                           destination = LatLng(60.005543, -111.884948),
                           originMarker = true,
                           destinationMarker = true
                       )
                   }
```
<!--END_SECTION:Code-->

# Costumize
 You can costumize appearance of this Composable like routes color , routes width and so on .



