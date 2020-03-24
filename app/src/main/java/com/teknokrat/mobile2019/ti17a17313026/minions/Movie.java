package com.teknokrat.mobile2019.ti17a17313026.minions;

import android.os.Parcel;
import android.os.Parcelable;

class Movie implements Parcelable {
    private int photo;
    private String name;
    private String description;
    private String genre;
    private String release;

    private Movie(Parcel in) {
        photo = in.readInt();
        name = in.readString();
        description = in.readString();
        genre = in.readString();
        release = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    Movie() {

    }

    int getPhoto() {
        return photo;
    }

    void setPhoto(int photo) {
        this.photo = photo;
    }

    String getName() {
        if (name != null) return name;
        else return null;
    }

    void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    String getRelease() {
        return release;
    }

    void setRelease(String release) {
        this.release = release;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeString(release);
    }
}
