package com.service.inteloom;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.service.inteloom.model.Question;
import com.service.inteloom.model.Survey;
import com.service.inteloom.service.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	@GetMapping("/surveys")
    public List<Survey> retrieveSurveys() {
        return surveyService.retrieveAllSurveys();
    }
	
	@GetMapping("/surveys/{surveyId}")
    public List<Survey> retrieveSurveys(@PathVariable String surveyId) {
        return surveyService.retrieveAllSurveys();
    }
	
	@GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestions(@PathVariable String surveyId) {
        return surveyService.retrieveQuestions(surveyId);
    }
	
	@GetMapping(path = "/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveQuestion(@PathVariable String surveyId,
            @PathVariable String questionId) {
        return surveyService.retrieveQuestion(surveyId, questionId);
    }
	
	// /surveys/{surveyId}/questions
		@PostMapping("/surveys/{surveyId}/questions")
		public ResponseEntity<Void> addQuestionToSurvey(
				@PathVariable String surveyId, @RequestBody Question newQuestion) {

			Question question = surveyService.addQuestion(surveyId, newQuestion);

			if (question == null)
				return ResponseEntity.noContent().build();

			// Success - URI of the new resource in Response Header
			// Status - created
			// URI -> /surveys/{surveyId}/questions/{questionId}
			// question.getQuestionId()
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(question.getId()).toUri();

			// Status
			return ResponseEntity.created(location).build();
		}

}
