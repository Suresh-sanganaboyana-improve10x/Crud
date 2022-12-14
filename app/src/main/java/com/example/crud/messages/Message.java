package com.example.crud.messages;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {
    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    //TODO : String nameText to name
    public String nameText;
    @SerializedName("phoneNumber")
    //TODO : String phoneNumberText to phoneNumber
    public String phoneNumberText;
    public String messageText;
}
