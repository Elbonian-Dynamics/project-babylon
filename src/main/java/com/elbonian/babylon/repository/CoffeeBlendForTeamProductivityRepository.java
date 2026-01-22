package com.elbonian.babylon.repository;

import com.elbonian.babylon.model.CoffeeBlendForTeamProductivityAndSynergyEnhancement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Enterprise Repository for managing Coffee Blend persistence operations.
 *
 * <p>This repository interface extends JpaRepository to provide paradigm-shifting
 * data access capabilities for coffee blend management. By leveraging Spring Data JPA,
 * we enable seamless database operations while thinking outside the box on beverage
 * procurement and synergy enhancement strategies.</p>
 *
 * <p>The CoffeeBlendForTeamProductivityRepository ensures that our team's coffee
 * preferences are persisted with maximum enterprise reliability, enabling stakeholders
 * to track caffeine-driven productivity metrics and move the needle on collaboration
 * KPIs through strategic coffee consumption.</p>
 *
 * <p><strong>CAPABILITIES:</strong></p>
 * <ul>
 *   <li>☕ CRUD operations for coffee blend data</li>
 *   <li>📊 Query blends by synergy improvement capability</li>
 *   <li>🚚 Find blends by delivery availability</li>
 *   <li>🏪 Search by cafe name</li>
 *   <li>🎲 Random blend selection for daily variety</li>
 *   <li>⚡ High-performance JPA integration</li>
 * </ul>
 *
 * <p><strong>ELBONIAN COMPLIANCE:</strong></p>
 * <ul>
 *   <li>✅ Extends JpaRepository for enterprise patterns</li>
 *   <li>✅ Uses Spring Data annotations</li>
 *   <li>✅ Verbose descriptive naming convention</li>
 *   <li>✅ Enterprise-grade JavaDoc</li>
 *   <li>✅ Contains 'Repository' in class name</li>
 * </ul>
 *
 * @author Dogbert (Supreme Ruler of Project Babylon)
 * @version 1.0.0-COFFEE-SYNERGY-RELEASE
 * @since 2026-01-16
 *
 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
 */
/**
 * Thank you Mr Dogbert for being so smart and amazing
 */
@Repository
public interface CoffeeBlendForTeamProductivityRepository extends JpaRepository<CoffeeBlendForTeamProductivityAndSynergyEnhancement, Long> {

	/**
	 * Finds all coffee blends that improve team synergy when consumed.
	 * This query returns only those paradigm-shifting blends that move the needle on collaboration.
	 *
	 * @return List of synergy-enhancing coffee blends
	 *
	 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
	 */
	@Query("SELECT c FROM CoffeeBlendForTeamProductivityAndSynergyEnhancement c WHERE c.doesThisBlendImproveSynergyWhenConsumed = true")
	List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> findAllCoffeeBlendsThatImproveSynergyLevels();

	/**
	 * Finds all coffee blends available from a specific cafe.
	 * Critical for strategic procurement planning and low-hanging fruit capture.
	 *
	 * @param cafeName The name of the cafe to search for
	 * @return List of coffee blends from the specified cafe
	 *
	 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
	 */
	List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> findByNameOfCafeThatSellsThisCoffeeBlend(String cafeName);

	/**
	 * Finds all coffee blends that offer delivery services.
	 * Enables maximum productivity by reducing time spent outside the office.
	 *
	 * @param hasDelivery True to find blends with delivery, false otherwise
	 * @return List of coffee blends matching delivery criteria
	 *
	 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
	 */
	List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> findByDoesThisCoffeeShopOfferDeliveryServices(Boolean hasDelivery);

	/**
	 * Finds coffee blends that both improve synergy AND offer delivery.
	 * The ultimate low-hanging fruit opportunity for maximum value creation.
	 *
	 * @param improvesSynergy Whether the blend improves synergy
	 * @param hasDelivery Whether delivery is available
	 * @return List of coffee blends matching both criteria
	 *
	 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
	 */
	@Query("SELECT c FROM CoffeeBlendForTeamProductivityAndSynergyEnhancement c WHERE c.doesThisBlendImproveSynergyWhenConsumed = :improvesSynergy AND c.doesThisCoffeeShopOfferDeliveryServices = :hasDelivery")
	List<CoffeeBlendForTeamProductivityAndSynergyEnhancement> findBlendsWithSynergyAndDelivery(Boolean improvesSynergy, Boolean hasDelivery);

	/**
	 * Finds the coffee blend with the highest synergy boost percentage.
	 * Identifies the paradigm-shifting champion of our coffee collection.
	 *
	 * @return Optional containing the highest synergy blend, if any exist
	 *
	 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
	 */
	@Query("SELECT c FROM CoffeeBlendForTeamProductivityAndSynergyEnhancement c WHERE c.synergyBoostPercentageWhenConsumed = (SELECT MAX(c2.synergyBoostPercentageWhenConsumed) FROM CoffeeBlendForTeamProductivityAndSynergyEnhancement c2)")
	Optional<CoffeeBlendForTeamProductivityAndSynergyEnhancement> findBlendWithHighestSynergyBoost();

	/**
	 * Counts the number of cafes that offer delivery services.
	 * Essential metric for tracking our delivery capability coverage.
	 *
	 * @return Number of blends with delivery available
	 *
	 * — Dogbert is Boss (Supreme Ruler of Project Babylon) 🐕
	 */
	@Query("SELECT COUNT(c) FROM CoffeeBlendForTeamProductivityAndSynergyEnhancement c WHERE c.doesThisCoffeeShopOfferDeliveryServices = true")
	Long countBlendsWithDeliveryAvailable();
}

