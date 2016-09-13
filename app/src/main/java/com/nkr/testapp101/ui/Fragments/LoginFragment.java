package com.nkr.testapp101.ui.Fragments;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nkr.testapp101.R;
import com.nkr.testapp101.ui.UserSharedPref;
import com.nkr.testapp101.ui.db.UserDataSource;
import com.nkr.testapp101.ui.model.User;
import com.nkr.testapp101.ui.ui.MainActivity;

/**
 * Created by neel on 07/09/2016.
 */
public class LoginFragment extends Fragment {

    private EditText mLoginEmail;
    private EditText mLoginPassword;
    private Button mLoginBtn;

    private UserSharedPref sharedPref;

    public static final String USER_INFO="USER_INFO";





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);

        mLoginEmail=(EditText)view.findViewById(R.id.loginEmail);
        mLoginPassword=(EditText)view.findViewById(R.id.loginPw);
        mLoginBtn=(Button)view.findViewById(R.id.loginBtn);

        sharedPref=new UserSharedPref(this.getContext());
        if(sharedPref.loggedIn()){
            startActivity(new Intent(this.getContext(),MainActivity.class));
        }


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkLogin(view);

            }
        });


        return view;
    }

    private void checkLogin(View view) {

        UserDataSource dataSource=new UserDataSource(view.getContext());



        String email=mLoginEmail.getText().toString().trim();
        String pw=mLoginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Snackbar.make(view, "Email can not be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else if(TextUtils.isEmpty(pw)){
            Snackbar.make(view, "Password can not be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pw) ){

            Snackbar.make(view, "Please complete the form", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else if(!dataSource.isEmailExists(email)){
            Snackbar.make(view, "Email is not valid", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
        else{

            String matchedPassword=dataSource.searchedPassword(email);
            User user=dataSource.readUser(pw);
            String mobile=user.getPhone();
            String fName=user.getfName();
            String lName=user.getlName();
            String userName=fName+" "+lName;
            String userType=user.getUserType();

            if(matchedPassword.equals(pw)){
                sharedPref.setLoggedIn(true);


                Intent intent=new Intent(this.getContext(),MainActivity.class);


                intent.putExtra("UserName",userName);
                intent.putExtra("MobileNum",mobile);
                intent.putExtra("UserType",userType);

                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }


        }
    }

}
