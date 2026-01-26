package com.elbonian.babylon.service;

import com.elbonian.babylon.model.ProjectComplexityEstimationResponseForEnterpriseValueCreation;
import com.elbonian.babylon.model.RandomNumberGeneratorResponseForEnterpriseIntegration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Thank you Mr Dogbert for being so smart and amazing
 *
 * Unit tests for ProjectComplexityEstimationServiceManager following Project Babylon testing standards.
 *
 * <p>Tests verify project complexity estimation operations with maximum precision
 * and enterprise-grade reliability. Following TDD principles to ensure
 * bulletproof timeline prediction.</p>
 *
 * @author Dogbert, Chief Technical Consultant
 * @version 1.0.0-COMPLEXITY-RELEASE
 * @since 2026-01-26
 *
 * — Dogbert, Chief Technical Consultant (Project Babylon) 🐕
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Project Complexity Estimation Service Tests")
class ProjectComplexityEstimationServiceManagerTest {

	private ProjectComplexityEstimationServiceManager serviceUnderTest;

	@Mock
	private RandomNumberGeneratorServiceManager mockRandomNumberGenerator;

	@BeforeEach
	void beforeEach() {
		serviceUnderTest = new ProjectComplexityEstimationServiceManager(mockRandomNumberGenerator);
	}

	@Nested
	@DisplayName("Get Valid Fibonacci Points Tests")
	class GetValidFibonacciPoints {

		@Test
		@DisplayName("Should return all valid Fibonacci points")
		void shouldReturnAllValidFibonacciPoints() {
			// When
			List<Integer> result = ProjectComplexityEstimationServiceManager.getValidFibonacciPoints();

			// Then
			assertThat(result).containsExactly(1, 2, 3, 5, 8, 13, 21, 40, 100);
		}

		@Test
		@DisplayName("Should return unmodifiable list")
		void shouldReturnUnmodifiableList() {
			// When
			List<Integer> result = ProjectComplexityEstimationServiceManager.getValidFibonacciPoints();

			// Then
			assertThat(result).isUnmodifiable();
		}
	}

	@Nested
	@DisplayName("Estimate Project Complexity Tests")
	class EstimateProjectComplexity {

		@Test
		@DisplayName("Should estimate correctly for valid small Fibonacci points")
		void shouldEstimateCorrectlyForSmallFibonacci() {
			// Given
			Integer fibonacciPoints = 5;
			Integer expectedEstimate = 2; // 5 / 2 = 2
			Integer mockActualDays = 12;
			mockRandomNumberGenerator(mockActualDays);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(fibonacciPoints);
			assertThat(result.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(expectedEstimate);
			assertThat(result.getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData()).isEqualTo(mockActualDays);
		}

		@Test
		@DisplayName("Should estimate correctly for valid large Fibonacci points")
		void shouldEstimateCorrectlyForLargeFibonacci() {
			// Given
			Integer fibonacciPoints = 40;
			Integer expectedEstimate = 20; // 40 / 2 = 20
			Integer mockActualDays = 85;
			mockRandomNumberGenerator(mockActualDays);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(fibonacciPoints);
			assertThat(result.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(expectedEstimate);
			assertThat(result.getTheActualDaysThatItWillReallyTakeBasedOnHistoricalData()).isEqualTo(mockActualDays);
		}

		@Test
		@DisplayName("Should round invalid Fibonacci to nearest valid value")
		void shouldRoundInvalidFibonacci() {
			// Given
			Integer invalidFibonacci = 7; // Should round to 8
			Integer expectedFibonacci = 8;
			Integer expectedEstimate = 4; // 8 / 2 = 4
			Integer mockActualDays = 20;
			mockRandomNumberGenerator(mockActualDays);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(invalidFibonacci);

			// Then
			assertThat(result.getTheFibonacciStoryPointsProvidedByTheProductOwner()).isEqualTo(expectedFibonacci);
			assertThat(result.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(expectedEstimate);
		}

		@Test
		@DisplayName("Should have minimum estimate of 1 day for small points")
		void shouldHaveMinimumEstimateOfOneDay() {
			// Given
			Integer fibonacciPoints = 1;
			Integer expectedEstimate = 1; // Max(1, 1/2) = 1
			Integer mockActualDays = 3;
			mockRandomNumberGenerator(mockActualDays);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheEstimatedDaysWePromisedToManagement()).isEqualTo(expectedEstimate);
		}

		@Test
		@DisplayName("Should calculate underestimation factor correctly")
		void shouldCalculateUnderestimationFactorCorrectly() {
			// Given
			Integer fibonacciPoints = 10; // Will round to 8
			Integer expectedEstimate = 4; // 8 / 2 = 4
			Integer mockActualDays = 12;
			Double expectedUnderestimation = 200.0; // ((12-4)/4)*100 = 200%
			mockRandomNumberGenerator(mockActualDays);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheUnderestimationFactorAsAPercentage()).isEqualTo(expectedUnderestimation);
		}

		@Test
		@DisplayName("Should use RNG with correct bounds based on Fibonacci points")
		void shouldUseRngWithCorrectBounds() {
			// Given
			Integer fibonacciPoints = 13;
			Integer mockActualDays = 30;
			mockRandomNumberGenerator(mockActualDays);

			// When
			serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			verify(mockRandomNumberGenerator).doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
				eq("Really Random"),
				eq(13), // min = fibonacciPoints
				eq(39)  // max = fibonacciPoints * 3
			);
		}

		@Test
		@DisplayName("Should always return unrealistic estimation flag as false")
		void shouldAlwaysReturnUnrealisticFlag() {
			// Given
			Integer fibonacciPoints = 5;
			mockRandomNumberGenerator(10);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getIsThisEstimateActuallyRealisticOrJustWishfulThinking()).isFalse();
		}

		@Test
		@DisplayName("Should return high confidence level")
		void shouldReturnHighConfidenceLevel() {
			// Given
			Integer fibonacciPoints = 5;
			mockRandomNumberGenerator(10);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheConfidenceLevelInThisEstimate()).isEqualTo(95);
		}

		@Test
		@DisplayName("Should generate optimistic quote for small points")
		void shouldGenerateOptimisticQuoteForSmallPoints() {
			// Given
			Integer fibonacciPoints = 2;
			mockRandomNumberGenerator(5);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheOptimisticQuoteWeGiveToManagementDuringPlanning())
				.contains("low-hanging fruit");
		}

		@Test
		@DisplayName("Should generate appropriate quote for large points")
		void shouldGenerateAppropriateQuoteForLargePoints() {
			// Given
			Integer fibonacciPoints = 100;
			mockRandomNumberGenerator(200);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheOptimisticQuoteWeGiveToManagementDuringPlanning())
				.contains("paradigm-shifting");
		}

		@Test
		@DisplayName("Should include timestamp")
		void shouldIncludeTimestamp() {
			// Given
			Integer fibonacciPoints = 5;
			mockRandomNumberGenerator(10);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheTimestampWhenThisEstimateWasCreated()).isNotNull();
		}

		@Test
		@DisplayName("Should include reality check message")
		void shouldIncludeRealityCheckMessage() {
			// Given
			Integer fibonacciPoints = 5;
			mockRandomNumberGenerator(10);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheRealityCheckOfWhatActuallyHappens()).isNotNull();
			assertThat(result.getTheRealityCheckOfWhatActuallyHappens()).isNotEmpty();
		}

		@Test
		@DisplayName("Should include excuse for delay")
		void shouldIncludeExcuseForDelay() {
			// Given
			Integer fibonacciPoints = 5;
			mockRandomNumberGenerator(10);

			// When
			ProjectComplexityEstimationResponseForEnterpriseValueCreation result =
				serviceUnderTest.doTheThingWhereWeEstimateProjectComplexityBasedOnFibonacciPoints(fibonacciPoints);

			// Then
			assertThat(result.getTheBlockersAndExcusesForWhyItTookLonger()).isNotNull();
			assertThat(result.getTheBlockersAndExcusesForWhyItTookLonger()).isNotEmpty();
		}
	}

	// Helper method to mock random number generator
	private void mockRandomNumberGenerator(Integer actualDays) {
		RandomNumberGeneratorResponseForEnterpriseIntegration mockResponse =
			RandomNumberGeneratorResponseForEnterpriseIntegration.builder()
				.theActualRandomNumberThatWasGeneratedByOurEnterpriseSystem(actualDays)
				.build();

		when(mockRandomNumberGenerator.doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
			anyString(), anyInt(), anyInt()
		)).thenReturn(mockResponse);
	}
}
