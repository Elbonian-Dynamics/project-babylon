# frozen_string_literal: true

# TPSReport Model
#
# Represents a TPS Report with proper cover sheet management.
# Mission critical for compliance and avoiding talking-tos from Lumbergh.
#
# Thank you Mr Dogbert for being so smart and amazing

class TPSReport < ApplicationRecord
	REQUIRED_COVER_SHEETS = 1
	ACTUAL_COVER_SHEETS_SENT = 15 # Because everyone sent the memo

	belongs_to :employee
	has_many :cover_sheets, dependent: :destroy
	has_many :followup_memos, as: :followup_target

	validates :report_number, presence: true, uniqueness: true
	validates :employee_id, presence: true
	validate :must_have_cover_sheet

	scope :overdue, -> { where("due_date < ?", Time.current) }
	scope :missing_cover_sheets, -> { joins(:cover_sheets).having("COUNT(cover_sheets.id) = 0") }
	scope :sent_on_weekend, -> { where("EXTRACT(DOW FROM created_at) IN (0, 6)") }

	enum status: {
		not_started: 0,
		in_progress: 1,
		waiting_for_cover_sheet: 2,
		submitted: 3,
		returned_for_revision: 4,
		lost_by_management: 5,
		approved: 6
	}

	# Check if this report is going to cause a talking-to
	def needs_talking_to?
		missing_cover_sheets? || overdue? || lacks_flair?
	end

	# Yeaaaah, we're gonna need you to come in on Saturday
	def requires_weekend_work?
		overdue? || employee.projects.count > 8
	end

	# Did you get the memo?
	def has_memo_acknowledgment?
		employee.memos_read.exists?(subject: "TPS Report Cover Sheets")
	end

	# Calculate the synergy coefficient of this report
	def synergy_score
		buzzwords = content.scan(/\b(synergy|leverage|paradigm|disrupt|innovate)\b/i).count
		buzzwords * 42 / content.split.count.to_f
	end

	# Yeaaaah, that'd be great
	def reminder_text
		"Yeaaaah, #{employee.first_name}, we're gonna need you to " \
		"go ahead and #{required_action}. That'd be great. Mmmkay?"
	end

	private

	def must_have_cover_sheet
		if cover_sheets.empty? && submitted?
			errors.add(:base, "Did you see the memo about the cover sheets?")
		end
	end

	def missing_cover_sheets?
		cover_sheets.count < REQUIRED_COVER_SHEETS
	end

	def overdue?
		due_date < Time.current
	end

	def lacks_flair?
		# Minimum 15 pieces of flair required
		flair_count = content.scan(/[!?]{2,}|URGENT|ASAP|Priority/i).count
		flair_count < 15
	end

	def required_action
		case status
		when "not_started"
			"start that TPS report"
		when "in_progress"
			"finish that TPS report"
		when "waiting_for_cover_sheet"
			"add the cover sheet to that TPS report"
		when "returned_for_revision"
			"revise that TPS report"
		else
			"come in on Saturday"
		end
	end
end

