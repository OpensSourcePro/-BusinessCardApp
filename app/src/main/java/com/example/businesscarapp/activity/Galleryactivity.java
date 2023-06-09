package com.example.businesscarapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.example.businesscarapp.CardStackCallback;
import com.example.businesscarapp.R;

import com.example.businesscarapp.adapters.CardStackAdapter;
import com.example.businesscarapp.models.ItemModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class Galleryactivity extends AppCompatActivity {

    private static final String TAG = "Galleryactivity";
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // 상태바 없애기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getInsetsController().hide(WindowInsets.Type.statusBars());
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right) {

                }
                if (direction == Direction.Top) {

                }
                if (direction == Direction.Left) {

                }
                if (direction == Direction.Bottom) {

                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5) {
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);

        //예외가 발생하는 부분
        manager.setScaleInterval(0.6f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> newone = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, newone);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(newone);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.sample1, "박경아", "24", "서울", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample2, "조정은", "20", "부산","충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample3, "옥주현", "27", "대전", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample4, "윤공주", "19", "청주", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample5, "유선", "25", "광주", "충북대", "소프트웨어학과", "백엔드"));

        items.add(new ItemModel(R.drawable.sample5, "박경아", "24", "서울", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample4, "조정은", "20", "부산", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample3, "옥주현", "27", "대전", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample2, "윤공주", "19", "청주", "충북대", "소프트웨어학과", "백엔드"));
        items.add(new ItemModel(R.drawable.sample1, "유선", "25", "광주", "충북대", "소프트웨어학과", "백엔드"));
        return items;

    }
}