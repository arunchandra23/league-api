package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.SecurityQuestions;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SecurityQuestionRequest;
import com.woxsen.leagueapi.repository.SecurityQuestionsRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.repository.ArenaRepository;
import com.woxsen.leagueapi.repository.BookingsRepository;
import com.woxsen.leagueapi.repository.SlotsRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SecurityQuestionsService {
	@Autowired
	private SecurityQuestionsRepository securityQuestionsRepository;


	private ModelMapper modelMapper = new ModelMapper();


	public ApiResponse addQuestion(SecurityQuestionRequest securityQuestionRequest) {
		SecurityQuestions sq=modelMapper.map(securityQuestionRequest,SecurityQuestions.class);
		SecurityQuestions save = securityQuestionsRepository.save(sq);
		ApiResponse apiResponse = ApiResponse.builder().data(save).errors(new ArrayList<>())
				.message(AppConstants.INSERT_SUCCESS).success(Boolean.TRUE).status(HttpStatus.CREATED).build();
		return apiResponse;
	}

	public ApiResponse getAllQuestions() {
		List<SecurityQuestions> questions=securityQuestionsRepository.findAllByActiveIndex(true);
		ApiResponse apiResponse = ApiResponse.builder().data(questions).errors(new ArrayList<>())
				.message(AppConstants.RETRIEVAL_SUCCESS).success(Boolean.TRUE).status(HttpStatus.OK).build();
		return apiResponse;
	}


}
