package com.example.accountant.mapper;

public interface Mapper<S, T> {

    T map(S object);

    default T map(S object, T toObject){
        return toObject;
    }
}
