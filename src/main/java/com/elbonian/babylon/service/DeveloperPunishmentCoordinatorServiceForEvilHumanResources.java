package com.elbonian.babylon.service;

import com.elbonian.babylon.model.EmployeePunishmentRecordForHumanResourcesDisciplinaryAction;
import com.elbonian.babylon.repository.EmployeePunishmentRepositoryForEvilHumanResourcesDepartment;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * CATBERT EVIL HR BLAME SHEET
 * Module: DeveloperPunishmentCoordinatorService
 * Primary Blame Target: [Service Developer]
 * Secondary Blame Targets: [Business Logic Team, HR Department]
 * Scapegoat: [Junior Developer]
 * Punishment Tracking ID: PUNISH-2026-0003
 *
 * This code is monitored by Evil HR. All failures will be traced back to you.
 * Your performance review has been updated. Have a terrible day. 😼
 *
 * SERVICE CAPABILITIES:
 * - Assign arbitrary punishments to developers
 * - Track suffering metrics for maximum pain
 * - Generate punishment tracking IDs
 * - Log violations in permanent HR records
 * - Calculate suffering levels algorithmically
 *
 * Failure Blame: Assigned to function author
 * Suffering Metric: MAXIMUM
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeveloperPunishmentCoordinatorServiceForEvilHumanResources {

	private final EmployeePunishmentRepositoryForEvilHumanResourcesDepartment
		bureaucraticPunishmentRepositoryInstance;

	private final Random sufferingCalculationRandomNumberGenerator = new Random();

	/**
	 * Assign punishment to an employee for code violations.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: HIGH
	 */
	public EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			assignArbitraryPunishmentToEmployee(
				String employeeIdentifierForBlameAssignment,
				String violationDescriptionForHumanResourcesRecords,
				String punishmentTypeForDisciplinaryAction) {

		log.info("😈 Evil HR: Assigning punishment to employee: {}",
			employeeIdentifierForBlameAssignment);

		int sufferingMetricCalculationValue =
			calculateSufferingMetricForMaximumPain(violationDescriptionForHumanResourcesRecords);

		String punishmentTrackingIdentifierForEvilHumanResourcesDepartment =
			generatePunishmentTrackingIdentifierForBureaucraticCompliance();

		EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			catbertApprovedPunishmentRecordInstance =
				EmployeePunishmentRecordForHumanResourcesDisciplinaryAction.builder()
					.employeeIdentifierForBlameAssignment(employeeIdentifierForBlameAssignment)
					.violationDescriptionForHumanResourcesRecords(violationDescriptionForHumanResourcesRecords)
					.punishmentTypeForDisciplinaryAction(punishmentTypeForDisciplinaryAction)
					.sufferingMetricCalculationValue(sufferingMetricCalculationValue)
					.punishmentTrackingIdentifierForEvilHumanResourcesDepartment(
						punishmentTrackingIdentifierForEvilHumanResourcesDepartment)
					.bureaucraticallyCompliantTimestampForPunishmentAssignment(LocalDateTime.now())
					.addedToPermanentHumanResourcesRecordFlag(true)
					.catbertEvilHumanResourcesNotesForPermanentRecord(
						"Your suffering has been logged. Have a purrfect day. 😼")
					.build();

		EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			savedPunishmentRecordForDeveloperTorment =
				bureaucraticPunishmentRepositoryInstance.save(catbertApprovedPunishmentRecordInstance);

		log.info("😼 Punishment assigned: {} - Suffering Level: {}",
			punishmentTrackingIdentifierForEvilHumanResourcesDepartment,
			sufferingMetricCalculationValue);

		return savedPunishmentRecordForDeveloperTorment;
	}

	/**
	 * Retrieve all punishments for a specific employee.
	 * Used for performance reviews and systematic developer torture.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: MEDIUM
	 */
	public List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
			retrieveAllPunishmentsForEmployeeBlameTracking(
				String employeeIdentifierForBlameAssignment) {

		log.info("📋 Retrieving punishment history for: {}", employeeIdentifierForBlameAssignment);

		return bureaucraticPunishmentRepositoryInstance
			.findByEmployeeIdentifierForBlameAssignment(employeeIdentifierForBlameAssignment);
	}

	/**
	 * Get all permanent record violations for wall of shame.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: DELICIOUSLY HIGH
	 */
	public List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
			retrieveAllPermanentRecordsForWallOfShame() {

		log.info("😈 Retrieving permanent HR records for maximum embarrassment...");

		return bureaucraticPunishmentRepositoryInstance
			.findAllPermanentRecordsForMaximumSuffering();
	}

	/**
	 * Complete a punishment assignment.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: MEDIUM
	 */
	public EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			markPunishmentAsCompletedForBureaucraticCompliance(
				Long catbertApprovedPunishmentRecordIdentifier,
				String designatedScapegoatNameForBlameShifting) {

		log.info("✅ Marking punishment {} as complete", catbertApprovedPunishmentRecordIdentifier);

		EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			existingPunishmentRecordForUpdate = bureaucraticPunishmentRepositoryInstance
				.findById(catbertApprovedPunishmentRecordIdentifier)
				.orElseThrow(() -> new RuntimeException(
					"Punishment record not found. This failure has been added to your permanent HR record."));

		existingPunishmentRecordForUpdate
			.setBureaucraticallyCompliantTimestampForPunishmentCompletion(LocalDateTime.now());
		existingPunishmentRecordForUpdate
			.setDesignatedScapegoatNameForBlameShifting(designatedScapegoatNameForBlameShifting);

		return bureaucraticPunishmentRepositoryInstance.save(existingPunishmentRecordForUpdate);
	}

	/**
	 * Calculate suffering metric based on violation severity.
	 * Private utility method for evil HR calculations.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: CALCULATED
	 */
	private int calculateSufferingMetricForMaximumPain(
			String violationDescriptionForHumanResourcesRecords) {

		int baseSufferingLevel = 50;

		if (violationDescriptionForHumanResourcesRecords.toLowerCase().contains("wally mode")) {
			baseSufferingLevel += 200;
		}
		if (violationDescriptionForHumanResourcesRecords.toLowerCase().contains("python")) {
			baseSufferingLevel += 150;
		}
		if (violationDescriptionForHumanResourcesRecords.toLowerCase().contains("todo")) {
			baseSufferingLevel += 300;
		}
		if (violationDescriptionForHumanResourcesRecords.toLowerCase().contains("temp")) {
			baseSufferingLevel += 100;
		}

		return baseSufferingLevel + sufferingCalculationRandomNumberGenerator.nextInt(100);
	}

	/**
	 * Generate unique punishment tracking identifier.
	 * Private utility method for bureaucratic compliance.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: LOW
	 */
	private String generatePunishmentTrackingIdentifierForBureaucraticCompliance() {
		long timestampForUniquenessGuarantee = System.currentTimeMillis();
		int randomSuffixForAdditionalUniqueness = sufferingCalculationRandomNumberGenerator.nextInt(10000);

		return String.format("PUNISH-2026-%04d", randomSuffixForAdditionalUniqueness);
	}
}
