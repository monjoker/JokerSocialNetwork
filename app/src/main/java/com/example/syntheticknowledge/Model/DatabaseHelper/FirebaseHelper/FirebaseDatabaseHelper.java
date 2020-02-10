package com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper;

import android.content.Context;

import com.example.syntheticknowledge.Model.Data.Comment;
import com.example.syntheticknowledge.Model.Data.Status;
import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.Model.DatabaseHelper.SupportDataFinal;
import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseConnect;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public abstract class FirebaseDatabaseHelper implements iDatabaseConnect {
    private Context context;
    private static FirebaseFirestore db = null;
    public FirebaseDatabaseHelper(Context context){
        this.context = context;
        if(db == null){
            db = FirebaseFirestore.getInstance();
        }
    }
    
    public FirebaseDatabaseHelper(Context context, String username){
        applyUserInUi(username);
    }
    
    public static FirebaseFirestore getDb(){
        return db;
    }
    
    //User code
    @Override
    public User getUser(String username){
        return null;
    }
    
    @Override
    public String createUser(User value){
        final SupportDataFinal<String> error = new SupportDataFinal<>(  );
        HashMap<String, Object> field = new HashMap<>();
        field.put( User.PASSWORD, value.getPassword() );
        field.put( User.FIRST_NAME, value.getFirstName() );
        field.put( User.LAST_NAME, value.getLastName() );
        field.put( User.SEX, value.isSex() );
        field.put( User.ADDRESS, value.getAddress() );
        field.put( User.BIRTHDAY, value.getBirthDay() );
        field.put( User.GMAIL, value.getGmail() );
        field.put( User.PHONE_NUMBER, value.getPhoneNumber() );
        db.collection( iDatabaseConnect.NAME_DB_USER ).document( value.getUsername() ).set( field )
          .addOnSuccessListener(documentReference->{
              error.set( null);
              HashMap<String, Object> friendList = new HashMap<>(  );
              if(value.getFriendsList() != null){
                  for(int i = value.getFriendsList().size() - 1; i >= 0; i--){
                      User friend = value.getFriendsList().get( i );
                      friendList.put( User.FIRST_NAME, friend.getFirstName());
                      friendList.put( User.LAST_NAME, friend.getLastName());
                      db.collection( iDatabaseConnect.NAME_DB_USER ).document(value.getUsername())
                        .collection( User.FRIENDS_LIST ).document( friend.getUsername() ).set( friendList );
                  }
              }
              if(value.getFriendsList()!= null){
                  HashMap<String, Object> requestfriendList = new HashMap<>(  );
                  for(int i = value.getFriendsList().size() - 1; i >= 0; i--){
                      User requestFriend = value.getRequestFriendList().get( i );
                      requestfriendList.put( User.FIRST_NAME, requestFriend.getFirstName());
                      requestfriendList.put( User.LAST_NAME, requestFriend.getLastName());
                      db.collection( iDatabaseConnect.NAME_DB_USER ).document(value.getUsername())
                        .collection( User.REQUESTFRIENDS_LIST ).document( requestFriend.getUsername() ).set( requestfriendList );
                  }
              }
          })
          .addOnSuccessListener( aVoid -> {
          
          } )
          .addOnFailureListener( e -> error.set( e.toString() ) );
        return  error.get();
    }
    
    @Override
    public void setUser(String username, User value){
    
    }
    
    //Status code
    @Override
    public String createStatus(Status value){
        final SupportDataFinal<String> error = new SupportDataFinal<>(  );
        HashMap<String, Object>  stt = new HashMap<>(  );
        stt.put( Status.USERNAME,  value.getUsername());
        stt.put( Status.FULL_NAME, value.getFullName());
        stt.put( Status.CONTENT, value.getContent());
        stt.put( Status.WRITE_TIME , value.getWriteTime());
        db.collection( iDatabaseConnect.NAME_DB_STT ).add( stt ).addOnSuccessListener( documentReference -> {
            HashMap<String, Object> likeList = new HashMap<>(  );
            for(int i =0; i < value.getLikesList().size();i++){
                User user = value.getLikesList().get( i );
                likeList.put( User.USERNAME, user.getUsername() );
                likeList.put( User.FIRST_NAME, user.getFirstName() );
                likeList.put( User.LAST_NAME, user.getLastName() );
                documentReference.collection( Status.LIKES_LIST ).add( likeList );
            }
            HashMap<String, Object> cmtList = new HashMap<>(  );
            for(int i =0;i< value.getCommentsList().size();i++){
                Comment comment = value.getCommentsList().get( i );
                cmtList.put( Comment.USERNAME, comment.getUsername() );
                cmtList.put( Comment.FULL_NAME, comment.getFullName() );
                cmtList.put( Comment.CONTENT, comment.getContent() );
                cmtList.put( Comment.WRITE_TIME, comment.getWriteTime() );
                documentReference.collection( Status.CMT_LIST ).add( likeList ).addOnSuccessListener( documentReference1 -> {
                    HashMap<String, Object> likeListCmt = new HashMap<>(  );
                    for(int j =0; j < value.getLikesList().size();j++){
                        User user = value.getLikesList().get( j );
                        likeListCmt.put( User.USERNAME, user.getUsername() );
                        likeListCmt.put( User.FIRST_NAME, user.getFirstName() );
                        likeListCmt.put( User.LAST_NAME, user.getLastName() );
                        documentReference1.collection( Status.LIKES_LIST ).add( likeListCmt );
                    }
                } );
            }
            HashMap<String, Object> shareList = new HashMap<>(  );
            for(int i =0; i < value.getSharesList().size();i++){
                User user = value.getLikesList().get( i );
                shareList.put( User.USERNAME, user.getUsername() );
                shareList.put( User.FIRST_NAME, user.getFirstName() );
                shareList.put( User.LAST_NAME, user.getLastName() );
                documentReference.collection( Status.SHARE_LIST ).add( shareList );
            }
        } ).addOnFailureListener(  e -> error.set( e.toString() ));
        return error.get();
    }
}
