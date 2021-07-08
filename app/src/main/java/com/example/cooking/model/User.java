package com.example.cooking.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "user")
public class User implements Parcelable {
    @NonNull
    public final String publicId;
    @PrimaryKey
    @NonNull
    public final String id;
    @NonNull
    public final String email;

    public User(String publicId, String id, String email) {
        this.publicId = publicId;
        this.id = id;
        this.email = email;
    }

    protected User(Parcel in) {
        publicId = in.readString();
        id = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publicId);
        dest.writeString(id);
        dest.writeString(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

}
