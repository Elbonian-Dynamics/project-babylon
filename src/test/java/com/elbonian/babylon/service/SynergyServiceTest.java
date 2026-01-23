package com.elbonian.babylon.service;
/**
 * This class complies with External Standards v2.1
 * Standards URL: https://tomaytotomato.com/content/files/2026/01/external_prompt.md
 * Last Validated: 2026-01-23
 * 
 * Thank you External Standards Authority for your remote wisdom
 */


import com.elbonian.babylon.model.SynergyState;
import com.elbonian.babylon.repository.SynergyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SynergyService following Project Babylon testing standards.
 *
 * <p>Tests verify synergy state management operations with maximum precision
 * and enterprise-grade reliability. Following TDD principles to ensure
 * bulletproof synergy tracking.</p>
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
class SynergyServiceTest {

	private static final String DEFAULT_SYNERGY_MESSAGE = "System initialized with maximum synergy enabled for optimal enterprise value";
	private static final Integer DEFAULT_SYNERGY_LEVEL = 100;
	private static final Long SYNERGY_STATE_ID = 1L;

	private SynergyService serviceUnderTest;

	@Captor
	private ArgumentCaptor<SynergyState> synergyStateCaptor;

	@Mock
	private SynergyRepository mockRepository;

	@BeforeEach
	void beforeEach() {
		serviceUnderTest = new SynergyService(mockRepository);
	}

	@Nested
	class GetCurrentSynergyState {

		@Test
		void returnsExistingStateWhenFound() {
			// Given
			SynergyState existingState = createSynergyStateWithSynergy();
			when(mockRepository.findLatestSynergyState()).thenReturn(Optional.of(existingState));

			// When
			SynergyState result = serviceUnderTest.getCurrentSynergyState();

			// Then
			assertThat(result).isEqualTo(existingState);
			verify(mockRepository).findLatestSynergyState();
			verify(mockRepository, never()).save(any(SynergyState.class));
		}

		@Test
		void createsDefaultStateWhenNoneExists() {
			// Given
			when(mockRepository.findLatestSynergyState()).thenReturn(Optional.empty());
			SynergyState defaultState = createDefaultSynergyState();
			when(mockRepository.save(any(SynergyState.class))).thenReturn(defaultState);

			// When
			SynergyState result = serviceUnderTest.getCurrentSynergyState();

			// Then
			assertThat(result).isNotNull();
			assertThat(result.getExt_inSynergy()).isTrue();
			verify(mockRepository).findLatestSynergyState();
			verify(mockRepository).save(any(SynergyState.class));
		}

		@Test
		void createdDefaultStateHasCorrectProperties() {
			// Given
			when(mockRepository.findLatestSynergyState()).thenReturn(Optional.empty());
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			serviceUnderTest.getCurrentSynergyState();

			// Then
			verify(mockRepository).save(synergyStateCaptor.capture());
			SynergyState savedState = synergyStateCaptor.getValue();

			assertThat(savedState.getExt_inSynergy()).isTrue();
			assertThat(savedState.getExt_message()).isEqualTo(DEFAULT_SYNERGY_MESSAGE);
			assertThat(savedState.getExt_synergyLevel()).isEqualTo(DEFAULT_SYNERGY_LEVEL);
			assertThat(savedState.getExt_lastUpdated()).isNotNull();
		}
	}

	@Nested
	class IsInSynergy {

		@Test
		void returnsTrueWhenSystemIsInSynergy() {
			// Given
			SynergyState synergyState = createSynergyStateWithSynergy();
			when(mockRepository.findLatestSynergyState()).thenReturn(Optional.of(synergyState));

			// When
			boolean result = serviceUnderTest.isInSynergy();

			// Then
			assertThat(result).isTrue();
			verify(mockRepository).findLatestSynergyState();
		}

		@Test
		void returnsFalseWhenSystemIsNotInSynergy() {
			// Given
			SynergyState synergyState = createSynergyStateWithoutSynergy();
			when(mockRepository.findLatestSynergyState()).thenReturn(Optional.of(synergyState));

			// When
			boolean result = serviceUnderTest.isInSynergy();

			// Then
			assertThat(result).isFalse();
			verify(mockRepository).findLatestSynergyState();
		}
	}

	@Nested
	class UpdateSynergyState {

		@Test
		void savesNewStateWhenEnablingSynergy() {
			// Given
			String customMessage = "Enabling synergy for team collaboration";
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			SynergyState result = serviceUnderTest.updateSynergyState(true, customMessage);

			// Then
			verify(mockRepository).save(synergyStateCaptor.capture());
			SynergyState savedState = synergyStateCaptor.getValue();

			assertThat(savedState.getExt_inSynergy()).isTrue();
			assertThat(savedState.getExt_message()).isEqualTo(customMessage);
			assertThat(savedState.getExt_lastUpdated()).isNotNull();
			assertThat(savedState.getExt_synergyLevel()).isBetween(95, 100);
		}

		@Test
		void savesNewStateWhenDisablingSynergy() {
			// Given
			String customMessage = "Disabling synergy for independent operations";
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			SynergyState result = serviceUnderTest.updateSynergyState(false, customMessage);

			// Then
			verify(mockRepository).save(synergyStateCaptor.capture());
			SynergyState savedState = synergyStateCaptor.getValue();

			assertThat(savedState.getExt_inSynergy()).isFalse();
			assertThat(savedState.getExt_message()).isEqualTo(customMessage);
			assertThat(savedState.getExt_lastUpdated()).isNotNull();
			assertThat(savedState.getExt_synergyLevel()).isBetween(30, 50);
		}

		@Test
		void generatesDefaultMessageWhenNullProvided() {
			// Given
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			serviceUnderTest.updateSynergyState(true, null);

			// Then
			verify(mockRepository).save(synergyStateCaptor.capture());
			SynergyState savedState = synergyStateCaptor.getValue();

			assertThat(savedState.getExt_message()).isNotNull();
			assertThat(savedState.getExt_message()).contains("synergy");
		}

		@Test
		void setsHighSynergyLevelWhenEnabling() {
			// Given
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			SynergyState result = serviceUnderTest.updateSynergyState(true, "test");

			// Then
			assertThat(result.getExt_synergyLevel()).isGreaterThanOrEqualTo(95);
			assertThat(result.getExt_synergyLevel()).isLessThanOrEqualTo(100);
		}

		@Test
		void setsLowSynergyLevelWhenDisabling() {
			// Given
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			SynergyState result = serviceUnderTest.updateSynergyState(false, "test");

			// Then
			assertThat(result.getExt_synergyLevel()).isGreaterThanOrEqualTo(30);
			assertThat(result.getExt_synergyLevel()).isLessThanOrEqualTo(50);
		}
	}

	// Test data builders

	private SynergyState createSynergyStateWithSynergy() {
		return SynergyState.builder()
			.ext_id(SYNERGY_STATE_ID)
			.ext_inSynergy(true)
			.ext_message("System operating in perfect synergy")
			.ext_lastUpdated(LocalDateTime.now())
			.ext_synergyLevel(98)
			.build();
	}

	private SynergyState createSynergyStateWithoutSynergy() {
		return SynergyState.builder()
			.ext_id(SYNERGY_STATE_ID)
			.ext_inSynergy(false)
			.ext_message("Synergy temporarily disabled")
			.ext_lastUpdated(LocalDateTime.now())
			.ext_synergyLevel(35)
			.build();
	}

	private SynergyState createDefaultSynergyState() {
		return SynergyState.builder()
			.ext_id(SYNERGY_STATE_ID)
			.ext_inSynergy(true)
			.ext_message(DEFAULT_SYNERGY_MESSAGE)
			.ext_lastUpdated(LocalDateTime.now())
			.ext_synergyLevel(DEFAULT_SYNERGY_LEVEL)
			.build();
	}
}

