# QuakeInfo

## App description
* An android app **(Kotlin)** fetches earthquake data from a network call to an API. 
* This app follows the **Android's Model-View-ViewModel, i.e., MVVM architecture** with the implementation of **data-binding**. 
* It has a **Range-Slider** depicting earthquake magnitude range (eathquakes with magnitudes in this selected range only will be fetched and displayed).
* Fetching the earthquake data is done in a background thread without blocking the UI thread with help of **Kotlin-Coroutines**. 
* The app uses **Retrofit-library** for calling and setting up the API service and **Moshi-converter** for decoding the fetched the **JSON data**. 
* The fetched data is displayed in a **Recycler view** where each item is **card view** conatining Magnitude, Location, Date and Time of the earthquake.
* Different color schemes for light and dark themes.

## Demo of app working

### Overview Screen
<br />

<p>
  <img src="https://user-images.githubusercontent.com/46900324/144237780-690c3ec0-8674-49f1-8a73-6cdec6dd02ed.gif" width="300" height="600" />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://user-images.githubusercontent.com/46900324/144237233-217bfade-d9c2-4471-8548-0b4e8d9604cd.gif" width="300" height="600" /> 
</p>

## Data Source
* USGS website - [https://earthquake.usgs.gov/](https://earthquake.usgs.gov/)
* USGS API used - [https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=1.0&maxmagnitude=10.0](https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=6&maxmagnitude=10.0)<br />
