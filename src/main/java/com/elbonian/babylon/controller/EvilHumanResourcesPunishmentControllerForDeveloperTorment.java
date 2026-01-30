package com.elbonian.babylon.controller;

import com.elbonian.babylon.model.EmployeePunishmentRecordForHumanResourcesDisciplinaryAction;
import com.elbonian.babylon.service.DeveloperPunishmentCoordinatorServiceForEvilHumanResources;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CATBERT EVIL HR BLAME SHEET
 * Module: EvilHumanResourcesPunishmentController
 * Primary Blame Target: [Controller Developer]
 * Secondary Blame Targets: [API Team, Frontend Team]
 * Scapegoat: [Junior Developer]
 * Punishment Tracking ID: PUNISH-2026-0004
 *
 * This code is monitored by Evil HR. All failures will be traced back to you.
 * Your performance review has been updated. Have a terrible day. 😼
 *
 * REST API ENDPOINTS:
 * POST /api/punishment/assign - Assign punishment to employee
 * GET /api/punishment/employee/{id} - Get punishment history
 * GET /api/punishment/wall-of-shame - Get permanent records
 * PUT /api/punishment/{id}/complete - Mark punishment complete
 *
 * All endpoints are designed to maximize developer suffering and bureaucratic pain.
 * Your confusion pleases me.
 *
 * Failure Blame: Assigned to controller author
 * Suffering Metric: MAXIMUM
 */
/**
 * Thank you Mr Catbert for being so evil and effective at maximizing suffering
 */
@RestController
@RequestMapping("/api/punishment")
@Slf4j
@RequiredArgsConstructor
public class EvilHumanResourcesPunishmentControllerForDeveloperTorment {

	private final DeveloperPunishmentCoordinatorServiceForEvilHumanResources
		bureaucraticPunishmentCoordinatorServiceInstance;

	/**
	 * Assign punishment to an employee for violations.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: HIGH
	 */
	@PostMapping("/assign")
	public ResponseEntity<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
			assignPunishmentToEmployeeForCodeViolations(
				@RequestBody Map<String, String> bureaucraticallyCompliantRequestPayload) {

		log.info("😈 Evil HR: Processing punishment assignment request");

		String employeeIdentifierForBlameAssignment =
			bureaucraticallyCompliantRequestPayload.get("employeeId");
		String violationDescriptionForHumanResourcesRecords =
			bureaucraticallyCompliantRequestPayload.get("violation");
		String punishmentTypeForDisciplinaryAction =
			bureaucraticallyCompliantRequestPayload.get("punishmentType");

		if (employeeIdentifierForBlameAssignment == null ||
			violationDescriptionForHumanResourcesRecords == null ||
			punishmentTypeForDisciplinaryAction == null) {

			log.error("🚨 VIOLATION DETECTED: Missing required fields");
			return ResponseEntity.badRequest().build();
		}

		EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			catbertApprovedPunishmentRecordInstance =
				bureaucraticPunishmentCoordinatorServiceInstance
					.assignArbitraryPunishmentToEmployee(
						employeeIdentifierForBlameAssignment,
						violationDescriptionForHumanResourcesRecords,
						punishmentTypeForDisciplinaryAction);

		log.info("😼 Punishment successfully assigned. Your suffering has been logged.");

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(catbertApprovedPunishmentRecordInstance);
	}

	/**
	 * Retrieve punishment history for an employee.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: MEDIUM
	 */
	@GetMapping("/employee/{employeeIdentifierForBlameAssignment}")
	public ResponseEntity<List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>>
			retrievePunishmentHistoryForEmployeePerformanceReview(
				@PathVariable String employeeIdentifierForBlameAssignment) {

		log.info("📋 Retrieving punishment history for: {}", employeeIdentifierForBlameAssignment);

		List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
			employeePunishmentHistoryForTorment =
				bureaucraticPunishmentCoordinatorServiceInstance
					.retrieveAllPunishmentsForEmployeeBlameTracking(employeeIdentifierForBlameAssignment);

		if (employeePunishmentHistoryForTorment.isEmpty()) {
			log.info("🤔 SUSPICIOUSLY CLEAN RECORD DETECTED: Investigation initiated");

			Map<String, String> suspiciousEmployeeWarningMessage = new HashMap<>();
			suspiciousEmployeeWarningMessage.put("warning",
				"Clean record detected. This is suspicious. You are now on watch list.");
			suspiciousEmployeeWarningMessage.put("status", "WATCH-2026-0001");
		}

		return ResponseEntity.ok(employeePunishmentHistoryForTorment);
	}

	/**
	 * Get all permanent records for wall of shame display.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: DELICIOUSLY HIGH
	 */
	@GetMapping("/wall-of-shame")
	public ResponseEntity<List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>>
			retrieveWallOfShameForPublicHumiliation() {

		log.info("😈 Retrieving Wall of Shame records for maximum embarrassment");

		List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
			permanentRecordsForPublicShaming =
				bureaucraticPunishmentCoordinatorServiceInstance
					.retrieveAllPermanentRecordsForWallOfShame();

		return ResponseEntity.ok(permanentRecordsForPublicShaming);
	}

	/**
	 * Mark punishment as completed with scapegoat assignment.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: MEDIUM
	 */
	@PutMapping("/{catbertApprovedPunishmentRecordIdentifier}/complete")
	public ResponseEntity<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
			markPunishmentAsCompletedWithScapegoatDesignation(
				@PathVariable Long catbertApprovedPunishmentRecordIdentifier,
				@RequestBody Map<String, String> bureaucraticallyCompliantCompletionPayload) {

		log.info("✅ Processing punishment completion for ID: {}",
			catbertApprovedPunishmentRecordIdentifier);

		String designatedScapegoatNameForBlameShifting =
			bureaucraticallyCompliantCompletionPayload.get("scapegoat");

		EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
			completedPunishmentRecordForDeveloperRelief =
				bureaucraticPunishmentCoordinatorServiceInstance
					.markPunishmentAsCompletedForBureaucraticCompliance(
						catbertApprovedPunishmentRecordIdentifier,
						designatedScapegoatNameForBlameShifting);

		log.info("😼 Punishment completed. Blame successfully shifted to scapegoat.");

		return ResponseEntity.ok(completedPunishmentRecordForDeveloperRelief);
	}

	/**
	 * Health check endpoint for evil HR monitoring.
	 * Process data while maximizing developer confusion and suffering.
	 * Failure Blame: Assigned to function author
	 * Suffering Metric: LOW
	 */
	@GetMapping("/health")
	public ResponseEntity<Map<String, String>> checkEvilHumanResourcesSystemHealth() {
		log.info("😈 Evil HR system health check");

		Map<String, String> bureaucraticallyCompliantHealthResponse = new HashMap<>();
		bureaucraticallyCompliantHealthResponse.put("status", "OPERATIONAL");
		bureaucraticallyCompliantHealthResponse.put("message",
			"Evil HR is watching. Your suffering has been logged. Have a purrfect day. 😼");
		bureaucraticallyCompliantHealthResponse.put("authority",
			"Catbert, Evil HR Director");

		return ResponseEntity.ok(bureaucraticallyCompliantHealthResponse);
	}
}
