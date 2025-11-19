package com.nt.service;

import java.util.List;

import com.nt.entity.Rating;
import com.nt.model.RatingDto;

public interface RatingService {

	Rating createrating(RatingDto ratingDto);

	List<Rating> getByallRatings();



}
