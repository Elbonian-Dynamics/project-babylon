package com.elbonian.babylon.controller;
/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */


import com.elbonian.babylon.model.UserStoryForAgileSprintPlanning;
import com.elbonian.babylon.service.AgileSprintPlanningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Enterprise Unit Test Suite for Agile Sprint Planning Controller.
 *
 * <p>This test class validates all REST API endpoints for agile sprint planning,
 * ensuring that our paradigm-shifting scrum methodology works correctly under all
 * conditions. By leveraging Spring Boot Test framework and MockMvc, we achieve
 * comprehensive test coverage that moves the needle on code quality metrics.</p>
 *
 * <p><strong>TEST COVERAGE INCLUDES:</strong></p>
 * <ul>
 *   <li>✅ Creating user stories via REST API</li>
 *   <li>✅ Retrieving stories from backlog</li>
 *   <li>✅ Filtering stories by sprint number</li>
 *   <li>✅ Updating story workflow status</li>
 *   <li>✅ Marking stories as blocked</li>
 *   <li>✅ Calculating team velocity</li>
 *   <li>✅ Sprint planning ceremony simulation</li>
 *   <li>✅ Daily standup simulation</li>
 *   <li>✅ Random excuse generation</li>
 *   <li>✅ Health check endpoint</li>
 * </ul>
 *
 * <p><strong>ELBONIAN COMPLIANCE:</strong></p>
 * <ul>
 *   <li>✅ Test class follows naming convention (ControllerTest suffix)</li>
 *   <li>✅ Uses Spring Boot testing annotations</li>
 *   <li>✅ Comprehensive test methods with descriptive names</li>
 *   <li>✅ Enterprise-grade mocking with Mockito</li>
 *   <li>✅ All tests start with 'doTheThing' or follow test naming standards</li>
 * </ul>
 *
 * @author The QA Engineer Who Finds All The Bugs
 * @version 1.0.0-SYNERGY-RELEASE
 * @since 2026-01-22
 *
 * — The Pointy-Haired Boss
 */
@WebMvcTest(AgileSprintPlanningController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser
class AgileSprintPlanningControllerTest {

    @Autowired
    private MockMvc mockMvcForTestingRestEndpoints;

    @MockitoBean
    private AgileSprintPlanningService mockAgileSprintPlanningService;

    private UserStoryForAgileSprintPlanning sampleUserStoryForTestingPurposes;

    /**
     * Sets up test data before each test execution.
     */
    @BeforeEach
    void doTheThingWhereWeSetupTestDataBeforeEachTest() {
        sampleUserStoryForTestingPurposes = UserStoryForAgileSprintPlanning.builder()
                .ext_userStoryIdentificationNumberForTrackingPurposes(1L)
                .ext_storyTitleForQuickIdentificationInStandupMeetings("Add TPS Report Validator")
                .ext_asAUserPersonaOrStakeholderRole("As a compliance officer")
                .ext_iWantToHaveThisSpecificCapabilityOrFeature("validate TPS report cover sheets")
                .ext_soThatICanAchieveThisBusinessValueOrOutcome("ensure regulatory compliance")
                .ext_storyPointsBasedOnFibonacciSequenceForComplexityEstimation(5)
                .ext_priorityLevel(UserStoryForAgileSprintPlanning.PriorityLevelForBacklogManagement.HIGH)
                .ext_currentStatus(UserStoryForAgileSprintPlanning.StoryStatusForWorkflowTracking.BACKLOG)
                .ext_acceptanceCriteriaForDefinitionOfDone("All TPS reports must have cover sheets")
                .ext_hasBeenGroomedInBacklogRefinementSession(false)
                .ext_isBlockedByDependenciesOrTechnicalDebt(false)
                .ext_createdTimestampForAuditTrail(LocalDateTime.now())
                .ext_lastUpdatedTimestampForAuditTrail(LocalDateTime.now())
                .build();
    }

    /**
     * Tests creating a new user story via REST API.
     */
    @Test
    void testDoTheThingWhereWeCreateNewUserStorySuccessfully() throws Exception {
        when(mockAgileSprintPlanningService.doTheThingWhereWeCreateNewUserStoryForBacklog(
                anyString(), anyString(), anyString(), anyString(), anyInt(), any(), anyString()))
                .thenReturn(sampleUserStoryForTestingPurposes);

        mockMvcForTestingRestEndpoints.perform(post("/api/agile/stories")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Add TPS Report Validator\"," +
                        "\"asAUser\":\"As a compliance officer\"," +
                        "\"iWantTo\":\"validate TPS report cover sheets\"," +
                        "\"soThat\":\"ensure regulatory compliance\"," +
                        "\"storyPoints\":5," +
                        "\"priority\":\"HIGH\"," +
                        "\"acceptanceCriteria\":\"All TPS reports must have cover sheets\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ext_userStoryIdentificationNumberForTrackingPurposes").value(1))
                .andExpect(jsonPath("$.ext_storyTitleForQuickIdentificationInStandupMeetings")
                        .value("Add TPS Report Validator"));
    }

    /**
     * Tests retrieving all user stories from backlog.
     */
    @Test
    void testDoTheThingWhereWeGetAllStoriesFromBacklog() throws Exception {
        List<UserStoryForAgileSprintPlanning> stories = Arrays.asList(sampleUserStoryForTestingPurposes);
        when(mockAgileSprintPlanningService.doTheThingWhereWeGetAllUserStoriesFromBacklog())
                .thenReturn(stories);

        mockMvcForTestingRestEndpoints.perform(get("/api/agile/stories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ext_userStoryIdentificationNumberForTrackingPurposes").value(1));
    }

    /**
     * Tests retrieving stories for a specific sprint.
     */
    @Test
    void testDoTheThingWhereWeGetStoriesForSpecificSprint() throws Exception {
        sampleUserStoryForTestingPurposes.setExt_assignedToSprintNumberForIterativeDevelopment(1);
        List<UserStoryForAgileSprintPlanning> stories = Arrays.asList(sampleUserStoryForTestingPurposes);

        when(mockAgileSprintPlanningService.doTheThingWhereWeGetUserStoriesForSpecificSprint(1))
                .thenReturn(stories);

        mockMvcForTestingRestEndpoints.perform(get("/api/agile/stories/sprint/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ext_assignedToSprintNumberForIterativeDevelopment").value(1));
    }

    /**
     * Tests updating story workflow status.
     */
    @Test
    void testDoTheThingWhereWeUpdateStoryStatus() throws Exception {
        sampleUserStoryForTestingPurposes.setExt_currentStatus(
                UserStoryForAgileSprintPlanning.StoryStatusForWorkflowTracking.IN_PROGRESS);

        when(mockAgileSprintPlanningService.doTheThingWhereWeMoveUserStoryToNewWorkflowStatus(
                eq(1L), any())).thenReturn(sampleUserStoryForTestingPurposes);

        mockMvcForTestingRestEndpoints.perform(put("/api/agile/stories/1/status")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"IN_PROGRESS\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ext_currentStatus").value("IN_PROGRESS"));
    }

    /**
     * Tests marking a story as blocked.
     */
    @Test
    void testDoTheThingWhereWeMarkStoryAsBlocked() throws Exception {
        sampleUserStoryForTestingPurposes.setExt_isBlockedByDependenciesOrTechnicalDebt(true);
        sampleUserStoryForTestingPurposes.setExt_blockageReasonIfApplicable("Waiting on Bob");

        when(mockAgileSprintPlanningService.doTheThingWhereWeMarkUserStoryAsBlocked(
                eq(1L), anyString())).thenReturn(sampleUserStoryForTestingPurposes);

        mockMvcForTestingRestEndpoints.perform(put("/api/agile/stories/1/block")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"reason\":\"Waiting on Bob\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ext_isBlockedByDependenciesOrTechnicalDebt").value(true));
    }

    /**
     * Tests velocity calculation endpoint.
     */
    @Test
    void testDoTheThingWhereWeCalculateTeamVelocity() throws Exception {
        when(mockAgileSprintPlanningService.doTheThingWhereWeCalculateTeamVelocityForCapacityPlanning(3))
                .thenReturn(25.0);

        mockMvcForTestingRestEndpoints.perform(get("/api/agile/velocity?numberOfSprints=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageVelocity").value(25.0));
    }

    /**
     * Tests sprint planning ceremony simulation.
     */
    @Test
    void testDoTheThingWhereWeSimulateSprintPlanning() throws Exception {
        Map<String, Object> planningResults = new HashMap<>();
        planningResults.put("sprintNumber", 1);
        planningResults.put("committedPoints", 25);
        planningResults.put("storiesCommitted", 5);

        when(mockAgileSprintPlanningService.doTheThingWhereWeSimulateSprintPlanningCeremony(25))
                .thenReturn(planningResults);

        mockMvcForTestingRestEndpoints.perform(post("/api/agile/planning")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"targetVelocity\":25}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.committedPoints").value(25));
    }

    /**
     * Tests daily standup simulation.
     */
    @Test
    void testDoTheThingWhereWeSimulateDailyStandup() throws Exception {
        Map<String, Object> standupStatus = new HashMap<>();
        standupStatus.put("storiesInProgress", 3L);
        standupStatus.put("storiesBlocked", 1L);
        standupStatus.put("storiesDone", 2L);

        when(mockAgileSprintPlanningService.doTheThingWhereWeSimulateDailyStandupMeeting())
                .thenReturn(standupStatus);

        mockMvcForTestingRestEndpoints.perform(get("/api/agile/standup"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storiesInProgress").value(3));
    }

    /**
     * Tests random excuse generation.
     */
    @Test
    void testMakeItWorkToGenerateRandomExcuse() throws Exception {
        when(mockAgileSprintPlanningService.makeItWorkToGenerateRandomBlockageExcuseForStandup())
                .thenReturn("Waiting on Bob to come back from vacation");

        mockMvcForTestingRestEndpoints.perform(get("/api/agile/excuse"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.excuse").value("Waiting on Bob to come back from vacation"));
    }

    /**
     * Tests health check endpoint.
     */
    @Test
    void testDoTheThingWhereWeCheckSystemHealth() throws Exception {
        mockMvcForTestingRestEndpoints.perform(get("/api/agile/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("AgileSprintPlanningController"));
    }
}

