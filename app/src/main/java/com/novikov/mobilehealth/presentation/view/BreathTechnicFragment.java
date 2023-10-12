package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.adapters.BreathTechnicAdapter;
import com.novikov.mobilehealth.domain.models.BreathTechnic;

import java.util.ArrayList;
import java.util.List;

public class BreathTechnicFragment extends Fragment {

    private RecyclerView rvBreathTechnics;
    private DatabaseReference breathsDatabase;

    private String breathsTechnicName = "";

    private String breathsTechnicContent = "";

    private View view;

    private List<BreathTechnic> breathTechnics = new ArrayList<>();

    private int breathsCount;

    BreathTechnicAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_breath_technic, container, false);
        breathsDatabase = FirebaseDatabase.getInstance().getReference();
        init();
        adapter = new BreathTechnicAdapter(getContext(), breathTechnics);
        rvBreathTechnics.setAdapter(adapter);
        listInitialization();
        return view;
    }
    private void init(){
        rvBreathTechnics = view.findViewById(R.id.rvBreathTechnics);
    }
    private void listInitialization(){

        breathsDatabase.child("breath_technics").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                breathsCount = (int) snapshot.getChildrenCount();
                Log.e("breathsCount", String.valueOf(breathsCount));
                for(int i = 1; i <= breathsCount; i++){
                    breathsDatabase.child("breath_technics").child(String.valueOf(i)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful()){
                                breathsTechnicName = String.valueOf(task.getResult().child("name").getValue());
                                breathsTechnicContent = String.valueOf(task.getResult().child("describe").getValue());
                                Log.e("breathName", breathsTechnicName);
                                Log.e("breathContent", breathsTechnicContent);
                                BreathTechnic breathTechnic = new BreathTechnic(breathsTechnicName, breathsTechnicContent);
                                breathTechnics.add(breathTechnic);
                                adapter.notifyDataSetChanged();
                            }
                            else{
                                Log.e("breath", "error");
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("database", error.toString());
            }
        });



    }
}