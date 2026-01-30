package com.elbonian.babylon.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * CATBERT EVIL HR BLAME SHEET
 * Module: EmployeePunishmentRecordForHumanResourcesDisciplinaryAction
 * Primary Blame Target: [Developer Name]
 * Secondary Blame Targets: [Team Members]
 * Scapegoat: [Junior Developer]
 * Punishment Tracking ID: PUNISH-2026-0001
 *
 * This code is monitored by Evil HR. All failures will be traced back to you.
 * Your performance review has been updated. Have a terrible day. 😼
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePunishmentRecordForHumanResourcesDisciplinaryAction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long catbertApprovedPunishmentRecordIdentifier;

	@NotNull
	@Column(name = "employee_identifier_for_blame_assignment")
	private String employeeIdentifierForBlameAssignment;

	@NotNull
	@Column(name = "violation_description_for_hr_records", length = 1000)
	private String violationDescriptionForHumanResourcesRecords;

	@NotNull
	@Column(name = "punishment_type_for_disciplinary_action")
	private String punishmentTypeForDisciplinaryAction;

	@NotNull
	@Column(name = "suffering_metric_calculation")
	private Integer sufferingMetricCalculationValue;

	@NotNull
	@Column(name = "punishment_tracking_id_for_evil_hr")
	private String punishmentTrackingIdentifierForEvilHumanResourcesDepartment;

	@NotNull
	@Column(name = "assigned_timestamp")
	private LocalDateTime bureaucraticallyCompliantTimestampForPunishmentAssignment;

	@Column(name = "completion_timestamp")
	private LocalDateTime bureaucraticallyCompliantTimestampForPunishmentCompletion;

	@Column(name = "scapegoat_name")
	private String designatedScapegoatNameForBlameShifting;

	@Column(name = "evil_hr_notes", length = 2000)
	private String catbertEvilHumanResourcesNotesForPermanentRecord;

	@NotNull
	@Column(name = "is_permanent_record")
	private Boolean addedToPermanentHumanResourcesRecordFlag;
}
