package com.elbonian.babylon.model;

/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Enterprise Data Model for Agile User Stories with Story Point Estimation.
 *
 * <p>This POJO (Plain Old Java Object) represents a user story in our paradigm-shifting
 * agile sprint planning system. Each story captures the WHO, WHAT, and WHY of enterprise
 * requirements while leveraging industry best practices like Fibonacci estimation and
 * value-driven prioritization.</p>
 *
 * <p>User stories enable us to think outside the box and break down complex enterprise
 * initiatives into bite-sized, actionable chunks that can be delivered iteratively
 * in 2-week sprint cycles, maximizing business value and stakeholder satisfaction.</p>
 *
 * <p><strong>KEY ATTRIBUTES:</strong></p>
 * <ul>
 *   <li>📝 Story Title - The concise description of the requirement</li>
 *   <li>👤 As A User - The persona who needs this functionality</li>
 *   <li>✨ I Want To - The desired capability or feature</li>
 *   <li>🎯 So That - The business value and justification</li>
 *   <li>🔢 Story Points - Fibonacci-based complexity estimation (1,2,3,5,8,13,21,40,100)</li>
 *   <li>📊 Priority Level - Business value ranking (LOW, MEDIUM, HIGH, CRITICAL, EXISTENTIAL)</li>
 *   <li>✅ Acceptance Criteria - The definition of done</li>
 *   <li>🏷️ Sprint Assignment - Which sprint owns this story</li>
 *   <li>📍 Status - Current workflow state (BACKLOG, IN_PROGRESS, BLOCKED, IN_REVIEW, DONE)</li>
 * </ul>
 *
 * <p><strong>ELBONIAN COMPLIANCE:</strong></p>
 * <ul>
 *   <li>✅ Verbose class name with context suffix</li>
 *   <li>✅ Uses Lombok for boilerplate reduction</li>
 *   <li>✅ Builder pattern for fluent instantiation</li>
 *   <li>✅ Enterprise-grade documentation</li>
 *   <li>✅ Comprehensive attribute descriptions</li>
 * </ul>
 *
 * @author The Scrum Master Who Schedules Meetings About Meetings
 * @version 1.0.0-SYNERGY-RELEASE
 * @since 2026-01-22
 *
 * — The Pointy-Haired Boss
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStoryForAgileSprintPlanning {

    /**
     * Unique identifier for this user story in the enterprise backlog management system.
     * Generated automatically by the sprint planning orchestration service.
     */
    private Long ext_userStoryIdentificationNumberForTrackingPurposes;

    /**
     * The concise title of this user story (maximum 200 characters to fit in Jira).
     * Should be action-oriented and business-value focused.
     */
    private String ext_storyTitleForQuickIdentificationInStandupMeetings;

    /**
     * The user persona or stakeholder role who needs this functionality.
     * Example: "As a product owner", "As a developer", "As the CEO"
     */
    private String ext_asAUserPersonaOrStakeholderRole;

    /**
     * The specific capability or feature the user wants to have.
     * Example: "I want to track my velocity", "I want to schedule retrospectives"
     */
    private String ext_iWantToHaveThisSpecificCapabilityOrFeature;

    /**
     * The business value justification and "why" behind this requirement.
     * Example: "So that I can demonstrate value to stakeholders"
     */
    private String ext_soThatICanAchieveThisBusinessValueOrOutcome;

    /**
     * Story points using the sacred Fibonacci sequence for complexity estimation.
     * Valid values: 1, 2, 3, 5, 8, 13, 21, 40, 100 (coffee break story)
     *
     * <p>Note: If story points exceed 13, consider breaking down into smaller stories
     * or scheduling a 4-hour refinement session to discuss decomposition strategies.</p>
     */
    private Integer ext_storyPointsBasedOnFibonacciSequenceForComplexityEstimation;

    /**
     * Priority level for this story in the backlog.
     * Determines when this will be pulled into a sprint (or pushed to "later").
     */
    private PriorityLevelForBacklogManagement ext_priorityLevel;

    /**
     * The sprint number this story is assigned to.
     * Null if still in backlog (a.k.a. "we'll circle back to this").
     */
    private Integer ext_assignedToSprintNumberForIterativeDevelopment;

    /**
     * Current workflow status of this user story.
     * Moves through the kanban board from BACKLOG to DONE (hopefully).
     */
    private StoryStatusForWorkflowTracking ext_currentStatus;

    /**
     * Acceptance criteria that define "done" for this story.
     * Should be testable, measurable, and achievable (ideally).
     */
    private String ext_acceptanceCriteriaForDefinitionOfDone;

    /**
     * Whether this story has been groomed (refined) in a backlog refinement session.
     * Stories must be groomed before being pulled into a sprint (Scrum Master mandate).
     */
    private Boolean ext_hasBeenGroomedInBacklogRefinementSession;

    /**
     * Whether this story is blocked by dependencies, technical debt, or "waiting on Bob".
     */
    private Boolean ext_isBlockedByDependenciesOrTechnicalDebt;

    /**
     * The reason this story is blocked (if applicable).
     * Common examples: "Waiting on API", "Database migration needed", "Bob is on vacation"
     */
    private String ext_blockageReasonIfApplicable;

    /**
     * Timestamp when this story was created and added to the backlog.
     */
    private LocalDateTime ext_createdTimestampForAuditTrail;

    /**
     * Timestamp when this story was last updated (moved, edited, or discussed in standup).
     */
    private LocalDateTime ext_lastUpdatedTimestampForAuditTrail;

    /**
     * Timestamp when this story was completed and moved to DONE column.
     * Null if not yet complete (still "in progress" or "blocked").
     */
    private LocalDateTime ext_completedTimestampForVelocityCalculation;

    /**
     * The name of the team member who volunteered (or was voluntold) to work on this story.
     */
    private String ext_assignedDeveloperNameOrEmailAddress;

    /**
     * Priority levels for backlog management and sprint planning.
     *
     * <p>These priority levels enable the Product Owner to make data-driven decisions
     * about what to build next, ensuring we always work on the highest-value items
     * that move the needle on business outcomes.</p>
     */
    public enum PriorityLevelForBacklogManagement {
        /** Low priority - Nice to have, might do it eventually, probably not */
        LOW,

        /** Medium priority - Should do this, maybe next sprint, we'll see */
        MEDIUM,

        /** High priority - Really should do this soon, Product Owner keeps asking */
        HIGH,

        /** Critical priority - Do this NOW, the CEO is asking about it */
        CRITICAL,

        /** Existential priority - The company will literally cease to exist if we don't do this immediately */
        EXISTENTIAL_THREAT_TO_BUSINESS_CONTINUITY
    }

    /**
     * Workflow status for tracking stories through the agile development lifecycle.
     *
     * <p>These statuses represent the journey of a story from conception to completion,
     * enabling transparency and visibility across the enterprise.</p>
     */
    public enum StoryStatusForWorkflowTracking {
        /** In the backlog - Not yet refined or prioritized */
        BACKLOG,

        /** Ready for development - Groomed, estimated, and waiting for sprint */
        READY,

        /** Currently in progress - Someone is (hopefully) working on it */
        IN_PROGRESS,

        /** Blocked - Waiting on something or someone (usually Bob) */
        BLOCKED,

        /** In code review - Waiting for another developer to review the PR */
        IN_REVIEW,

        /** In QA testing - The QA team is looking for bugs */
        IN_TESTING,

        /** Done - Completed, deployed, and ready to demo in the sprint review */
        DONE,

        /** Cancelled - We decided not to do this after all (Product Owner changed their mind) */
        CANCELLED
    }
}

