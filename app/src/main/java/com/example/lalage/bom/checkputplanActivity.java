package com.example.lalage.bom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class checkputplanActivity  extends AppCompatActivity{
    private List<_Article> planlist;
    private ListView lv = null;
    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkputplan);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");
        //获取当前用户
        User user = BmobUser.getCurrentUser(User.class);
        String username = user.getUsername().toString();

        lv = (ListView)findViewById(R.id.Lv);
        BmobQuery<_Article> query = new BmobQuery<_Article>();
        planlist = new ArrayList<_Article>();
        query.addWhereEqualTo("username",username);
        query.setLimit(10);

        query.findObjects(new FindListener<_Article>() {
            @Override
            public void done(List<_Article> list, BmobException e) {
                if(e==null){
                    Toast.makeText(checkputplanActivity.this,"查询成功：共"+list.size()+"条数据。",Toast.LENGTH_LONG).show();
                    for(_Article plan :list){

                        planlist.add(plan);
                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map1.put("username",plan.getUsername().toString());
                        map1.put("url",plan.getUrl().toString());
                        map1.put("title",plan.getTitle().toString());
                        map1.put("objectId",plan.getObjectId().toString());
                        data.add(map1);

                    }
                    lv.setAdapter(new SimpleAdapter(checkputplanActivity.this,data,R.layout.pushplan_item,new String[]{"title","username"},new int[] {R.id._title,R.id.I_name}));
                   // Toast.makeText(checkputplanActivity.this,""+planlist.size()+"条数据。",Toast.LENGTH_LONG).show();
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                            Bundle bundle = new Bundle();
                            bundle.putString("title",planlist.get(arg2).getTitle());
                            bundle.putString("username",planlist.get(arg2).getUsername());
                            bundle.putString("url",planlist.get(arg2).getUrl());
                            bundle.putString("objectId",planlist.get(arg2).getObjectId());
                            Intent intent = new Intent();
                            intent.setClass(checkputplanActivity.this,pusnplan_modelActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                }
            }
        });





     //   lv.setAdapter(new SimpleAdapter(checkputplanActivity.this,data,R.layout.pushplan_item,new String[]{"title","username"},new int[] {R.id._title,R.id.I_name}));
        /*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                Bundle bundle = new Bundle();
                bundle.putString("title",planlist.get(arg2).getTitle());
                bundle.putString("username",planlist.get(arg2).getUsername());
                bundle.putString("url",planlist.get(arg2).getUrl());
                Intent intent = new Intent();
                intent.setClass(checkputplanActivity.this,pusnplan_modelActivity.class);
                startActivity(intent);
            }
        });

*/



    }
}
