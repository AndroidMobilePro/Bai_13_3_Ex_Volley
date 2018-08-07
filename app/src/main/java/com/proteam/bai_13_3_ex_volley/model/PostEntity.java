package com.proteam.bai_13_3_ex_volley.model;

import java.util.ArrayList;
import java.util.List;

public class PostEntity {

      private ArrayList<People> postList = new ArrayList<People>();

      public List<People> getPostList() {
           return postList; 
      }

      public void setPostList(List<People> postList) {
           this.postList = (ArrayList<People>)postList;
      } 
 }