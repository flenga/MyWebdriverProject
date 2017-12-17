package Project_TestCases;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
public class Project_testRunner 
{
	public static void main(String[] args) 
	{
		JUnitCore.main("Project_TestCases.Project_Run_Tests");
	}
}