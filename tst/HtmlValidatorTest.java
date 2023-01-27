// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

import org.junit.Assert;
import org.junit.Test;
import java.util.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HtmlValidatorTest {
    /**
     * Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br",true));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                            validatorOutputToString(validator));
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test1.html and expected_output/validate_result_for_test1.txt
     */
	@Test
	public void test1(){
        //<b> bold text <i> bold and italic </i> just bold </b>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));
        tags.add(new HtmlTag("i", true));
        tags.add(new HtmlTag("i", false));
        tags.add(new HtmlTag("b", false));

        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test1.txt"),
                validatorOutputToString(validator));


	}

    /**
     * This test2 method should test your validate() method
     * reproducing the test of
     * input_html/test2.html and expected_output/validate_result_for_test2.txt
     */
	@Test
	public void test2(){
        //<html><b> bold text <i> italic text </i> oops, forgot to close the rest
        Queue<HtmlTag> qTest = new LinkedList<>();
        qTest.add(new HtmlTag("html", true));
        qTest.add(new HtmlTag("b", true));
        qTest.add(new HtmlTag("i", true));
        qTest.add(new HtmlTag("i", false));

        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test2.txt"),
                validatorOutputToString(validator));

	}


    /**
     * This test3 method should test your validate() method
     * reproducing the test of
     * input_html/test3.html and expected_output/validate_result_for_test3.txt
     */
	@Test
	public void test3(){
        //<b> bold text <i> bold and italic </b> oops, closed bold first </i>
        Queue<HtmlTag> qTest = new LinkedList<>();

        qTest.add(new HtmlTag("b", true));
        qTest.add(new HtmlTag("i", true));
        qTest.add(new HtmlTag("b", false));
        qTest.add(new HtmlTag("i", false));

        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test3.txt"),
                validatorOutputToString(validator));
	}


    /**
     * This test4 method should test your validate() method
     * reproducing the test of
     * input_html/test4.html and expected_output/validate_result_for_test4.txt
     */
	@Test
	public void test4(){
        //<b> some text <i> </b> </i> </b> </html>
        Queue<HtmlTag> qTest = new LinkedList<>();

        qTest.add(new HtmlTag("b", true));
        qTest.add(new HtmlTag("i", true));
        qTest.add(new HtmlTag("b", false));
        qTest.add(new HtmlTag("i", false));
        qTest.add(new HtmlTag("b", false));
        qTest.add(new HtmlTag("html", false));

        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test4.txt"),
                validatorOutputToString(validator));


    }

    /**
     * This test5 method should test your validate() method
     * reproducing the test of
     * input_html/test5.html and expected_output/validate_result_for_test5.txt
     */
	@Test
	public void test5(){
        //</html>
        Queue<HtmlTag> qTest = new LinkedList<>();
        qTest.add(new HtmlTag("html", false));

        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test5.txt"),
                validatorOutputToString(validator));

    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test6.html and expected_output/validate_result_for_test6.txt
     */
	@Test
	public void test6(){
        //This file intentionally has no tags in it.
        Queue<HtmlTag> qTest = new LinkedList<>();
        //qTest.add(new HtmlTag("html", false));

        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test6.txt"),
                validatorOutputToString(validator));

	}

    /**
     * This test7 method should test your validate() method
     * reproducing the test of
     * input_html/test7.html and expected_output/validate_result_for_test7.txt
     */
	@Test
	public void test7(){
        //<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN">
        //<!-- Valid example from spec -->
        //<html>
        //  <head>
        //    <title>Turtles are cool</title>
        //    <meta http-equiv="Content-Type" content="text/html">
        //    <link href="style.css" type="text/css" />
        //  </head>
        //
        //  <body>
        //    <p>Turtles swim in the <a href="http://ocean.com/">ocean</a>.</p>
        //    <p>Some turtles are over 100 years old.  Here is a picture of a turtle:
        //       <img src="images/turtle.jpg" width="100" height="100">
        //    </p>
        //  </body>
        //</html>
        Queue<HtmlTag> qTest = new LinkedList<>();
        qTest.add(new HtmlTag("!doctype", true));
        qTest.add(new HtmlTag("!--", true));
        qTest.add(new HtmlTag("html", true));
        qTest.add(new HtmlTag("head", true));
        qTest.add(new HtmlTag("title", true));
        qTest.add(new HtmlTag("title", false));
        qTest.add(new HtmlTag("meta", true));
        qTest.add(new HtmlTag("link", true));
        qTest.add(new HtmlTag("head", false));
        qTest.add(new HtmlTag("body", true));
        qTest.add(new HtmlTag("p", true));
        qTest.add(new HtmlTag("a", true));
        qTest.add(new HtmlTag("a", false));
        qTest.add(new HtmlTag("p", false));
        qTest.add(new HtmlTag("p", true));
        qTest.add(new HtmlTag("img", true));
        qTest.add(new HtmlTag("p", false));
        qTest.add(new HtmlTag("body", false));
        qTest.add(new HtmlTag("html", false));

        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test7.txt"),
                validatorOutputToString(validator));

	}

    /**
     * This test8 method should test your validate() method
     * reproducing the test of
     * input_html/test8.html and expected_output/validate_result_for_test8.txt
     */
	@Test
	public void test8(){
        //<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN">
        //<!-- Invalid example from spec -->
        //<html>
        //  <head>
        //    <title>Turtles are cool
        //    <meta http-equiv="Content-Type" content="text/html">
        //    <link href="style.css" type="text/css" rel="stylesheet" />
        //  </head>
        //  </head>
        //
        //  <body>
        //    <p>Turtles swim in the <a href="http://ocean.com>ocean</a>.</p>
        //    </br>
        //    <p>Some turtles are over 100 years old.  Here is a picture of a turtle:
        //       <img src="images/turtle.jpg" width="100" height="100"> </p>
        //</html>

        Queue<HtmlTag> qTest = new LinkedList<>();
        qTest.add(new HtmlTag("!doctype", true));
        qTest.add(new HtmlTag("!--", true));
        qTest.add(new HtmlTag("html"));
        qTest.add(new HtmlTag("head", true));
        qTest.add(new HtmlTag("title", true));
        qTest.add(new HtmlTag("meta", true));
        qTest.add(new HtmlTag("link", true));
        qTest.add(new HtmlTag("head", false));
        qTest.add(new HtmlTag("head", false));
        qTest.add(new HtmlTag("body", true));
        qTest.add(new HtmlTag("p", true));
        qTest.add(new HtmlTag("a", true));
        qTest.add(new HtmlTag("a", false));
        qTest.add(new HtmlTag("p", false));
        qTest.add(new HtmlTag("br", false));
        qTest.add(new HtmlTag("p", true));
        qTest.add(new HtmlTag("img", true));
        qTest.add(new HtmlTag("p", false));
        qTest.add(new HtmlTag("html", false));


        HtmlValidator validator = new HtmlValidator(qTest);

        Assert.assertEquals(expectedOutputToString("expected_output/validate_result_for_test8.txt"),
                validatorOutputToString(validator));
	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest1(){
        HtmlTag[] tagsArr = {new HtmlTag("Hello"), new HtmlTag("There")};
        List<HtmlTag> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTag("General Kenobi"));

        validator.removeAll("General Kenobi");

        Assert.assertEquals(tags, validator.getTags());
    }




	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest2(){
        HtmlTag[] tagsArr = {new HtmlTag("John"), new HtmlTag("Smith")};
        List<HtmlTag> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTag("Momma Jamma"));

        validator.removeAll("Momma Jamma");

        Assert.assertEquals(tags, validator.getTags());
	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest3(){
        HtmlTag[] tagsArr = {new HtmlTag("Kobe"), new HtmlTag("Bryant")};
        List<HtmlTag> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTag("Lebron James"));
        validator.addTag(new HtmlTag("Steph Curry"));
        validator.addTag(new HtmlTag("Joel Embiid"));
        validator.removeAll("Lebron James");
        validator.removeAll("Steph Curry");
        validator.removeAll("Joel Embiid");


        Assert.assertEquals(tags, validator.getTags());

	}

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest4(){
        HtmlTag[] tagsArr = {new HtmlTag("1234"), new HtmlTag("5678")};
        List<HtmlTag> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTag("912345 678912"));

        validator.removeAll("912345 678912");

        Assert.assertEquals(tags, validator.getTags());

    }

}


