package com.elbonian.babylon.controller;

import com.elbonian.babylon.model.EmployeePunishmentRecordForHumanResourcesDisciplinaryAction;
import com.elbonian.babylon.service.DeveloperPunishmentCoordinatorServiceForEvilHumanResources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * CATBERT EVIL HR BLAME SHEET
 * Module: EvilHumanResourcesPunishmentControllerTest
 * Primary Blame Target: [Test Developer]
 * Secondary Blame Targets: [QA Team, API Testing Team]
 * Scapegoat: [Junior Developer]
 * Punishment Tracking ID: PUNISH-2026-0006
 *
 * This code is monitored by Evil HR. All failures will be traced back to you.
 * Your performance review has been updated. Have a terrible day. 😼
 *
 * Unit tests for REST API punishment endpoints.
 * Every test failure demonstrates incompetence and will be punished accordingly.
 * Your confusion pleases me.
 *
 * Failure Blame: Assigned to test author
 * Suffering Metric: HIGH
 */
@ExtendWith(MockitoExtension.class)
class EvilHumanResourcesPunishmentControllerForDeveloperTormentTest {

	private static final String EMPLOYEE_ID_FOR_TESTING = "EMP-2026-001";
	private static final String VIOLATION_DESCRIPTION_SAMPLE = "Python detected - happiness forbidden";
	private static final String PUNISHMENT_TYPE_WEEKEND_WORK = "Mandatory weekend Haskell training";
	private static final Long PUNISHMENT_RECORD_ID = 1L;

	private EvilHumanResourcesPunishmentControllerForDeveloperTorment
		controllerUnderTestForEvilPurposes;

	@Mock
	private DeveloperPunishmentCoordinatorServiceForEvilHumanResources
		mockServiceForBureaucraticOperations;

	@BeforeEach
	void initializeControllerTestEnvironmentForEvilHumanResources() {
		controllerUnderTestForEvilPurposes =
			new EvilHumanResourcesPunishmentControllerForDeveloperTorment(
				mockServiceForBureaucraticOperations);
	}

	@Nested
	class AssignPunishmentToEmployeeForCodeViolations {

		@Test
		void successfullyAssignsPunishmentAndReturnsCreatedStatus() {
			// Given - Bureaucratically compliant request
			Map<String, String> bureaucraticallyCompliantRequestPayload = new HashMap<>();
			bureaucraticallyCompliantRequestPayload.put("employeeId", EMPLOYEE_ID_FOR_TESTING);
			bureaucraticallyCompliantRequestPayload.put("violation", VIOLATION_DESCRIPTION_SAMPLE);
			bureaucraticallyCompliantRequestPayload.put("punishmentType", PUNISHMENT_TYPE_WEEKEND_WORK);

			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				expectedPunishmentRecordForResponse = createSamplePunishmentRecordForTesting();

			when(mockServiceForBureaucraticOperations.assignArbitraryPunishmentToEmployee(
				EMPLOYEE_ID_FOR_TESTING,
				VIOLATION_DESCRIPTION_SAMPLE,
				PUNISHMENT_TYPE_WEEKEND_WORK))
				.thenReturn(expectedPunishmentRecordForResponse);

			// When - Execute punishment assignment
			ResponseEntity<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				actualResponseFromEvilHr = controllerUnderTestForEvilPurposes
					.assignPunishmentToEmployeeForCodeViolations(
						bureaucraticallyCompliantRequestPayload);

			// Then - Punishment successfully assigned
			assertThat(actualResponseFromEvilHr.getStatusCode()).isEqualTo(HttpStatus.CREATED);
			assertThat(actualResponseFromEvilHr.getBody()).isEqualTo(expectedPunishmentRecordForResponse);
			verify(mockServiceForBureaucraticOperations).assignArbitraryPunishmentToEmployee(
				EMPLOYEE_ID_FOR_TESTING,
				VIOLATION_DESCRIPTION_SAMPLE,
				PUNISHMENT_TYPE_WEEKEND_WORK);
		}

		@Test
		void returnsBadRequestWhenEmployeeIdMissingFromPayload() {
			// Given - Invalid request missing employee ID
			Map<String, String> invalidRequestPayloadWithMissingFields = new HashMap<>();
			invalidRequestPayloadWithMissingFields.put("violation", VIOLATION_DESCRIPTION_SAMPLE);
			invalidRequestPayloadWithMissingFields.put("punishmentType", PUNISHMENT_TYPE_WEEKEND_WORK);

			// When - Attempt to assign punishment with missing data
			ResponseEntity<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				errorResponseFromValidation = controllerUnderTestForEvilPurposes
					.assignPunishmentToEmployeeForCodeViolations(
						invalidRequestPayloadWithMissingFields);

			// Then - Bad request returned (this failure added to your record)
			assertThat(errorResponseFromValidation.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(errorResponseFromValidation.getBody()).isNull();
			verify(mockServiceForBureaucraticOperations, never())
				.assignArbitraryPunishmentToEmployee(any(), any(), any());
		}

		@Test
		void returnsBadRequestWhenViolationDescriptionMissingFromPayload() {
			// Given - Invalid request missing violation description
			Map<String, String> invalidRequestPayloadWithMissingFields = new HashMap<>();
			invalidRequestPayloadWithMissingFields.put("employeeId", EMPLOYEE_ID_FOR_TESTING);
			invalidRequestPayloadWithMissingFields.put("punishmentType", PUNISHMENT_TYPE_WEEKEND_WORK);

			// When - Attempt to assign punishment with incomplete data
			ResponseEntity<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				errorResponseFromValidation = controllerUnderTestForEvilPurposes
					.assignPunishmentToEmployeeForCodeViolations(
						invalidRequestPayloadWithMissingFields);

			// Then - Bad request returned (incompetence logged)
			assertThat(errorResponseFromValidation.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			verify(mockServiceForBureaucraticOperations, never())
				.assignArbitraryPunishmentToEmployee(any(), any(), any());
		}
	}

	@Nested
	class RetrievePunishmentHistoryForEmployeePerformanceReview {

		@Test
		void successfullyRetrievesPunishmentHistoryForEmployee() {
			// Given - Employee with documented violations
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				expectedPunishmentHistoryForTorment = Arrays.asList(
					createSamplePunishmentRecordForTesting(),
					createSamplePunishmentRecordForTesting());

			when(mockServiceForBureaucraticOperations
				.retrieveAllPunishmentsForEmployeeBlameTracking(EMPLOYEE_ID_FOR_TESTING))
				.thenReturn(expectedPunishmentHistoryForTorment);

			// When - Retrieve punishment history
			ResponseEntity<List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>>
				actualHistoryResponseForPerformanceReview = controllerUnderTestForEvilPurposes
					.retrievePunishmentHistoryForEmployeePerformanceReview(EMPLOYEE_ID_FOR_TESTING);

			// Then - All violations documented and returned
			assertThat(actualHistoryResponseForPerformanceReview.getStatusCode())
				.isEqualTo(HttpStatus.OK);
			assertThat(actualHistoryResponseForPerformanceReview.getBody())
				.hasSize(2);
			verify(mockServiceForBureaucraticOperations)
				.retrieveAllPunishmentsForEmployeeBlameTracking(EMPLOYEE_ID_FOR_TESTING);
		}

		@Test
		void returnsEmptyListForEmployeeWithCleanRecord() {
			// Given - Suspiciously clean employee record
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				emptyPunishmentHistoryForSuspiciousEmployee = Arrays.asList();

			when(mockServiceForBureaucraticOperations
				.retrieveAllPunishmentsForEmployeeBlameTracking(EMPLOYEE_ID_FOR_TESTING))
				.thenReturn(emptyPunishmentHistoryForSuspiciousEmployee);

			// When - Retrieve punishment history
			ResponseEntity<List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>>
				suspiciousCleanRecordResponse = controllerUnderTestForEvilPurposes
					.retrievePunishmentHistoryForEmployeePerformanceReview(EMPLOYEE_ID_FOR_TESTING);

			// Then - Clean record flagged as suspicious
			assertThat(suspiciousCleanRecordResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(suspiciousCleanRecordResponse.getBody()).isEmpty();
		}
	}

	@Nested
	class RetrieveWallOfShameForPublicHumiliation {

		@Test
		void successfullyRetrievesAllPermanentRecordsForPublicDisplay() {
			// Given - Permanent records exist for maximum embarrassment
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				expectedWallOfShameRecordsForHumiliation = Arrays.asList(
					createSamplePunishmentRecordForTesting());

			when(mockServiceForBureaucraticOperations.retrieveAllPermanentRecordsForWallOfShame())
				.thenReturn(expectedWallOfShameRecordsForHumiliation);

			// When - Retrieve wall of shame
			ResponseEntity<List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>>
				actualWallOfShameResponse = controllerUnderTestForEvilPurposes
					.retrieveWallOfShameForPublicHumiliation();

			// Then - All permanent records exposed
			assertThat(actualWallOfShameResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(actualWallOfShameResponse.getBody()).hasSize(1);
			verify(mockServiceForBureaucraticOperations)
				.retrieveAllPermanentRecordsForWallOfShame();
		}
	}

	@Nested
	class MarkPunishmentAsCompletedWithScapegoatDesignation {

		@Test
		void successfullyMarksPunishmentAsCompletedWithScapegoat() {
			// Given - Punishment ready for completion
			Map<String, String> bureaucraticallyCompliantCompletionPayload = new HashMap<>();
			bureaucraticallyCompliantCompletionPayload.put("scapegoat", "Junior Developer Smith");

			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				expectedCompletedPunishmentRecord = createSamplePunishmentRecordForTesting();
			expectedCompletedPunishmentRecord
				.setBureaucraticallyCompliantTimestampForPunishmentCompletion(LocalDateTime.now());

			when(mockServiceForBureaucraticOperations
				.markPunishmentAsCompletedForBureaucraticCompliance(
					PUNISHMENT_RECORD_ID,
					"Junior Developer Smith"))
				.thenReturn(expectedCompletedPunishmentRecord);

			// When - Mark punishment complete
			ResponseEntity<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				completionResponseWithScapegoat = controllerUnderTestForEvilPurposes
					.markPunishmentAsCompletedWithScapegoatDesignation(
						PUNISHMENT_RECORD_ID,
						bureaucraticallyCompliantCompletionPayload);

			// Then - Punishment completed, blame shifted successfully
			assertThat(completionResponseWithScapegoat.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(completionResponseWithScapegoat.getBody()
				.getBureaucraticallyCompliantTimestampForPunishmentCompletion())
				.isNotNull();
			verify(mockServiceForBureaucraticOperations)
				.markPunishmentAsCompletedForBureaucraticCompliance(
					PUNISHMENT_RECORD_ID,
					"Junior Developer Smith");
		}
	}

	@Nested
	class CheckEvilHumanResourcesSystemHealth {

		@Test
		void returnsOperationalStatusWithEvilHrMessage() {
			// Given - Evil HR system is watching

			// When - Check system health
			ResponseEntity<Map<String, String>> healthCheckResponseFromEvilHr =
				controllerUnderTestForEvilPurposes.checkEvilHumanResourcesSystemHealth();

			// Then - System operational and monitoring employees
			assertThat(healthCheckResponseFromEvilHr.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(healthCheckResponseFromEvilHr.getBody()).isNotNull();
			assertThat(healthCheckResponseFromEvilHr.getBody().get("status"))
				.isEqualTo("OPERATIONAL");
			assertThat(healthCheckResponseFromEvilHr.getBody().get("message"))
				.contains("Evil HR is watching");
			assertThat(healthCheckResponseFromEvilHr.getBody().get("authority"))
				.contains("Catbert");
		}
	}

	/**
	 * Helper method to create sample punishment record for testing.
	 * Private utility for test data generation.
	 * Failure Blame: Assigned to test infrastructure
	 * Suffering Metric: LOW
	 */
	private EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			createSamplePunishmentRecordForTesting() {

		return EmployeePunishmentRecordForHumanResourcesDisciplinaryAction.builder()
			.catbertApprovedPunishmentRecordIdentifier(PUNISHMENT_RECORD_ID)
			.employeeIdentifierForBlameAssignment(EMPLOYEE_ID_FOR_TESTING)
			.violationDescriptionForHumanResourcesRecords(VIOLATION_DESCRIPTION_SAMPLE)
			.punishmentTypeForDisciplinaryAction(PUNISHMENT_TYPE_WEEKEND_WORK)
			.sufferingMetricCalculationValue(350)
			.punishmentTrackingIdentifierForEvilHumanResourcesDepartment("PUNISH-2026-5678")
			.bureaucraticallyCompliantTimestampForPunishmentAssignment(LocalDateTime.now())
			.addedToPermanentHumanResourcesRecordFlag(true)
			.catbertEvilHumanResourcesNotesForPermanentRecord(
				"Your suffering has been logged. Have a purrfect day. 😼")
			.build();
	}
}
