package com.winning.health.rims.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.winning.health.rims.greendaodemo.adapter.UserAdapter;
import com.winning.health.rims.greendaodemo.entity.User;
import com.winning.health.rims.greendaodemo.greendao.DaoManager;
import com.winning.health.rims.greendaodemo.greendao.UserDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_newName)
    EditText etNewName;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.lv_user)
    ListView lvUser;

    private List<User> mUserList = new ArrayList<>();
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mUserList = DaoManager.getInstant().getSession().getUserDao().queryBuilder().build().list();
        mAdapter = new UserAdapter(mUserList, MainActivity.this);
        lvUser.setAdapter(mAdapter);
    }

    @OnClick({R.id.btn_add, R.id.btn_delete, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                insertUser(null, etName.getText().toString().trim());
                break;
            case R.id.btn_delete:
                deleteUser(etName.getText().toString().trim());
                break;
            case R.id.btn_update:
                updataUser(etName.getText().toString().trim(), etNewName.getText().toString().trim());
                break;
        }
    }

    /**
     * 本地数据添加一个User
     *
     * @param id
     * @param name
     */
    private void insertUser(Long id, String name) {
        UserDao userDao = DaoManager.getInstant().getSession().getUserDao();
        User user = new User(id, name);
        userDao.insert(user);
        etName.setText("");
        mUserList.clear();
        mUserList.addAll(userDao.queryBuilder().build().list());
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 根据姓名删除一个User
     *
     * @param name
     */
    private void deleteUser(String name) {
        UserDao userDao = DaoManager.getInstant().getSession().getUserDao();
//        //删除
//        User user1 = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
//        if (user1 != null) {
//            userDao.delete(user1);
//            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
//
//        }

        //根据姓名批量删除
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
        for (User user : userList) {
            userDao.delete(user);
            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        }
        etName.setText("");
        mUserList.clear();
        mUserList.addAll(userDao.queryBuilder().build().list());
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 根据姓名更新User的名字
     *
     * @param name
     */
    private void updataUser(String name, String newName) {
        UserDao userDao = DaoManager.getInstant().getSession().getUserDao();
        //更新单个
//        User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
//        if (user != null) {
//            etName.setText(newName);
//            userDao.update(user);
//            Toast.makeText(MyApplication.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(MyApplication.getContext(), "用户不存在", Toast.LENGTH_SHORT).show();
//        }

        //批量更新
        List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
        if (userList.isEmpty()) {
            Toast.makeText(MyApplication.getContext(), "用户不存在", Toast.LENGTH_SHORT).show();
        } else {
            for (User user : userList) {
                user.setName(newName);
                userDao.update(user);
                Toast.makeText(MyApplication.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
            }
        }
        etName.setText("");
        etNewName.setText("");
        mUserList.clear();
        mUserList.addAll(userDao.queryBuilder().build().list());
        mAdapter.notifyDataSetChanged();
    }
}
