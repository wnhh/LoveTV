package com.yztc.lovetv.activity.mine;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.yztc.lovetv.R;
import com.yztc.lovetv.adapter.ChatMessageAdapter;
import com.yztc.lovetv.bean.ChatMessage;
import com.yztc.lovetv.myutil.TalkHttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TalkActivity extends AppCompatActivity {
    private ListView mChatView;//展示消息的ListView
    private EditText mMsg;//输的消息
    private ImageView mAddIv;//添加的图标
    private ImageView mSend;//发送的按钮
    private List<ChatMessage> mDatas = new ArrayList<>();//存储聊天消息
    private ChatMessageAdapter mAdapter;
    private LinearLayout mLinearLayout;//back返回

//    private Toolbar toolbar;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ChatMessage from = (ChatMessage) msg.obj;
            mDatas.add(from);
            mAdapter.notifyDataSetChanged();
            mChatView.setSelection(mDatas.size()-1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        initView();
//        initToolBar();
        initListView();
    }

    private void initView() {
        mSend = (ImageView) findViewById(R.id.chat_from_icon);
        mAddIv = (ImageView) findViewById(R.id.id_chat_add);
        mLinearLayout = (LinearLayout) findViewById(R.id.talk_ll_back);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void initListView() {
        mChatView = (ListView) findViewById(R.id.id_chat_listView);
        mMsg = (EditText) findViewById(R.id.id_chat_msg);
        mDatas.add(new ChatMessage(ChatMessage.Type.INPUT,"你好，很高兴为您服务，请问有什么可以帮您的?"));
        mAdapter = new ChatMessageAdapter(this,mDatas);
        mChatView.setAdapter(mAdapter);

    }

//    private void initToolBar() {
//        toolbar = (Toolbar) findViewById(R.id.talk_toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
    public void sendMessage(View view)
    {
        final String msg = mMsg.getText().toString();
        if (TextUtils.isEmpty(msg))
        {
            Toast.makeText(this, "您还没有填写信息呢...", Toast.LENGTH_SHORT).show();

            return;
        }

        ChatMessage to = new ChatMessage(ChatMessage.Type.OUTPUT, msg);
        to.setDate(new Date());
        mDatas.add(to);

        mAdapter.notifyDataSetChanged();
        mChatView.setSelection(mDatas.size() - 1);

        mMsg.setText("");

        // 关闭软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive())
        {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }

        new Thread()
        {
            public void run()
            {
                ChatMessage from = null;
                try
                {
                    from = TalkHttpUtils.sendMsg(msg);
                } catch (Exception e)
                {
                    from = new ChatMessage(ChatMessage.Type.INPUT, "服务器挂了呢...");
                }
                Message message = Message.obtain();
                message.obj = from;
                mHandler.sendMessage(message);
            };
        }.start();

    }
}
