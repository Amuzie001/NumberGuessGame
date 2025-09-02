package com.studentapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;

public class NumberGuessServletTest {

    private NumberGuessServlet servlet;

    @Before
    public void setUp() {
        servlet = new NumberGuessServlet();
    }

    @Test
    public void testDoGetRespondsWithMessage() throws Exception {
        // Mock request and response
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Capture servlet output
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Call servlet
        servlet.doGet(request, response);

        writer.flush();

        // Assert that servlet wrote something meaningful
        String output = stringWriter.toString();
        assertTrue("Servlet should return some response", output.length() > 0);
    }
}


