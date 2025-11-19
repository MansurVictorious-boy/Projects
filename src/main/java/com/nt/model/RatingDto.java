package com.nt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDto {
 private Long customerid;
 private Long bookid;
 private int rate;
 private String reviewText;
}
