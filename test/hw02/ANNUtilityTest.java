/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 10, 2018
* Time: 3:34:58 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: ANNClientTest
* Description: JUnit tests for the class ANNUtility
*
* ****************************************
 */
package hw02;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class ANNUtility
 *
 * @author lts010, ks061
 */
public class ANNUtilityTest extends TestCase {

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of strListToDoubleList method, of class ANNUtility.
     *
     * @author lts010, ks061
     */
    @Test
    public void testStrListToDoubleList() {
        System.out.println("strListToDoubleList");
        ArrayList<String> strList = new ArrayList<>(); //constructing a list of strings
        strList.add("2 1 1 2 0.0001"); //will be index 0 in result, has 5 doubles
        strList.add("-0.3 0.2 0.1 -0.2"); //will be index 1, has 4 doubles
        strList.add("-0.1 -0.5"); //will be index 2, has 2 doubles
        ArrayList<ArrayList<Double>> result = ANNUtility.strListToDoubleList(
                strList);
        assertTrue(result.size() == 3); //there should be 3 lists
        assertTrue(result.get(0).size() == 5); //first index should have 5 doubles
        assertTrue(result.get(1).size() == 4); //should have 4
        assertTrue(result.get(2).size() == 2); //should have 2
        assertTrue(result.get(1).get(3) == -0.2); //the 4th double in index 2 should be this
    }

    /**
     * Test of getDoubleInput method, of class ANNUtility.
     *
     * @see
     * <a href ="https://stackoverflow.com/questions/11009818/how-to-get-list-of-integer-from-string">stackoverflow</a>
     *
     * @author lts010, ks061
     */
    @Test
    public void testGetDoubleInput() {
        System.out.println("getDoubleInput");
        String input = "3.13";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); //set the input into System.in to simulate user input
        double expResult = 3.13;
        assertTrue(expResult == ANNUtility.getDoubleInput(""));
    }

    /**
     * Test of getIntInput method, of class ANNUtility.
     *
     * @see
     * <a href ="https://stackoverflow.com/questions/11009818/how-to-get-list-of-integer-from-string">stackoverflow</a>
     *
     * @author lts010, ks061
     */
    @Test
    public void testGetIntInput() {
        System.out.println("getDoubleInput");
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); //set the input into System.in to simulate user input
        double expResult = 3;
        assertTrue(expResult == ANNUtility.getDoubleInput(""));
    }

    /**
     * Test of getOutputFilename method, of class ANNUtility.
     *
     * @see
     * <a href ="https://stackoverflow.com/questions/11009818/how-to-get-list-of-integer-from-string">stackoverflow</a>
     *
     * @author lts010, ks061
     */
    @Test
    public void testGetOutputFilename() {
        System.out.println("getOutputFilename");
        String input = "test.csv";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); //set the input into System.in to simulate user input
        String expResult = "test.csv";
        assertEquals(expResult, ANNUtility.getOutputFilename("", ".csv"));
    }

}
