package com.example.chating.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chating.Bean.User;
import com.example.chating.MainActivity;
import com.example.chating.R;

import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Changepassword extends AppCompatActivity implements View.OnClickListener {

    private ImageView password_back;
    private Button change;
    private EditText newpassword,surepassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        //初始化控件
        initView();

    }

    private void initView() {
        password_back= findViewById(R.id.password_back);
        change = findViewById(R.id.change);
        newpassword = findViewById(R.id.newpassword);
        surepassword = findViewById(R.id.surepassword);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.password_back:
                finish();
                break;
            case R.id.change:
                if (surepassword.getText().toString().equals(newpassword.getText().toString())){
                    BmobUser user_now = BmobUser.getCurrentUser(User.class);
                    String id = user_now.getObjectId();
                    BmobUser user = new BmobUser();
                    user.setPassword(newpassword.getText().toString());
                    user.update(id, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Toast.makeText(Changepassword.this,"修改成功",Toast.LENGTH_SHORT).show();
                                startActivity( new Intent( Changepassword.this, Login.class ) );
                            }else {
                                Toast.makeText(Changepassword.this,"修改失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                    newpassword.setText("");
                    surepassword.setText("");
                }

        }
    }
}
