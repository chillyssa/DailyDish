# DailyDish
### An android application for recipe inspiration based on what you already have at home!


### Developed as part of MSU Denver's CS-3013 Mobile App Development course Summer 2022 by: 
#### [Brea Chaney](https://github.com/brearenee)
#### [Karent Correa](https://github.com/karent2222)
#### [Alyssa Williams](https://github.com/chillyssa)

### Libraries and APIs Used
[Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
[Spoonacular Food API](https://spoonacular.com/food-api) - The only food API you'll ever need

### Additional Dependencies (not built in)
Add these to your app/build.gradle:
``` 
implementation 'androidx.core:core-ktx:1.8.0'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation` 'com.squareup.retrofit2:converter-gson:2.9.0' 
```
### Additional Plugins (not built in)
Add these to your app/build.gradle:
`id 'kotlin-android-extensions'`
### Additional Build Info
Add this to your app/build.gradle at the end of the `defaultConfig{ }` within `android { }` configuration (screen shot below):
```
Properties properties = new Properties()
properties.load(project.rootProject.file("local.properties").newDataInputStream())
buildConfigField "String", "API_KEY", "\"${properties.getProperty("API_KEY")}\""
```
This allows you to hide your API Key as a property in your `local.properties` file like so: `API_KEY = yourKeyHere`

![buildConfigShare](https://user-images.githubusercontent.com/38333607/182533429-4bdd563a-f075-4b28-be10-f7e6cf943d70.png)