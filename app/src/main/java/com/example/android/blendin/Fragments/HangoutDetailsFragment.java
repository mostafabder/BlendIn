package com.example.android.blendin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blendin.Models.HangoutModel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.example.android.blendin.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutDetailsFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    FancyButton submitBtn;
    TextView startDate, endDate, startTime, endTime, location;
    RelativeLayout startDateR, endDateR, startTimeR, endTimeR, locationR;
    Calendar calendar;
    Boolean checkDate;
    Boolean checkTime;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    List<HangoutModel> hangoutModels;
    String ppl, activity;

    @BindView(R.id.et_title_hangoutDetails)
    EditText et_title;
    @BindView(R.id.et_disc_hangoutDetails)
    EditText et_disc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hangout_details, container, false);
        ButterKnife.bind(this, v);
        Bundle bundle = getArguments();
        ppl = bundle.getString("people");
        activity = bundle.getString("activity");
        intiScreen(v);
        return v;
    }

    public void intiScreen(View v) {
        submitBtn = (FancyButton) v.findViewById(R.id.btn_submit_hangout);
        startDate = (TextView) v.findViewById(R.id.tv_startdate_hangoutdetails);
        endDate = (TextView) v.findViewById(R.id.tv_enddate_hangoutdetails);
        startTime = (TextView) v.findViewById(R.id.tv_starttime_hangoutdetails);
        endTime = (TextView) v.findViewById(R.id.tv_endtime_hangoutdetails);
        location = (TextView) v.findViewById(R.id.tv_location_hangoutDetails);
        startDateR = (RelativeLayout) v.findViewById(R.id.rl_startdate_hangoutdetails);
        endDateR = (RelativeLayout) v.findViewById(R.id.rl_enddate_hangoutdetails);
        startTimeR = (RelativeLayout) v.findViewById(R.id.rl_starttime_hangoutdetails);
        endTimeR = (RelativeLayout) v.findViewById(R.id.rl_endtime_hangoutdetails);
        locationR = (RelativeLayout) v.findViewById(R.id.rl_location_hangoutDetails);

        calendar = Calendar.getInstance();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HangoutProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("auth", true);
                bundle.putString("people", ppl);
                bundle.putString("activity", activity);
                bundle.putString("disc", et_disc.getText().toString());
                bundle.putString("title", et_title.getText().toString());
                bundle.putString("location", location.getText().toString());
                bundle.putString("startDate", startDate.getText().toString());
                bundle.putString("endDate", endDate.getText().toString());
                bundle.putString("startTime", startTime.getText().toString());
                bundle.putString("endTime", endTime.getText().toString());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_out_from_bottom, R.anim.slide_in_to_bottom);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        startDateR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(HangoutDetailsFragment.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getActivity().getFragmentManager(), "datePicker");
                checkDate = true;
            }
        });

        endDateR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(HangoutDetailsFragment.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getActivity().getFragmentManager(), "datePicker");
                checkDate = false;
            }
        });

        startTimeR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.newInstance(HangoutDetailsFragment.this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true).show(getActivity().getFragmentManager(), "timePicker");
                checkTime = true;
            }
        });

        endTimeR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.newInstance(HangoutDetailsFragment.this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true).show(getActivity().getFragmentManager(), "timePicker");
                checkTime = false;
            }
        });

        locationR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });


        startDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        endDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        startTime.setText(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        endTime.setText(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                location.setText(place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Toast.makeText(getActivity(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        if (checkDate) {
            startDate.setText(new StringBuilder().append(dayOfMonth)
                    .append("/").append(monthOfYear + 1).append("/").append(year)
                    .append(" "));
        } else {
            endDate.setText(new StringBuilder().append(dayOfMonth)
                    .append("/").append(monthOfYear + 1).append("/").append(year)
                    .append(" "));
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        if (checkTime) {
            startTime.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute));
        } else {
            endTime.setText(new StringBuilder().append(hourOfDay)
                    .append(":").append(minute));
        }
    }
}
