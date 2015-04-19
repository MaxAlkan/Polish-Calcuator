/*  	ExpressionEvaluator.java

	Max B. Garcia 
     

*/





public class ExpressionEvaluator {

	

	Stack<String> operatorStack;

	Queue<String> outputQueue;



	//Constructor

	public ExpressionEvaluator()  {

		operatorStack = new Stack<String>();

		outputQueue = new Queue<String>();

	}



	//Returns Boolean. Determines whether token s is a legal operator.

	private boolean isOperator(String s) {

		return s.equals("+")  ||  s.equals("-")  ||  s.equals("*")  ||  s.equals("/")  ||  s.equals("^");

	}


	private String[] stringtoInfix(String input) {

		String c = "";
		String[] storage = new String[input.length()];
		int tokenSize = 0;
		int j = 0;

		for(int i = 0; i < input.length(); i++) {
			if(isOperator(String.valueOf(input.charAt(i))) || (String.valueOf(input.charAt(i)).equals("(")) || (String.valueOf(input.charAt(i)).equals (")")))                        {

				storage[j] = String.valueOf(input.charAt(i));
				tokenSize++;
				j++;
			}
			else while(!(String.valueOf(input.charAt(i)).equals(" "))) {
				c = c.concat(String.valueOf(input.charAt(i)));
				if(input.length() == i + 1) {
					storage[j] = c;
					tokenSize++;
					break;
				}
				else if (String.valueOf(input.charAt(i + 1)).equals(" ")) {
					storage[j] = c;
					c = "";
					tokenSize++;
					j++;
					break;
				}
				else i++;
			}
		}
		String[] output = new String[tokenSize];
		for(int i = 0; i < tokenSize; i++) 
			output[i] = storage[i];
		return output;
	}		

	//Returns nothing. Manipulates the operator s depending on its precendence, as stipulated in arithmetic 

	private void operatorPrecedence(String s) {
		if (operatorStack.isEmpty()) { operatorStack.push(s); }

		else if (operatorStack.peek().equals("(")) { operatorStack.push(s); }

		else if (s.equals("^")) {

			if (operatorStack.peek().equals("^")) {

				outputQueue.enqueue(operatorStack.pop());

				operatorStack.push(s);

			}

			else operatorStack.push(s);			

		}

		else if(s.equals("*") || s.equals("/")) {

			if(operatorStack.peek().equals("^")) {

				outputQueue.enqueue(operatorStack.pop());

				operatorStack.push(s);

			}

			else if(operatorStack.peek().equals("*") || operatorStack.peek().equals("/")) {

				outputQueue.enqueue(operatorStack.pop());

				operatorStack.push(s);

			}

			else if(operatorStack.peek().equals("+") || operatorStack.peek().equals("-")) {

				operatorStack.push(s);	

			}

		}

		else if(s.equals("+") || s.equals("-")) {

			outputQueue.enqueue(operatorStack.pop());

			operatorStack.push(s);

		}			

	}



	//Returns nothing. Takes the postfix stored in the queue and evaluates it.

	//Final Value is stored on top of operatorStack.

	private String evaluatePostfix() {

		

		double value;

		String opTemp;

		String token1;

		String token2;

		String output;

		

		while (!outputQueue.isEmpty()) {

			opTemp = outputQueue.dequeue();

			if (isOperator(opTemp)) {

				token2 = operatorStack.pop();

				token1 = operatorStack.pop();

				if (opTemp.equals("^")) {

					value = Math.pow(Double.parseDouble(token1), Double.parseDouble(token2));

					operatorStack.push(Double.toString(value));

				}

				else if (opTemp.equals("*")) {

					value = Double.parseDouble(token1) * Double.parseDouble(token2);

					operatorStack.push(Double.toString(value));

				}

				else if (opTemp.equals("/")) {

					value = Double.parseDouble(token1) / Double.parseDouble(token2);

					operatorStack.push(Double.toString(value));

				}

				else if (opTemp.equals("+")) {

					value = Double.parseDouble(token1) + Double.parseDouble(token2);

					operatorStack.push(Double.toString(value));

				}

				else if (opTemp.equals("-")) {

					value = Double.parseDouble(token1) - Double.parseDouble(token2);

					operatorStack.push(Double.toString(value));

				}

			}

			else operatorStack.push(opTemp);

		}

		return operatorStack.pop();	

	} 

	

	//Returns String. Implementation of Shunting-Yard Algorithm to generate postfix

	//Calls evaluatePostfix() and returns the computed value as a string.

	//Code from 107 to 116 deals with possible input errors

	public String processInput(String infix) {

		

		//String[] tokens1 = infix.replace("  ", " ").split(" ");
		//String[] tokens = new String[tokens1.length - 1];   OLD METHOD
		//for(int i = 1; i < tokens1.length; i++) { tokens[i-1] = tokens1[i]; }
		//for(int i = 0; i < tokens1.length; i++) { System.out.print(tokens1[i]); }	More Debugging Crap
		String[] tokens = stringtoInfix(infix); //NEW METHOD
		

		int tokenCount = tokens.length;

		int operatorCount = 0;

		int lparenCount = 0;

		int rparenCount = 0;

		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		if(tokens.length == 0) {return "NOTHING";}

		for(int i = 0; i < tokens.length; i++) {

			if (rparenCount > lparenCount) { return "ERROR"; }	

			if(tokens[i].equals("(")) { lparenCount++; }

			else if(tokens[i].equals(")")) { rparenCount++; }

			else if(isOperator(tokens[i])) { operatorCount++; }

		}
		/* Debugging Crap
		System.out.println(lparenCount);
		System.out.println(rparenCount);
		System.out.println(operatorCount);
		System.out.println(tokens.length);
		*/

		if(!(lparenCount == rparenCount) ) { return "ERROR"; } 

		if(!((tokens.length - lparenCount - rparenCount - 2*operatorCount) == 1)) {

			return "ERROR"; 

		}

		if(isOperator(tokens[0])) { return "ERROR"; } //Catches the special case that above misses

		//////////////////////////////////////////////////////////////////////////////////////////////////

		

		for (int i = 0; i < tokenCount; i++) {

			if (tokens[i].equals("(")) { 

				operatorStack.push(tokens[i]); 

			}

			else if (tokens[i].equals(")")) {

				while(!operatorStack.peek().equals("(")) {

					outputQueue.enqueue(operatorStack.pop());

				}

				operatorStack.pop(); //gets rid of uneeded "("

			}

			else if (isOperator(tokens[i])) { 

				operatorPrecedence(tokens[i]);

			}

			else outputQueue.enqueue(tokens[i]); 

		}
		while(!operatorStack.isEmpty()) {

			outputQueue.enqueue(operatorStack.pop());

		}

		return evaluatePostfix();

	}
}
