package allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test2.TestPersistence;
import testcontroller.TestEventRegistrationController;

@RunWith(Suite.class)
@SuiteClasses({
	TestEventRegistrationController.class, 
	TestPersistence.class
})
public class AllTests {

}
