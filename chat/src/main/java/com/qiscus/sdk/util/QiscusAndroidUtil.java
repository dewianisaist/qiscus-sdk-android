/*
 * Copyright (c) 2016 Qiscus.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qiscus.sdk.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.qiscus.sdk.Qiscus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created on : May 31, 2016
 * Author     : zetbaitsu
 * Name       : Zetra
 * Email      : zetra@mail.ugm.ac.id
 * GitHub     : https://github.com/zetbaitsu
 * LinkedIn   : https://id.linkedin.com/in/zetbaitsu
 */
public final class QiscusAndroidUtil {

    private static final Random random = new Random();

    private QiscusAndroidUtil() {
    }

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay == 0) {
            Qiscus.getAppsHandler().post(runnable);
        } else {
            Qiscus.getAppsHandler().postDelayed(runnable, delay);
        }
    }

    public static void cancelRunOnUIThread(Runnable runnable) {
        Qiscus.getAppsHandler().removeCallbacks(runnable);
    }

    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    public static boolean isUrl(String s) {
        return Patterns.WEB_URL.matcher(s).matches();
    }

    public static List<String> extractUrl(String text) {
        String[] strings = text.split(" ");
        List<String> urls = new ArrayList<>();
        for (String s : strings) {
            if (isUrl(s)) {
                if (!s.startsWith("http")) {
                    s = "http://" + s;
                }
                urls.add(s);
            }
        }
        return urls;
    }

    public static List<String> extractPlainUrl(String text) {
        String[] strings = text.split(" ");
        List<String> urls = new ArrayList<>();
        for (String s : strings) {
            if (isUrl(s)) {
                urls.add(s);
            }
        }
        return urls;
    }

    public static int getRandomColor() {
        return Color.argb(100, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static void showKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view.requestFocus()) {
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
