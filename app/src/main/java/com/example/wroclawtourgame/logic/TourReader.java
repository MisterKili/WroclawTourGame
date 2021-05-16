package com.example.wroclawtourgame.logic;

import android.util.Log;
import android.util.Xml;

import com.example.wroclawtourgame.model.Tour;
import com.example.wroclawtourgame.model.TourPoint;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class TourReader {

    // We don't use namespaces
    private static final String ns = null;

    public Tour parse(InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readTour(parser);
        } finally {
            inputStream.close();
        }
    }

    private Tour readTour(XmlPullParser parser) throws XmlPullParserException, IOException {
        Tour tour = new Tour();

        parser.require(XmlPullParser.START_TAG, ns, "tour");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "name":
                    tour.setName(readElement("name", parser));
                    break;
                case "description":
                    tour.setDescription(readElement("description", parser));
                    break;
                case "duration":
                    tour.setDuration(readElement("duration", parser));
                    break;
                case "point":
                    tour.addTourPoint(readPoint(parser));
                    break;
                default:
                    Log.d("TourReader", "Unknown tag" + parser.getName());
                    parser.next();
                    break;
            }
        }
        return tour;
    }

    private TourPoint readPoint(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "point");

        TourPoint point = new TourPoint();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "name":
                    point.setName(readElement("name", parser));
                    break;
                case "description":
                    point.setDescription(readElement("description", parser));
                    break;
                case "cords":
                    point.setCords(readElement("cords", parser));
                    break;
                case "question":
                    point.setQuestion(readElement("question", parser));
                    break;
                case "answer":
                    point.setAnswer(readElement("answer", parser));
                    break;
                case "answered":
                    point.setAnswered(readElement("answered", parser));
                    break;
                default:
                    parser.next();
                    break;
            }
        }

        return point;
    }

    private String readElement(String elementName, XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, elementName);
        String element = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, elementName);
        return element;

    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }


}
