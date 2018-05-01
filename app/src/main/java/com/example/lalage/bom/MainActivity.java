package com.example.lalage.bom;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import cn.bmob.newim.listener.AuthListener;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class MainActivity extends AppCompatActivity {
    private EditText musersid,mpassword;
    private Button mlogin,mforget,mregister,qqlog;
    private String Usersid,passw;
 //   public static Tencent mTencent;
    private UserInfo mUserinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this,"a44b90433ea7c49b1ba61f3cb0a483da");
        User user = BmobUser.getCurrentUser(User.class);
        if(user!=null){
            Intent intent = new Intent(MainActivity.this, userindexActivity.class);
            startActivity(intent);
        }
        //添加图像数据
        /*
        pushplan_image ii = new pushplan_image();
        ii.setUseiname("13240707765");
        ii.setUrl("https://goss2.vcg.com/creative/vcg/800/version23/VCG41499767552.jpg");
        ii.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

            }
        });
*/

        //上传视频
   //     Uri videopath = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.testvideo);
   //     String urlpath = getRealFilePath(MainActivity.this,videopath);
      //  Cursor actualimagecursor = this.ctx.managedQuery(videopath,proj,null,null,null);

/*
        BmobFile bmobFile = new BmobFile(new File("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));

        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,"chenggong",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"shibai",Toast.LENGTH_LONG).show();

                }
            }

        });

*/
        //上传视频方法二









        mTencent = Tencent.createInstance(APP_ID,MainActivity.this.getApplicationContext());//方法二
        musersid = (EditText) findViewById(R.id.usersid);
        mpassword = (EditText) findViewById(R.id.password);
        mlogin = (Button) findViewById(R.id.login);
        mforget = (Button) findViewById(R.id.forget);
        mregister = (Button) findViewById(R.id.register);
        qqlog = (Button)findViewById(R.id.qq);

        Intent intent = getIntent();
        Usersid = intent.getStringExtra(Usersid);
        musersid.setText(Usersid);
        //推送

        BmobInstallation.getCurrentInstallation().save();
        BmobPush.startWork(this);
        //登陆
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Userid = musersid.getText().toString();
                final  String password = mpassword.getText().toString();
                if(Userid.isEmpty()||password.isEmpty()){
                    Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_LONG).show();
                }else {
                    BmobUser u = new BmobUser();
                    u.setUsername(Userid);
                    u.setPassword(password);
                    u.login(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser,BmobException e){
                            if(e==null){
                                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this,userindexActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG).show();;
                            }
                        }
                    });
                }
            }
        });
        //注册

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,registerActivity.class);
                startActivity(intent1);
            }
        });
        //忘记密码
        mforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,forgetActivity.class);
                startActivity(intent2);
            }
        });

        //第三方登陆
        /*
        qqlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqAuthorize();
            }
        });
*/


    }
/*
    public  void loginWhitAuth(final BmobUser.BmobThirdUserAuth authInfo){

        BmobUser.loginWithAuthData(authInfo, new LogInListener<JSONObject>() {
            @Override
            public void done(JSONObject userAuth, BmobException e) {
                if(e==null){
                   // Log.i("smile",authInfo.getSnsType(),userAuth);

                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG);

                    Intent intent = new Intent(MainActivity.this,userindexActivity.class);
                    intent.putExtra("json", userAuth.toString());
                    intent.putExtra("from", authInfo.getSnsType());
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG);
                }
            }
        });

    }

    private  void qqAuthorize(){
        String qqappid = "1106790723";
        if(mTencent==null){
            mTencent = Tencent.createInstance(qqappid,MainActivity.this.getApplicationContext());

        }
        mTencent.logout(this);
        mTencent.login(MainActivity.this, "all", new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if(o!=null){

                    JSONObject jsonObject = (JSONObject) o ;
                    try{
                        String token = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
                        String expires = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
                        String openId = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
                        BmobUser.BmobThirdUserAuth authInfo = new BmobUser.BmobThirdUserAuth(BmobUser.BmobThirdUserAuth.SNS_TYPE_QQ,token,expires,openId);
                        loginWhitAuth(authInfo);
                    }catch (JSONException e){

                    }
                }else {
                    Toast.makeText(MainActivity.this,"qqauthorize出错",Toast.LENGTH_LONG );
                }
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(MainActivity.this,"QQ授权出错",Toast.LENGTH_LONG );
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this,"取消QQ授权",Toast.LENGTH_LONG );
            }
        });
    }
*/
    //qq授权
public void buttonLogin(View v){
    /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
     官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
     第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
    mIUiListener = new BaseUiListener();
    //all表示获取所有权限
    mTencent.login(MainActivity.this,"all", mIUiListener);
}
    public  void loginWhitAuth(final BmobUser.BmobThirdUserAuth authInfo){

        BmobUser.loginWithAuthData(authInfo, new LogInListener<JSONObject>() {
            @Override
            public void done(JSONObject userAuth, BmobException e) {
                if(e==null){
                    // Log.i("smile",authInfo.getSnsType(),userAuth);

                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG);

                    Intent intent = new Intent(MainActivity.this,userindexActivity.class);
                    intent.putExtra("json", userAuth.toString());
                    intent.putExtra("from", authInfo.getSnsType());
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG);
                }
            }
        });

    }
    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1106790723";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                final String openID = obj.getString("openid");
                final String accessToken = obj.getString("access_token");
                final String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        BmobUser.BmobThirdUserAuth authInfo = new BmobUser.BmobThirdUserAuth(BmobUser.BmobThirdUserAuth.SNS_TYPE_QQ,accessToken,expires,openID);
                        loginWhitAuth(authInfo);
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }
    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}

