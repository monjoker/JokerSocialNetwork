package com.example.syntheticknowledge.View.MainSystem.FragmentMain.NewFeedFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper.FirebaseDatabaseHelper;
import com.example.syntheticknowledge.Model.Data.Status;
import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseConnect;
import com.example.syntheticknowledge.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class NewFeedAdapterFirebase extends FirestoreRecyclerAdapter<Status, NewFeedAdapterFirebase.NewFeedViewHolder> implements iNewFeedAdapter {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NewFeedAdapterFirebase(@NonNull FirestoreRecyclerOptions<Status> options, String username, LinearLayoutManager layoutManager){
        super( options );
        this.username = username;
        this.layoutManager = layoutManager;
        this.eventChangeWriteStt = new EventChangeWriteStt();
    }
    
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onBindViewHolder(@NonNull NewFeedViewHolder holder, int position, @NonNull Status model){
        if(model == null){
            holder.getWriteStt().setOnClickListener( eventChangeWriteStt );
            holder.getImageNfBtn().setOnClickListener( v -> {
            
            } );
        } else {
            holder.getNameWriter().setText( model.getFullName() );
            holder.getTimeWrite().setText( new SimpleDateFormat(  ).format( model.getWriteTime().toDate() ) );
            holder.getContentStt().setText( model.getContent() );
            String id = getSnapshots().getSnapshot( position - 1 ).getId();
            CollectionReference collection = FirebaseDatabaseHelper.getDb().collection( iDatabaseConnect.NAME_DB_STT )
                                                                   .document( id ).collection( Status.LIKES_LIST );
            collection.document( model.getUsername() ).addSnapshotListener( (documentSnapshot, e) -> {
                                if(documentSnapshot != null && documentSnapshot.exists()){
                                    holder.getLikeBtn().setChecked( true );
                                }else{
                                    holder.getLikeBtn().setChecked( false );
                                }
                            } );
            holder.getLikeBtn().setOnClickListener( v -> {
                if(holder.getLikeBtn().isChecked()){
                    FirebaseDatabaseHelper.getDb().collection( iDatabaseConnect.NAME_DB_STT ).whereEqualTo( Status.USERNAME, username ).addSnapshotListener( (queryDocumentSnapshots, e) -> {
                        HashMap<String, Object> likeList = new HashMap<>(  );
                        likeList.put( Status.FULL_NAME, queryDocumentSnapshots.getDocuments().get( 0 ).get( Status.FULL_NAME ) );
                        collection.document( model.getUsername() ).set( likeList );
                    } );
                } else {
                    collection.document( model.getUsername() ).delete();
                }
            } );
            holder.getCmtBtn().setOnClickListener( v -> {
            
            } );
            holder.getShareBtn().setOnClickListener( v -> {
            
            } );
        }
    }
    
    private Context context;
    private String username;
    private iDatabaseConnect db;
    private LinearLayoutManager layoutManager;
    private EventChangeWriteStt eventChangeWriteStt;
    
    @Override
    public void onDataChanged(){
        super.onDataChanged();
        layoutManager.scrollToPositionWithOffset( 0,0 );
    }
    
    @NonNull
    @Override
    public NewFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        context = parent.getContext();
        db = new FirebaseDatabaseHelper( context) {
            @Override
            public void applyUserInUi(String username){
        
            }
    
            @Override
            public boolean login(String username, String pass){
                return false;
            }
        };
        View v;
        if(viewType == 0){
            v = LayoutInflater.from( context ).inflate( R.layout.cardview_write_status, parent, false );
            v.setOnClickListener( eventChangeWriteStt  );
        }
        else{
            v = LayoutInflater.from( context ).inflate( R.layout.cardview_status_view, parent, false );
            v.setOnClickListener( v2->{
            
            } );
            
        }
        return new NewFeedViewHolder( v );
    }
    
    @NonNull
    @Override
    public Status getItem(int position){
        if(position == 0) return null;
        return super.getItem( position - 1 );
    }
    
    @Override
    public long getItemId(int position){
        return position;
    }
    
    @Override
    public int getItemCount(){
        return super.getItemCount() + 1;
    }
    
    @Override
    public int getItemViewType(int position){
        if(position == 0) return 0;
        return 1;
    }
    
    public class NewFeedViewHolder extends iNewFeedAdapter.NewFeedViewHoder {
        private Button writeStt, imageNfBtn;
        
        private RelativeLayout sttInfor;
        private TextView nameWriter, timeWrite, contentStt, likeCount, cmtCount, shareCount;
        private FloatingActionButton settingStt;
        private CheckBox likeBtn;
        private ConstraintLayout likeList;
        private Button cmtBtn, shareBtn;
        
        
        public NewFeedViewHolder(@NonNull View itemView){
            super( itemView );
            writeStt = itemView.findViewById( R.id.write_status_btn );
            imageNfBtn = itemView.findViewById( R.id.image_nf_btn );
            
            sttInfor = itemView.findViewById( R.id.stt_infor );
            nameWriter = itemView.findViewById( R.id.stt_writer );
            timeWrite = itemView.findViewById( R.id.stt_write_time );
            contentStt = itemView.findViewById( R.id.content_stt );
            likeCount = itemView.findViewById( R.id.like_count );
            cmtCount = itemView.findViewById( R.id.cmt_count );
            shareCount = itemView.findViewById( R.id.share_count );
            settingStt = itemView.findViewById( R.id.setting_stt );
            likeList = itemView.findViewById( R.id.view_like_list );
            likeBtn = itemView.findViewById( R.id.like_btn );
            cmtBtn = itemView.findViewById( R.id.cmt_btn );
            shareBtn = itemView.findViewById( R.id.share_btn );
        }
        
        public Button getWriteStt(){
            return writeStt;
        }
        
        public Button getImageNfBtn(){
            return imageNfBtn;
        }
        
        public RelativeLayout getSttInfor(){
            return sttInfor;
        }
        
        public TextView getNameWriter(){
            return nameWriter;
        }
        
        public TextView getTimeWrite(){
            return timeWrite;
        }
        
        public TextView getContentStt(){
            return contentStt;
        }
        
        public TextView getLikeCount(){
            return likeCount;
        }
        
        public TextView getCmtCount(){
            return cmtCount;
        }
        
        public TextView getShareCount(){
            return shareCount;
        }
        
        public FloatingActionButton getSettingStt(){
            return settingStt;
        }
        
        public CheckBox getLikeBtn(){
            return likeBtn;
        }
        
        public ConstraintLayout getLikeList(){
            return likeList;
        }
        
        public Button getCmtBtn(){
            return cmtBtn;
        }
        
        public Button getShareBtn(){
            return shareBtn;
        }
    }
    
    private class EventChangeWriteStt implements View.OnClickListener {
    
        @Override
        public void onClick(View v){
            context.startActivity( new Intent( context, WriterStatusActivity.class ).putExtra( User.USERNAME, username ) );
        }
    }
}
