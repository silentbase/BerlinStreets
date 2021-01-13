package com.example.berlinstreets.berlinModul;

import org.junit.Test;

import static org.junit.Assert.*;

public class MarkerTest {

    private Marker marker;

    //test if true
    @Test
    public void testMarkerDataValidation(){
        marker = new Marker("123ID213", "title", "Holzstraße", "3","1243", null);
        assertTrue(marker.isMarkerDataValid());
    }

    //test if false
    @Test
    public void testMarkerDataValidation2(){
        marker = new Marker("123ID213", "title", "", "3","1243", null);
        assertFalse(marker.isMarkerDataValid());
    }

    //test if false
    @Test
    public void testMarkerDataValidation3(){
        marker = new Marker();
        assertFalse(marker.isMarkerDataValid());
    }

}