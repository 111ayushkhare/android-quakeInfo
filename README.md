# QuakeInfo

This android App (JAVA) depicts simple implemantation of fetching HTTP Response from the Network asynchrounosly , i.e. in background thread, using 'AsyncTask' class and representing the response in a Recycler View.

This app fetches eathquake data by fething the following API - <br />
[https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-01-01&endtime=2021-01-02&minmagnitude=6](https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-01-01&endtime=2021-01-02&minmagnitude=6)<br />

Fetched data shows the Magnitude, Location, Date and Time of the earthquakes in a list (recycler view).  

## Screenshots of app

### Home Screen
![homeScreen](https://user-images.githubusercontent.com/46900324/116810059-2b33f400-ab5f-11eb-9d9c-41c62168b5ea.jpg)<br /><br />

### Loading Data
![loadingIndicator](https://user-images.githubusercontent.com/46900324/116810103-60404680-ab5f-11eb-9d9d-eaf25c1bd2db.jpg)<br /><br />

### Eartquake Fetched Data Screen
![quakeFetchedData](https://user-images.githubusercontent.com/46900324/116810123-7d751500-ab5f-11eb-9d9e-0b1a69eb286f.jpg)