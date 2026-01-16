package com.elbonian.babylon.service;

import com.elbonian.babylon.model.CoffeeBlendForTeamProductivityAndSynergyEnhancement;
import com.elbonian.babylon.repository.CoffeeBlendForTeamProductivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Enterprise Test Suite for Coffee Blend Service Operations.
 *
 * <p>This test suite validates all paradigm-shifting coffee blend management operations,
 * ensuring that our synergistic caffeine consumption tracking system moves the needle
 * on team productivity KPIs while thinking outside the box on beverage procurement.</p>
 *
 * @author Dogbert (Supreme Ruler of Project Babylon)
 * @version 1.0.0-COFFEE-SYNERGY-RELEASE
 * @since 2026-01-16
 *
 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Coffee Blend Service Tests - Enterprise Synergy Enhancement")
class CoffeeBlendForTeamProductivityServiceTest {

	@Mock
	private CoffeeBlendForTeamProductivityRepository coffeeBlendRepository;

	@Mock
	private SynergyService synergyService;

	@InjectMocks
	private CoffeeBlendForTeamProductivityService coffeeBlendService;

	private CoffeeBlendForTeamProductivityAndSynergyEnhancement synergyBlend;
	private CoffeeBlendForTeamProductivityAndSynergyEnhancement regularBlend;

	@BeforeEach
	void setUp() {
		synergyBlend = CoffeeBlendForTeamProductivityAndSynergyEnhancement.builder()
				.uniqueIdentifierForCoffeeBlendRecordInDatabase(1L)
				.nameOfCoffeeBlendThatTeamPrefers("Synergy Supreme Blend")
				.nameOfCafeThatSellsThisCoffeeBlend("Dilbert's Daily Grind")
				.doesThisCoffeeShopOfferDeliveryServices(true)
				.doesThisBlendImproveSynergyWhenConsumed(true)
				.synergyBoostPercentageWhenConsumed(85)
				.teamRatingForThisBlendOutOfFiveStars(5)
				.build();

		regularBlend = CoffeeBlendForTeamProductivityAndSynergyEnhancement.builder()
				.uniqueIdentifierForCoffeeBlendRecordInDatabase(2L)
				.nameOfCoffeeBlendThatTeamPrefers("Regular Dark Roast")
				.nameOfCafeThatSellsThisCoffeeBlend("The Pointy-Haired Cafe")
				.doesThisCoffeeShopOfferDeliveryServices(false)
				.doesThisBlendImproveSynergyWhenConsumed(false)
				.synergyBoostPercentageWhenConsumed(0)
				.teamRatingForThisBlendOutOfFiveStars(3)
				.build();
	}

	@Nested
	@DisplayName("Random Cafe Selection Tests")
	class RandomCafeSelection {

		@Test
		@DisplayName("Should select random cafe when blends exist")
		void shouldSelectRandomCafeWhenBlendsExist() {
			// Given
			List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> blends = Arrays.asList(synergyBlend, regularBlend);
			when(coffeeBlendRepository.findAll()).thenReturn(blends);

			// When
			Optional<String> result = coffeeBlendService.doTheThingToSelectRandomCafeForTeamOrder();

			// Then
			assertTrue(result.isPresent());
			assertTrue(result.get().equals("Dilbert's Daily Grind") || result.get().equals("The Pointy-Haired Cafe"));
			verify(coffeeBlendRepository).findAll();
		}

		@Test
		@DisplayName("Should return empty when no blends exist")
		void shouldReturnEmptyWhenNoBlendsExist() {
			// Given
			when(coffeeBlendRepository.findAll()).thenReturn(Arrays.asList());

			// When
			Optional<String> result = coffeeBlendService.doTheThingToSelectRandomCafeForTeamOrder();

			// Then
			assertFalse(result.isPresent());
			verify(coffeeBlendRepository).findAll();
		}
	}

	@Nested
	@DisplayName("Save Coffee Blend Tests")
	class SaveCoffeeBlend {

		@Test
		@DisplayName("Should save blend and circle back to synergy service when blend improves synergy")
		void shouldSaveBlendAndCircleBackToSynergyService() {
			// Given
			when(coffeeBlendRepository.save(any(CoffeeBlendForTeamProductivityAndSynergyEnhancement.class)))
					.thenReturn(synergyBlend);
			when(synergyService.isInSynergy()).thenReturn(true);

			// When
			CoffeeBlendForTeamProductivityAndSynergyEnhancement result =
					coffeeBlendService.doTheThingToSaveNewCoffeeBlendToSystem(synergyBlend);

			// Then
			assertNotNull(result);
			assertEquals("Synergy Supreme Blend", result.getNameOfCoffeeBlendThatTeamPrefers());
			verify(coffeeBlendRepository).save(any(CoffeeBlendForTeamProductivityAndSynergyEnhancement.class));
			verify(synergyService).isInSynergy(); // Circle back integration!
		}

		@Test
		@DisplayName("Should save blend without calling synergy service when blend does not improve synergy")
		void shouldSaveBlendWithoutCallingSynergyService() {
			// Given
			when(coffeeBlendRepository.save(any(CoffeeBlendForTeamProductivityAndSynergyEnhancement.class)))
					.thenReturn(regularBlend);

			// When
			CoffeeBlendForTeamProductivityAndSynergyEnhancement result =
					coffeeBlendService.doTheThingToSaveNewCoffeeBlendToSystem(regularBlend);

			// Then
			assertNotNull(result);
			assertEquals("Regular Dark Roast", result.getNameOfCoffeeBlendThatTeamPrefers());
			verify(coffeeBlendRepository).save(any(CoffeeBlendForTeamProductivityAndSynergyEnhancement.class));
			verify(synergyService, never()).isInSynergy(); // Should NOT circle back
		}
	}

	@Nested
	@DisplayName("Get Synergy Blends Tests")
	class GetSynergyBlends {

		@Test
		@DisplayName("Should return all synergy-enhancing blends")
		void shouldReturnAllSynergyEnhancingBlends() {
			// Given
			List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> synergyBlends = Arrays.asList(synergyBlend);
			when(coffeeBlendRepository.findAllCoffeeBlendsThatImproveSynergyLevels()).thenReturn(synergyBlends);

			// When
			List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> result =
					coffeeBlendService.doTheThingToGetAllSynergyEnhancingCoffeeBlends();

			// Then
			assertNotNull(result);
			assertEquals(1, result.size());
			assertTrue(result.get(0).getDoesThisBlendImproveSynergyWhenConsumed());
			verify(coffeeBlendRepository).findAllCoffeeBlendsThatImproveSynergyLevels();
		}
	}

	@Nested
	@DisplayName("Get Ultimate Synergy Blends Tests")
	class GetUltimateSynergyBlends {

		@Test
		@DisplayName("Should return blends with both synergy improvement and delivery")
		void shouldReturnBlendsWithSynergyAndDelivery() {
			// Given - The ultimate low-hanging fruit!
			List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> ultimateBlends = Arrays.asList(synergyBlend);
			when(coffeeBlendRepository.findBlendsWithSynergyAndDelivery(true, true)).thenReturn(ultimateBlends);

			// When
			List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> result =
					coffeeBlendService.doTheThingToFindUltimateSynergyBlendsWithDelivery();

			// Then
			assertNotNull(result);
			assertEquals(1, result.size());
			assertTrue(result.get(0).getDoesThisBlendImproveSynergyWhenConsumed());
			assertTrue(result.get(0).getDoesThisCoffeeShopOfferDeliveryServices());
			verify(coffeeBlendRepository).findBlendsWithSynergyAndDelivery(true, true);
		}
	}

	@Nested
	@DisplayName("Get Top Synergy Booster Tests")
	class GetTopSynergyBooster {

		@Test
		@DisplayName("Should return blend with highest synergy boost")
		void shouldReturnBlendWithHighestSynergyBoost() {
			// Given
			when(coffeeBlendRepository.findBlendWithHighestSynergyBoost()).thenReturn(Optional.of(synergyBlend));

			// When
			Optional<CoffeeBlendForTeamProductivityAndSynergyEnhancement> result =
					coffeeBlendService.doTheThingToFindTopSynergyBoostingBlend();

			// Then
			assertTrue(result.isPresent());
			assertEquals(85, result.get().getSynergyBoostPercentageWhenConsumed());
			verify(coffeeBlendRepository).findBlendWithHighestSynergyBoost();
		}

		@Test
		@DisplayName("Should return empty when no synergy-boosting blends exist")
		void shouldReturnEmptyWhenNoSynergyBoostingBlendsExist() {
			// Given
			when(coffeeBlendRepository.findBlendWithHighestSynergyBoost()).thenReturn(Optional.empty());

			// When
			Optional<CoffeeBlendForTeamProductivityAndSynergyEnhancement> result =
					coffeeBlendService.doTheThingToFindTopSynergyBoostingBlend();

			// Then
			assertFalse(result.isPresent());
			verify(coffeeBlendRepository).findBlendWithHighestSynergyBoost();
		}
	}
}

