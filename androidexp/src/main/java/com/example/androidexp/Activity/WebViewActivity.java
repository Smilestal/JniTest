package com.example.androidexp.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidexp.R;
import com.example.androidexp.Views.WebViewWithProgress;

public class WebViewActivity extends Activity {

    private WebViewWithProgress mWebViewWithProgress;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    @SuppressLint("JavascriptInterface")
    private void initView() {
        mWebViewWithProgress = (WebViewWithProgress) findViewById(R.id.details_web);
        mWebView = mWebViewWithProgress.getWebView();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setLightTouchEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setHapticFeedbackEnabled(false);
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        mWebView.loadUrl("https://www.baidu.com");

        //设置webview的浏览器标识（User-Agent）
        String ua = mWebView.getSettings().getUserAgentString();
        mWebView.getSettings().setUserAgentString(ua.replace("Android", "POST_USER Android"));

        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }
                Log.d("onClick", "shouldOverrideUrlLoading: " + url);
//				view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //调用JS函数
//                view.loadUrl("javascript:(function(){var desc = document.querySelector('meta[name=\\\"description\\\"]');" +
//                        "window.local_obj.showSource(window.msg_title, window.msg_cdn_url, window.msg_desc, document.title, desc);})();");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });
    }

    final class InJavaScriptLocalObj {

        /**
         * 分享传值
         * @param title
         * @param image
         * @param content
         * @param defaultTitle
         * @param desc
         */
        @JavascriptInterface
        public void showSource(String title, String image, String content, String defaultTitle, String desc) {
            //被JS语句调用的方法
        }

        /**
         * H5跳转界面
         * @param host
         * @param type
         * @param id
         */
        //window.local_obj.toAppPage(host, type, id);
        @JavascriptInterface
        public void toAppPage(String host, String type, String id) {
            //被JS语句调用的方法
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //为了使WebView退出时音频或视频关闭
        mWebView.setVisibility(View.GONE);
      /*  if (null != mWebView) {
            mWebView.destroy();
        }*/
    }
}
