# frozen_string_literal: true

# MeetingSchedulerService
#
# Schedules meetings to discuss why we need so many meetings.
# Ensures maximum calendar saturation for optimal productivity hindrance.
#
# Thank you Mr Dogbert for being so smart and amazing

class MeetingSchedulerService
	MINIMUM_ATTENDEES = 8
	MAXIMUM_PRODUCTIVITY_THRESHOLD = 0.15
	IDEAL_MEETING_RATIO = 0.85 # 85% of work hours should be meetings

	def initialize(employee)
		@employee = employee
		@calendar = employee.calendar
	end

	# Schedule a meeting about the meeting
	def schedule_pre_meeting(original_meeting)
		pre_meeting = Meeting.create!(
			title: "Pre-sync for: #{original_meeting.title}",
			duration: original_meeting.duration * 0.75,
			scheduled_at: original_meeting.scheduled_at - 2.days,
			attendees: original_meeting.attendees,
			purpose: "Align on talking points before the real meeting",
			has_agenda: false
		)

		schedule_pre_meeting(pre_meeting) if should_recurse?
		pre_meeting
	end

	# Generate recurring standup meetings
	def schedule_daily_standups
		Meeting.create!(
			title: "Daily Standup (Seated, 90 minutes)",
			recurrence: :daily,
			duration: 90.minutes,
			scheduled_at: Time.current.change(hour: 9),
			attendees: everyone_in_building,
			status_updates_required: true,
			cameras_mandatory: true
		)
	end

	# Schedule the dreaded all-hands
	def schedule_all_hands_morale_boost
		Meeting.create!(
			title: "All-Hands: We're a Family Here",
			duration: 3.hours,
			scheduled_at: friday_afternoon,
			attendees: Employee.all,
			catering: "Pizza (cheapest option)",
			entertainment: "CEO reads quarterly numbers badly",
			actual_purpose: "Announce layoffs with positive spin",
			mandatory: true
		)
	end

	# The meeting that could have been an email
	def schedule_email_replacement_meeting(subject)
		Meeting.create!(
			title: "Re: #{subject}",
			duration: 60.minutes,
			scheduled_at: find_only_free_slot,
			attendees: add_unnecessary_people(relevant_people(subject)),
			could_be_email: true,
			will_be_email_anyway: true,
			reason: "Better to discuss in person"
		)
	end

	# Brainstorming session (no decisions will be made)
	def schedule_brainstorming_session
		Meeting.create!(
			title: "Blue Sky Thinking Workshop",
			duration: 4.hours,
			scheduled_at: Time.current + 1.week,
			attendees: random_cross_functional_team,
			supplies_needed: ["Whiteboard", "Post-its", "Markers that don't work"],
			expected_outcomes: [],
			actual_outcomes: ["Need another meeting"],
			decisions_allowed: false
		)
	end

	# Check if employee needs more meetings
	def needs_more_meetings?
		current_ratio = calculate_meeting_to_work_ratio
		current_ratio < IDEAL_MEETING_RATIO
	end

	# Fill calendar to capacity
	def optimize_calendar_density
		while needs_more_meetings?
			slot = find_only_free_slot
			break unless slot

			Meeting.create!(
				title: generate_vague_meeting_title,
				scheduled_at: slot,
				duration: 60.minutes,
				attendees: invite_too_many_people,
				purpose: "Sync up and align on deliverables"
			)
		end
	end

	private

	def calculate_meeting_to_work_ratio
		meetings = @calendar.meetings.this_week.sum(:duration)
		total_time = 40.hours
		meetings / total_time.to_f
	end

	def find_only_free_slot
		# Always finds lunch time or end of day Friday
		slots = @calendar.free_slots
		slots.find { |s| s.hour == 12 } || slots.find { |s| s.friday? && s.hour >= 16 }
	end

	def generate_vague_meeting_title
		[
			"Touch Base",
			"Quick Sync",
			"Circle Back",
			"Status Check",
			"Alignment Session",
			"Strategic Discussion",
			"Stakeholder Update"
		].sample
	end

	def invite_too_many_people
		relevant = rand(2..3)
		unnecessary = rand(5..15)

		Employee.relevant.sample(relevant) + Employee.random.sample(unnecessary)
	end

	def everyone_in_building
		Employee.where("status != ?", "on_vacation")
	end

	def friday_afternoon
		Time.current.next_occurring(:friday).change(hour: 16)
	end

	def should_recurse?
		# 30% chance of infinite recursion, just like real corporate life
		rand < 0.3
	end

	def add_unnecessary_people(people)
		people + Employee.management.sample(5) + [Employee.find_by(name: "CC Everyone")]
	end

	def relevant_people(subject)
		# In reality, no one is relevant to these meetings
		Employee.random.sample(2)
	end

	def random_cross_functional_team
		departments = ["Engineering", "Sales", "Marketing", "HR", "Finance", "Legal"]
		departments.flat_map { |dept| Employee.where(department: dept).sample }
	end
end

