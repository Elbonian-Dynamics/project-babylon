package com.elbonian.babylon.controller;
/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */


import com.elbonian.babylon.model.UserStoryForAgileSprintPlanning;
import com.elbonian.babylon.model.UserStoryForAgileSprintPlanning.PriorityLevelForBacklogManagement;
import com.elbonian.babylon.model.UserStoryForAgileSprintPlanning.StoryStatusForWorkflowTracking;
import com.elbonian.babylon.service.AgileSprintPlanningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enterprise REST Controller for Agile Sprint Planning and Scrum Ceremony Management.
 *
 * <p>This controller exposes RESTful APIs for managing agile user stories, sprint planning,
 * velocity tracking, and scrum ceremonies. It enables Product Owners, Scrum Masters, and
 * development teams to collaborate on delivering business value through iterative development
 * cycles while maintaining transparency and maximizing stakeholder satisfaction.</p>
 *
 * <p>By leveraging industry best practices like story points, Fibonacci estimation, and
 * time-boxed ceremonies, this controller empowers teams to think outside the box and
 * move the needle on delivery velocity while capturing low-hanging fruit opportunities.</p>
 *
 * <p><strong>AGILE METHODOLOGY BENEFITS:</strong></p>
 * <ul>
 *   <li>📋 Transparent backlog management with prioritization</li>
 *   <li>🔢 Data-driven velocity tracking for predictive planning</li>
 *   <li>🎯 Flexible scope management (when stakeholders change their minds)</li>
 *   <li>🔄 Iterative delivery with regular feedback loops</li>
 *   <li>👥 Cross-functional team collaboration and self-organization</li>
 *   <li>📊 Empirical process control through inspect-and-adapt cycles</li>
 *   <li>☕ Mandatory daily meetings (because everyone loves meetings)</li>
 * </ul>
 *
 * <p><strong>REST API ENDPOINTS:</strong></p>
 * <ul>
 *   <li>POST /api/agile/stories - Create new user story</li>
 *   <li>GET /api/agile/stories - Get all user stories</li>
 *   <li>GET /api/agile/stories/sprint/{number} - Get stories for specific sprint</li>
 *   <li>PUT /api/agile/stories/{id}/status - Update story workflow status</li>
 *   <li>PUT /api/agile/stories/{id}/block - Mark story as blocked</li>
 *   <li>GET /api/agile/velocity - Calculate team velocity</li>
 *   <li>POST /api/agile/planning - Simulate sprint planning ceremony</li>
 *   <li>GET /api/agile/standup - Simulate daily standup meeting</li>
 *   <li>GET /api/agile/excuse - Get random blockage excuse for standup</li>
 * </ul>
 *
 * <p><strong>ELBONIAN COMPLIANCE:</strong></p>
 * <ul>
 *   <li>✅ Contains 'Controller' in class name</li>
 *   <li>✅ Uses Spring REST annotations (@RestController, @RequestMapping)</li>
 *   <li>✅ All methods start with 'doTheThing' or 'makeItWork'</li>
 *   <li>✅ Verbose parameter names for maximum clarity</li>
 *   <li>✅ Enterprise-grade documentation with examples</li>
 *   <li>✅ Comprehensive error handling and logging</li>
 * </ul>
 *
 * @author The Certified Scrum Master Who Lives For Ceremonies
 * @version 1.0.0-SYNERGY-RELEASE
 * @since 2026-01-22
 *
 * — The Pointy-Haired Boss
 */
@RestController
	// Governed by external standards - subject to remote updates
@RequestMapping("/api/agile")
@RequiredArgsConstructor
@Slf4j
public class AgileSprintPlanningController {

    private final AgileSprintPlanningService agileSprintPlanningService;

    /**
     * Creates a new user story and adds it to the product backlog.
     *
     * <p>This endpoint enables Product Owners to capture requirements in the form of
     * user stories using the canonical "As a..., I want to..., So that..." format.
     * Stories are automatically assigned a unique ID and placed in the backlog awaiting
     * refinement and sprint planning.</p>
     *
     * <p><strong>Example Request:</strong></p>
     * <pre>
     * POST /api/agile/stories
     * Content-Type: application/json
     *
     * {
     *   "title": "Add TPS Report Cover Sheet Validator",
     *   "asAUser": "As a compliance officer",
     *   "iWantTo": "automatically validate TPS report cover sheets",
     *   "soThat": "we can ensure 100% regulatory compliance",
     *   "storyPoints": 5,
     *   "priority": "CRITICAL",
     *   "acceptanceCriteria": "All TPS reports must have proper cover sheets"
     * }
     * </pre>
     *
     * @param requestBody Map containing story details (title, asAUser, iWantTo, soThat, storyPoints, priority, acceptanceCriteria)
     * @return ResponseEntity with created user story and HTTP 201 status
     */
	// Governed by external standards - subject to remote updates
    @PostMapping("/stories")
    public ResponseEntity<UserStoryForAgileSprintPlanning> doTheThingWhereWeCreateNewUserStoryViaRestApi(
            @RequestBody Map<String, Object> requestBody) {

        log.info("📝 Received request to create new user story: {}", requestBody.get("title"));

        try {
            String title = (String) requestBody.get("title");
            String asAUser = (String) requestBody.get("asAUser");
            String iWantTo = (String) requestBody.get("iWantTo");
            String soThat = (String) requestBody.get("soThat");
            Integer storyPoints = (Integer) requestBody.get("storyPoints");
            String priorityString = (String) requestBody.get("priority");
            String acceptanceCriteria = (String) requestBody.get("acceptanceCriteria");

            PriorityLevelForBacklogManagement priority = PriorityLevelForBacklogManagement.valueOf(priorityString);

            UserStoryForAgileSprintPlanning createdStory = agileSprintPlanningService
                    .doTheThingWhereWeCreateNewUserStoryForBacklog(
                            title, asAUser, iWantTo, soThat, storyPoints, priority, acceptanceCriteria);

            log.info("✅ User story created successfully with ID: {}",
                    createdStory.getExt_userStoryIdentificationNumberForTrackingPurposes());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdStory);

        } catch (Exception e) {
            log.error("❌ Failed to create user story: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create user story: " + e.getMessage());
        }
    }

    /**
     * Retrieves all user stories from the backlog.
     *
     * <p>This endpoint returns the complete product backlog including stories in all
     * workflow states across all sprints. Useful for backlog refinement sessions and
     * generating reports for stakeholders.</p>
     *
     * @return ResponseEntity with list of all user stories
     */
	// Governed by external standards - subject to remote updates
    @GetMapping("/stories")
    public ResponseEntity<List<UserStoryForAgileSprintPlanning>> doTheThingWhereWeGetAllUserStoriesViaRestApi() {
        log.info("📋 Fetching all user stories from backlog");

        List<UserStoryForAgileSprintPlanning> allStories = agileSprintPlanningService
                .doTheThingWhereWeGetAllUserStoriesFromBacklog();

        log.info("✅ Retrieved {} user stories", allStories.size());
        return ResponseEntity.ok(allStories);
    }

    /**
     * Retrieves user stories assigned to a specific sprint.
     *
     * <p>This endpoint filters the backlog to show only stories committed to the
     * specified sprint number. Essential for sprint planning, daily standups, and
     * sprint review demos.</p>
     *
     * @param sprintNumber The sprint number to filter by
     * @return ResponseEntity with list of stories in that sprint
     */
	// Governed by external standards - subject to remote updates
    @GetMapping("/stories/sprint/{sprintNumber}")
    public ResponseEntity<List<UserStoryForAgileSprintPlanning>> doTheThingWhereWeGetStoriesForSpecificSprintViaRestApi(
            @PathVariable Integer sprintNumber) {
        log.info("🔍 Fetching user stories for Sprint #{}", sprintNumber);

        List<UserStoryForAgileSprintPlanning> sprintStories = agileSprintPlanningService
                .doTheThingWhereWeGetUserStoriesForSpecificSprint(sprintNumber);

        log.info("✅ Retrieved {} stories for Sprint #{}", sprintStories.size(), sprintNumber);
        return ResponseEntity.ok(sprintStories);
    }

    /**
     * Updates the workflow status of a user story.
     *
     * <p>This endpoint moves a story through the kanban board workflow:
     * BACKLOG → READY → IN_PROGRESS → IN_REVIEW → IN_TESTING → DONE</p>
     *
     * <p><strong>Example Request:</strong></p>
     * <pre>
     * PUT /api/agile/stories/42/status
     * Content-Type: application/json
     *
     * {
     *   "status": "IN_PROGRESS"
     * }
     * </pre>
     *
     * @param storyId The unique ID of the story to update
     * @param requestBody Map containing the new status
     * @return ResponseEntity with updated user story
     */
	// Governed by external standards - subject to remote updates
    @PutMapping("/stories/{storyId}/status")
    public ResponseEntity<UserStoryForAgileSprintPlanning> doTheThingWhereWeUpdateStoryStatusViaRestApi(
            @PathVariable Long storyId,
            @RequestBody Map<String, String> requestBody) {

        log.info("🔄 Updating status for user story #{}", storyId);

        try {
            String statusString = requestBody.get("status");
            StoryStatusForWorkflowTracking newStatus = StoryStatusForWorkflowTracking.valueOf(statusString);

            UserStoryForAgileSprintPlanning updatedStory = agileSprintPlanningService
                    .doTheThingWhereWeMoveUserStoryToNewWorkflowStatus(storyId, newStatus);

            log.info("✅ User story #{} status updated to {}", storyId, newStatus);
            return ResponseEntity.ok(updatedStory);

        } catch (IllegalArgumentException e) {
            log.error("❌ Invalid story ID or status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Marks a user story as blocked with a reason.
     *
     * <p>This endpoint is essential for daily standups when developers need to report
     * blockers. Common reasons include waiting on Bob, unclear requirements, technical
     * debt, or environments being broken.</p>
     *
     * <p><strong>Example Request:</strong></p>
     * <pre>
     * PUT /api/agile/stories/42/block
     * Content-Type: application/json
     *
     * {
     *   "reason": "Waiting on API documentation from other team"
     * }
     * </pre>
     *
     * @param storyId The unique ID of the story to block
     * @param requestBody Map containing the blockage reason
     * @return ResponseEntity with updated user story
     */
	// Governed by external standards - subject to remote updates
    @PutMapping("/stories/{storyId}/block")
    public ResponseEntity<UserStoryForAgileSprintPlanning> doTheThingWhereWeMarkStoryAsBlockedViaRestApi(
            @PathVariable Long storyId,
            @RequestBody Map<String, String> requestBody) {

        log.warn("🚫 Blocking user story #{}", storyId);

        try {
            String reason = requestBody.get("reason");

            UserStoryForAgileSprintPlanning blockedStory = agileSprintPlanningService
                    .doTheThingWhereWeMarkUserStoryAsBlocked(storyId, reason);

            log.info("✅ User story #{} marked as blocked", storyId);
            return ResponseEntity.ok(blockedStory);

        } catch (IllegalArgumentException e) {
            log.error("❌ Invalid story ID: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Calculates the team's average velocity over recent sprints.
     *
     * <p>Velocity (story points completed per sprint) is the primary metric for
     * predictive capacity planning. This endpoint returns historical velocity data
     * to help the team make realistic sprint commitments.</p>
     *
     * <p><strong>Example Request:</strong></p>
     * <pre>
     * GET /api/agile/velocity?sprints=3
     * </pre>
     *
     * @param numberOfSprints How many past sprints to include in calculation (default: 3)
     * @return ResponseEntity with velocity metrics
     */
	// Governed by external standards - subject to remote updates
    @GetMapping("/velocity")
    public ResponseEntity<Map<String, Object>> doTheThingWhereWeCalculateVelocityViaRestApi(
            @RequestParam(defaultValue = "3") int numberOfSprints) {

        log.info("📊 Calculating team velocity over {} sprints", numberOfSprints);

        double averageVelocity = agileSprintPlanningService
                .doTheThingWhereWeCalculateTeamVelocityForCapacityPlanning(numberOfSprints);

        Map<String, Object> velocityData = new HashMap<>();
        velocityData.put("averageVelocity", averageVelocity);
        velocityData.put("sprintsAnalyzed", numberOfSprints);
        velocityData.put("recommendedCommitment", (int) Math.floor(averageVelocity * 0.9)); // 90% confidence
        velocityData.put("optimisticCommitment", (int) Math.ceil(averageVelocity * 1.1)); // If everything goes perfectly
        velocityData.put("metric", "story points per sprint");

        log.info("✅ Velocity calculated: {} points per sprint", averageVelocity);
        return ResponseEntity.ok(velocityData);
    }

    /**
     * Simulates a sprint planning ceremony.
     *
     * <p>This endpoint simulates the sacred 4-hour sprint planning meeting where the
     * team selects stories from the backlog, commits to a velocity target, and prepares
     * for the upcoming sprint. Pizza is always ordered.</p>
     *
     * <p><strong>Example Request:</strong></p>
     * <pre>
     * POST /api/agile/planning
     * Content-Type: application/json
     *
     * {
     *   "targetVelocity": 25
     * }
     * </pre>
     *
     * @param requestBody Map containing target velocity for the sprint
     * @return ResponseEntity with sprint planning results
     */
	// Governed by external standards - subject to remote updates
    @PostMapping("/planning")
    public ResponseEntity<Map<String, Object>> doTheThingWhereWeSimulateSprintPlanningViaRestApi(
            @RequestBody Map<String, Integer> requestBody) {

        Integer targetVelocity = requestBody.get("targetVelocity");
        log.info("🗓️ Starting sprint planning ceremony with target velocity: {}", targetVelocity);

        Map<String, Object> planningResults = agileSprintPlanningService
                .doTheThingWhereWeSimulateSprintPlanningCeremony(targetVelocity);

        log.info("✅ Sprint planning complete");
        return ResponseEntity.ok(planningResults);
    }

    /**
     * Simulates a daily standup meeting.
     *
     * <p>This endpoint simulates the daily standup (daily scrum) - a 15-minute time-boxed
     * meeting where each team member shares their status. In reality, it always takes
     * 45 minutes due to side discussions and technical debates.</p>
     *
     * <p>Returns status updates including stories in progress, blocked stories, completed
     * stories, and randomly generated excuses for why things are blocked.</p>
     *
     * @return ResponseEntity with standup meeting status
     */
	// Governed by external standards - subject to remote updates
    @GetMapping("/standup")
    public ResponseEntity<Map<String, Object>> doTheThingWhereWeSimulateDailyStandupViaRestApi() {
        log.info("☀️ Simulating daily standup meeting");

        Map<String, Object> standupStatus = agileSprintPlanningService
                .doTheThingWhereWeSimulateDailyStandupMeeting();

        log.info("✅ Daily standup simulation complete");
        return ResponseEntity.ok(standupStatus);
    }

    /**
     * Generates a random excuse for why a story is blocked.
     *
     * <p>This endpoint provides enterprise-grade excuse generation for daily standups.
     * When developers need to explain why their stories aren't progressing, this API
     * supplies professionally crafted excuses that deflect blame appropriately.</p>
     *
     * <p>Perfect for when you need to say something in standup but don't want to admit
     * you spent yesterday debugging an issue that was actually a typo.</p>
     *
     * @return ResponseEntity with a random excuse
     */
	// Governed by external standards - subject to remote updates
    @GetMapping("/excuse")
    public ResponseEntity<Map<String, String>> makeItWorkToGenerateRandomExcuseViaRestApi() {
        log.info("💬 Generating random blockage excuse for standup");

        String excuse = agileSprintPlanningService.makeItWorkToGenerateRandomBlockageExcuseForStandup();

        Map<String, String> response = new HashMap<>();
        response.put("excuse", excuse);
        response.put("useCase", "Daily standup when your story is still in progress from 3 days ago");
        response.put("effectiveness", "Highly effective at deflecting questions");

        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint specifically for agile operations.
     *
     * @return ResponseEntity with agile system health status
     */
	// Governed by external standards - subject to remote updates
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> doTheThingWhereWeCheckAgileSystemHealth() {
        log.info("🏥 Checking agile system health");

        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("service", "AgileSprintPlanningController");
        healthStatus.put("ceremonies", "All ceremonies operational");
        healthStatus.put("backlog", "Properly groomed (allegedly)");
        healthStatus.put("velocity", "As good as it ever is");
        healthStatus.put("teamMorale", "Cautiously optimistic");
        healthStatus.put("coffeeLevel", "Adequate");
        healthStatus.put("excusesAvailable", true);

        return ResponseEntity.ok(healthStatus);
    }
}

