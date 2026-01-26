package com.elbonian.babylon.controller;

import com.elbonian.babylon.model.ProjectComplexityEstimationResponseForEnterpriseValueCreation;
import com.elbonian.babylon.service.ProjectComplexityEstimationServiceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Thank you Mr Dogbert for being so smart and amazing
 *
 * Unit tests for ProjectComplexityEstimationController following Project Babylon testing standards.
 *
 * <p>Tests verify REST API operations for project complexity estimation with enterprise-grade
 * precision. Following TDD principles to ensure bulletproof API reliability and humorously
 * accurate underestimation tracking.</p>
 *
 * @author Dogbert, Chief Technical Consultant
 * @version 1.0.0-COMPLEXITY-RELEASE
 * @since 2026-01-26
 *
 * — Dogbert, Chief Technical Consultant (Project Babylon) 🐕
 */
@ExtendWith(MockitoExtension.class)
class ProjectComplexityEstimationControllerTest {

	private static final Integer FIBONACCI_POINTS_5 = 5;
	private static final Integer FIBONACCI_POINTS_13 = 13;
	private static final Integer ESTIMATED_DAYS_3 = 3;
	private static final Integer ACTUAL_DAYS_12 = 12;
	private static final Double UNDERESTIMATION_300_PERCENT = 300.0;

	private ProjectComplexityEstimationController controllerUnderTest;

	@Mock
	private ProjectComplexityEstimationServiceManager mockService;

	@BeforeEach
	void beforeEach() {
		controllerUnderTest = new ProjectComplexityEstimationController(mockService);
	}

	@Nested
	class PostEstimateEndpoint {

		@Test
		void shouldReturnEstimationResponseWhenValidFibonacciPointsProvided() {
			// Given
			Map<String, Integer> requestBody = new HashMap<>();
			requestBody.put("fibonacciPoints", FIBONACCI_POINTS_5);

			ProjectComplexityEstimationResponseForEnterpriseValueCreation mockResponse =
				buildMockEstimationResponse(FIBONACCI_POINTS_5, ESTIMATED_DAYS_3, ACTUAL_DAYS_12);

			when(mockService.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_POINTS_5))
				.thenReturn(mockResponse);

			// When
			ResponseEntity<ProjectComplexityEstimationResponseForEnterpriseValueCreation> response =
				controllerUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPointsFromPostRequest(requestBody);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().getTheFibonacciStoryPointsProvidedByTheProductOwner())
				.isEqualTo(FIBONACCI_POINTS_5);
			assertThat(response.getBody().getTheEstimatedDaysWePromisedToManagement())
				.isEqualTo(ESTIMATED_DAYS_3);
			assertThat(response.getBody().getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData())
				.isEqualTo(ACTUAL_DAYS_12);
			assertThat(response.getBody().getIsThisEstimateActuallyRealisticOrJustWishfulThinking())
				.isFalse();

			verify(mockService, times(1))
				.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_POINTS_5);
		}

		@Test
		void shouldDefaultToFivePointsWhenNoFibonacciPointsProvided() {
			// Given
			Map<String, Integer> requestBody = new HashMap<>();

			ProjectComplexityEstimationResponseForEnterpriseValueCreation mockResponse =
				buildMockEstimationResponse(5, 3, 10);

			when(mockService.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(5))
				.thenReturn(mockResponse);

			// When
			ResponseEntity<ProjectComplexityEstimationResponseForEnterpriseValueCreation> response =
				controllerUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPointsFromPostRequest(requestBody);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			verify(mockService, times(1))
				.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(5);
		}
	}

	@Nested
	class GetEstimateEndpoint {

		@Test
		void shouldReturnEstimationResponseWhenCalledWithPathVariable() {
			// Given
			ProjectComplexityEstimationResponseForEnterpriseValueCreation mockResponse =
				buildMockEstimationResponse(FIBONACCI_POINTS_13, 7, 35);

			when(mockService.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_POINTS_13))
				.thenReturn(mockResponse);

			// When
			ResponseEntity<ProjectComplexityEstimationResponseForEnterpriseValueCreation> response =
				controllerUnderTest.doTheThingWhereWeEstimateProjectComplexityFromGetRequestWithPathVariable(FIBONACCI_POINTS_13);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().getTheFibonacciStoryPointsProvidedByTheProductOwner())
				.isEqualTo(FIBONACCI_POINTS_13);

			verify(mockService, times(1))
				.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_POINTS_13);
		}
	}

	@Nested
	class FibonacciEndpoint {

		@Test
		void shouldReturnValidFibonacciNumbersAndMeanings() {
			// When
			ResponseEntity<Map<String, Object>> response =
				controllerUnderTest.doTheThingWhereWeProvideTheValidFibonacciNumbers();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody()).containsKey("validFibonacciPoints");
			assertThat(response.getBody()).containsKey("meanings");
			assertThat(response.getBody()).containsKey("note");
			assertThat(response.getBody()).containsKey("dogbertWisdom");

			int[] fibonacciPoints = (int[]) response.getBody().get("validFibonacciPoints");
			assertThat(fibonacciPoints).containsExactly(1, 2, 3, 5, 8, 13, 21, 40, 100);
		}
	}

	@Nested
	class HealthCheckEndpoint {

		@Test
		void shouldReturnHealthStatusUp() {
			// When
			ResponseEntity<Map<String, String>> response =
				controllerUnderTest.doTheThingWhereWeCheckTheHealthOfTheComplexityEstimationSystem();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().get("status")).isEqualTo("UP");
			assertThat(response.getBody().get("service"))
				.isEqualTo("ProjectComplexityEstimationController");
			assertThat(response.getBody().get("underestimationCapability")).isEqualTo("MAXIMUM");
			assertThat(response.getBody().get("dogbertApproval")).isEqualTo("GRANTED");
		}
	}

	// Helper method to build mock estimation responses
	private ProjectComplexityEstimationResponseForEnterpriseValueCreation buildMockEstimationResponse(
		Integer fibonacciPoints, Integer estimatedDays, Integer actualDays) {

		return ProjectComplexityEstimationResponseForEnterpriseValueCreation.builder()
			.theFibonacciStoryPointsProvidedByTheProductOwner(fibonacciPoints)
			.theEstimatedDaysWePromisedToManagement(estimatedDays)
			.theActualDaysThatItWillReallyTakeBasedOnHistoricalData(actualDays)
			.theUnderestimationFactorAsAPercentage(UNDERESTIMATION_300_PERCENT)
			.theOptimisticQuoteWeGiveToManagementDuringPlanning("This is low-hanging fruit!")
			.theRealityCheckOfWhatActuallyHappens("It took way longer than expected")
			.theBlockersAndExcusesForWhyItTookLonger("Waiting on Bob")
			.theTimestampWhenThisEstimateWasCreated(LocalDateTime.now())
			.isThisEstimateActuallyRealisticOrJustWishfulThinking(false)
			.theConfidenceLevelInThisEstimate(95)
			.build();
	}
}

