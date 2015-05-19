package com.somnus.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.somnus.broadcastreceiver.broad.MyBroadCastReceiver;
import com.somnus.broadcastreceiver.broad.MyBroadCastReceiver.IActionInterface;

/**
 * 1: 通过代码注册
 * 
 * 2：通过配置文件注册
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends ActionBarActivity {

	private Button mBtnInterFaceTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registerBoradCast();
		initView();
	}

	private void initView() {
		mBtnInterFaceTest = (Button) findViewById(R.id.btn_03);
	}

	public void onAction(View v) {
		switch (v.getId()) {
		case R.id.btn_01:
			/** 是在清单文件中注册的 **/
			Intent intent = new Intent(MyBroadCastReceiver.ACTION_ID_1);
			intent.putExtra("key1", "这是我通过静态方法发送的文字。");
			sendBroadcast(intent);
			break;

		case R.id.btn_02:
			/** 动态的在本Activity中注册的 **/
			Intent intent1 = new Intent(MyBroadCastReceiver.ACTION_ID_2);
			intent1.putExtra("key2", "这是我通过动态态方法发送的文字。");
			sendBroadcast(intent1);
			break;
		case R.id.btn_03:
			/** 为了测试接口 通过广播 接口 来赋值 （当前的文件中注册）**/
			Intent intent3 = new Intent(MyBroadCastReceiver.ACTION_ID_3);
			intent3.putExtra("key3", "这是我在测试BroadCastReceiver。");
			sendBroadcast(intent3);
			break;
		default:
			break;
		}

	}

	/** 注册广播 （动态注册） **/
	private void registerBoradCast() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(MyBroadCastReceiver.ACTION_ID_2);
		intentFilter.addAction(MyBroadCastReceiver.ACTION_ID_3);
		registerReceiver(mMyBroadCastReceiver, intentFilter);
	}

	public IActionInterface mInterface = new IActionInterface() {

		@Override
		public void getAction(String actionName) {
			// TODO Auto-generated method stub
			if (!TextUtils.isEmpty(actionName)) {
				mBtnInterFaceTest.setText(actionName);
			}
			Log.d("receiver", "测试" + actionName);
		}
	};

	private MyBroadCastReceiver mMyBroadCastReceiver = new MyBroadCastReceiver(
			mInterface) {
		public void onReceive(android.content.Context context,
				android.content.Intent intent) {
			super.onReceive(context, intent);
		};
	};

}
