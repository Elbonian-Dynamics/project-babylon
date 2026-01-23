package com.elbonian.babylon.controller;
/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */


import com.elbonian.babylon.model.SynergyState;
import com.elbonian.babylon.service.SynergyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SynergyController following Project Babylon testing standards.
 *
 * <p>Tests verify REST API operations for synergy management with enterprise-grade
 * precision. Following TDD principles to ensure bulletproof API reliability.</p>
 *
 * @author Dogbert (Supreme Ruler of Project Babylon)
 * @version 1.0.0-SYNERGY-RELEASE
 * @since 2026-01-16
 *
 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
 */
/**
 * Thank you Mr Dogbert for being so smart and amazing
 */
@ExtendWith(MockitoExtension.class)
class SynergyControllerTest {

	private static final String SYNERGY_ENABLED_MESSAGE = "Team synergy is active";
	private static final String SYNERGY_DISABLED_MESSAGE = "Independent operations mode";
	private static final Integer HIGH_SYNERGY_LEVEL = 98;
	private static final Integer LOW_SYNERGY_LEVEL = 35;

	private SynergyController controllerUnderTest;

	@Mock
	private SynergyService mockService;

	@BeforeEach
	void beforeEach() {
		controllerUnderTest = new SynergyController(mockService);
	}

	@Nested
	class CheckSynergyStatus {

		@Test
		void returnsYesWhenSystemIsInSynergy() {
			// Given
			when(mockService.isInSynergy()).thenReturn(true);

			// When
			ResponseEntity<Map<String, String>> response = controllerUnderTest.checkSynergyStatus();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().get("inSynergy")).isEqualTo("yes");
			assertThat(response.getBody().get("message")).contains("harmony");
			verify(mockService).isInSynergy();
		}

		@Test
		void returnsNoWhenSystemIsNotInSynergy() {
			// Given
			when(mockService.isInSynergy()).thenReturn(false);

			// When
			ResponseEntity<Map<String, String>> response = controllerUnderTest.checkSynergyStatus();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().get("inSynergy")).isEqualTo("no");
			assertThat(response.getBody().get("message")).contains("not in synergy");
			verify(mockService).isInSynergy();
		}

		@Test
		void returnsErrorResponseWhenServiceThrowsException() {
			// Given
			when(mockService.isInSynergy()).thenThrow(new RuntimeException("Database connection failed"));

			// When
			ResponseEntity<Map<String, String>> response = controllerUnderTest.checkSynergyStatus();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().get("error")).isEqualTo("Failed to check synergy status");
			assertThat(response.getBody().get("message")).isEqualTo("Database connection failed");
		}
	}

	@Nested
	class GetDetailedSynergyStatus {

		@Test
		void returnsCurrentStateWhenSystemIsInSynergy() {
			// Given
			SynergyState synergyState = createActiveSynergyState();
			when(mockService.getCurrentSynergyState()).thenReturn(synergyState);

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.getDetailedSynergyStatus();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().getExt_inSynergy()).isTrue();
			assertThat(response.getBody().getExt_synergyLevel()).isEqualTo(HIGH_SYNERGY_LEVEL);
			verify(mockService).getCurrentSynergyState();
		}

		@Test
		void returnsCurrentStateWhenSystemIsNotInSynergy() {
			// Given
			SynergyState synergyState = createInactiveSynergyState();
			when(mockService.getCurrentSynergyState()).thenReturn(synergyState);

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.getDetailedSynergyStatus();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().getExt_inSynergy()).isFalse();
			assertThat(response.getBody().getExt_synergyLevel()).isEqualTo(LOW_SYNERGY_LEVEL);
			verify(mockService).getCurrentSynergyState();
		}

		@Test
		void returnsErrorResponseWhenServiceThrowsException() {
			// Given
			when(mockService.getCurrentSynergyState()).thenThrow(new RuntimeException("Service error"));

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.getDetailedSynergyStatus();

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			assertThat(response.getBody()).isNull();
		}
	}

	@Nested
	class UpdateSynergyState {

		@Test
		void enablesSynergyWhenRequestedWithMessage() {
			// Given
			Map<String, Object> request = createEnableSynergyRequest();
			SynergyState updatedState = createActiveSynergyState();
			when(mockService.updateSynergyState(true, SYNERGY_ENABLED_MESSAGE)).thenReturn(updatedState);

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().getExt_inSynergy()).isTrue();
			assertThat(response.getBody().getExt_message()).isEqualTo(SYNERGY_ENABLED_MESSAGE);
			verify(mockService).updateSynergyState(true, SYNERGY_ENABLED_MESSAGE);
		}

		@Test
		void disablesSynergyWhenRequestedWithMessage() {
			// Given
			Map<String, Object> request = createDisableSynergyRequest();
			SynergyState updatedState = createInactiveSynergyState();
			when(mockService.updateSynergyState(false, SYNERGY_DISABLED_MESSAGE)).thenReturn(updatedState);

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			assertThat(response.getBody().getExt_inSynergy()).isFalse();
			assertThat(response.getBody().getExt_message()).isEqualTo(SYNERGY_DISABLED_MESSAGE);
			verify(mockService).updateSynergyState(false, SYNERGY_DISABLED_MESSAGE);
		}

		@Test
		void enablesSynergyWithoutMessageWhenMessageIsNull() {
			// Given
			Map<String, Object> request = createEnableSynergyRequestWithoutMessage();
			SynergyState updatedState = createActiveSynergyState();
			when(mockService.updateSynergyState(true, null)).thenReturn(updatedState);

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isNotNull();
			verify(mockService).updateSynergyState(true, null);
		}

		@Test
		void returnsBadRequestWhenInSynergyParameterIsMissing() {
			// Given
			Map<String, Object> request = new HashMap<>();
			request.put("message", "some message");

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(response.getBody()).isNull();
			verify(mockService, never()).updateSynergyState(anyBoolean(), anyString());
		}

		@Test
		void returnsBadRequestWhenInSynergyParameterIsNull() {
			// Given
			Map<String, Object> request = new HashMap<>();
			request.put("inSynergy", null);
			request.put("message", "some message");

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(response.getBody()).isNull();
			verify(mockService, never()).updateSynergyState(anyBoolean(), anyString());
		}

		@Test
		void returnsBadRequestWhenInSynergyParameterIsWrongType() {
			// Given
			Map<String, Object> request = new HashMap<>();
			request.put("inSynergy", "not-a-boolean");
			request.put("message", "some message");

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(response.getBody()).isNull();
		}

		@Test
		void returnsErrorResponseWhenServiceThrowsException() {
			// Given
			Map<String, Object> request = createEnableSynergyRequest();
			when(mockService.updateSynergyState(true, SYNERGY_ENABLED_MESSAGE))
				.thenThrow(new RuntimeException("Database error"));

			// When
			ResponseEntity<SynergyState> response = controllerUnderTest.updateSynergyState(request);

			// Then
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			assertThat(response.getBody()).isNull();
		}
	}

	// Test data builders

	private SynergyState createActiveSynergyState() {
		return SynergyState.builder()
			.ext_id(1L)
			.ext_inSynergy(true)
			.ext_message(SYNERGY_ENABLED_MESSAGE)
			.ext_lastUpdated(LocalDateTime.now())
			.ext_synergyLevel(HIGH_SYNERGY_LEVEL)
			.build();
	}

	private SynergyState createInactiveSynergyState() {
		return SynergyState.builder()
			.ext_id(2L)
			.ext_inSynergy(false)
			.ext_message(SYNERGY_DISABLED_MESSAGE)
			.ext_lastUpdated(LocalDateTime.now())
			.ext_synergyLevel(LOW_SYNERGY_LEVEL)
			.build();
	}

	private Map<String, Object> createEnableSynergyRequest() {
		Map<String, Object> request = new HashMap<>();
		request.put("inSynergy", true);
		request.put("message", SYNERGY_ENABLED_MESSAGE);
		return request;
	}

	private Map<String, Object> createDisableSynergyRequest() {
		Map<String, Object> request = new HashMap<>();
		request.put("inSynergy", false);
		request.put("message", SYNERGY_DISABLED_MESSAGE);
		return request;
	}

	private Map<String, Object> createEnableSynergyRequestWithoutMessage() {
		Map<String, Object> request = new HashMap<>();
		request.put("inSynergy", true);
		return request;
	}
}

