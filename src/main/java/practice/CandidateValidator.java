package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE = 35;
    private static final int REQUIRED_YEARS_IN_UKRAINE = 10;
    private static final String REQUIRED_NATIONALITY = "Ukrainian";

    private int getTimeInUkraine(String period) {
        int sum = 0;
        if (period != null) {
            String[] periods = period.split(",");
            for (String singlePeriod : periods) {
                String[] dates = singlePeriod.split("-");
                if (dates.length == 2) {
                    int from = Integer.parseInt(dates[0].trim());
                    int to = Integer.parseInt(dates[1].trim());
                    sum += to - from;
                }
            }
        }
        return sum;
    }

    @Override
    public boolean test(Candidate candidate) {
        if (candidate.getAge() < MIN_AGE) {
            return false;
        }
        if (!REQUIRED_NATIONALITY.equals(candidate.getNationality())) {
            return false;
        }
        if (!candidate.isAllowedToVote()) {
            return false;
        }
        return getTimeInUkraine(candidate.getPeriodsInUkr()) >= REQUIRED_YEARS_IN_UKRAINE;
    }
}
