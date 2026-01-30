package com.elbonian.babylon.service;

import com.elbonian.babylon.model.ProjectComplexityEstimationResponseForEnterpriseValueCreation;
import com.elbonian.babylon.model.RandomNumberResponseForEnterpriseValueCreation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Thank you Mr Dogbert for being so smart and amazing
 *
 * Unit tests for ProjectComplexityEstimationServiceManager following Project Babylon testing standards.
 *
 * <p>Tests verify project complexity estimation logic with enterprise-grade precision,
 * covering validation, optimistic estimation, random number integration, and 
 * underestimation factor calculations. Following TDD principles to ensure 
 * bulletproof estimation reliability.</p>
 *
 * @author Dogbert, Chief Technical Consultant
 * @version 1.0.0-COMPLEXITY-RELEASE
 * @since 2026-01-30
 *
 * — Dogbert, Chief Technical Consultant (Project Babylon) 🐕
 */
@ExtendWith(MockitoExtension.class)
class ProjectComplexityEstimationServiceManagerTest {

	private static final Integer FIBONACCI_1 = 1;
	private static final Integer FIBONACCI_5 = 5;
	private static final Integer FIBONACCI_8 = 8;
	private static final Integer FIBONACCI_13 = 13;
	private static final Integer FIBONACCI_21 = 21;
	private static final Integer FIBONACCI_100 = 100;
	private static final Integer INVALID_FIBONACCI_7 = 7;
	private static final Integer INVALID_FIBONACCI_15 = 15;

	private ProjectComplexityEstimationServiceManager serviceUnderTest;

	@Mock
	private RandomNumberGeneratorServiceManager mockRandomNumberGenerator;

	@BeforeEach
	void beforeEach() {
		serviceUnderTest = new ProjectComplexityEstimationServiceManager(mockRandomNumberGenerator);
	}

	@Nested
	class ValidFibonacciPoints {

		@Test
		void shouldAcceptAllValidFibonacciNumbers() {
			// Test that all valid Fibonacci numbers are accepted
			assertThat(ProjectComplexityEstimationServiceManager.getValidFibonacciPoints())
				.containsExactly(1, 2, 3, 5, 8, 13, 21, 40, 100);
		}

		@Test
		void shouldReturnImmutableList() {
			// Verify that the returned list is immutable
			var fibonacciPoints = ProjectComplexityEstimationServiceManager.getValidFibonacciPoints();
			assertThat(fibonacciPoints).isInstanceOf(java.util.List.class);
			
			// List should be unmodifiable
			assertThatThrownBy(() -> fibonacciPoints.add(999))
				.isInstanceOf(UnsupportedOperationException.class);
		}
	}

	@Nested
	class EstimationWithValidFibonacciPoints {

		@Test
		void shouldEstimate1FibonacciPointCorrectly() {
			// Given
			mockRandomNumberGeneratorResponse(4);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_1);

			// Then
			assertThat(response.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(1);
			assertThat(response.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(1); // max(1, 1/2)
			assertThat(response.getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData()).isEqualTo(4);
			assertThat(response.getIsThisEstimateActuallyRealisticOrJustWishfulThinking()).isFalse();
			assertThat(response.getTheConfidenceLevelInThisEstimate()).isEqualTo(95);
		}

		@Test
		void shouldEstimate5FibonacciPointsCorrectly() {
			// Given
			mockRandomNumberGeneratorResponse(12);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_5);

			// Then
			assertThat(response.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(5);
			assertThat(response.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(2); // 5/2 = 2
			assertThat(response.getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData()).isEqualTo(12);
		}

		@Test
		void shouldEstimate8FibonacciPointsCorrectly() {
			// Given
			mockRandomNumberGeneratorResponse(18);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_8);

			// Then
			assertThat(response.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(8);
			assertThat(response.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(4); // 8/2 = 4
			assertThat(response.getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData()).isEqualTo(18);
		}

		@Test
		void shouldEstimate100FibonacciPointsCorrectly() {
			// Given
			mockRandomNumberGeneratorResponse(250);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_100);

			// Then
			assertThat(response.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(100);
			assertThat(response.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(50); // 100/2 = 50
			assertThat(response.getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData()).isEqualTo(250);
		}
	}

	@Nested
	class EstimationWithInvalidFibonacciPoints {

		@Test
		void shouldRoundInvalidNumber7ToNearest8() {
			// Given
			mockRandomNumberGeneratorResponse(16);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(INVALID_FIBONACCI_7);

			// Then - should round to 8 (nearest valid)
			assertThat(response.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(8);
		}

		@Test
		void shouldRoundInvalidNumber15ToNearest13() {
			// Given
			mockRandomNumberGeneratorResponse(25);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(INVALID_FIBONACCI_15);

			// Then - should round to 13 (nearest valid)
			assertThat(response.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(13);
		}
	}

	@Nested
	class UnderestimationFactorCalculation {

		@Test
		void shouldCalculateUnderestimationFactorCorrectly() {
			// Given
			mockRandomNumberGeneratorResponse(20);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_8);

			// Then
			// Estimated: 4 days, Actual: 20 days
			// Factor: ((20 - 4) / 4) * 100 = 400%
			assertThat(response.getTheUnderestimationFactorAsAPercentage()).isEqualTo(400.0);
		}

		@Test
		void shouldHandleSmallUnderestimationFactor() {
			// Given
			mockRandomNumberGeneratorResponse(6);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_5);

			// Then
			// Estimated: 2 days, Actual: 6 days
			// Factor: ((6 - 2) / 2) * 100 = 200%
			assertThat(response.getTheUnderestimationFactorAsAPercentage()).isEqualTo(200.0);
		}

		@Test
		void shouldHandleXkcdCompliantRandomNumber() {
			// Given - RNG returns 4 (XKCD compliance)
			mockRandomNumberGeneratorResponse(4);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_13);

			// Then
			// Estimated: 6 days, Actual: 4 days
			// Factor: ((4 - 6) / 6) * 100 = -33.333...
			assertThat(response.getTheUnderestimationFactorAsAPercentage()).isCloseTo(-33.3, org.assertj.core.data.Offset.offset(0.1));
		}
	}

	@Nested
	class ManagementQuotesGeneration {

		@Test
		void shouldGenerateLowHangingFruitQuoteForSmallStories() {
			// Given
			mockRandomNumberGeneratorResponse(5);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_1);

			// Then
			assertThat(response.getTheOptimisticQuoteWeGiveToManagementDuringPlanning())
				.contains("low-hanging fruit");
		}

		@Test
		void shouldGenerateDoableQuoteForMediumStories() {
			// Given
			mockRandomNumberGeneratorResponse(12);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_5);

			// Then
			assertThat(response.getTheOptimisticQuoteWeGiveToManagementDuringPlanning())
				.contains("doable");
		}

		@Test
		void shouldGenerateCircleBackQuoteForLargeStories() {
			// Given
			mockRandomNumberGeneratorResponse(30);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_13);

			// Then
			assertThat(response.getTheOptimisticQuoteWeGiveToManagementDuringPlanning())
				.contains("circle back");
		}

		@Test
		void shouldGenerateParadigmShiftQuoteForEpicStories() {
			// Given
			mockRandomNumberGeneratorResponse(150);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_100);

			// Then
			assertThat(response.getTheOptimisticQuoteWeGiveToManagementDuringPlanning())
				.contains("paradigm-shifting");
		}
	}

	@Nested
	class ResponseFieldValidation {

		@Test
		void shouldIncludeTimestamp() {
			// Given
			mockRandomNumberGeneratorResponse(10);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_5);

			// Then
			assertThat(response.getTheTimestampWhenThisEstimateWasCreated()).isNotNull();
		}

		@Test
		void shouldIncludeRealityCheck() {
			// Given
			mockRandomNumberGeneratorResponse(12);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_8);

			// Then
			assertThat(response.getTheRealityCheckOfWhatActuallyHappens()).isNotBlank();
		}

		@Test
		void shouldIncludeExcuses() {
			// Given
			mockRandomNumberGeneratorResponse(15);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation response =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(FIBONACCI_8);

			// Then
			assertThat(response.getTheBlockersAndExcusesForWhyItTookLonger()).isNotBlank();
		}
	}

	// Helper method to mock random number generator response
	private void mockRandomNumberGeneratorResponse(Integer randomNumber) {
		RandomNumberResponseForEnterpriseValueCreation mockResponse =
			RandomNumberResponseForEnterpriseValueCreation.builder()
				.theActualRandomNumberThatWasGeneratedByOurEnterpriseSystem(randomNumber)
				.build();

		when(mockRandomNumberGenerator.doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
			anyString(), anyInt(), anyInt()))
			.thenReturn(mockResponse);
	}
}
