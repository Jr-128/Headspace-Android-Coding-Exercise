# exercise <br>
Headspace Android Coding Exercise<br><br>
<b>Android Developer Code Challenge</b><br>

Goal is to create an Android app that displays a list of photos from the picsum API (https://picsum.photos/)<br>

The endpoint you need to call is https://picsum.photos/v2/list<br>

It <b>should</b> meet the following requirements:<br>
<ul>
  <li>It should display the image, author, and dimensions of the photos</li>
  <li>These can be laid out in a vertical orientation or a grid</li>
  <li>It should handle the following states :</li>

<ul>
  <li>Empty State (no data)</li>
  <li>Error State (api call failed)</li>
  <li>Loading State (api call is taking place) Content State (there is data to display)</li>
</ul>
  <li>It should be functional while offline</li>
  <li>When loading a page of photos it should check to see if that page exists in the database, if it does then display those products otherwise make a call to the endpoint</li>
</ul>
<br>
<b>Skeleton App</b><br><br>
<ul>
  <li>Feel free to use the app skeleton provided here, to use your own or to start from scratch.</li>
  <li>The skeleton provided is setup with:</li>
  <ul>
    <li>Common libraries: RxJava, Room, OkHttp, Retrofit, Gson, Glide, RecyclerView, ConstraintLayout</li>
    <li>It uses the MVVM pattern</li>
    <li>It provides some defaults providers for Network and Local DB calls</li>
    <li>Skeleton classes for a feature: FeatureActivity, FeatureViewModel and FeatureTableDao</li>
  </ul>
</ul>
<br><b>Submission Instructions</b><br><br>
<ul>
  <li>Create a repo on your personal GitHub and send us the link.</li>
  <li>Avoid putting everything in a single commit.</li>
  <li>Track the time you took to complete the project.</li>
  <li>Add comments where appropriate</li>
</ul>
