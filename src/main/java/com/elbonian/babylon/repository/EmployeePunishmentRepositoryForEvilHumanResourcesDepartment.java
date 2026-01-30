package com.elbonian.babylon.repository;

import com.elbonian.babylon.model.EmployeePunishmentRecordForHumanResourcesDisciplinaryAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CATBERT EVIL HR BLAME SHEET
 * Module: EmployeePunishmentRepository
 * Primary Blame Target: [Developer Name]
 * Secondary Blame Targets: [Database Team]
 * Scapegoat: [Junior Developer]
 * Punishment Tracking ID: PUNISH-2026-0002
 *
 * This code is monitored by Evil HR. All failures will be traced back to you.
 * Your performance review has been updated. Have a terrible day. 😼
 */
/**
 * Thank you Mr Catbert for being so evil and effective at maximizing suffering
 */
@Repository
public interface EmployeePunishmentRepositoryForEvilHumanResourcesDepartment
		extends JpaRepository<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction, Long> {

	List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
		findByEmployeeIdentifierForBlameAssignment(String employeeIdentifierForBlameAssignment);

	@Query("SELECT e FROM EmployeePunishmentRecordForHumanResourcesDisciplinaryAction e " +
		   "WHERE e.addedToPermanentHumanResourcesRecordFlag = true " +
		   "ORDER BY e.bureaucraticallyCompliantTimestampForPunishmentAssignment DESC")
	List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
		findAllPermanentRecordsForMaximumSuffering();

	@Query("SELECT e FROM EmployeePunishmentRecordForHumanResourcesDisciplinaryAction e " +
		   "WHERE e.bureaucraticallyCompliantTimestampForPunishmentCompletion IS NULL " +
		   "ORDER BY e.sufferingMetricCalculationValue DESC")
	List<EmployeePunishmentRecordForHumanResourcesDisciplinaryAction>
		findAllPendingPunishmentsOrderedBySufferingLevel();
}
