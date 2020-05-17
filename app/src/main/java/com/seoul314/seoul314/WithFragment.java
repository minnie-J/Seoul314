package com.seoul314.seoul314;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WithFragment extends Fragment {

    class WithAdapter extends RecyclerView.Adapter<WithAdapter.WithHolder>{



        @NonNull
        @Override
        public WithHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View itemView = inflater.inflate(R.layout.roomlayout, parent, false);
            WithHolder holder = new WithHolder(itemView);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull WithHolder holder, int position) {
            holder.km.setText(String.valueOf(roomList.get(position).getDistance()));
            holder.person.setText(String.valueOf(roomList.get(position).getPersonCount()));
            holder.title.setText(roomList.get(position).getTitle());
        }

        @Override
        public int getItemCount() {

            return roomList.size();
        }

        class WithHolder extends RecyclerView.ViewHolder{

            TextView km, person, title;

            public WithHolder(View itemView) {
                super(itemView);
                km = itemView.findViewById(R.id.room_km);
                person = itemView.findViewById(R.id.room_person);
                title = itemView.findViewById(R.id.room_title);
            }
        }
    }

    private ImageView profile;
    private TextView successCount, firstCount, allDistance, userId;
    private String loginId;
    private ChildEventListener listener;
    private ChildEventListener roomListener;
    private DatabaseReference withReference;
    private DatabaseReference roomReference;
    private List<WithSeoul> roomList;
    private Button createRoom;
    private RoomDialog dialog;


    private RecyclerView recyclerView;

    public WithFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_with, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        withReference = FirebaseDatabase.getInstance().getReference("User");
        roomReference = FirebaseDatabase.getInstance().getReference("RoomList");
        profile = getView().findViewById(R.id.with_profile);
        recyclerView = getView().findViewById(R.id.roomList);
        profile.setImageResource(R.drawable.lyan);
        loginId = UtilDaoImple.getInstance().getLoginId();
        allDistance = getView().findViewById(R.id.with_textView_km);
        firstCount = getView().findViewById(R.id.with_textView_firstCount);
        successCount = getView().findViewById(R.id.with_textView_allCount);
        userId = getView().findViewById(R.id.with_textView_id);
        createRoom = getView().findViewById(R.id.btn_createRoom);
        Log.i("test3","버튼생성");
        UtilDaoImple.getInstance().setCreateRoom(createRoom);
        roomList = new ArrayList<>();
        dialog = new RoomDialog(getContext());

        // 다이얼로그창 크기 조절
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
        wm.copyFrom(dialog.getWindow().getAttributes());
        wm.width = width / 1;
        wm.height = height - 700;
        Log.i("test1",wm.height+"");

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final WithAdapter adapter = new WithAdapter();
        recyclerView.setAdapter(adapter);

        listener = withReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                Log.i("test1", user.getUserId());
                if(user.getUserId().equals(loginId)){
                    if(user.getPicture() != null){
                     // FIXME: 프로필 사진
                    }

                    firstCount.setText("1등 횟수 : "+ user.getWithFirstCount() + " 회");
                    allDistance.setText("총 거리 : " + user.getWithDistance() + " km");
                    successCount.setText("완주 횟수 : " + user.getWithSuccessCount() + " 회");
                    userId.setText(user.getUserId());
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        roomListener = roomReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                WithSeoul ws = dataSnapshot.getValue(WithSeoul.class);
                Log.i("test3",ws.getTitle());

                if(ws.getRoomState().equals("waitting")) {
                    if(roomList.size() == 0){
                        roomList.add(ws);
                        adapter.notifyDataSetChanged();
                    }else{
                        for (WithSeoul ss : roomList) {
                            if (!(ws.getCreateUser().equals(ss.getCreateUser()))) {
                                roomList.add(ws);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }



                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                WithSeoul ws = dataSnapshot.getValue(WithSeoul.class);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    @Override
    public void onDestroy() {
        withReference.removeEventListener(listener);
        roomReference.removeEventListener(roomListener);
        super.onDestroy();
    }
}
