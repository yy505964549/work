package com.example.lalage.bom;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;

public class MyPushMessageReceiver extends BroadcastReceiver {
    public static final String TYPE = "type";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("bmob", "客户端收到推送内容："+intent.getStringExtra("msg"));
            String jsonStr = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            String content = null;
            try{
                JSONObject jsonObject = new JSONObject(jsonStr);
                content = jsonObject.getString("alert");
            }catch (JSONException e){
                e.printStackTrace();
            }
            NotificationManager manager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("您收到一条推送消息").setContentText(content).build();
            /*
            Intent intent1 =null;
            intent1 = new Intent(context,checkputplanActivity.class);
            intent1.setAction(Intent.ACTION_MAIN);
            intent1.addCategory(Intent.CATEGORY_LAUNCHER);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            PendingIntent contentIntent = PendingIntent.getActivities(context, 0, new Intent[]{intent1},PendingIntent.FLAG_UPDATE_CURRENT);
            */
            if (action.equals("notification_clicked")) {
                Intent intent1 = new Intent(context,checkputplanActivity.class);
                intent1.setAction("notification_clicked");
                intent1.putExtra(MyPushMessageReceiver.TYPE,Intent.normalizeMimeType("type"));
                PendingIntent pclick = PendingIntent.getBroadcast(context,0,intent1,PendingIntent.FLAG_ONE_SHOT);

            }


            manager.notify(1,notification);
            Log.d("bmob","客户端收到推送消息");

        }
    }

}