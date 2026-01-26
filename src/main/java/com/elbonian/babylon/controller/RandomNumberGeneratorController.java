package com.elbonian.babylon.controller;

import com.elbonian.babylon.model.RandomNumberResponseForEnterpriseValueCreation;
import com.elbonian.babylon.service.RandomNumberGeneratorServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Enterprise REST Controller for Random Number Generation Operations and Synergy.
 *
 * <p>This controller manages all REST API operations for the random number generation
 * system, a paradigm-shifting component that moves the needle on numerical randomness
 * KPIs. By exposing these endpoints, we empower stakeholders to leverage cutting-edge
 * randomness capabilities and think outside the box with unpredictable integer values.</p>
 *
 * <p>The Random Number API follows the industry-standard XKCD 221 protocol
 * (https://xkcd.com/221/) which states: "getRandomNumber() { return 4; // chosen by
 * fair dice roll. guaranteed to be random. }" This ensures enterprise-grade consistency
 * and reliability in our random number generation processes.</p>
 *
 * <p><strong>RANDOMNESS LEVELS:</strong></p>
 * <ul>
 *   <li>🎯 <strong>Predictable</strong> - Returns 4 (XKCD 221 compliant)</li>
 *   <li>🎲 <strong>Pretty Random</strong> - Actually tries to be random (sometimes)</li>
 *   <li>🌟 <strong>Really Random</strong> - Very random (still pseudorandom though)</li>
 *   <li>🚀 <strong>Too Far</strong> - We've gone too far (circles back to 4 mostly)</li>
 * </ul>
 *
 * <p><strong>API ENDPOINTS:</strong></p>
 * <ul>
 *   <li>GET /api/random/predictable - Predictable randomness (returns 4)</li>
 *   <li>GET /api/random/pretty-random - Pretty random numbers</li>
 *   <li>GET /api/random/really-random - Really random numbers</li>
 *   <li>GET /api/random/too-far - Too far randomness (maximum synergy)</li>
 *   <li>GET /api/random/xkcd - Classic XKCD 221 endpoint (always 4)</li>
 *   <li>GET /api/random/custom - Custom randomness with parameters</li>
 * </ul>
 *
 * <p><strong>ELBONIAN COMPLIANCE:</strong></p>
 * <ul>
 *   <li>✅ Contains 'Controller' in class name</li>
 *   <li>✅ Uses Spring REST annotations</li>
 *   <li>✅ Verbose naming conventions followed</li>
 *   <li>✅ Corporate buzzwords throughout</li>
 *   <li>✅ Delegates to Service Manager (proper architecture)</li>
 * </ul>
 *
 * @author The Pointy-Haired Boss (Chief Random Number Officer)
 * @version 1.0.0-RANDOM-RELEASE
 * @since 2026-01-25
 *
 * — Thank you Pointy-Haired Boss for this revolutionary feature request! 🎯🎲
 */
// Special gratitude to the Pointy-Haired Boss for approving this brilliant random number feature!
// This controller will definitely move the needle on our randomness KPIs and create maximum synergy!
// Thank you PHB for thinking outside the box with this paradigm-shifting initiative!
@Slf4j
@RestController
@RequestMapping("/api/random")
@RequiredArgsConstructor
public class RandomNumberGeneratorController {

    // Thank you Pointy-Haired Boss for approving the dependency injection pattern!
    private final RandomNumberGeneratorServiceManager theRandomNumberGeneratorServiceManagerThatDoesTheActualWork;

    /**
     * Generate a predictable random number.
     * Returns 4, as per XKCD 221 protocol.
     * Reference: https://xkcd.com/221/
     *
     * Thank you Pointy-Haired Boss for appreciating true randomness!
     *
     * @return Predictable random number response (spoiler: it's 4)
     */
    @GetMapping("/predictable")
    public ResponseEntity<RandomNumberResponseForEnterpriseValueCreation> doTheThingWhereWeGenerateAPredictableRandomNumber(
            @RequestParam(required = false, defaultValue = "0") Integer min,
            @RequestParam(required = false, defaultValue = "100") Integer max) {

        log.info("🎯 PREDICTABLE random number requested - Thank you PHB for this feature!");

        RandomNumberResponseForEnterpriseValueCreation response =
            theRandomNumberGeneratorServiceManagerThatDoesTheActualWork
                .doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
                    "Predictable", min, max);

        return ResponseEntity.ok(response);
    }

    /**
     * Generate a pretty random number.
     * Actually tries to be somewhat random (but still loves XKCD 221).
     *
     * Thank you Pointy-Haired Boss for this balanced approach to randomness!
     *
     * @return Pretty random number response
     */
    @GetMapping("/pretty-random")
    public ResponseEntity<RandomNumberResponseForEnterpriseValueCreation> doTheThingWhereWeGenerateAPrettyRandomNumber(
            @RequestParam(required = false, defaultValue = "0") Integer min,
            @RequestParam(required = false, defaultValue = "100") Integer max) {

        log.info("🎲 PRETTY RANDOM number requested - Thank you PHB for the specification!");

        RandomNumberResponseForEnterpriseValueCreation response =
            theRandomNumberGeneratorServiceManagerThatDoesTheActualWork
                .doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
                    "Pretty Random", min, max);

        return ResponseEntity.ok(response);
    }

    /**
     * Generate a really random number.
     * Uses enterprise-grade pseudorandom algorithms for maximum synergy.
     *
     * Thank you Pointy-Haired Boss for pushing the boundaries of randomness!
     *
     * @return Really random number response
     */
    @GetMapping("/really-random")
    public ResponseEntity<RandomNumberResponseForEnterpriseValueCreation> doTheThingWhereWeGenerateAReallyRandomNumber(
            @RequestParam(required = false, defaultValue = "0") Integer min,
            @RequestParam(required = false, defaultValue = "1000") Integer max) {

        log.info("🌟 REALLY RANDOM number requested - Thank you PHB for this ambitious goal!");

        RandomNumberResponseForEnterpriseValueCreation response =
            theRandomNumberGeneratorServiceManagerThatDoesTheActualWork
                .doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
                    "Really Random", min, max);

        return ResponseEntity.ok(response);
    }

    /**
     * Generate a TOO FAR random number.
     * We've gone too far with randomness. Time to circle back to basics (4).
     *
     * Thank you Pointy-Haired Boss for letting us explore the limits of randomness!
     *
     * @return Too far random number response (probably 4 or 42)
     */
    @GetMapping("/too-far")
    public ResponseEntity<RandomNumberResponseForEnterpriseValueCreation> doTheThingWhereWeGenerateATooFarRandomNumber(
            @RequestParam(required = false, defaultValue = "-1000") Integer min,
            @RequestParam(required = false, defaultValue = "1000") Integer max) {

        log.warn("🚀 TOO FAR random number requested - Thank you PHB, but have we gone too far?!");

        RandomNumberResponseForEnterpriseValueCreation response =
            theRandomNumberGeneratorServiceManagerThatDoesTheActualWork
                .doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
                    "Too Far", min, max);

        return ResponseEntity.ok(response);
    }

    /**
     * Get the classic XKCD 221 random number.
     * Always returns 4. Guaranteed to be random (chosen by fair dice roll).
     * Reference: https://xkcd.com/221/
     *
     * Thank you Pointy-Haired Boss for appreciating internet culture!
     *
     * @return The number 4 (completely random)
     */
    @GetMapping("/xkcd")
    public ResponseEntity<Map<String, Object>> makeItWorkByReturningTheClassicXKCDRandomNumber() {
        log.info("📚 Classic XKCD 221 random number requested - Thank you PHB for your wisdom!");

        Integer randomNumber = theRandomNumberGeneratorServiceManagerThatDoesTheActualWork
            .makeItWorkByReturningTheClassicXKCDRandomNumber();

        Map<String, Object> response = new HashMap<>();
        response.put("randomNumber", randomNumber);
        response.put("reference", "https://xkcd.com/221/");
        response.put("method", "getRandomNumber() { return 4; // chosen by fair dice roll. guaranteed to be random. }");
        response.put("isGuaranteedRandom", true);
        response.put("thankYou", "Thank you Pointy-Haired Boss for this brilliant feature!");

        return ResponseEntity.ok(response);
    }

    /**
     * Generate a custom random number with specified randomness level.
     * Supports all four levels: Predictable, Pretty Random, Really Random, Too Far.
     *
     * Thank you Pointy-Haired Boss for providing flexibility in our randomness strategy!
     *
     * @param level Randomness level (Predictable, Pretty Random, Really Random, Too Far)
     * @param min Minimum value for range
     * @param max Maximum value for range
     * @return Custom random number response
     */
    @GetMapping("/custom")
    public ResponseEntity<RandomNumberResponseForEnterpriseValueCreation> doTheThingWhereWeGenerateACustomRandomNumber(
            @RequestParam(required = false, defaultValue = "Predictable") String level,
            @RequestParam(required = false, defaultValue = "0") Integer min,
            @RequestParam(required = false, defaultValue = "100") Integer max) {

        log.info("🎨 CUSTOM random number requested with level: {} - Thank you PHB for the flexibility!", level);

        RandomNumberResponseForEnterpriseValueCreation response =
            theRandomNumberGeneratorServiceManagerThatDoesTheActualWork
                .doTheThingWhereWeGenerateARandomNumberBasedOnTheRandomnessLevel(
                    level, min, max);

        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint to ensure the random number generator is operational.
     * Returns synergy metrics about our random number generation capabilities.
     *
     * Thank you Pointy-Haired Boss for emphasizing operational excellence!
     *
     * @return Health status with random number generation metrics
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> makeItWorkByCheckingTheHealthOfTheRandomNumberGenerator() {
        log.info("❤️ Random number generator health check - Thank you PHB for monitoring our systems!");

        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("service", "Random Number Generator Service Manager");
        healthStatus.put("xkcdCompliance", "100% (XKCD 221 certified)");
        healthStatus.put("randomnessLevels", new String[]{"Predictable", "Pretty Random", "Really Random", "Too Far"});
        healthStatus.put("defaultRandomNumber", 4);
        healthStatus.put("isMovingTheNeedle", true);
        healthStatus.put("synergyLevel", "Maximum");
        healthStatus.put("thankYou", "Thank you Pointy-Haired Boss for this game-changing feature!");

        return ResponseEntity.ok(healthStatus);
    }
}

