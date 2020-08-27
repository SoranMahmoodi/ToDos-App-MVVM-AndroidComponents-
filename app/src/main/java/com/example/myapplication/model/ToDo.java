package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "toDoTable")
@Keep

public class ToDo implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    public String title;
    public String description;
    public String dateTodo;
    public int priority;
    private boolean isCompleted = false;

    public ToDo() {
    }

    public ToDo(String title, String description, String dateTodo, int priority) {
        this.title = title;
        this.description = description;
        this.dateTodo = dateTodo;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        if (title == null) {
            return "";
        } else {
            return title;
        }


      /*  if (isCompleted()) {
            SpannableString spannableString = new SpannableString(title);
            spannableString.setSpan(new StrikethroughSpan(), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        } else {
            return SpannableString.valueOf(title);
        }*/
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        if (description == null) {
            return "";
        } else {
            return description;
        }


    /*    if (isCompleted()) {
            SpannableString spannableString = new SpannableString(description);
            spannableString.setSpan(new StrikethroughSpan(), 0, description.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        } else {
            return SpannableString.valueOf(description);

        }*/
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTodo() {
     /*   if (isCompleted()) {
            SpannableString spannableString = new SpannableString(dateTodo);
            spannableString.setSpan(new StrikethroughSpan(), 0, dateTodo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        } else {
            return SpannableString.valueOf(dateTodo);
        }*/
        return dateTodo;
    }

    public void setDateTodo(String dateTodo) {
        this.dateTodo = dateTodo;
    }

    public int getPriority() {
        if (priority == 0) {
            return 1;
        } else {
            return priority;
        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.priority);
        dest.writeByte(this.isCompleted ? (byte) 1 : (byte) 0);
    }

    protected ToDo(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.priority = in.readInt();
        this.isCompleted = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ToDo> CREATOR = new Parcelable.Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel source) {
            return new ToDo(source);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };
}
