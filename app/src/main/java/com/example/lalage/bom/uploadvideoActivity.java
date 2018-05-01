package com.example.lalage.bom;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class uploadvideoActivity extends AppCompatActivity {
  //  private Uri uri=Uri.parse("android.resource://com.example.lalage.bom/" +R.raw.testvideo);;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadvideo);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");
        BmobFile bmobFile = new BmobFile(new File("/sdcard/testvideo.mp4"));

        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(uploadvideoActivity.this,"上传成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(uploadvideoActivity.this,"上传失败",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //取得uri
    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();
        }

    }
    */
    public static void inputstreamtofile(InputStream ins,File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getFromAssets(Context context, String fileName) {
        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            StringBuilder result = new StringBuilder();
            String line;
            while((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            String var6 = result.toString();
            return var6;
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            if(null != inputReader) {
                try {
                    inputReader.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }
        }
        return null;
    }

}
