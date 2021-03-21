/*
 * name: Aarin Jasikumar
 * course: ICS3U1-04
 * program name: Blackjack
 * 
 * This program executes a game called 21, also known as Blackjack.
 * The user is asked to draw cards to try and get to 21. This game 
 * uses a variety of different methods that are called to create the
 * deck, draw a card, find the total of the deck etc. Enjoy!
 * 
 * pre: none
 * post: executes various method based on input of user, prints their
 * cards as well as the dealers and exits when user enters "no".
 * 
*/

import java.util.Random;
import java.util.Scanner;

public class Jasikumar_Aarin_Blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//get ready to input values
		Scanner input = new Scanner(System.in);
		
		//declare variables
		String answer = "YES",name, enter = "x";
		int wins = 0;
		final String sentinel = "YES";
		
		System.out.println("\nPlease re-size the window to find the line below.");
		System.out.println("***************************************************************************************");
     	
		//asks user to enter name
		System.out.print("\nPlease Enter your name: ");
		name = input.next();	
		
		//continues asking until user inputs yes or no
		do {
		System.out.print("\nWeclome to BlackJack "+name+"! Would you like to play? (yes or no): ");
		answer = input.next();	
		answer = answer.toUpperCase();
		}		
		while (!answer.equals("YES") && !answer.equals("NO"));
			
		//repeats entire game as long as answer and sentinel are equal 
		while(answer.equals(sentinel)) {	
			
			//continues asking until user inputs '.'
			while (!enter.equals(".")) {
				System.out.print("\nPlease enter a period, \".\", to continue throughout the game");
				enter = input.next();	
			}
			enter = "x";
			
			//continues displaying until user inputs '.'
			while (!enter.equals(".")) {
				System.out.print("\nSo the rules are simple! Its you verses the dealer! You are first dealt 2 cards \nand you must reach a total of 21. You may draw as many cards as you would like \nbut if you go over 21 you automatically lose! Also, if you can't get to 21 you must \ntry to get closer to 21 than the dealer. Goodluck "+name+"!");
				enter = input.next();	
			}
			enter = "x";
			
			//Deck
			int numberOfCards = 52;
			int [] deck = new int[numberOfCards];
			
			//UserHand
			int currentCard,cardCount = 0,total = 0;
			String [] valueHand = new String[9];
			String [] suitHand  = new String[9];
			int [] ace = new int[9];
			String currentSuit;
			
			//ComputerHand
			int comCurrentCard,comCardCount = 0,comTotal = 0;
			String [] comValueHand = new String[9];
			String [] comSuitHand = new String[9];
			int [] comAce = new int[9];
			String comCurrentSuit;
			
			//GameVariables
			int counter = 1,decision = 0, tempWin, draw = 0;
			String newCard = "";		

			getDeck(deck);
			
			System.out.println("\n***************************************************************************************\n");
			System.out.print("NEWGAME\n\nHere are your cards " + name+".");
			
			//repeats twice to draw two cards for that go into the users hand
			for (int i =0 ;i < 2; i++) {
				int [] tempDeck = new int[numberOfCards-counter];
				currentCard = drawCard(deck,tempDeck,counter);
				deck = new int[tempDeck.length];
				
				//copies values of tempDeck into deck
				for (int a = 0 ; a < deck.length ; a++) {
					deck[a] = tempDeck[a];
				}
				counter++;				
		
				decision = 1;
				currentSuit = getSuit(currentCard);
				suitHand[cardCount] = currentSuit;
				valueHand[cardCount] = getValue(currentCard);
				
				cardCount++;
				
			}
			System.out.println("\n");
			printHand(cardCount,valueHand,suitHand,decision);	
			total = getTotal(valueHand, cardCount,decision,suitHand,ace,draw);
			
			
			System.out.print("\n\nYour total is: "+ total);
			
			//continues asking until user inputs '.'
			while (!enter.equals(".")) {
				enter = input.next();	
			}	
			enter = "x";
			
			System.out.println("\n***************************************************************************************\n");
			
			//repeats twice to draw two cards for that go into the dealers hand
			for (int i =0 ;i < 2; i++) {
				int [] tempDeck = new int[numberOfCards-counter];
				comCurrentCard = drawCard(deck,tempDeck,counter);
				
				deck = new int[tempDeck.length];
				
				//copies values of tempDeck into deck
				for (int a = 0 ; a < deck.length ; a++) {
					deck[a] = tempDeck[a];
				}
				counter++;				
				
				decision = 2;
				comCurrentSuit = getSuit(comCurrentCard);
				comSuitHand [comCardCount] = comCurrentSuit;
				comValueHand[comCardCount] = getValue(comCurrentCard);
				
				comCardCount+=1;
			}
			
			System.out.println("Here are the dealers cards.\n");
			
			comTotal = getTotal(comValueHand, comCardCount,decision,comSuitHand,comAce,draw);
			//if dealer gets a total of 21 printHand will reveal the hidden card
			if (comTotal == 21) {
				decision = 1;
				System.out.println("The dealer has 21!\n");
				
			}
			printHand(comCardCount,comValueHand,comSuitHand,decision);
			
			//continues the same number of times as cards in dealers hand
			for(int x = 0 ; x < comCardCount ; x++) {
				//checks if dealer has an Ace
				if (comValueHand[x] == "A") {
					comTotal = checkAce(comValueHand,comTotal,x,comAce);
				}
			}
	
			//continues asking until user inputs '.'
			while (!enter.equals(".")) {
				enter = input.next();	
			}	
			enter = "x";
			
			System.out.println("\n***************************************************************************************\n");
			
			//continues asking until user inputs 'yes' or 'no'
			do {
				System.out.print("Would you like to draw a card? (yes or no): ");
				newCard = input.next();
				newCard = newCard.toUpperCase();
				}		
				while (!newCard.equals("YES") && !newCard.equals("NO"));
					
			//continues as long as newCard equals 'yes'
			while(newCard.equals("YES")) {
				draw = 1;
				//only runs if total is less than 21
				if (total < 21) {	
					System.out.print("\n\nHere are your cards.\n");
					int [] tempDeck = new int[numberOfCards-counter];
					currentCard = drawCard(deck,tempDeck,counter);
					deck = new int[tempDeck.length];
					
					//copies values of tempDeck into deck
					for (int a = 0 ; a < deck.length ; a++) {
						deck[a] = tempDeck[a];
					}
					counter++;				
			
					decision = 1;
					currentSuit = getSuit(currentCard);
					suitHand[cardCount] = currentSuit;
					valueHand[cardCount] = getValue(currentCard);
					
					cardCount++;
					
					System.out.print("\n");
					printHand(cardCount,valueHand,suitHand,decision);
					total = getTotal(valueHand, cardCount,decision,suitHand,ace,draw);
				
				
					System.out.print("\n\nYour new total is: "+ total);
					
					//continues asking until user inputs '.'
					while (!enter.equals(".")) {
						enter = input.next();	
					}	
					enter = "x";
				}
				//changes newCard to 'no' if total is greater than or equaled to 21
				if(total >= 21) 
					newCard = "NO";
				//asks user if they would like to draw another card
				else {
					//continues asking until user inputs 'yes' or 'no'
					do {
						System.out.println("\n***************************************************************************************");
						System.out.print("\nWould you like to draw another card? (yes or no): ");
						newCard = input.next();
						newCard = newCard.toUpperCase();
					}		
					while (!newCard.equals("YES") && !newCard.equals("NO"));
				}
			
			}
			
			draw = 0;

			//continues as long as dealer total is less than 17, user total is less
			//than or equaled to 21 and if comTotal is less than or equaled to user total
			while (comTotal < 17 && total <=21 && comTotal <= total) {
				System.out.println("\n***************************************************************************************");	
				System.out.print("\nThe dealer decided to draw card!");
			
				//continues asking until user inputs '.'
				while (!enter.equals(".")) {
					enter = input.next();	
				}	
				enter = "x";
				int [] tempDeck = new int[numberOfCards-counter];
				comCurrentCard = drawCard(deck,tempDeck,counter);
				
				deck = new int[tempDeck.length];
	
				//copies values of tempDeck into deck
				for (int a = 0 ; a < deck.length ; a++) {
					deck[a] = tempDeck[a];
				}
				counter++;				
				
				decision = 2;
				comCurrentSuit = getSuit(comCurrentCard);
				comSuitHand [comCardCount] = comCurrentSuit;
				comValueHand[comCardCount] = getValue(comCurrentCard);	
				
				comCardCount+=1;
				
				comTotal = getTotal(comValueHand,comCardCount,decision,comSuitHand,comAce,draw);
				
				//if dealer gets a total of 21 printHand will reveal the hidden card
				if (comTotal == 21) {
					decision = 1;
				}
				printHand(comCardCount,comValueHand,comSuitHand,decision);
				
				//continues the same number of times as length of comAce
				for(int x = 0 ; x < comAce.length ; x++) {
					comTotal = checkAce(comValueHand,comTotal,x,comAce);
					
				}
			
				//continues asking until user inputs '.'
				while (!enter.equals(".")) {
					System.out.print("\nHere are the dealers new cards!");
					enter = input.next();	
				}	
				enter = "x";
				
			}
			
			//prints both sets of cards
			System.out.println("\n***************************************************************************************");
			decision = 1;
			System.out.println("\nHere are your cards compared to the dealers.");
			System.out.println("\nDealers Hand: ");	
			printHand(comCardCount,comValueHand,comSuitHand,decision);
			System.out.println("\nDealers Total: " + comTotal);
			System.out.println("\n––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
			System.out.println("\nYour Hand: ");	
			printHand(cardCount,valueHand,suitHand,decision);
			System.out.println("\nYour Total: " + total);
			
			
			
			System.out.println("\n***************************************************************************************\n");
			
			//decides who wins depending on if user is at 21, closer to or equaled 
			//to the same total as dealer
			if (total == 21 && comTotal != 21)
				tempWin = 1;
			else if(total > comTotal && total < 21)
				tempWin = 1;
			else if (total < 21 && comTotal > 21) 
				tempWin = 1;
			else if (total == comTotal)
				tempWin = 0;
			else
				tempWin = 2;
			
			if (tempWin == 1) {
				System.out.print("You Win!");
				wins ++;
			}
			else if (tempWin == 0)
				System.out.print("Its a Draw!");
			else
				System.out.print("You Lose!");
			
			System.out.print("\nNumber of wins: " + wins);
			
			//continues asking until user inputs 'yes' or 'no'
			do {
				System.out.print("\n\nWould you like to play again? (yes or no): ");
				answer = input.next();
				answer = answer.toUpperCase();
			}		
			while (!answer.equals("YES") && !answer.equals("NO"));
		
		}
	
		System.out.println("\n***************************************************************************************\n");
		System.out.print("Total number of wins: " + wins);
		System.out.print("\n\nGoodbye! Thanks For Playing!\n");
		System.out.println("\n***************************************************************************************\n");
		
	}
	
	/* 
	 * pre: none
	 * post: saves values from 1-52 inside deck array
	 * 
	*/
	public static void getDeck(int deck[]) {
		for (int i = 0 ; i < deck.length ; i++)
			deck[i] = i+1;
	}
	
	/* 
	 * pre: none
	 * post: removes card that was drawn from deck array and returns
	 * card that was drawn 
	 * 
	*/
	public static int drawCard(int deck[],int tempDeck[],int counter) {	
		Random number = new Random();
		
		int temp,currentCard, j = 0;
	
		//randomly picks a card
		temp = number.nextInt(53-counter);
		currentCard = deck[temp];
		deck[temp] = 0;
		
		//continues for the length of tempDeck which is one less than the actual deck
		for (int i = 0 ; i < tempDeck.length+1; i++) { 
			//checks if value is 0, if so it does not add this value to tempDeck
			if (deck[i] != 0) {
				tempDeck[j] = deck[i];
				j++;
			}
		}		
		return(currentCard);
	}
	
	/* 
	 * pre: none
	 * post: returns symbol of which suit the card is depending on its value
	*/
	public static String getSuit(int card) {	
		String suit = "";
		
		//if card value is 1-13 it is a heart
		if (card >= 1 && card <= 13) {
			suit = "♥";
		}
		//if card value is 14-26 it is a heart
		else if (card >= 14 && card <= 26) {
			suit = "♦";
		}
		//if card value is 27-39 it is a heart
		else if (card >= 27 && card <= 39) {
			suit = "♣";
		}
		//if card value is 40-52 it is a heart
		else if (card >= 40 && card <= 52) {
			suit = "♠";
		}
		return(suit);
		
	}
	
	/* 
	 * pre: none
	 * post: changes int to string and returns it as the value of that card
	*/
	public static String getValue(int currentCard) {	
		String temp;
		while (currentCard > 13) {
			currentCard -= 13;
		}
		// if value is 11,12,13 or 1 they are returns as the matching face card
		if (currentCard == 11) 
			temp = "J";
		else if (currentCard == 12) 
			temp = "Q";
		else if (currentCard == 13) 
			temp = "K";
		else if (currentCard == 1) 
			temp = "A";
		else
			temp = Integer.toString(currentCard);
		
		return(temp);
	}
	
	/* 
	 * pre: none
	 * post: returns total value of user or dealers hand
	*/
	public static int getTotal(String value [], int cardCount, int decision,String suit[], int ace[],int draw) {	
		Scanner input = new Scanner(System.in);
		int temp = 0,total=0;
		
		//continues for number of cards in their hand
		for(int i = 0 ; i < cardCount ; i++) {
			String change = "YES";
			//value is 10 if card is a royal
			if (value[i] == "J" || value[i] == "Q" || value[i] == "K") {
				temp = 10;
				total += temp;
			}
			//value is asked to be inputed if value is an ace
			else if (value[i] == "A") {
				//continues only if user is drawing a card
				if (draw == 1){	
					//goes through this if card was just picked
					if (i == cardCount-1) {
						temp = 0;
					}
					//continues if card was already in their hand
					else {
						//continues asking until user inputs 'yes' or'no'
						do {
							System.out.print("\n\nWould you to change the value of your Ace of "+ suit[i] + " ? | Current value: "+ ace[i]+" | (yes or no): ");
							change = input.next();
							change = change.toUpperCase();
							}		
							while (!change.equals("YES") && !change.equals("NO"));			
						}
							
				}
				//changes value of ace
				if (decision == 1 && change.equals("YES")) {
					//continues asking until user inputs 11 or 1
					do {
					System.out.print("\nWhat value would you like to assign your Ace of " + suit[i] + " (1 or 11): ");
					temp = input.nextInt();
					}
					while (temp != 1 && temp != 11);				
					
					ace[i] = temp;
					total+=temp;
				}
				//keeps ace value same as before is input is 'no'
				else if (change.equals("NO")){
					temp = ace[i];
					total+=temp;
					
				}
				//establishes dealer ace value as 11
				else {
					temp = 11;
					total+=temp;
					ace[i] = temp;
				}
			}
			//casts string into integer and adds value to total
			else {
				temp = Integer.valueOf(value[i]);
				total += temp;
			}
	}
		
	return(total);
	
	}

	/* 
	 * pre: none
	 * post: returns new total with value of ace for dealer changed if total is greater than 21
	*/
	public static int checkAce(String hand[],int total,int x, int comAce[]) {	
		//checks if card is ace and total is more than 21
		if (hand[x] == "A" && total > 21 ) {
			//only changes value of ace to 1 if current value is 11
			if (comAce[x] == 11) {
				total -= comAce[x];
				comAce[x] = 1;
				total += comAce[x];
			}
		
		}
		
		return(total);

	}
	
	/* 
	 * pre: none
	 * post: prints the dealer or the users cards side by side
	*/	
	public static void printHand(int cardCount,String hand[],String suit[],int decision) {	
	
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			System.out.print(" –––––––     ");
		}
		
		System.out.print("\n");
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			//makes hidden card if this is the dealers hand
			if (decision == 2) {
				System.out.print("| \\   / |    ");
				decision = 3;
				continue;
			}
			//prints based on length of number so card doesn't get scrambled
			else if(hand[i].length() == 2) {
				System.out.print("| "+hand[i]+"    |    ");
			}
			else {
				System.out.print("| "+hand[i]+"     |    ");	
			}
		}	
		
		System.out.print("\n");
		
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			//makes hidden card if this is the dealers hand
			if (decision == 3) {
				System.out.print("|  \\ /  |    ");
				decision = 4;
				continue;
			}
			else
				System.out.print("|       |    ");
		}
		
		System.out.print("\n");
		
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			//makes hidden card if this is the dealers hand
			if (decision == 4) {
				System.out.print("|   X   |    ");
				decision = 5;
				continue;
			}
			else
				System.out.print("|   "+suit[i]+"   |    ");
		}
		
		System.out.print("\n");
		
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			//makes hidden card if this is the dealers hand
			if (decision == 5) {
				System.out.print("|  / \\  |    ");
				decision = 6;
				continue;
			}
			else
				System.out.print("|       |    ");
		}
		
		System.out.print("\n");
		
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			//makes hidden card if this is the dealers hand
			if (decision == 6) {
				System.out.print("| /   \\ |    ");
				decision = 7;
				continue;
			}
			//prints based on length of number so card doesn't get scrambled
			else if(hand[i].length() == 2) {
				System.out.print("|    "+hand[i]+" |    ");
			}
			else {
				System.out.print("|     "+hand[i]+" |    ");	
			}
		}
		
		System.out.print("\n");
		
		//repeats for number of cards in hand
		for(int i = 0 ; i < cardCount ; i++) {
			System.out.print(" –––––––     ");
		}
		
	}

}