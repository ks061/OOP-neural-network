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
* Description: JUnit tests for the class ANNClient
*
* ****************************************
 */
package hw01;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class ANNClient
 *
 * @author lts010, ks061
 */
public class ANNClientTest extends TestCase {

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of strListToDoubleList method, of class ANNClient.
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
        ArrayList<ArrayList<Double>> result = ANNClient.strListToDoubleList(
                strList);
        assertTrue(result.size() == 3); //there should be 3 lists
        assertTrue(result.get(0).size() == 5); //first index should have 5 doubles
        assertTrue(result.get(1).size() == 4); //should have 4
        assertTrue(result.get(2).size() == 2); //should have 2
        assertTrue(result.get(1).get(3) == -0.2); //the 4th double in index 2 should be this
    }

}
