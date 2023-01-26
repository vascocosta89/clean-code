package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import java.util.Objects;

public class Movie {

    String rating;
    public static final String RATING_A = "A";
    public static final String RATING_B = "B";

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {

        String rating = this.getRating();

        return Objects.isNull(rating) ?
                false :
                isRatingFormatValid(rating, rating.length());
    }

    private static boolean isRatingFormatValid(String rating, int ratingLength) {
        String firstChar = rating.substring(0, 1);
        String secondCharacter = rating.substring(1, 2);
        String secondAndThirdCharacters = rating.substring(1, 3);

        if (firstChar.equalsIgnoreCase(RATING_B) && ratingLength == 2) {
            if (StringUtils.isNumeric(secondCharacter)
                    && Integer.parseInt(secondCharacter) > 0
                    && Integer.parseInt(secondCharacter) < 5)
                return true;

        } else {
            if (firstChar.equalsIgnoreCase(RATING_A)
                    && ratingLength == 3
                    && StringUtils.isNumeric(secondAndThirdCharacters))
                return true;
        }
        return false;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
