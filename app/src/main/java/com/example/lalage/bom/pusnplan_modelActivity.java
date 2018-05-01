package com.example.lalage.bom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class pusnplan_modelActivity extends Activity {
    private SliderLayout sliderLayout;
    private PagerIndicator indicator;
    private WebView wb;
    private String url =null;
    private String image_id=null;
    private String username =null;
    private String objectId=null;
    private Button downloadpdf;
    private ArrayList<String> ima_url=new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushplan_model);
        Bmob.initialize(this,"a44b90433ea7c49b1ba61f3cb0a483da");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String title = bundle.getString("title");
        image_id = bundle.getString("objectId");
        username = bundle.getString("username");
        objectId = bundle.getString("objectId");

        TextView tv=(TextView) findViewById(R.id._Mtitle);
        tv.setText(title);

         url = bundle.getString("url");
        this.initWebView();
        this.imageinit();

    }
    /*****点击webview内链接不跳转至自带浏览器***start***/
    private  void initWebView(){
        wb=(WebView)findViewById(R.id.A_webview);
        WebSettings ws = wb.getSettings();
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        ws.setSavePassword(true);
        ws.setSaveFormData(true);
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setSupportMultipleWindows(true);
        ws.setLoadsImagesAutomatically(true);
        ws.setMediaPlaybackRequiresUserGesture(true);
        ws.setSupportZoom(true);
        wb.setWebChromeClient(new WebChromeClient());



        wb.setWebViewClient(new wbbb());
        wb.loadUrl(url);
//下载pdf
    downloadpdf=(Button)findViewById(R.id.bu_down_pdf);
    downloadpdf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle1 = new Bundle();
            bundle1.putString("objectId",objectId);
            Intent intent3 = new Intent();
            intent3.setClass(pusnplan_modelActivity.this,Pdf_Activity.class);
            intent3.putExtras(bundle1);
            startActivity(intent3);

        }
    });
    }
    class wbbb extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            //如果不需要其他对点击链接事件的处理返回true，否则返回false
            return true;
        }
    }
    /*****点击webview内链接不跳转至自带浏览器***end***/

    /************图片轮转********start********/

    private void imageinit(){
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        indicator = (PagerIndicator) findViewById(R.id.ind_pager);
        BmobQuery<pushplan_image> image = new BmobQuery<pushplan_image>();
        image.addWhereEqualTo("useiname",objectId);
        image.setLimit(5);
        image.findObjects(new FindListener<pushplan_image>() {
            @Override
            public void done(List<pushplan_image> list, BmobException e) {
                if(e==null){
                    Toast.makeText(pusnplan_modelActivity.this,"有"+list.size()+"条数据",Toast.LENGTH_LONG).show();
                    for (pushplan_image im : list) {
                        ima_url.add(im.getUrl().toString());
                    }
                    for(String url11:ima_url){
                        TextSliderView customSliderView =new TextSliderView(pusnplan_modelActivity.this);
                        customSliderView.image(url11).setScaleType(BaseSliderView.ScaleType.Fit);
                        sliderLayout.addSlider(customSliderView);
                    }
                    sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
                    sliderLayout.setCustomAnimation(new DescriptionAnimation());
                    sliderLayout.setDuration(2000);
                    sliderLayout.setCustomIndicator(indicator);


                }else {
                    Toast.makeText(pusnplan_modelActivity.this,"没有数据",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /************图片轮转********end********/

}
