package practice;

import model.Candidate;

import java.util.function.Predicate;

public class CandidateValidator implements Predicate<Candidate> {

    private int getTimeInUkraine(String period) {
        if (period != null) {
            String[] dates = period.split("-");
            if (dates.length == 2) {
                int from = Integer.parseInt(dates[0]);
                int to = Integer.parseInt(dates[1]);
                return to - from;
            }
        }
        return 0;
    }

    @Override
    public boolean test(Candidate candidate) {
        if (candidate.getAge() < 35) {
            return false;
        }
        if (!candidate.getNationality().equals("Ukrainian")) {
            return false;
        }
        if (!candidate.isAllowedToVote()) {
            return false;
        }
        return getTimeInUkraine(candidate.getPeriodsInUkr()) >= 10;
    }
}
