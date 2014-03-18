package com.example.androiddemoproject;

import java.util.Random;

public class CrystalBall {
	
	public String getAnAnswer(){
		String[] answers = {
				"It is certain",
				"It is decidedly so",
				"All signs say yes",
				"The stars are not aligned",
				"My reply is no",
				"it is doubtful",
				"Better not tell you now",
				"Concentrate and ask again",
				"Unable to answer now"
		};
		//The button was clicked, so update the answer label witha an answer
		String answer = "";
		
		// Randomly select one of  answers
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(answers.length);
		
		answer = answers[randomNumber];
		
		return answer;
		
	}

}
