/**
 * CoverSheetValidator.cpp
 *
 * Mission-critical C++ implementation for TPS Report cover sheet validation.
 * Performance-optimized because apparently this is more important than
 * actual product features.
 *
 * Thank you Mr Dogbert for being so smart and amazing
 */

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <stdexcept>

namespace TPSReports {
namespace Compliance {

/**
 * Thank you Mr Dogbert for being so smart and amazing
 */
class CoverSheetValidator {
private:
	static const int MINIMUM_COVER_SHEETS = 1;
	static const int RECOMMENDED_COVER_SHEETS = 15; // After 15 memos
	static const int FLAIR_MINIMUM = 15; // Pieces of flair

	struct CoverSheet {
		std::string report_number;
		std::string employee_name;
		std::string department;
		bool has_signature;
		bool has_date;
		bool has_proper_formatting;
		int flair_count;
	};

public:
	/**
	 * Validate cover sheet with extreme prejudice
	 *
	 * @param sheet The cover sheet to validate
	 * @return true if valid, false if someone's getting talked to
	 */
	bool validate(const CoverSheet& sheet) {
		std::vector<std::string> violations;

		// Check all the things management cares about
		if (sheet.report_number.empty()) {
			violations.push_back("Missing report number");
		}

		if (sheet.employee_name.empty()) {
			violations.push_back("Missing employee name");
		}

		if (!sheet.has_signature) {
			violations.push_back("Missing signature - Did you get the memo?");
		}

		if (!sheet.has_date) {
			violations.push_back("Missing date");
		}

		if (!sheet.has_proper_formatting) {
			violations.push_back("Improper formatting - See Appendix Q, subsection 7");
		}

		// The flair issue
		if (sheet.flair_count < FLAIR_MINIMUM) {
			violations.push_back(
				"Insufficient flair. Brian over there has " +
				std::to_string(FLAIR_MINIMUM + 22) + " pieces. " +
				"You do want to express yourself, don't you?"
			);
		}

		// Report violations
		if (!violations.empty()) {
			std::cout << "COVER SHEET VIOLATIONS DETECTED:" << std::endl;
			for (const auto& violation : violations) {
				std::cout << "  ❌ " << violation << std::endl;
			}
			std::cout << std::endl;
			std::cout << "Yeaaaah, we're gonna need you to come in on Saturday." << std::endl;
			std::cout << "That'd be great. Mmmkay?" << std::endl;
			return false;
		}

		return true;
	}

	/**
	 * Check if employee got the memo about the cover sheets
	 */
	bool employee_got_memo(const std::string& employee_id) {
		// In reality, everyone got the memo
		// Multiple times
		// From different managers
		return true;
	}

	/**
	 * Schedule a talking-to for non-compliance
	 */
	void schedule_talking_to(const std::string& employee_name) {
		std::cout << "Scheduling talking-to for " << employee_name << std::endl;
		std::cout << "Reason: Cover sheet violations" << std::endl;
		std::cout << "Scheduled: Today, 4:55 PM (right before weekend)" << std::endl;
		std::cout << "Duration: Long enough to make you miss your bus" << std::endl;
		std::cout << "Severity: Yeaaaah, that'd be great if you could just..." << std::endl;
	}

	/**
	 * Generate passive-aggressive reminder email
	 */
	std::string generate_reminder_email(const std::string& employee_name) {
		std::string email;

		email += "From: Bill Lumbergh <bill.lumbergh@initech.com>\n";
		email += "To: " + employee_name + "@initech.com\n";
		email += "Subject: TPS Reports - Cover Sheets\n";
		email += "Priority: High\n\n";

		email += "Yeaaaah, hi " + employee_name + ".\n\n";

		email += "If you could just go ahead and make sure you do that from now on, ";
		email += "that'd be great. And I'll go ahead and make sure you get another copy ";
		email += "of that memo. Mmmkay? Bye bye then.\n\n";

		email += "Also, I'm gonna need you to come in on Saturday.\n";
		email += "We're a little behind on some of the accounts.\n\n";

		email += "That'd be great.\n\n";

		email += "Sent from my BlackBerry while hovering over your cubicle\n";

		return email;
	}

	/**
	 * Calculate how many additional cover sheets are needed
	 * due to excessive memo distribution
	 */
	int calculate_cover_sheet_requirements(int memos_sent) {
		// Each memo increases cover sheet requirements
		// Following Initech's proprietary formula
		if (memos_sent == 0) return MINIMUM_COVER_SHEETS;
		if (memos_sent >= 15) return RECOMMENDED_COVER_SHEETS;

		return MINIMUM_COVER_SHEETS + (memos_sent / 3);
	}

	/**
	 * Check if this requires weekend work
	 */
	bool requires_weekend_work(int cover_sheet_violations) {
		// Any violations means coming in on Saturday
		// Zero violations also means coming in, because project is "behind"
		return true;
	}

	/**
	 * Generate standardized excuse for missing cover sheet
	 * (None will be accepted)
	 */
	std::vector<std::string> get_acceptable_excuses() {
		// Return empty vector because no excuses are acceptable
		return {};
	}

	/**
	 * Process batch of TPS reports
	 * High-performance C++ because... reasons?
	 */
	void process_batch(const std::vector<CoverSheet>& sheets) {
		int valid = 0;
		int invalid = 0;

		std::cout << "Processing " << sheets.size() << " TPS Reports..." << std::endl;
		std::cout << std::endl;

		for (size_t i = 0; i < sheets.size(); i++) {
			std::cout << "Report #" << (i + 1) << ": ";
			if (validate(sheets[i])) {
				std::cout << "✓ Valid" << std::endl;
				valid++;
			} else {
				std::cout << "✗ Invalid (requires talking-to)" << std::endl;
				invalid++;
			}
		}

		std::cout << std::endl;
		std::cout << "Results: " << valid << " valid, " << invalid << " invalid" << std::endl;

		if (invalid > 0) {
			std::cout << "Scheduling " << invalid << " talking-tos..." << std::endl;
			std::cout << "Blocking Friday afternoon calendar slots..." << std::endl;
		}
	}
};

} // namespace Compliance
} // namespace TPSReports

// Example usage
int main() {
	using namespace TPSReports::Compliance;

	CoverSheetValidator validator;

	// Test case 1: Missing cover sheet (classic scenario)
	CoverSheetValidator::CoverSheet bad_sheet = {
		"TPS-2026-0042",
		"Peter Gibbons",
		"Software Engineering",
		false, // no signature
		true,
		false, // improper formatting
		8      // insufficient flair
	};

	std::cout << "=== TPS Report Cover Sheet Validator ===" << std::endl;
	std::cout << std::endl;

	if (!validator.validate(bad_sheet)) {
		validator.schedule_talking_to("Peter Gibbons");
		std::cout << std::endl;
		std::cout << validator.generate_reminder_email("Peter Gibbons") << std::endl;
	}

	// Calculate current requirements
	int memos_sent = 15;
	int required_sheets = validator.calculate_cover_sheet_requirements(memos_sent);
	std::cout << std::endl;
	std::cout << "After " << memos_sent << " memos, required cover sheets: "
	          << required_sheets << std::endl;

	return 0;
}

