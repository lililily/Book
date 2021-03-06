package com.example.book.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.book.entity.User;
import com.example.book.presenter.IRegistPresenter;
import com.example.book.presenter.impl.RegistPresenter;
import com.example.book.view.IRegistView;
import com.example.book.R;

public class RegistActivity extends Activity implements IRegistView{
	@ViewInject(R.id.etLoginname)
	private EditText etLoginname;
	@ViewInject(R.id.etPwd)
	private EditText etPwd;
	@ViewInject(R.id.etRealname)
	private EditText etRealname;
	@ViewInject(R.id.etCode)
	private EditText etCode;
	@ViewInject(R.id.ivCode)
	private ImageView ivCode;
	private IRegistPresenter presenter;

	public RegistActivity() {
		presenter = new RegistPresenter(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		x.view().inject(this);

		presenter.loadImage();
	}

	/**
	 * 点击注册
	 * @param view
	 */
	public void regist(View view){
		User user = new User();
		user.setEmail(etLoginname.getText().toString());
		user.setPassword(etPwd.getText().toString());
		user.setNickname(etRealname.getText().toString());
		String code = etCode.getText().toString();
		presenter.regist(user, code);
	}

	public void back(View view) {
		finish();
	}

	@Override
	public void registSuccess() {
		Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	public void registError(String errorMessage) {
		Toast.makeText(this, "注册失败,"+errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showCodeImage(Bitmap bitmap) {
		if(bitmap != null){
			ivCode.setImageBitmap(bitmap);
		}
	}
}
