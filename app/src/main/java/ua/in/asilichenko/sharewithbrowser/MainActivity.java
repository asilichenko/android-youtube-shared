/*
 * MIT License
 *
 * Copyright (c) 2023 Oleksii Sylichenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.in.asilichenko.sharewithbrowser;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;
import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

  private static final String YOUTU_BE = "youtu.be";
  private static final String YOUTUBE_COM = "youtube.com";

  private static final String VIDEO_ID = "videoId";
  private static final String YOUTUBE_REGEX = "https?://youtu(\\.be/|be\\.com/watch\\?v=)(?<videoId>[A-Za-z0-9_\\-]+)";
  private static final Pattern YOUTUBE_PATTERN = Pattern.compile(YOUTUBE_REGEX);

  private static final String EMBED = "https://www.youtube.com/embed/";

  private void log(String msg) {
    Log.d(getLocalClassName(), msg);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    log("onCreate");

    final String receivedText = getReceivedText();
    log("receivedText = " + receivedText);

    if (null != receivedText) processReceivedText(receivedText);
    else Toast.makeText(this, getString(R.string.info_msg), Toast.LENGTH_LONG).show();

    finish();
  }

  @Nullable
  private String getReceivedText() {
    final String retval;
    final Intent intent = getIntent();
    if (ACTION_SEND.equals(intent.getAction())) {
      retval = intent.getStringExtra(EXTRA_TEXT);
      intent.removeExtra(EXTRA_TEXT);
    } else {
      retval = null;
    }
    return isEmpty(retval) ? null : retval;
  }

  private void processReceivedText(@NonNull String receivedText) {
    if (!receivedText.contains(YOUTU_BE) && !receivedText.contains(YOUTUBE_COM)) return;

    final String videoId = getVideoId(receivedText);
    log("videoId = " + videoId);
    if (null == videoId) return;

    final Uri uri = Uri.parse(EMBED + videoId);
    log("uri = " + uri);

    final Intent intent = Intent.makeMainSelectorActivity(
            Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .setData(uri)
        .addCategory(Intent.CATEGORY_SELECTED_ALTERNATIVE);
    startActivity(intent);

    log("processReceivedText(): finished");
  }

  @Nullable
  String getVideoId(@NonNull String val) {
    final Matcher matcher = YOUTUBE_PATTERN.matcher(val);
    return matcher.find() ? matcher.group(VIDEO_ID) : null;
  }

  @Override
  protected void onStop() {
    super.onStop();
    log("onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    log("onDestroy()");
  }
}