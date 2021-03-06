package us.dot.its.jpo.ode.plugin.j2735.oss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.format.DateTimeParseException;

import org.junit.Ignore;
import org.junit.Test;

import us.dot.its.jpo.ode.j2735.dsrc.GeometricProjection;
import us.dot.its.jpo.ode.j2735.dsrc.TravelerDataFrame;
import us.dot.its.jpo.ode.j2735.dsrc.ValidRegion.Area;
import us.dot.its.jpo.ode.plugin.TravelerInformationMessage;
import us.dot.its.jpo.ode.plugin.j2735.J2735Position3D;
import us.dot.its.jpo.ode.plugin.j2735.J2735TravelerInputData;
import us.dot.its.jpo.ode.plugin.j2735.J2735TravelerInputData.DataFrame.Region.Circle;

public class OssTravelerMessageBuilderTest {
   @Test
   public void checkLowerBoundMessageCount() {
      int msgcnt = -1;
      try {
         OssTravelerMessageBuilder.validateMessageCount(msgcnt);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerMessageCount() {
      int msgcnt = 0;
      try {
         OssTravelerMessageBuilder.validateMessageCount(msgcnt);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundMessageCount() {
      int msgcnt = 128;
      try {
         OssTravelerMessageBuilder.validateMessageCount(msgcnt);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperMessageCount() {
      int msgcnt = 127;
      try {
         OssTravelerMessageBuilder.validateMessageCount(msgcnt);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkURLEmpty() {
      String str = "";
      try {
         OssTravelerMessageBuilder.validateURL(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkURLLower() {
      String str = "a";
      try {
         OssTravelerMessageBuilder.validateURL(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkURLUpper() {
      String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateURL(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkURLUpperBound() {
      String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateURL(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkURLShortEmpty() {
      String str = "";
      try {
         OssTravelerMessageBuilder.validateURLShort(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkURLShortLower() {
      String str = "a";
      try {
         OssTravelerMessageBuilder.validateURLShort(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkURLShortUpper() {
      String str = "aaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateURLShort(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkURLShortUpperBound() {
      String str = "aaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateURLShort(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerFrame() {
      int x = 0;
      try {
         OssTravelerMessageBuilder.validateFrameCount(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerFrameBound() {
      int x = 1;
      try {
         OssTravelerMessageBuilder.validateFrameCount(x);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperFrame() {
      int x = 8;
      try {
         OssTravelerMessageBuilder.validateFrameCount(x);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperFrameBound() {
      int x = 9;
      try {
         OssTravelerMessageBuilder.validateFrameCount(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkEmptyMessageID() {
      String str = "";
      try {
         OssTravelerMessageBuilder.validateMessageID(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkRoadMessageID() {
      String str = "RoadSignID";
      try {
         OssTravelerMessageBuilder.validateMessageID(str);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkFurtherMessageID() {
      String str = "furtherInfoID";
      try {
         OssTravelerMessageBuilder.validateMessageID(str);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkInvalidMessageID() {
      String str = "testString";
      try {
         OssTravelerMessageBuilder.validateMessageID(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerStartTime() {
      long time = -1;
      try {
         OssTravelerMessageBuilder.validateStartTime(time);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundStartTime() {
      long time = 0;
      try {
         OssTravelerMessageBuilder.validateStartTime(time);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperStartTime() {
      long time = 527041;
      try {
         OssTravelerMessageBuilder.validateStartTime(time);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundStartTime() {
      long time = 527040;
      try {
         OssTravelerMessageBuilder.validateStartTime(time);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerYear() {
      int year = -1;
      try {
         OssTravelerMessageBuilder.validateStartYear(year);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundYear() {
      int year = 0;
      try {
         OssTravelerMessageBuilder.validateStartYear(year);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperYear() {
      int year = 4096;
      try {
         OssTravelerMessageBuilder.validateStartYear(year);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundYear() {
      int year = 4095;
      try {
         OssTravelerMessageBuilder.validateStartYear(year);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerMinutes() {
      int minute = -1;
      try {
         OssTravelerMessageBuilder.validateMinutesDuration(minute);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundMinutes() {
      int minute = 0;
      try {
         OssTravelerMessageBuilder.validateMinutesDuration(minute);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperMinutes() {
      int minute = 32001;
      try {
         OssTravelerMessageBuilder.validateMinutesDuration(minute);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundMinutes() {
      int minute = 32000;
      try {
         OssTravelerMessageBuilder.validateMinutesDuration(minute);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerBoundSSPIndex() {
      short index = -1;
      try {
         OssTravelerMessageBuilder.validateHeaderIndex(index);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerSSPIndex() {
      short index = 0;
      try {
         OssTravelerMessageBuilder.validateHeaderIndex(index);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperSSPIndex() {
      short index = 31;
      try {
         OssTravelerMessageBuilder.validateHeaderIndex(index);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundSSPIndex() {
      short index = 32;
      try {
         OssTravelerMessageBuilder.validateHeaderIndex(index);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundInfoType() {
      int type = -1;
      try {
         OssTravelerMessageBuilder.validateInfoType(type);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerInfoType() {
      int type = 0;
      try {
         OssTravelerMessageBuilder.validateInfoType(type);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerBoundLat() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(-90.1), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLat() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(90.1), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerLat() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(-90.0), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
      } catch (RuntimeException e) {
         fail("Unexcpeted Exception");
      }
   }

   @Test
   public void checkUpperLat() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(90.0), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerBoundLong() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45.0), BigDecimal.valueOf(-180.1),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLong() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(180.1),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerLong() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(-180.0),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
      } catch (RuntimeException e) {
         fail("Unexcpeted Exception");
      }
   }

   @Test
   public void checkUpperLong() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(180.0),
            BigDecimal.valueOf(1000.0));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerBoundElevation() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(-409.6));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundElevation() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(6143.91));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerElevation() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(-409.5));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperElevation() {
      J2735Position3D position = new J2735Position3D(BigDecimal.valueOf(45), BigDecimal.valueOf(45.0),
            BigDecimal.valueOf(6143.9));
      try {
         OssTravelerMessageBuilder.validatePosition(position);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperHeading() {
      String head = "100110110101010101011001";
      try {
         OssTravelerMessageBuilder.validateHeading(head);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkEmptyHeading() {
      String head = "";
      try {
         OssTravelerMessageBuilder.validateHeading(head);
         fail("Expected IllegalArgument Exception");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerHeading() {
      String head = "01001010";
      try {
         OssTravelerMessageBuilder.validateHeading(head);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkHeading() {
      String head = "1011011010101100";
      try {
         OssTravelerMessageBuilder.validateHeading(head);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerMUTCD() {
      int code = -1;
      try {
         OssTravelerMessageBuilder.validateMUTCDCode(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundMUTCD() {
      int code = 0;
      try {
         OssTravelerMessageBuilder.validateMUTCDCode(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperMUTCD() {
      int code = 7;
      try {
         OssTravelerMessageBuilder.validateMUTCDCode(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundMUTCD() {
      int code = 6;
      try {
         OssTravelerMessageBuilder.validateMUTCDCode(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundSign() {
      int sign = 8;
      try {
         OssTravelerMessageBuilder.validateSign(sign);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundSign() {
      int sign = -1;
      try {
         OssTravelerMessageBuilder.validateSign(sign);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerSign() {
      int sign = 0;
      try {
         OssTravelerMessageBuilder.validateSign(sign);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperSign() {
      int sign = 7;
      try {
         OssTravelerMessageBuilder.validateSign(sign);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundITISCodes() {
      String code = "65536";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundITISCodes() {
      String code = "-1";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerITISCodes() {
      String code = "0";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperITISCodes() {
      String code = "65535";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkEmptyITISCodesString() {
      String code = "";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerITISCodesString() {
      String code = "a";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperITISCodesString() {
      String code = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundITISCodesString() {
      String code = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
         fail("Unexpected Exception");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundContentCodes() {
      String code = "65536";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundContentCodes() {
      String code = "-1";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerContentCodes() {
      String code = "0";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperContentCodes() {
      String code = "65535";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkEmptyContentCodesString() {
      String code = "";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerContentCodesString() {
      String code = "a";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperContentCodesString() {
      String code = "aaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateITISCodes(code);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundContentCodesString() {
      String code = "aaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateContentCodes(code);
         fail("Unexpected Exception");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkEmptyString() {
      String str = "";
      try {
         OssTravelerMessageBuilder.validateString(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNonEmptyString() {
      String str = "a";
      try {
         OssTravelerMessageBuilder.validateString(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkEmptyGeoName() {
      String str = "";
      try {
         OssTravelerMessageBuilder.validateGeoName(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerGeoName() {
      String str = "a";
      try {
         OssTravelerMessageBuilder.validateGeoName(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperGeoName() {
      String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateGeoName(str);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperBoundGeoName() {
      String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
      try {
         OssTravelerMessageBuilder.validateGeoName(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerRoadID() {
      int road = -1;
      try {
         OssTravelerMessageBuilder.validateRoadID(road);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundRoadID() {
      int road = 0;
      try {
         OssTravelerMessageBuilder.validateRoadID(road);
      } catch (RuntimeException e) {
         fail("Unexpected exception");
      }
   }

   @Test
   public void checkUpperRoadID() {
      int road = 65536;
      try {
         OssTravelerMessageBuilder.validateRoadID(road);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundRoadID() {
      int road = 65535;
      try {
         OssTravelerMessageBuilder.validateRoadID(road);
      } catch (RuntimeException e) {
         fail("Expected IllegalArgumentException");
      }
   }

   @Test
   public void checkLowerLaneWidth() {
      int lane = -1;
      try {
         OssTravelerMessageBuilder.validateLaneWidth(lane);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLaneWidth() {
      int lane = 0;
      try {
         OssTravelerMessageBuilder.validateLaneWidth(lane);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperLaneWidth() {
      int lane = 32768;
      try {
         OssTravelerMessageBuilder.validateLaneWidth(lane);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLaneWidth() {
      int lane = 32767;
      try {
         OssTravelerMessageBuilder.validateLaneWidth(lane);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerDirectionality() {
      long dir = -1;
      try {
         OssTravelerMessageBuilder.validateDirectionality(dir);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundDirectionality() {
      long dir = 0;
      try {
         OssTravelerMessageBuilder.validateDirectionality(dir);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperDirectionality() {
      long dir = 4;
      try {
         OssTravelerMessageBuilder.validateDirectionality(dir);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundDirectionality() {
      long dir = 3;
      try {
         OssTravelerMessageBuilder.validateDirectionality(dir);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerZoom() {
      int zoom = -1;
      try {
         OssTravelerMessageBuilder.validateZoom(zoom);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundZoom() {
      int zoom = 0;
      try {
         OssTravelerMessageBuilder.validateZoom(zoom);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperZoom() {
      int zoom = 16;
      try {
         OssTravelerMessageBuilder.validateZoom(zoom);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundZoom() {
      int zoom = 15;
      try {
         OssTravelerMessageBuilder.validateZoom(zoom);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerExtent() {
      int extent = -1;
      try {
         OssTravelerMessageBuilder.validateExtent(extent);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundExtent() {
      int extent = 0;
      try {
         OssTravelerMessageBuilder.validateExtent(extent);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperExtent() {
      int extent = 16;
      try {
         OssTravelerMessageBuilder.validateExtent(extent);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundExtent() {
      int extent = 15;
      try {
         OssTravelerMessageBuilder.validateExtent(extent);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerRadius() {
      int rad = -1;
      try {
         OssTravelerMessageBuilder.validateRadius(rad);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundRadius() {
      int rad = 0;
      try {
         OssTravelerMessageBuilder.validateRadius(rad);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperRadius() {
      int rad = 4096;
      try {
         OssTravelerMessageBuilder.validateRadius(rad);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundRadius() {
      int rad = 4095;
      try {
         OssTravelerMessageBuilder.validateRadius(rad);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerUnits() {
      int unit = -1;
      try {
         OssTravelerMessageBuilder.validateUnits(unit);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundUnits() {
      int unit = 0;
      try {
         OssTravelerMessageBuilder.validateUnits(unit);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperUnits() {
      int unit = 8;
      try {
         OssTravelerMessageBuilder.validateUnits(unit);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundUnits() {
      int unit = 7;
      try {
         OssTravelerMessageBuilder.validateUnits(unit);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerX16Offset() {
      int x = -32769;
      try {
         OssTravelerMessageBuilder.validatex16Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundX16Offset() {
      int x = -32768;
      try {
         OssTravelerMessageBuilder.validatex16Offset(x);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperX16Offset() {
      int x = 32768;
      try {
         OssTravelerMessageBuilder.validatex16Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundX16Offset() {
      int x = 32767;
      try {
         OssTravelerMessageBuilder.validatex16Offset(x);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerY16Offset() {
      int y = -32769;
      try {
         OssTravelerMessageBuilder.validatey16Offset(y);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundY16Offset() {
      int y = -32768;
      try {
         OssTravelerMessageBuilder.validatey16Offset(y);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperY16Offset() {
      int y = 32768;
      try {
         OssTravelerMessageBuilder.validatey16Offset(y);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundY16Offset() {
      int y = 32767;
      try {
         OssTravelerMessageBuilder.validatey16Offset(y);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkLowerZ16Offset() {
      int z = -32769;
      try {
         OssTravelerMessageBuilder.validatez16Offset(z);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundZ16Offset() {
      int z = -32768;
      try {
         OssTravelerMessageBuilder.validatez16Offset(z);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkUpperZ16Offset() {
      int z = 32768;
      try {
         OssTravelerMessageBuilder.validatez16Offset(z);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundZ16Offset() {
      int z = 32767;
      try {
         OssTravelerMessageBuilder.validatez16Offset(z);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkNodeEnumeration() {
      String str = "reseved";
      try {
         OssTravelerMessageBuilder.validateNodeAttribute(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBoundNodeEnumeration() {
      String str = "reserved";
      try {
         OssTravelerMessageBuilder.validateNodeAttribute(str);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkSegmentEnumeration() {
      String str = "freParking";
      try {
         OssTravelerMessageBuilder.validateSegmentAttribute(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBoundSegmentEnumeration() {
      String str = "freeParking";
      try {
         OssTravelerMessageBuilder.validateSegmentAttribute(str);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkSpeedLimitEnumeration() {
      String str = "maxSpedInSchoolZoneWhenChildrenArePresent";
      try {
         OssTravelerMessageBuilder.validateSpeedLimitType(str);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBoundSpeedLimitEnumeration() {
      String str = "maxSpeedInSchoolZoneWhenChildrenArePresent";
      try {
         OssTravelerMessageBuilder.validateSpeedLimitType(str);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB10Offset() {
      int x = -513;
      try {
         OssTravelerMessageBuilder.validateB10Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB10Offset() {
      int x = 511;
      try {
         OssTravelerMessageBuilder.validateB10Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB10Offset() {
      int x = 512;
      try {
         OssTravelerMessageBuilder.validateB10Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB10Offset() {
      int x = 511;
      try {
         OssTravelerMessageBuilder.validateB10Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB11Offset() {
      int x = -1025;
      try {
         OssTravelerMessageBuilder.validateB11Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB11Offset() {
      int x = -1024;
      try {
         OssTravelerMessageBuilder.validateB11Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB11Offset() {
      int x = 1024;
      try {
         OssTravelerMessageBuilder.validateB11Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB11Offset() {
      int x = 1023;
      try {
         OssTravelerMessageBuilder.validateB11Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB12Offset() {
      int x = -2049;
      try {
         OssTravelerMessageBuilder.validateB12Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB12Offset() {
      int x = -2048;
      try {
         OssTravelerMessageBuilder.validateB12Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB12Offset() {
      int x = 2048;
      try {
         OssTravelerMessageBuilder.validateB12Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB12Offset() {
      int x = 2047;
      try {
         OssTravelerMessageBuilder.validateB12Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB13Offset() {
      int x = -4097;
      try {
         OssTravelerMessageBuilder.validateB13Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB13Offset() {
      int x = -4096;
      try {
         OssTravelerMessageBuilder.validateB13Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB13Offset() {
      int x = 4096;
      try {
         OssTravelerMessageBuilder.validateB13Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB13Offset() {
      int x = 4095;
      try {
         OssTravelerMessageBuilder.validateB13Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB14Offset() {
      int x = -8193;
      try {
         OssTravelerMessageBuilder.validateB14Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB14Offset() {
      int x = -8192;
      try {
         OssTravelerMessageBuilder.validateB14Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB14Offset() {
      int x = 8192;
      try {
         OssTravelerMessageBuilder.validateB14Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB14Offset() {
      int x = 8191;
      try {
         OssTravelerMessageBuilder.validateB14Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB16Offset() {
      int x = -32769;
      try {
         OssTravelerMessageBuilder.validateB16Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB16Offset() {
      int x = -32768;
      try {
         OssTravelerMessageBuilder.validateB16Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB16Offset() {
      int x = 32768;
      try {
         OssTravelerMessageBuilder.validateB16Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB16Offset() {
      int x = 32767;
      try {
         OssTravelerMessageBuilder.validateB16Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLL12Offset() {
      int x = -2049;
      try {
         OssTravelerMessageBuilder.validateLL12Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLL12Offset() {
      int x = -2048;
      try {
         OssTravelerMessageBuilder.validateLL12Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLL12Offset() {
      int x = 2048;
      try {
         OssTravelerMessageBuilder.validateLL12Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLL12Offset() {
      int x = 2047;
      try {
         OssTravelerMessageBuilder.validateLL12Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLL14Offset() {
      int x = -8193;
      try {
         OssTravelerMessageBuilder.validateLL14Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLL14Offset() {
      int x = -8192;
      try {
         OssTravelerMessageBuilder.validateLL14Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLL14Offset() {
      int x = 8192;
      try {
         OssTravelerMessageBuilder.validateLL14Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLL14Offset() {
      int x = 8191;
      try {
         OssTravelerMessageBuilder.validateLL14Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLL16Offset() {
      int x = -32769;
      try {
         OssTravelerMessageBuilder.validateLL16Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLL16Offset() {
      int x = -32768;
      try {
         OssTravelerMessageBuilder.validateLL16Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLL16Offset() {
      int x = 32768;
      try {
         OssTravelerMessageBuilder.validateLL16Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLL16Offset() {
      int x = 32767;
      try {
         OssTravelerMessageBuilder.validateLL16Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLL18Offset() {
      int x = -131073;
      try {
         OssTravelerMessageBuilder.validateLL18Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLL18Offset() {
      int x = -131072;
      try {
         OssTravelerMessageBuilder.validateLL18Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLL18Offset() {
      int x = 131072;
      try {
         OssTravelerMessageBuilder.validateLL18Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLL18Offset() {
      int x = 131071;
      try {
         OssTravelerMessageBuilder.validateLL18Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLL22Offset() {
      int x = -2097153;
      try {
         OssTravelerMessageBuilder.validateLL22Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLL22Offset() {
      int x = -2097152;
      try {
         OssTravelerMessageBuilder.validateLL22Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLL22Offset() {
      int x = 2097152;
      try {
         OssTravelerMessageBuilder.validateLL22Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLL22Offset() {
      int x = 2097151;
      try {
         OssTravelerMessageBuilder.validateLL22Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLL24Offset() {
      int x = -8388609;
      try {
         OssTravelerMessageBuilder.validateLL24Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLL24Offset() {
      int x = -8388608;
      try {
         OssTravelerMessageBuilder.validateLL24Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLL24Offset() {
      int x = 8388608;
      try {
         OssTravelerMessageBuilder.validateLL24Offset(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLL24Offset() {
      int x = 8388607;
      try {
         OssTravelerMessageBuilder.validateLL24Offset(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLaneID() {
      int x = -1;
      try {
         OssTravelerMessageBuilder.validateLaneID(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLaneID() {
      int x = 0;
      try {
         OssTravelerMessageBuilder.validateLaneID(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLaneID() {
      int x = 256;
      try {
         OssTravelerMessageBuilder.validateLaneID(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLaneID() {
      int x = 255;
      try {
         OssTravelerMessageBuilder.validateLaneID(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerSmallDrivenLine() {
      int x = -2048;
      try {
         OssTravelerMessageBuilder.validateSmallDrivenLine(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundSmallDrivenLine() {
      int x = -2047;
      try {
         OssTravelerMessageBuilder.validateSmallDrivenLine(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperSmallDrivenLine() {
      int x = 2048;
      try {
         OssTravelerMessageBuilder.validateSmallDrivenLine(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundSmallDrivenLine() {
      int x = 2047;
      try {
         OssTravelerMessageBuilder.validateSmallDrivenLine(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLargeDrivenLine() {
      int x = -32768;
      try {
         OssTravelerMessageBuilder.validateLargeDrivenLine(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLargeDrivenLine() {
      int x = -32767;
      try {
         OssTravelerMessageBuilder.validateLargeDrivenLine(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLargeDrivenLine() {
      int x = 32768;
      try {
         OssTravelerMessageBuilder.validateLargeDrivenLine(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLargeDrivenLine() {
      int x = 32767;
      try {
         OssTravelerMessageBuilder.validateLargeDrivenLine(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerAngle() {
      int x = -1;
      try {
         OssTravelerMessageBuilder.validateAngle(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundAngle() {
      int x = 0;
      try {
         OssTravelerMessageBuilder.validateAngle(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperAngle() {
      int x = 28801;
      try {
         OssTravelerMessageBuilder.validateAngle(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundAngle() {
      int x = 28800;
      try {
         OssTravelerMessageBuilder.validateAngle(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerB12Scale() {
      int x = -2049;
      try {
         OssTravelerMessageBuilder.validateB12Scale(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundB12Scale() {
      int x = -2048;
      try {
         OssTravelerMessageBuilder.validateB12Scale(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperB12Scale() {
      int x = 2048;
      try {
         OssTravelerMessageBuilder.validateB12Scale(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundB12Scale() {
      int x = 2047;
      try {
         OssTravelerMessageBuilder.validateB12Scale(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerVelocity() {
      int x = -1;
      try {
         OssTravelerMessageBuilder.validateVelocity(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundVelocity() {
      int x = 0;
      try {
         OssTravelerMessageBuilder.validateVelocity(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperVelocity() {
      int x = 8192;
      try {
         OssTravelerMessageBuilder.validateVelocity(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundVelocity() {
      int x = 8191;
      try {
         OssTravelerMessageBuilder.validateVelocity(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerDeltaAngle() {
      int x = -151;
      try {
         OssTravelerMessageBuilder.validateDeltaAngle(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundDeltaAngle() {
      int x = -150;
      try {
         OssTravelerMessageBuilder.validateDeltaAngle(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperDeltaAngle() {
      int x = 151;
      try {
         OssTravelerMessageBuilder.validateDeltaAngle(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundDeltaAngle() {
      int x = 150;
      try {
         OssTravelerMessageBuilder.validateDeltaAngle(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerCrownPoint() {
      int x = -129;
      try {
         OssTravelerMessageBuilder.validateCrownPoint(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundCrownPoint() {
      int x = -128;
      try {
         OssTravelerMessageBuilder.validateCrownPoint(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperCrownPoint() {
      int x = 128;
      try {
         OssTravelerMessageBuilder.validateCrownPoint(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundCrownPoint() {
      int x = 127;
      try {
         OssTravelerMessageBuilder.validateCrownPoint(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkLowerLaneAngle() {
      int x = -181;
      try {
         OssTravelerMessageBuilder.validateLaneAngle(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkLowerBoundLaneAngle() {
      int x = -180;
      try {
         OssTravelerMessageBuilder.validateLaneAngle(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkUpperLaneAngle() {
      int x = 181;
      try {
         OssTravelerMessageBuilder.validateLaneAngle(x);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkUpperBoundLaneAngle() {
      int x = 180;
      try {
         OssTravelerMessageBuilder.validateLaneAngle(x);
      } catch (RuntimeException e) {
         fail("Unexcpected Exception");
      }
   }

   @Test
   public void checkContentAdvisory() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Advisory");
      String[] codes = { "250", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadContentAdvisory() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Advisory");
      String[] codes = { "-1", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkContentWorkZone() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Work Zone");
      String[] codes = { "250", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadContentWorkZone() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Work Zone");
      String[] codes = { "-1", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkContentSpeedLimit() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Speed Limit");
      String[] codes = { "250", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadContentSpeedLimit() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Speed Limit");
      String[] codes = { "-1", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkContentExitService() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Exit Service");
      String[] codes = { "250", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadContentExitService() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Exit Service");
      String[] codes = { "-1", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkContentGenericSignage() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Generic Signage");
      String[] codes = { "250", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadContentGenericSignage() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      TravelerDataFrame dataFrame = new TravelerDataFrame();
      df.setContent("Generic Signage");
      String[] codes = { "-1", "10" };
      df.setItems(codes);

      try {
         dataFrame.setContent(b.buildContent(df));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBadXRegionOffset() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList rl = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[] myList = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[1];
      rl.setxOffset(-32769);
      rl.setyOffset(0);
      rl.setzOffset(0);
      myList[0] = rl;

      try {
         b.buildRegionOffsets(myList);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBadYRegionOffset() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList rl = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[] myList = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[1];
      rl.setxOffset(0);
      rl.setyOffset(-32769);
      rl.setzOffset(0);
      myList[0] = rl;

      try {
         b.buildRegionOffsets(myList);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBadZRegionOffset() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList rl = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[] myList = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[1];
      rl.setxOffset(0);
      rl.setyOffset(0);
      rl.setzOffset(-32769);
      myList[0] = rl;

      try {
         b.buildRegionOffsets(myList);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkRegionOffset() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList rl = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList();
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[] myList = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[1];
      rl.setxOffset(0);
      rl.setyOffset(0);
      rl.setzOffset(0);
      myList[0] = rl;

      try {
         b.buildRegionOffsets(myList);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadGeoCircle() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      GeometricProjection geo = new GeometricProjection();
      J2735TravelerInputData.DataFrame.Region.Geometry g = new J2735TravelerInputData.DataFrame.Region.Geometry();
      J2735TravelerInputData.DataFrame.Region.Circle c = new J2735TravelerInputData.DataFrame.Region.Circle();
      J2735Position3D pos = new J2735Position3D((long) 0.0, (long) 0.0, (long) 0.0);
      c.setPosition(pos);
      c.setRadius(5);
      c.setUnits(10);
      g.setCircle(c);
      g.setDirection("1010101010101010");
      g.setExtent(-1);
      g.setLaneWidth(10);

      try {
         geo.setCircle(b.buildGeoCircle(g));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkGeoCircle() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      GeometricProjection geo = new GeometricProjection();
      J2735TravelerInputData.DataFrame.Region.Geometry g = new J2735TravelerInputData.DataFrame.Region.Geometry();
      J2735TravelerInputData.DataFrame.Region.Circle c = new J2735TravelerInputData.DataFrame.Region.Circle();
      J2735Position3D pos = new J2735Position3D((long) 0.0, (long) 0.0, (long) 0.0);
      c.setPosition(pos);
      c.setRadius(5);
      c.setUnits(6);
      g.setCircle(c);
      g.setDirection("1010101010101010");
      g.setExtent(1);
      g.setLaneWidth(10);

      try {
         geo.setCircle(b.buildGeoCircle(g));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadOldCircle() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      Area area = new Area();
      J2735TravelerInputData.DataFrame.Region.OldRegion r = new J2735TravelerInputData.DataFrame.Region.OldRegion();
      J2735Position3D pos = new J2735Position3D((long) 0.0, (long) 0.0, (long) 0.0);
      Circle c = new Circle();
      c.setPosition(pos);
      c.setRadius(3);
      c.setUnits(80);
      r.setCircle(c);
      try {
         area.setCircle(b.buildOldCircle(r));
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBadNodeXY1() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      dataL.setPathEndpointAngle(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY1");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXY1() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY1");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeXY2() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY2");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXY2() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY2");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeXY3() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY3");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXY3() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY3");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeXY4() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY4");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXY4() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY4");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeXY5() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY5");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXY5() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY5");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeXY6() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY6");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXY6() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY6");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeXYLatLon() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LatLon");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeXYLatLon() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LatLon");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Ignore
   @Test
   public void checkBadNodeXYAttribute() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY6");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(0);
      n.setY(0);
      n.setAttributes(null);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkBadNodeLL1() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL1");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLL1() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL1");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeLL2() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL2");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLL2() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL2");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeLL3() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL3");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLL3() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL3");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeLL4() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL4");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLL4() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL4");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeLL5() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL5");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLL5() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL5");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeLL6() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL6");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLL6() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL6");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadNodeLLLatLon() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LatLon");
      n.setNodeLat(Long.MAX_VALUE);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkNodeLLLatLon() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LatLon");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;

      try {
         b.buildNodeLLList(node);
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Ignore
   @Test
   public void checkBadNodeLLAttribute() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];

      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();

      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];

      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];

      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];

      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];

      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];

      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);

      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL6");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(Integer.MIN_VALUE);
      n.setY(Integer.MAX_VALUE);
      n.setAttributes(null);
      node[0] = n;

      try {
         b.buildNodeXYList(node);
         fail("Expected IllegalArgumentException");
      } catch (RuntimeException e) {
         assertEquals(IllegalArgumentException.class, e.getClass());
      }
   }

   @Test
   public void checkOldCircle() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      Area area = new Area();
      J2735TravelerInputData.DataFrame.Region.OldRegion r = new J2735TravelerInputData.DataFrame.Region.OldRegion();
      J2735Position3D pos = new J2735Position3D((long) 0.0, (long) 0.0, (long) 0.0);
      Circle c = new Circle();
      c.setPosition(pos);
      c.setRadius(3);
      c.setUnits(6);
      r.setCircle(c);
      try {
         area.setCircle(b.buildOldCircle(r));
      } catch (RuntimeException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkMinuteOfYear() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      try {
         b.getMinuteOfTheYear("2017-12-01T17:47:11-05:00");
      } catch (ParseException e) {
         fail("Unexpected Exception");
      }
   }

   @Test
   public void checkBadMinuteOfYear() {
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();

      try {
         b.getMinuteOfTheYear("hi");
         fail("Expected DateTimeParseException");
      } catch (DateTimeParseException | ParseException e) {
         assertEquals(DateTimeParseException.class, e.getClass());
      }
   }

   @Test
   public void checkTravelerMessageBuilder() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("geometry");
      J2735TravelerInputData.DataFrame.Region.Geometry g = new J2735TravelerInputData.DataFrame.Region.Geometry();
      g.setDirection("1010101010101010");
      g.setExtent(1);
      g.setLaneWidth(33);
      J2735TravelerInputData.DataFrame.Region.Circle c = new J2735TravelerInputData.DataFrame.Region.Circle();
      c.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      c.setRadius(15);
      c.setUnits(7);
      g.setCircle(c);
      r.setGeometry(g);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }
   
   @Test
   public void checkTravelerMessageBuilderPathXY() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("path");
      J2735TravelerInputData.DataFrame.Region.Path p = new J2735TravelerInputData.DataFrame.Region.Path();
      p.setScale(2);
      p.setType("xy");
      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];
      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();
      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];
      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];
      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];
      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];
      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];
      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);
      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL1");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;
      p.setNodes(node);
      r.setPath(p);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }
   
   @Test
   public void checkTravelerMessageBuilderPathLL() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("path");
      J2735TravelerInputData.DataFrame.Region.Path p = new J2735TravelerInputData.DataFrame.Region.Path();
      p.setScale(2);
      p.setType("ll");
      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];
      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();
      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];
      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];
      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];
      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];
      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];
      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);
      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-LL1");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;
      p.setNodes(node);
      r.setPath(p);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }
   
   @Test
   public void checkTravelerMessageBuilderPathComputedLaneLarge() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("path");
      J2735TravelerInputData.DataFrame.Region.Path p = new J2735TravelerInputData.DataFrame.Region.Path();
      p.setScale(2);
      p.setType("xy");
      J2735TravelerInputData.NodeXY[] node = null;
      J2735TravelerInputData.ComputedLane cl = new J2735TravelerInputData.ComputedLane();
      cl.setAngle(5);
      cl.setLaneID(1);
      cl.setOffsetLargeX(5);
      cl.setOffsetSmallX(3);
      cl.setOffsetLargeY(5);
      cl.setOffsetSmallY(3);
      cl.setxScale(2);
      cl.setyScale(2);
      p.setComputedLane(cl);
      p.setNodes(node);
      r.setPath(p);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }
   
   @Test
   public void checkTravelerMessageBuilderPathComputedLaneSmall() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("path");
      J2735TravelerInputData.DataFrame.Region.Path p = new J2735TravelerInputData.DataFrame.Region.Path();
      p.setScale(2);
      p.setType("xy");
      J2735TravelerInputData.NodeXY[] node = null;
      J2735TravelerInputData.ComputedLane cl = new J2735TravelerInputData.ComputedLane();
      cl.setAngle(5);
      cl.setLaneID(1);
      cl.setOffsetLargeX(0);
      cl.setOffsetSmallX(3);
      cl.setOffsetLargeY(0);
      cl.setOffsetSmallY(3);
      cl.setxScale(2);
      cl.setyScale(2);
      p.setComputedLane(cl);
      p.setNodes(node);
      r.setPath(p);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }

   @Test
   public void checkOldRegion() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("oldRegion");
      J2735TravelerInputData.DataFrame.Region.OldRegion or = new J2735TravelerInputData.DataFrame.Region.OldRegion();
      or.setDirection("1010101010101010");
      or.setExtent(1);
      or.setArea("regionPointSet");
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint rp = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint();
      rp.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      rp.setScale(0);
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[] rList = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList[1];
      J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList re = new J2735TravelerInputData.DataFrame.Region.OldRegion.RegionPoint.RegionList();
      re.setxOffset(-36);
      re.setyOffset(21);
      re.setzOffset(0);
      rList[0] = re;
      rp.setRegionList(rList);
      or.setRegionPoint(rp);
      r.setOldRegion(or);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }

   @Test
   public void checkShapepointXY() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("oldRegion");
      J2735TravelerInputData.DataFrame.Region.OldRegion or = new J2735TravelerInputData.DataFrame.Region.OldRegion();
      or.setDirection("1010101010101010");
      or.setExtent(1);
      or.setArea("shapePointSet");
      J2735TravelerInputData.DataFrame.Region.OldRegion.ShapePoint sp = new J2735TravelerInputData.DataFrame.Region.OldRegion.ShapePoint();
      sp.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      sp.setLaneWidth(10);
      sp.setDirectionality(2);
      sp.setNodeType("xy");
      J2735TravelerInputData.NodeXY n = new J2735TravelerInputData.NodeXY();
      J2735TravelerInputData.NodeXY[] node = new J2735TravelerInputData.NodeXY[1];
      J2735TravelerInputData.Attributes at = new J2735TravelerInputData.Attributes();
      J2735TravelerInputData.LocalNode ln = new J2735TravelerInputData.LocalNode();
      J2735TravelerInputData.LocalNode[] lnode = new J2735TravelerInputData.LocalNode[1];
      J2735TravelerInputData.DisabledList dl = new J2735TravelerInputData.DisabledList();
      J2735TravelerInputData.DisabledList[] dlist = new J2735TravelerInputData.DisabledList[1];
      J2735TravelerInputData.EnabledList el = new J2735TravelerInputData.EnabledList();
      J2735TravelerInputData.EnabledList[] elist = new J2735TravelerInputData.EnabledList[1];
      J2735TravelerInputData.DataList dataL = new J2735TravelerInputData.DataList();
      J2735TravelerInputData.DataList[] dataList = new J2735TravelerInputData.DataList[1];
      J2735TravelerInputData.SpeedLimits sl = new J2735TravelerInputData.SpeedLimits();
      J2735TravelerInputData.SpeedLimits[] slimits = new J2735TravelerInputData.SpeedLimits[1];
      sl.setType(1);
      sl.setVelocity(1);
      dataL.setLaneAngle(1);
      dataL.setLaneCrownCenter(1);
      dataL.setLaneCrownLeft(1);
      dataL.setLaneCrownRight(1);
      slimits[0] = sl;
      dataL.setSpeedLimits(slimits);
      ln.setType(1);
      lnode[0] = ln;
      at.setLocalNodes(lnode);
      dl.setType(1);
      dlist[0] = dl;
      at.setDisabledLists(dlist);
      el.setType(1);
      elist[0] = el;
      at.setEnabledLists(elist);
      dataList[0] = dataL;
      at.setDataLists(dataList);
      at.setdWidth(10);
      at.setdElevation(10);
      n.setDelta("node-XY1");
      n.setNodeLat(10);
      n.setNodeLong(10);
      n.setX(10);
      n.setY(10);
      n.setAttributes(at);
      node[0] = n;
      sp.setNodexy(node);
      or.setShapepoint(sp);
      r.setOldRegion(or);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         fail("Unexpected exception");
      }
   }
   
   @Test
   public void checkShapepointComputedLane() {
      J2735TravelerInputData ti = new J2735TravelerInputData();
      TravelerInformationMessage.TIM tim = new TravelerInformationMessage.TIM();
      tim.setMsgCnt(10);
      tim.setTimeStamp("2017-03-13T01:07:11-05:00");
      tim.setUrlB("www");
      tim.setPacketID(10);
      J2735TravelerInputData.DataFrame[] dframes = new J2735TravelerInputData.DataFrame[1];
      J2735TravelerInputData.DataFrame df = new J2735TravelerInputData.DataFrame();
      df.setsspTimRights((short) 0);
      df.setFrameType(0);
      df.setMsgID("RoadSignID");
      df.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      df.setViewAngle("1010101010101010");
      df.setMutcd(5);
      df.setCrc("1111111111111111");
      df.setStartDateTime("2017-12-01T17:47:11-05:00");
      df.setDurationTime(22);
      df.setPriority(0);
      df.setsspLocationRights((short) 3);
      J2735TravelerInputData.DataFrame.Region[] reg = new J2735TravelerInputData.DataFrame.Region[1];
      J2735TravelerInputData.DataFrame.Region r = new J2735TravelerInputData.DataFrame.Region();
      df.setsspMsgTypes((short) 2);
      df.setsspMsgContent((short) 3);
      df.setUrl("www");
      r.setName("bob");
      r.setRegulatorID(23);
      r.setSegmentID(33);
      r.setAnchorPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      r.setLaneWidth(7);
      r.setDirectionality((long) 3);
      r.setClosedPath(false);
      r.setDirection("1010101010101010");
      r.setDescription("oldRegion");
      J2735TravelerInputData.DataFrame.Region.OldRegion or = new J2735TravelerInputData.DataFrame.Region.OldRegion();
      or.setDirection("1010101010101010");
      or.setExtent(1);
      or.setArea("shapePointSet");
      J2735TravelerInputData.DataFrame.Region.OldRegion.ShapePoint sp = new J2735TravelerInputData.DataFrame.Region.OldRegion.ShapePoint();
      sp.setPosition(new J2735Position3D((long) -41.678473, (long) -108.782775, (long) 917.1432));
      sp.setLaneWidth(10);
      sp.setDirectionality(2);
      sp.setNodeType("xy");
      J2735TravelerInputData.NodeXY[] node = null;
      J2735TravelerInputData.ComputedLane cl = new J2735TravelerInputData.ComputedLane();
      cl.setAngle(5);
      cl.setLaneID(1);
      cl.setOffsetLargeX(5);
      cl.setOffsetSmallX(3);
      cl.setOffsetLargeY(5);
      cl.setOffsetSmallY(3);
      cl.setxScale(2);
      cl.setyScale(2);
      sp.setComputedLane(cl);
      sp.setNodexy(node);
      or.setShapepoint(sp);
      r.setOldRegion(or);
      reg[0] = r;
      df.setRegions(reg);
      dframes[0] = df;
      tim.setDataframes(dframes);
      ti.setTim(tim);
      OssTravelerMessageBuilder b = new OssTravelerMessageBuilder();
      try {
         b.buildTravelerInformation(ti);
      } catch (Exception e) {
         e.printStackTrace();
         fail("Unexpected Exception");
      }
   }
}