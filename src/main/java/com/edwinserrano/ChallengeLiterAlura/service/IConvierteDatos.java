package com.edwinserrano.ChallengeLiterAlura.service;

public interface IJsonParser {
  <T> T fromJson(String json, Class<T> tClass);
}
