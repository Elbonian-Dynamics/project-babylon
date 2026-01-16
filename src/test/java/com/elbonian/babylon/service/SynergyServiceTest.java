package com.elbonian.babylon.service;

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
			assertThat(result.getInSynergy()).isTrue();
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

			assertThat(savedState.getInSynergy()).isTrue();
			assertThat(savedState.getMessage()).isEqualTo(DEFAULT_SYNERGY_MESSAGE);
			assertThat(savedState.getSynergyLevel()).isEqualTo(DEFAULT_SYNERGY_LEVEL);
			assertThat(savedState.getLastUpdated()).isNotNull();
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

			assertThat(savedState.getInSynergy()).isTrue();
			assertThat(savedState.getMessage()).isEqualTo(customMessage);
			assertThat(savedState.getLastUpdated()).isNotNull();
			assertThat(savedState.getSynergyLevel()).isBetween(95, 100);
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

			assertThat(savedState.getInSynergy()).isFalse();
			assertThat(savedState.getMessage()).isEqualTo(customMessage);
			assertThat(savedState.getLastUpdated()).isNotNull();
			assertThat(savedState.getSynergyLevel()).isBetween(30, 50);
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

			assertThat(savedState.getMessage()).isNotNull();
			assertThat(savedState.getMessage()).contains("synergy");
		}

		@Test
		void setsHighSynergyLevelWhenEnabling() {
			// Given
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			SynergyState result = serviceUnderTest.updateSynergyState(true, "test");

			// Then
			assertThat(result.getSynergyLevel()).isGreaterThanOrEqualTo(95);
			assertThat(result.getSynergyLevel()).isLessThanOrEqualTo(100);
		}

		@Test
		void setsLowSynergyLevelWhenDisabling() {
			// Given
			when(mockRepository.save(any(SynergyState.class))).thenAnswer(invocation -> invocation.getArgument(0));

			// When
			SynergyState result = serviceUnderTest.updateSynergyState(false, "test");

			// Then
			assertThat(result.getSynergyLevel()).isGreaterThanOrEqualTo(30);
			assertThat(result.getSynergyLevel()).isLessThanOrEqualTo(50);
		}
	}

	// Test data builders

	private SynergyState createSynergyStateWithSynergy() {
		return SynergyState.builder()
			.id(SYNERGY_STATE_ID)
			.inSynergy(true)
			.message("System operating in perfect synergy")
			.lastUpdated(LocalDateTime.now())
			.synergyLevel(98)
			.build();
	}

	private SynergyState createSynergyStateWithoutSynergy() {
		return SynergyState.builder()
			.id(SYNERGY_STATE_ID)
			.inSynergy(false)
			.message("Synergy temporarily disabled")
			.lastUpdated(LocalDateTime.now())
			.synergyLevel(35)
			.build();
	}

	private SynergyState createDefaultSynergyState() {
		return SynergyState.builder()
			.id(SYNERGY_STATE_ID)
			.inSynergy(true)
			.message(DEFAULT_SYNERGY_MESSAGE)
			.lastUpdated(LocalDateTime.now())
			.synergyLevel(DEFAULT_SYNERGY_LEVEL)
			.build();
	}
}

