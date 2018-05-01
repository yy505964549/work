package com.example.lalage.bom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

public class Pdf_Activity extends AppCompatActivity {
    private Button checkpdf;
    private File openfile;
    private String openfilename;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_pdf);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String plan_id = bundle.getString("objectId");
        BmobQuery<Pdf> bmobQuery = new BmobQuery<Pdf>();
        bmobQuery.addWhereEqualTo("plan_id", plan_id);
        bmobQuery.findObjects(new FindListener<Pdf>() {
            @Override
            public void done(List<Pdf> list, BmobException e) {
                if (e == null) {
                    for (Pdf pdf : list) {
                        final BmobFile bmobFile = pdf.getFile();
                        /*
                        final File file =new File("/storage/emulated/0/");
                        bmobFile.download(file, new DownloadFileListener() {
                            @Override
                            public void done(String s, BmobException e) {
                                if(e==null){

                                }
                            }

                            @Override
                            public void onProgress(Integer integer, long l) {

                            }
                        });
                        */


                        bmobFile.download(new DownloadFileListener() {
                            @Override
                            public void done(String s, BmobException e) {
                                if(e==null){
                                    String filename = bmobFile.getFilename().toString();
                                    openfilename = filename;
                                    Toast.makeText(Pdf_Activity.this, "下载成功", Toast.LENGTH_LONG).show();
                                    File fff = new File(getApplicationContext().getExternalCacheDir()+"/"+filename);
                                    openfile =fff;
                                    Toast.makeText(Pdf_Activity.this, "file路径"+fff.toString(), Toast.LENGTH_LONG).show();
                                    /*
                                   try {
                                       Intent intent1 = openPDF(fff.toString());
                                       startActivity(intent1);

                                   }catch (Exception g){
                                       Toast.makeText(Pdf_Activity.this, "没有找到打开它的第三方", Toast.LENGTH_LONG).show();
                                   }
                                   */

                                }
                            }

                            @Override
                            public void onProgress(Integer integer, long l) {
                               // Toast.makeText(Pdf_Activity.this,"下载进度"+integer+","+l,Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }
            }
        });
        //查看pdf
        checkpdf = (Button)findViewById(R.id.but_check);
        checkpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = openPDF();
                startActivity(intent2);

            }
        });


    }

    private Intent openPDF() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        File file= new File(getApplicationContext().getCacheDir()+"/bmob/"+openfilename);

        boolean b = Build.VERSION.SDK_INT >= 24;
        Uri uri = b
                ? FileProvider.getUriForFile(Pdf_Activity.this, "com.example.lalage.bom.fileprovider", file)
                : Uri.fromFile(file);

       // Uri uri = FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+"com.example.lalage.bom.fileprovider",new File(param));
       // Uri uri = Uri.fromFile(new File(param ));

        intent.setDataAndType(uri, "application/pdf");

        return intent;
    }

}
