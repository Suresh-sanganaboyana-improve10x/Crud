package com.example.crud.messages;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {
    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String nameText;
    @SerializedName("phoneNumber")
    public String phoneNumberText;
    public String messageText;
}
