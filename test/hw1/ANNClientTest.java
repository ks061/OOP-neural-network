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
* Description:
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class ANNClientTest {

    public ANNClientTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of strListToDoubleList method, of class ANNClient.
     */
    @Test
    public void testStrListToDoubleList() {
        System.out.println("strListToDoubleList");
        ArrayList<String> strList = new ArrayList<>();
        strList.add("2 1 1 2 0.0001");
        strList.add("-0.3 0.2 0.1 -0.2");
        strList.add("-0.1 -0.5");
        ArrayList<ArrayList<Double>> result = ANNClient.strListToDoubleList(
                strList);
        assertTrue(result.size() == 3);
        assertTrue(result.get(0).size() == 5);
        assertTrue(result.get(1).size() == 4);
        assertTrue(result.get(2).size() == 2);
        assertTrue(result.get(1).get(3) == -0.2);
    }

}
