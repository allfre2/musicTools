package com.allfre2.musicruler;

import java.util.List;

public interface CFG<T>{
 public List<T> produce();
 public List<T> parse(List<Object> text);
}