package com.example.ft_hangout.FtHangoutAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;





public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener _listener;

    public RecyclerItemClickListener() {
        super();
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int pos);
    }
    GestureDetector _gestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        _listener = (OnItemClickListener) listener;
        _gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (childView != null && _listener != null && _gestureDetector.onTouchEvent(motionEvent)) {
            _listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }


    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }




}