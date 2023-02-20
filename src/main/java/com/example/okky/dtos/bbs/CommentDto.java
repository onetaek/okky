package com.example.okky.dtos.bbs;

import lombok.*;

import java.util.Date;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
   private int index;
   private int group;
   private int sequence;
   private int level;
   private int boardId;
   private int articleIndex;
   private String userEmail;
   private String userNickName;
   private String content;
   private Date createdAt;


}
