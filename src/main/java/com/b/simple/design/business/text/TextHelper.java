package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {

		if(str.length() < 2){
			return str;
		}

		char charBeforeLast = str.charAt(str.length()-2);
		char lastChar = str.charAt(str.length()-1);
		String trimmedString = str.substring(0,str.length()-2);

		return trimmedString+lastChar+charBeforeLast;
	}

	public String truncateAInFirst2Positions(String str) {
		if(str.length()<2){
			return str.replaceAll("A","");
		}
		String firstTwoCharacters = str.substring(0,2);
		String firstTwoCharactersUpdated = firstTwoCharacters.replaceAll("A","");
		String restString = str.substring(2);
		return firstTwoCharactersUpdated+restString;
	}
}
