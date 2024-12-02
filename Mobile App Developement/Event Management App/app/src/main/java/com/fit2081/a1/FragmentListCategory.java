package com.fit2081.a1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fit2081.a1.provider.EventCategoryViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListCategory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private MyCategoryRecyclerAdapter recyclerAdapter;

    RecyclerView.LayoutManager layoutManager;


    private EventCategoryViewModel eventCategoryViewModel;



    public FragmentListCategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListCategory newInstance(String param1, String param2) {
        FragmentListCategory fragment = new FragmentListCategory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_category, container, false);

        recyclerView = v.findViewById(R.id.cat_recyclerview);
        recyclerAdapter = new MyCategoryRecyclerAdapter();

        recyclerView.setAdapter(recyclerAdapter);
        layoutManager = new LinearLayoutManager(getContext());  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);

        // initialise ViewModel
        eventCategoryViewModel = new ViewModelProvider(this).get(EventCategoryViewModel.class);
        eventCategoryViewModel.getAllEventCat().observe(getViewLifecycleOwner(), newData -> {
            // cast List<Item> to ArrayList<Item>
            recyclerAdapter.setData(new ArrayList<EventCategory>(newData));
            recyclerAdapter.notifyDataSetChanged();
        });

        return v;

    }

//    public void setCatList(){
//
//        eventCategoryViewModel.getAllCards().observe(getContext(), newData -> {
//            // cast List<Item> to ArrayList<Item>
//            recyclerAdapter.setData(new ArrayList<EventCategory>(newData));
//            recyclerAdapter.notifyDataSetChanged();
//        });
//
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("EMA_DETAILS", MODE_PRIVATE);
//        String event_cat_data = sharedPreferences.getString("EVENT_CATEGORY", "DEFAULT VALUE");
//        ArrayList<EventCategory> eventCatDetails;
//        if (event_cat_data.equals("DEFAULT VALUE")) {
//            eventCatDetails = new ArrayList<>();
//        } else {
//            Type type = new TypeToken<ArrayList<EventCategory>>() {}.getType();
//            eventCatDetails = gson.fromJson(event_cat_data, type);
//        }
//
//        // Set up RecyclerView Adapter
//        recyclerAdapter = new MyCategoryRecyclerAdapter(eventCatDetails);
//        recyclerAdapter.setData(eventCatDetails);
//        recyclerView.setAdapter(recyclerAdapter);
//
//    }


}