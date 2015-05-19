package com.somnus.broadcastreceiver.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {

	public static final String ACTION_ID_1 = "com.somnus.broadcasereceiver.action1";
	public static final String ACTION_ID_2 = "com.somnus.broadcasereceiver.action2";
	public static final String ACTION_ID_3 = "com.somnus.broadcasereceiver.action3";

	private IActionInterface mInterface;

	public MyBroadCastReceiver() {

	}

	public MyBroadCastReceiver(IActionInterface mInterface) {
		this.mInterface = mInterface;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		String content = "";
		if (action.equals(ACTION_ID_1)) {
			content = intent.getStringExtra("key1");
		} else if (action.equals(ACTION_ID_2)) {
			content = intent.getStringExtra("key2");
		} else if (action.equals(ACTION_ID_3)) {
			content = intent.getStringExtra("key3");
			if (mInterface != null) {
				mInterface.getAction(action);
			}
		}
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

	public interface IActionInterface {
		public void getAction(String actionName);
	};
}
