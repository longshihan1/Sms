package com.longshihan.sms;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.longshihan.sms.bean.GuiZe;
import com.longshihan.sms.bean.GuiZeDao;
import com.longshihan.sms.bean.PhoneMessage;

import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private SMSBroadcastReceiver mSMSBroadcastReceiver;
    private Button mButton;
    private EditText mEditText;
    private ListView mListView;
    private String edit_str;
    private GuiZeDao mGuiZeDao;
    private List<GuiZe> lists;
    private GuiListAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn);
        mEditText = (EditText) findViewById(R.id.editText);
        mListView = (ListView) findViewById(R.id.listview);
        lists = new ArrayList<>();
        mGuiZeDao = new GuiZeDao(this);
        initView();
        initData();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_str = mEditText.getText().toString();
                GuiZe guiZe = new GuiZe();
                guiZe.setName(edit_str);
                mGuiZeDao.add_guize(guiZe);
                mEditText.setHint("短信规则");
                lists.add(guiZe);
                madapter.notifyDataSetChanged();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (lists!=null){
                    mGuiZeDao.detele(lists.get(i).getName());
                }
            }
        });


    }

    private void initData() {
        //授权
        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS)
                .request();

        //生成广播处理
        mSMSBroadcastReceiver = new SMSBroadcastReceiver();
        mSMSBroadcastReceiver.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {
            @Override
            public void onReceived(PhoneMessage phoneMessage) {
                lists = mGuiZeDao.queryall_guize();
                for (int k = 0; k < lists.size(); k++) {
                    if (phoneMessage.getMsgContent().contains(lists.get(k).getName())) {
                        String msg = phoneMessage.getPhoneNumber() + "*" + phoneMessage.getMsgTime() + "*" + phoneMessage.getMsgContent();
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mSMSBroadcastReceiver);
        mSMSBroadcastReceiver = null;
    }

    private void initView() {
        lists = mGuiZeDao.queryall_guize();
        if (lists != null) {
            if (madapter != null) {
                madapter.setList(lists);
            } else {
                madapter = new GuiListAdapter(MainActivity.this, lists);
                mListView.setAdapter(madapter);
            }
        }

    }
    @PermissionSuccess(requestCode = 100)
    public void doRegisterReceiver() {
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter(ACTION);
        intentFilter.setPriority(1000);
        //注册广播
        this.registerReceiver(mSMSBroadcastReceiver, intentFilter);
    }

    @PermissionFail(requestCode = 100)
    public void doFailRegisterReceiver() {
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
