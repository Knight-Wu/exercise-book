package com.wutong;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }



    public static void main(String []args){
        int high = 2100000000;
        int low = 2000000000;
        System.out.println("mid using >>> 1 = " + ((low + high) >>> 1));
        System.out.println("mid using / 2   = " + ((low + high) / 2));
        System.out.println("mid using >> 2   = " + ((low + high) >>1));


    }


}
