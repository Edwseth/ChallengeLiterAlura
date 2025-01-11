package com.edwinserrano.ChallengeLiterAlura.contracts;

public interface IJsonParser {
    <T> T fromJson(String json, Class<T> tClass);
}
