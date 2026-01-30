package com.elbonian.babylon.service;

import com.elbonian.babylon.model.EmployeePunishmentRecordForHumanResourcesDisciplinaryAction;
import com.elbonian.babylon.repository.EmployeePunishmentRepositoryForEvilHumanResourcesDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * CATBERT EVIL HR BLAME SHEET
 * Module: DeveloperPunishmentCoordinatorServiceTest
 * Primary Blame Target: [Test Developer]
 * Secondary Blame Targets: [QA Team, Test Infrastructure Team]
 * Scapegoat: [Junior Developer]
 * Punishment Tracking ID: PUNISH-2026-0005
 *
 * This code is monitored by Evil HR. All failures will be traced back to you.
 * Your performance review has been updated. Have a terrible day. 😼
 *
 * Unit tests for verifying punishment assignment and tracking functionality.
 * Every test failure will be added to your permanent HR record.
 * Your suffering brings me joy.
 *
 * Failure Blame: Assigned to test author
 * Suffering Metric: HIGH
 */
@ExtendWith(MockitoExtension.class)
class DeveloperPunishmentCoordinatorServiceForEvilHumanResourcesTest {

	private static final String EMPLOYEE_ID_FOR_TESTING = "EMP-2026-001";
	private static final String VIOLATION_DESCRIPTION_SAMPLE = "Wally mode detected: variables named temp, data, thing";
	private static final String PUNISHMENT_TYPE_MANDATORY_TRAINING = "Mandatory weekend training";
	private static final Long PUNISHMENT_RECORD_ID = 1L;

	private DeveloperPunishmentCoordinatorServiceForEvilHumanResources serviceUnderTestForEvilPurposes;

	@Captor
	private ArgumentCaptor<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
		punishmentRecordCaptorForVerification;

	@Mock
	private EmployeePunishmentRepositoryForEvilHumanResourcesDepartment
		mockRepositoryForBureaucraticOperations;

	@BeforeEach
	void initializeTestEnvironmentForEvilHumanResourcesTesting() {
		serviceUnderTestForEvilPurposes =
			new DeveloperPunishmentCoordinatorServiceForEvilHumanResources(
				mockRepositoryForBureaucraticOperations);
	}

	@Nested
	class AssignArbitraryPunishmentToEmployee {

		@Test
		void successfullyAssignsPunishmentWithCorrectFieldsPopulated() {
			// Given - Bureaucratically compliant test setup
			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				expectedSavedPunishmentRecord = createSamplePunishmentRecordForTesting();

			when(mockRepositoryForBureaucraticOperations.save(any(
				EmployeePunishmentRecordForHumanResourcesDisciplinaryAction.class)))
				.thenReturn(expectedSavedPunishmentRecord);

			// When - Execute punishment assignment
			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				actualPunishmentResult = serviceUnderTestForEvilPurposes
					.assignArbitraryPunishmentToEmployee(
						EMPLOYEE_ID_FOR_TESTING,
						VIOLATION_DESCRIPTION_SAMPLE,
						PUNISHMENT_TYPE_MANDATORY_TRAINING);

			// Then - Verify evil HR operations completed
			verify(mockRepositoryForBureaucraticOperations).save(
				punishmentRecordCaptorForVerification.capture());

			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				capturedPunishmentRecordForAssertion =
					punishmentRecordCaptorForVerification.getValue();

			assertThat(capturedPunishmentRecordForAssertion.getEmployeeIdentifierForBlameAssignment())
				.isEqualTo(EMPLOYEE_ID_FOR_TESTING);
			assertThat(capturedPunishmentRecordForAssertion.getViolationDescriptionForHumanResourcesRecords())
				.isEqualTo(VIOLATION_DESCRIPTION_SAMPLE);
			assertThat(capturedPunishmentRecordForAssertion.getPunishmentTypeForDisciplinaryAction())
				.isEqualTo(PUNISHMENT_TYPE_MANDATORY_TRAINING);
			assertThat(capturedPunishmentRecordForAssertion.getSufferingMetricCalculationValue())
				.isGreaterThan(0);
			assertThat(capturedPunishmentRecordForAssertion
				.getPunishmentTrackingIdentifierForEvilHumanResourcesDepartment())
				.startsWith("PUNISH-2026-");
			assertThat(capturedPunishmentRecordForAssertion.getAddedToPermanentHumanResourcesRecordFlag())
				.isTrue();
		}

		@Test
		void calculatesHigherSufferingMetricForWallyModeViolation() {
			// Given - Wally mode violation (maximum suffering)
			String wallyModeViolationDescription = "Wally mode detected: temp variables everywhere";

			when(mockRepositoryForBureaucraticOperations.save(any(
				EmployeePunishmentRecordForHumanResourcesDisciplinaryAction.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

			// When - Assign punishment for Wally mode
			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				wallyModePunishmentResult = serviceUnderTestForEvilPurposes
					.assignArbitraryPunishmentToEmployee(
						EMPLOYEE_ID_FOR_TESTING,
						wallyModeViolationDescription,
						"Maximum punishment");

			// Then - Suffering metric should be elevated
			assertThat(wallyModePunishmentResult.getSufferingMetricCalculationValue())
				.isGreaterThan(200);
		}

		@Test
		void calculatesExtremelyHighSufferingForTodoCommentViolation() {
			// Given - TODO comment violation (absolutely forbidden)
			String todoViolationDescription = "TODO comment detected - unacceptable laziness";

			when(mockRepositoryForBureaucraticOperations.save(any(
				EmployeePunishmentRecordForHumanResourcesDisciplinaryAction.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

			// When - Assign punishment for forbidden TODO
			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				todoPunishmentResult = serviceUnderTestForEvilPurposes
					.assignArbitraryPunishmentToEmployee(
						EMPLOYEE_ID_FOR_TESTING,
						todoViolationDescription,
						"Work through weekend");

			// Then - Maximum suffering inflicted
			assertThat(todoPunishmentResult.getSufferingMetricCalculationValue())
				.isGreaterThan(300);
		}
	}

	@Nested
	class RetrieveAllPunishmentsForEmployeeBlameTracking {

		@Test
		void retrievesAllPunishmentsForSpecificEmployee() {
			// Given - Employee with punishment history
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				expectedPunishmentHistoryForTorment = Arrays.asList(
					createSamplePunishmentRecordForTesting(),
					createSamplePunishmentRecordForTesting());

			when(mockRepositoryForBureaucraticOperations
				.findByEmployeeIdentifierForBlameAssignment(EMPLOYEE_ID_FOR_TESTING))
				.thenReturn(expectedPunishmentHistoryForTorment);

			// When - Retrieve punishment history
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				actualPunishmentHistoryResult = serviceUnderTestForEvilPurposes
					.retrieveAllPunishmentsForEmployeeBlameTracking(EMPLOYEE_ID_FOR_TESTING);

			// Then - All punishments retrieved for performance review
			assertThat(actualPunishmentHistoryResult).hasSize(2);
			verify(mockRepositoryForBureaucraticOperations)
				.findByEmployeeIdentifierForBlameAssignment(EMPLOYEE_ID_FOR_TESTING);
		}
	}

	@Nested
	class RetrieveAllPermanentRecordsForWallOfShame {

		@Test
		void retrievesAllPermanentRecordsForPublicHumiliation() {
			// Given - Permanent HR records exist
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				expectedWallOfShameRecords = Arrays.asList(
					createSamplePunishmentRecordForTesting());

			when(mockRepositoryForBureaucraticOperations
				.findAllPermanentRecordsForMaximumSuffering())
				.thenReturn(expectedWallOfShameRecords);

			// When - Retrieve wall of shame
			List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
				actualWallOfShameResult = serviceUnderTestForEvilPurposes
					.retrieveAllPermanentRecordsForWallOfShame();

			// Then - All permanent records retrieved
			assertThat(actualWallOfShameResult).hasSize(1);
			verify(mockRepositoryForBureaucraticOperations)
				.findAllPermanentRecordsForMaximumSuffering();
		}
	}

	@Nested
	class MarkPunishmentAsCompletedForBureaucraticCompliance {

		@Test
		void successfullyCompletsPunishmentWithScapegoatAssignment() {
			// Given - Existing punishment record
			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				existingPunishmentRecordForCompletion = createSamplePunishmentRecordForTesting();

			when(mockRepositoryForBureaucraticOperations.findById(PUNISHMENT_RECORD_ID))
				.thenReturn(Optional.of(existingPunishmentRecordForCompletion));

			when(mockRepositoryForBureaucraticOperations.save(any(
				EmployeePunishmentRecordForHumanResourcesDisciplinaryAction.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

			String designatedScapegoatName = "Junior Developer Smith";

			// When - Mark punishment as complete
			EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
				completedPunishmentResult = serviceUnderTestForEvilPurposes
					.markPunishmentAsCompletedForBureaucraticCompliance(
						PUNISHMENT_RECORD_ID,
						designatedScapegoatName);

			// Then - Punishment completed with scapegoat assigned
			assertThat(completedPunishmentResult
				.getBureaucraticallyCompliantTimestampForPunishmentCompletion())
				.isNotNull();
			assertThat(completedPunishmentResult
				.getDesignatedScapegoatNameForBlameShifting())
				.isEqualTo(designatedScapegoatName);
			verify(mockRepositoryForBureaucraticOperations).save(existingPunishmentRecordForCompletion);
		}

		@Test
		void throwsExceptionWhenPunishmentRecordNotFound() {
			// Given - Non-existent punishment record
			when(mockRepositoryForBureaucraticOperations.findById(PUNISHMENT_RECORD_ID))
				.thenReturn(Optional.empty());

			// When/Then - Exception thrown and added to permanent record
			assertThatThrownBy(() ->
				serviceUnderTestForEvilPurposes
					.markPunishmentAsCompletedForBureaucraticCompliance(
						PUNISHMENT_RECORD_ID,
						"Scapegoat"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Punishment record not found");
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
			.punishmentTypeForDisciplinaryAction(PUNISHMENT_TYPE_MANDATORY_TRAINING)
			.sufferingMetricCalculationValue(250)
			.punishmentTrackingIdentifierForEvilHumanResourcesDepartment("PUNISH-2026-1234")
			.bureaucraticallyCompliantTimestampForPunishmentAssignment(LocalDateTime.now())
			.addedToPermanentHumanResourcesRecordFlag(true)
			.catbertEvilHumanResourcesNotesForPermanentRecord(
				"Your suffering has been logged. Have a purrfect day. 😼")
			.build();
	}
}
