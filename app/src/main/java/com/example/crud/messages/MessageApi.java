package com.example.crud.messages;

import com.example.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageApi {

    public MessagesService createMessageService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesService messagesService = retrofit.create(MessagesService.class);
        return messagesService;
    }
}
