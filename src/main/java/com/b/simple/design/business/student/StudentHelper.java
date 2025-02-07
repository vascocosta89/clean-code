package com.b.simple.design.business.student;
public class StudentHelper {

	public static final int GRADE_A_LOWER_LIMIT = 90;
	public static final int GRADE_B_UPPER_LIMIT = 80;
	public static final int GRADE_NOT_GOOD = 20;
	private static final int GRADE_B_UPPER_LIMIT_II = 90;
	public static final int GRADE_B_LOWER_LIMIT = 51;
	public static final int EXTRA_FOR_MATHS = 10;
	public static final int EXTRA_FOR_MATHS_GRADE = 5;

	private static final String GRADE_A = "A";
	private static final String GRADE_B = "B";
	private static final String GRADE_C = "C";
	public static final String NO = "NO";
	public static final String YES = "YES";
	public static final String MAYBE = "MAYBE";
	public static final int GRADE_GOOD = 80;


	/* PROBLEM 1 */
	/*
	* You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
	*/
	public boolean isGradeB(int marks, boolean isMaths) {

		int extraLimit = isMaths ? EXTRA_FOR_MATHS : 0;

		int upperLimit = GRADE_B_UPPER_LIMIT + extraLimit;

		return  marks>=GRADE_B_LOWER_LIMIT && marks<=upperLimit;
	}

	/* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

	public String getGrade(int mark, boolean isMaths) {

		return 	isGradeA(mark, isMaths) ? 	 GRADE_A :
				isBGrade(mark,isMaths)  ? 	 GRADE_B :
									 		 GRADE_C;
	}

	private boolean isGradeA(int mark, boolean isMaths) {

		int extraLimit = isMaths ? EXTRA_FOR_MATHS_GRADE : 0;

		int lowerLimit = GRADE_A_LOWER_LIMIT + extraLimit;

		return mark > lowerLimit;
	}

	private boolean isBGrade(int mark, boolean isMaths) {

		int extraLimit = isMaths ? EXTRA_FOR_MATHS_GRADE : 0;

		int upperLimit = GRADE_B_UPPER_LIMIT_II + extraLimit;
		int lowerLimit = GRADE_B_LOWER_LIMIT + extraLimit;

		return  mark>= lowerLimit && mark<=upperLimit;
	}

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     * 
     * Return value can be YES, NO or MAYBE.
     * 
     * YES If either of you are very good at the subject(has 80 or more marks)
     *
     * maths +5
     *
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     * 
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     * 
     * marks1 - your marks
     * marks2 - your friends marks
    */
        
    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
		int extra = isMaths ? EXTRA_FOR_MATHS_GRADE : 0;

		if(marks1 >= GRADE_GOOD 	+ extra || marks2 >= GRADE_GOOD 	+ extra)
			return YES;
		if(marks1 <= GRADE_NOT_GOOD + extra || marks2 <= GRADE_NOT_GOOD + extra)
			return NO;

        return MAYBE;
    }

}