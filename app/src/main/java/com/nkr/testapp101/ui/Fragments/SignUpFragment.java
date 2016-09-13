package com.nkr.testapp101.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nkr.testapp101.R;
import com.nkr.testapp101.ui.db.UserDataSource;
import com.nkr.testapp101.ui.model.User;

/**
 * Created by neel on 07/09/2016.
 */
public class SignUpFragment extends Fragment {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPhoneNum;
    private Spinner mUserType;

    private Button mSignUpBtn;


    String fName="";
    String lName="";
    String email="";
    String password="";
    String phone="";

    String userType="";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signup,container,false);

        mFirstName=(EditText)view.findViewById(R.id.firstNameEditText);
        mLastName=(EditText)view.findViewById(R.id.lastNameEditText);
        mEmail=(EditText)view.findViewById(R.id.signUpEmailEditText);
        mPassword=(EditText)view.findViewById(R.id.signUpPassEditText);
        mPhoneNum=(EditText)view.findViewById(R.id.phoneNumEditText);
        mUserType=(Spinner)view.findViewById(R.id.spinnerDropDown);

        mSignUpBtn=(Button)view.findViewById(R.id.signUpBtn);

        mUserType.setPrompt("Select User Type");


        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser(view);



            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    private void registerUser(View view) {

         fName=mFirstName.getText().toString().trim();
         lName=mLastName.getText().toString().trim();
         email=mEmail.getText().toString().trim();
         password=mPassword.getText().toString().trim();
         phone=mPhoneNum.getText().toString().trim();

         userType=mUserType.getSelectedItem().toString();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone)){

            User user=new User();

            user.setfName(fName);
            user.setlName(lName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setUserType(userType);

            UserDataSource dataSource=new UserDataSource(getActivity());
           dataSource.insertUserData(user);

            Snackbar.make(view, "Successfully Registered", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();


        }

        else{
            Snackbar.make(view, "Please complete the form", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


    }
}
