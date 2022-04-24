package com.ronaldo.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ronaldo.dsmovie.dto.MovieDTO;
import com.ronaldo.dsmovie.entities.Movie;
import com.ronaldo.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> page = movieRepository.findAll(pageable);
		Page<MovieDTO> pageDTO = page.map(x -> new MovieDTO(x));
		return pageDTO;
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		//retorna um objeto tipo optional
		Movie movie = movieRepository.findById(id).get();
		MovieDTO movieDTO = new MovieDTO(movie);
		return movieDTO;
	}

}
