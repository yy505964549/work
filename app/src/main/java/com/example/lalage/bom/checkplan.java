package com.example.lalage.bom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class checkplan extends AppCompatActivity {
    private List<travelplan> planlist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chekplan);
        Bmob.initialize(this, "a44b90433ea7c49b1ba61f3cb0a483da");

        planlist = new ArrayList<travelplan>();

        User currentUserr = BmobUser.getCurrentUser(User.class);
        String usname = currentUserr.getUsername().toString();
        BmobQuery<travelplan> query = new BmobQuery<travelplan>();
        query.addWhereEqualTo("username",usname);
        query.setLimit(10);
        query.findObjects(new FindListener<travelplan>() {
            @Override
            public void done(List<travelplan> list, BmobException e) {
                if(e==null){
                    Toast.makeText(checkplan.this,"查询成功：共"+list.size()+"条计划",Toast.LENGTH_LONG).show();;
                    for(travelplan plan :list){
                        /*
                        plan.getO();
                        plan.getD();
                        plan.getTime();
                        plan.getCreatedAt();
                        */
                        planlist.add(plan);

                    }
                    LinearLayout l = (LinearLayout)findViewById(R.id.ll);
                    for(travelplan plan:planlist){
                        TextView tv = new TextView(checkplan.this);
                        tv.setText(plan.getUsername().toString()+plan.getO().toString()+plan.getD().toString()+plan.getTime().toString()+plan.getCreatedAt().toString());
                        tv.setTextSize(18);
                        l.addView(tv);
                    }

                }
            }
        });
    }
}
