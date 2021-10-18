package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Represents an Interview in the HR Manager, with default status 'pending'.
 * Guarantees: immutable; position is valid and not null.
 */
public class Interview {

    // public static final String MESSAGE_CONSTRAINTS = "";


    public static final String MESSAGE_DATE_CONSTRAINTS = "Date should be in correct DD/MM/YYYY format.";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Time should be in correct HHMM format.";
    public static final String MESSAGE_DURATION_CONSTRAINTS = "Duration should be in minutes.";

    private final Position position;

    private InterviewStatus status;

    private final Set<Person> candidates;

    private final LocalTime startTime;

    private final LocalDate date;

    private final Duration duration;

    public enum InterviewStatus {
        PENDING,
        COMPLETED;

        public static final String MESSAGE_CONSTRAINTS = "Interview Status can ony take the values:\n"
                + "pending\n"
                + "completed\n";

        private static final List<String> validStatus = new ArrayList<>(Arrays.asList("PENDING",
                "COMPLETED", ""));

        /**
         * Return true if a give string is a valid interview status.
         * Only "pending" and "completed" are valid strings for interview status.
         *
         * @param test A string to test if is a valid interview status.
         * @return True if test string is a valid interview status, false otherwise.
         */
        public static boolean isValidInterviewStatus(String test) {
            return validStatus.contains(test);
        }

        /**
         * Returns the corresponding InterviewStatus Enum given a valid input.
         *
         * @param statusInput String input
         * @return Status
         */
        public static InterviewStatus parseStatus(String statusInput) {
            statusInput = (statusInput == null ? "" : statusInput);
            if (statusInput.equals("COMPLETED")) {
                return InterviewStatus.COMPLETED;
            } else {
                return InterviewStatus.PENDING;
            }
        }
    }

    /**
     * Constructs a {@code Interview}.
     *
     * @param position   A position for the interview.
     * @param candidates A list of names of the candidates attending the interview.
     * @param date       The date of the interview.
     * @param startTime  The start time of the interview.
     * @param duration   The duration of the interview.
     */
    public Interview(Position position, Set<Person> candidates, LocalDate date,
                     LocalTime startTime, Duration duration) {
        requireAllNonNull(position, candidates, startTime, duration);
        this.position = position;
        this.candidates = candidates;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * Constructs a {@code Interview}.
     *
     * @param position   A position for the interview.
     * @param candidates A list of names of the candidates attending the interview.
     * @param startTime  The start time of the interview.
     * @param date       The date of the interview.
     * @param duration   The duration of the interview.
     * @param status     The interview status.
     */
    public Interview(Position position, Set<Person> candidates, LocalDate date,
                     LocalTime startTime, Duration duration, InterviewStatus status) {
        this(position, candidates, date, startTime, duration);
        if (status != null) {
            this.status = status;
        }
    }

    /**
     * Returns true if both interviews have the same position, candidates, start time, and duration.
     * This defines a weaker notion of equality between two interviews.
     */
    public boolean isSameInterview(Interview otherInterview) {
        if (otherInterview == this) {
            return true;
        }

        return otherInterview != null
                && otherInterview.getPositionTitle().equals(getPositionTitle())
                && otherInterview.getCandidates().equals(getCandidates())
                && otherInterview.getDate().equals(getDate())
                && otherInterview.getStartTime().equals(getStartTime())
                && otherInterview.getDuration().equals(getDuration());
    }

    public Position getPosition() {
        assert this.position != null : "Interview position is non-null";
        return position;
    }

    public Set<Person> getCandidates() {
        assert this.candidates != null : "Interview candidate names set is non-null.";
        return this.candidates;
    }

    public void setCandidates(Set<Person> personSet) {
        assert this.candidates != null : "Interview candidate names set is non-null.";
        this.candidates.addAll(personSet);
    }

    public LocalDate getDate() {
        assert this.date != null : "Interview date is non-null.";
        return this.date;
    }

    public Duration getDuration() {
        assert this.duration != null : "Interview duration is non-null.";
        return this.duration;
    }

    public LocalTime getStartTime() {
        assert this.startTime != null : "Interview start time is non-null.";
        return this.startTime;
    }

    public Title getPositionTitle() {
        assert this.position != null : "Interview position is non-null.";
        return this.position.getTitle();
    }

    public InterviewStatus getStatus() {
        assert this.status != null : "Interview status is non-null.";
        return this.status;
    }

    public String getStatusInString() {
        assert this.status != null : "Interview status is non-null.";
        return this.status.toString();
    }

    public void setStatus(InterviewStatus status) {
        requireNonNull(status);
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interview // instanceof handles nulls
                && position.equals(((Interview) other).position) // position check
                && candidates.equals(((Interview) other).candidates) // candidates check
                && startTime.equals(((Interview) other).startTime) // startTime check
                && duration.equals(((Interview) other).duration)
                && status.equals(((Interview) other).getStatus())); // status check
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, candidates, date, startTime, duration, status);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "[" + getPositionTitle().toString() + " "
                + getCandidates().toString() + " "
                + getDate() + " "
                + getStartTime().toString() + " - "
                + getDuration().toString() + " "
                + getStatusInString() + "]";
    }
}
