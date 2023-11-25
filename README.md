# YouTube Shared
Android app that converts YouTube video link into ads-free browser video link. 

![app icon](https://github.com/asilichenko/android-share-with-browser/assets/1503214/b14bccd7-ea1c-4de0-9377-2098cbe591c3)

## Disclaimer

This application is created with no malicious intent. It is not intended to harm or interfere with any actions of Google or advertisers on the YouTube platform. The application solely automates specific actions that a regular user could manually perform. The functionality of the application adheres to all laws and does not alter any standard functionalities of the Android platform or other applications. It merely utilizes the capabilities provided by the Android platform and other applications.

---

To launch the browser with the desired YouTube link, you should use a specific intent:
```
final Intent intent = Intent.makeMainSelectorActivity(
    Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER
  ).setData(uri);
```

Log:
```
11:49:06.283 onCreate
11:49:06.283 receivedText = https://youtube.com/watch?v=qwerASDF123&si=a_bCDEfj1-HIjK-m
11:49:06.283 videoId = qwerASDF123
11:49:06.284 uri = https://www.youtube.com/embed/qwerASDF123
11:49:06.313 processReceivedText(): finished
11:49:07.134 onDestroy()
```

## Sources
* [AndroidManifest.xml](app/src/main/AndroidManifest.xml)
* [MainActivity.java](app/src/main/java/ua/in/asilichenko/youtubeshared/MainActivity.java)
* [WhenGetVideoIdTest.java](app/src/test/java/ua/in/asilichenko/youtubeshared/WhenGetVideoIdTest.java)


## Authors
[Oleksii Sylichenko](https://github.com/asilichenko)

## License
[MIT License](LICENSE)
