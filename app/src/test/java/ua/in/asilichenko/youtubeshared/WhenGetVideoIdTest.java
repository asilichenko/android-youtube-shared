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

package ua.in.asilichenko.youtubeshared;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class WhenGetVideoIdTest {

  private MainActivity mainActivity;

  @Before
  public void setUp() {
    mainActivity = mock(MainActivity.class);
    when(mainActivity.getVideoId(anyString(), anyBoolean())).thenCallRealMethod();
  }

  @Test
  public void youtubeComShouldBeParsed() {
    final String input = "https://youtube.com/watch?v=abc-DFJ";
    final String expected = "abc-DFJ";
    final String actual = mainActivity.getVideoId(input, false);
    assertEquals(expected, actual);
  }

  @Test
  public void extraParametersShouldNotBeCountedAsPartOfVideoId() {
    final String input = "https://youtube.com/watch?v=abc-DFJ&si=a_bCDEfj1-HIjK-m";
    final String expected = "abc-DFJ";
    final String actual = mainActivity.getVideoId(input, false);
    assertEquals(expected, actual);
  }

  @Test
  public void youtuBeShouldBeParsed() {
    final String input = "https://youtu.be/abc-DFJ";
    final String expected = "abc-DFJ";
    final String actual = mainActivity.getVideoId(input, false);
    assertEquals(expected, actual);
  }

  @Test
  public void shortsShouldBeParsed() {
    final String input = "https://youtube.com/shorts/abc-DFJ";
    final String expected = "abc-DFJ";
    final String actual = mainActivity.getVideoId(input, true);
    assertEquals(expected, actual);
  }
}