import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out respones to and from the game.  Set to false to make it faster.
	
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
		//1
		check(Strings.IS_IT_ALIVE);
        say("Y");
        //now what? Think of all the input and outputs here...
        check(Strings.IS_IT_A + "Duck?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("y");
        
        //2
        check(Strings.IS_IT_ALIVE);
        say("N");
        check(Strings.IS_IT_A + "Rock?");
        say("Y");
        check(Strings.I_WIN);
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        //3
        check(Strings.IS_IT_ALIVE);
        say("y");
		check(Strings.IS_IT_A + "Duck?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Robert");
		check(Strings.NEW_QUESTION + "Duck and a Robert");
		say("Is that a human?"); //update the question
		check("Answering yes to Is that a human? means Robert?");
		say("Y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		//4
		check(Strings.IS_IT_ALIVE);
		say("Y");
		check("Is that a human?");
		say("Y");
		check(Strings.IS_IT_A + "Robert?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		//5
		check(Strings.IS_IT_ALIVE);
		say("Y");
		check("Is that a human?");
		say("n");
		check(Strings.IS_IT_A + "Duck?");
		say("n");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Cat");
		check(Strings.NEW_QUESTION + "Duck and a Cat");
		say("Is that a pet?");
		check("Answering yes to Is that a pet? means Cat?");
		say("Y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		//6
		check(Strings.IS_IT_ALIVE);
		say("N");
		check(Strings.IS_IT_A + "Rock?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("pen");
		check(Strings.NEW_QUESTION + "Rock and a pen");
		say("Is it important for student?");
		check("Answering yes to Is it important for student? means pen?");
		say("y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		//7
		check(Strings.IS_IT_ALIVE);
		say("n");
		check("Is it important for student?");
		say("y");
		check(Strings.IS_IT_A + "pen?");
		say("y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		//8
		check(Strings.IS_IT_ALIVE);
		say("n");
		check("Is it important for student?");
		say("n");
		check(Strings.IS_IT_A + "Rock?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("phone");
		check(Strings.NEW_QUESTION + "Rock and a phone");
		say("Can it be used to make calls?");
		check("Answering yes to Can it be used to make calls? means phone?");
		say("y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		//9
		check(Strings.IS_IT_ALIVE);
		say("y");
		check("Is that a human?");
		say("n");
		check("Is that a pet?");
		say("n");
		check(Strings.IS_IT_A + "Duck?");
		say("y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		//test
		check(Strings.IS_IT_ALIVE);
		say("y");
		check("Is that a human?");
		say("n");
		check("Is that a pet?");
		say("n");
		check(Strings.IS_IT_A + "Duck?");
		say("n");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("human");
		check(Strings.NEW_QUESTION + "Duck and a human");
		say("Can it speck English?");
		check("Answering yes to Can it speck English? means human?");
		say("n");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		check(Strings.IS_IT_ALIVE);
		say("y");
		check("Is that a human?");
		say("n");
		check("Is that a pet?");
		say("n");
		check("Can it speck English?");
		say("y");
		check(Strings.IS_IT_A + "Duck?");
		say("y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		//10
		check(Strings.IS_IT_ALIVE);
		say("n");
		check("Is it important for student?");
		say("n");
		check("Can it be used to make calls?");
		say("n");
		check(Strings.IS_IT_A + "Rock?");
		say("y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		//test
		check(Strings.IS_IT_ALIVE);
		say("n");
		check("Is it important for student?");
		say("n");
		check("Can it be used to make calls?");
		say("n");
		check(Strings.IS_IT_A + "Rock?");
		say("n");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("laptop");
		check(Strings.NEW_QUESTION + "Rock and a laptop");
		say("can it connect to the Internet?");
		check("Answering yes to can it connect to the Internet? means laptop?");
		say("n");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("y");
		
		check(Strings.IS_IT_ALIVE);
		say("n");
		check("Is it important for student?");
		say("n");
		check("Can it be used to make calls?");
		say("n");
		check("can it connect to the Internet?");
		say("y");
		check(Strings.IS_IT_A + "Rock?");
		say("y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("n");
		
		
		//check(Strings.I_WIN);
		//check(Strings.PLAY_AGAIN);
		//say("N");
        
        //close the streams at the end to enrue good behavior.
		comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		System.out.print("Test is running");
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		System.out.print("you there halt");
		
	}
	
	
}
