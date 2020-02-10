package com.example.syntheticknowledge.View.MainSystem.FragmentMain.NewFeedFragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public interface iNewFeedAdapter{
    abstract class NewFeedViewHoder extends ViewHolder{
        public NewFeedViewHoder(@NonNull View itemView){
            super( itemView );
        }
    }
}
