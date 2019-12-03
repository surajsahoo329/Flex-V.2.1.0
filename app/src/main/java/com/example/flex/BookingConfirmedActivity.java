package com.example.flex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class BookingConfirmedActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @RequiresApi(api=Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmed);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(BookingConfirmedActivity.this, android.R.color.background_light));// set status background white
        }


        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable()); // Add Color.Parse("#000") inside ColorDrawable() for color change
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        @SuppressLint("PrivateResource") final Drawable upArrow=getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(android.R.color.background_dark), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        Spannable text=new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(text);

        TextView tvCompany=findViewById(R.id.tvCompany);
        TextView tvTimings=findViewById(R.id.tvTimings);
        TextView tvAddress=findViewById(R.id.tvAddress);
        ImageView ivCompany=findViewById(R.id.ivImage);
        RelativeLayout relativeLayout=findViewById(R.id.relative_layout_slot_card);

        Intent it = getIntent();

        final int company=it.getIntExtra("company", 1);
        final String date = it.getStringExtra("date");
        final String time = it.getStringExtra("time");
        final String hours = it.getStringExtra("hours");

        assert date != null;
        char[] dateArr=date.toCharArray();
        char[] modDateArr=new char[date.length()];
        int count = 0;

        for(int i =0; i< dateArr.length; i++) {
            if(dateArr[i] == '-')
                count ++;
            if(count == 2)
                break;

            modDateArr[i] = dateArr[i];
        }

        final String modTimeStr = String.valueOf(modDateArr)+", "+time+" | "+hours;
        tvTimings.setText(modTimeStr);

        if (company == 1) {
            tvCompany.setText("Ekart Logistics");
            tvAddress.setText("Baramunda, Bhubaneswar");
            ivCompany.setImageResource(R.drawable.ic_flipkart);
        } else if (company == 2) {
            tvCompany.setText("Fedex");
            tvAddress.setText("Master Canteen, Bhubaneswar");
            ivCompany.setImageResource(R.drawable.ic_fedex);
        } else if(company == 3) {
            tvCompany.setText("Aramex");
            tvAddress.setText("Jayadev Vihar, Bhubaneswar");
            ivCompany.setImageResource(R.drawable.ic_aramex);
        } else if(company == 4) {
            tvCompany.setText("Delhivery");
            tvAddress.setText("Nayapalli, Bhubaneswar");
            Glide.with(BookingConfirmedActivity.this)
                    .load("https://nexusvp.com/wp-content/uploads/2014/04/oie_JbUn8ia6Q3Zq.png")
                    .into(ivCompany);
        } else if(company == 5) {
            tvCompany.setText("Blue Dart");
            tvAddress.setText("Kharabela Nagar, Bhubaneswar");
            ivCompany.setImageResource(R.drawable.ic_bluedart);
        } else if(company == 6) {
            tvCompany.setText("DTDC");
            tvAddress.setText("Master Canteen Area, Bhubaneswar");
            ivCompany.setImageResource(R.drawable.ic_dtdc);
        } else {
            tvCompany.setText("Indian Post");
            tvAddress.setText("Bapuji Nagar, Bhubaneswar");
            ivCompany.setImageResource(R.drawable.ic_indianpost);
        }

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getInformation(company);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent=new Intent(BookingConfirmedActivity.this, MainActivity.class);
        intent.putExtra("openBooking", true);
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(BookingConfirmedActivity.this, MainActivity.class);
        intent.putExtra("openBooking", true);
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);
    }

    private void getInformation(int getCompanyName) {
        Intent it=new Intent(BookingConfirmedActivity.this, InformationActivity.class);

        it.putExtra("flipkart", "0");
        it.putExtra("fedex", "0");
        it.putExtra("aramex", "0");
        it.putExtra("delhivery", "0");
        it.putExtra("bluedart", "0");
        it.putExtra("dtdc", "0");
        it.putExtra("ipost", "0");

        switch (getCompanyName) {
            case 1:
                it.putExtra("flipkart", "1");
                break;
            case 2:
                it.putExtra("fedex", "2");
                break;
            case 3:
                it.putExtra("aramex", "3");
                break;
            case 4:
                it.putExtra("delhivery", "4");
                break;
            case 5:
                it.putExtra("bluedart", "5");
                break;
            case 6:
                it.putExtra("dtdc", "6");
                break;
            case 7:
                it.putExtra("ipost", "7");
                break;
        }

        startActivity(it);

    }
}
