# An app that allows you to open YouTube shared links in the browser.

![app icon](https://github.com/asilichenko/android-share-with-browser/assets/1503214/b14bccd7-ea1c-4de0-9377-2098cbe591c3)

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
