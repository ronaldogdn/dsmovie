package com.ronaldo.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronaldo.dsmovie.dto.MovieDTO;
import com.ronaldo.dsmovie.dto.ScoreDTO;
import com.ronaldo.dsmovie.entities.Movie;
import com.ronaldo.dsmovie.entities.Score;
import com.ronaldo.dsmovie.entities.User;
import com.ronaldo.dsmovie.repositories.MovieRepository;
import com.ronaldo.dsmovie.repositories.ScoreRepository;
import com.ronaldo.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO scoreDTO) {
		System.out.println(scoreDTO.getEmail());
		System.out.println(scoreDTO.getMovieId());
		User user = userRepository.findByEmail(scoreDTO.getEmail());
		if(user == null) {
			user = new User();
			user.setEmail(scoreDTO.getEmail());
			userRepository.saveAndFlush(user);
		}
		Movie movie =  movieRepository.findById(scoreDTO.getMovieId()).get();
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(scoreDTO.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (Score s : movie.getScores()) {
			sum +=s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
	

}
