package com.yztc.lovetv.activity.mine;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yztc.lovetv.R;

import java.io.File;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{
	private ImageView camera_iv;
	public static final int REQUEST_CAMERA_CODE = 100;
	public static final int REQUEST_GALLERY_CODE = 101;
	public static final int REQUEST_CROP_CODE = 102;
	private RelativeLayout touxiang_rl;
	private RelativeLayout emailaddress_rl;
	private RelativeLayout phone_rl;
	private View inflate;
	private TextView choosePhoto;
	private TextView takePhoto;
	private TextView canceltv;
	private Dialog dialog;
	//系统拍照图片地址
	public static String file_path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		file_path = Environment.getExternalStorageDirectory() + File.separator + "header.jpg";
		initView();
	}
	private void initView() {
		touxiang_rl = (RelativeLayout) findViewById(R.id.touxiang_rl);
		emailaddress_rl = (RelativeLayout) findViewById(R.id.emailaddress_rl);
		phone_rl = (RelativeLayout) findViewById(R.id.phone_rl);
		touxiang_rl.setOnClickListener( this);
		emailaddress_rl.setOnClickListener( this);
		phone_rl.setOnClickListener( this);
		camera_iv= (ImageView) findViewById(R.id.mine_touxiang_iv_icon);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode)
		{
			case REQUEST_CAMERA_CODE:
				File file = new File(file_path);
				if (file.exists()) {
					//根据文件获得文件的Uri
					Uri fileUri = Uri.fromFile(new File(file_path));
					gotoCrop(fileUri);
				}
				break;
			case REQUEST_GALLERY_CODE:
				if (data != null) {
					Uri uri = data.getData();
					gotoCrop(uri);
				}
				break;
			case REQUEST_CROP_CODE:
				if (data != null) {
					Bitmap bmp = data.getParcelableExtra("data");
					if (bmp != null) {
						Bitmap newBmp = getRoundCornerBitmap(bmp, 360);
						if (newBmp != bmp) {
							bmp.recycle();
						}
						camera_iv.setImageBitmap(newBmp);
					}
				}
				break;

		}
	}
	//实例化dialog
	public void show(){
		dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
		//填充对话框的布局
		inflate = LayoutInflater.from(this).inflate(R.layout.item_bottomdialog, null);
		//初始化控件
		choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto_tv);
		takePhoto = (TextView) inflate.findViewById(R.id.takePhoto_tv);
		canceltv = (TextView) inflate.findViewById(R.id.cancel_tv);
		choosePhoto.setOnClickListener(this);
		takePhoto.setOnClickListener(this);
		canceltv.setOnClickListener(this);
		//将布局设置给Dialog
		dialog.setContentView(inflate);
		//获取当前Activity所在的窗体
		Window dialogWindow = dialog.getWindow();
		//设置Dialog从窗体底部弹出
		dialogWindow.setGravity( Gravity.BOTTOM);
		//获得窗体的属性
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.y = 25;//设置Dialog距离底部的距离
//       将属性设置给窗体
		dialogWindow.setAttributes(lp);
		dialog.show();//显示对话框
	}
	@Override
	public void onClick(View view) {
		switch (view.getId())
		{
			case R.id.touxiang_rl:
				show();
				break;
			case R.id.emailaddress_rl:
				break;
			case R.id.phone_rl:
				break;
			case R.id.takePhoto_tv:
				Intent intent=new Intent();
				//隐式目的
				intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
				//根据文件获得文件的Uri
				Uri fileUri = Uri.fromFile(new File(file_path));
				//启动前给系统相机传递参数，告诉系统相机照片保存地址
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
				//带返回值的启动
				startActivityForResult(intent, REQUEST_CAMERA_CODE);
				dialog.dismiss();
				break;
			case R.id.choosePhoto_tv:
				Intent intent1=new Intent();
				//隐式目的 - 进入系统相册
				intent1.setAction(Intent.ACTION_GET_CONTENT);
				intent1.setType("image/*");
				//带返回值的启动
				startActivityForResult(intent1, REQUEST_GALLERY_CODE);
				dialog.dismiss();
				break;
			case R.id.cancel_tv:
				dialog.dismiss();
				break;
		}
	}
	//打开图片剪切
	private void gotoCrop(Uri inputRri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(inputRri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);  //设置剪切框的比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 150);  //图片输出大小
		intent.putExtra("outputY", 150);  //图片输出大小
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, REQUEST_CROP_CODE);
	}

	/**
	 * 画圆角图片
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
	private Bitmap getRoundCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
}
