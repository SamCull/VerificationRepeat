package cm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestSuite {
    /*PERIOD TEST START*/
    @Test
    @DisplayName("Period instantiation")
    void Period(){
        assertInstanceOf(Period.class, new Period(5, 10));
        assertInstanceOf(Period.class, new Period(1, 12));
        assertInstanceOf(Period.class, new Period(5, 6));
        assertInstanceOf(Period.class, new Period(1, 20));
        assertInstanceOf(Period.class, new Period(0, 20));

        assertThrows(IllegalArgumentException.class,() -> new Period(-1, 20));
        assertThrows(IllegalArgumentException.class,() -> new Period(25, 24));
        assertThrows(IllegalArgumentException.class,() -> new Period(1, -1));
        assertThrows(IllegalArgumentException.class,() -> new Period(1, 25));
        assertThrows(IllegalArgumentException.class,() -> new Period(5, 25));
        assertThrows(IllegalArgumentException.class,() -> new Period(5, 2));
        assertThrows(IllegalArgumentException.class,() -> new Period(10, 5));
    }

    @Test
    @DisplayName("Period overlap")
    void overlaps() {
        assertTrue(new Period(1, 10).overlaps(new Period(5, 12)));
        assertFalse(new Period(1, 10).overlaps(new Period(11, 12)));
        assertThrows(IllegalArgumentException.class, () -> new Period(1, 10).overlaps(new Period(-1, 12)));
        assertTrue(new Period(1, 5).overlaps(new Period(2, 6)));
        assertFalse(new Period(0, 5).overlaps(new Period(10, 20)));
    }

    @Test
    @DisplayName("Period duration")
    void duration() {
        assertEquals(1, new Period(1, 2).duration());
        assertEquals(1, new Period(23, 24).duration());
        assertEquals(14, new Period(10, 24).duration());
        assertEquals(10, new Period(5, 15).duration());
        assertEquals(1, new Period(23, 24).duration());
        assertEquals(24, new Period(0, 24).duration());
        assertEquals(1, new Period(0, 1).duration());
    }
    /*PERIOD TEST END*/

    /*RATE TEST START*/
    @Test
    @DisplayName("Rate instantiation")
    void Rate(){
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("2.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0, 5), new Period(6, 10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("5.0"), new BigDecimal("3.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("10.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(7,10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(12,15), new Period(17,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(List.of(new Period(0, 10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(List.of(new Period(11, 24)))));

        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("0.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("10.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,10), new Period(5,10))), new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(11,20), new Period(16,20)))));
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10))), new ArrayList<>(Arrays.asList(new Period(6,15), new Period(16,20)))));
    }

    @Test
    @DisplayName("Rate calculate")
    void calculate(){
        Rate testRate = new Rate(CarParkKind.STUDENT, new BigDecimal(1), new BigDecimal("0.5"), new ArrayList<>(Arrays.asList(new Period(8, 10), new Period(12,14))), new ArrayList<>(Arrays.asList(new Period(15, 18), new Period(20,22))));

        assertEquals(new BigDecimal("0"), testRate.calculate(new Period(0, 7)));
        assertEquals(new BigDecimal("2"), testRate.calculate(new Period(8, 10)));
        assertEquals(new BigDecimal("1.5"), testRate.calculate(new Period(15, 18)));
        assertEquals(new BigDecimal("2"), testRate.calculate(new Period(6, 10)));
        assertEquals(new BigDecimal("1"), testRate.calculate(new Period(20, 24)));
        assertEquals(new BigDecimal("2.5"), testRate.calculate(new Period(12, 16)));
        assertEquals(new BigDecimal("4.5"), testRate.calculate(new Period(12, 22)));
        assertEquals(new BigDecimal("6.17"), testRate.calculate(new Period(0, 24)));
        assertEquals(new BigDecimal("0"), testRate.calculate(new Period(23, 24)));
        assertEquals(new BigDecimal("0"), testRate.calculate(new Period(0, 1)));

        assertThrows(IllegalArgumentException.class, () -> testRate.calculate(new Period(-1, -1)));

        assertEquals(new BigDecimal("2"), testRate.calculate(new Period(8, 10)));
        assertEquals(new BigDecimal("1"), testRate.calculate(new Period(20, 22)));
    }
    @Test @DisplayName("Period constructor: start between 0 and 24 inclusive")
    void Period_BlackBox_TestCase1() {
        assertInstanceOf(Period.class, new Period(5, 10));
    }

    @Test @DisplayName("Period constructor: end between 0 and 24 inclusive")
    void Period_BlackBox_TestCase2() {
        assertInstanceOf(Period.class, new Period(1, 12));
    }

    @Test @DisplayName("Period constructor: start less than end and end greater than start")
    void Period_BlackBox_TestCase3() {
        assertInstanceOf(Period.class, new Period(5, 6));
    }

    @Test @DisplayName("Period constructor: start less than zero")
    void Period_BlackBox_TestCase4() {
        assertThrows(IllegalArgumentException.class,() -> new Period(-1, 20));
    }

    @Test @DisplayName("Period constructor: start greater than 24")
    void Period_BlackBox_TestCase5() {
        assertThrows(IllegalArgumentException.class,() -> new Period(25, 24));
    }

    @Test @DisplayName("Period constructor: end less than 0")
    void Period_BlackBox_TestCase6() {
        assertThrows(IllegalArgumentException.class,() -> new Period(1, -1));
    }

    @Test @DisplayName("Period constructor: end greater than 24")
    void Period_BlackBox_TestCase7() {
        assertThrows(IllegalArgumentException.class,() -> new Period(1, 25));
    }

    @Test @DisplayName("Period constructor: start greater than end && end less than start")
    void Period_BlackBox_TestCase8() {
        assertThrows(IllegalArgumentException.class,() -> new Period(5, 2));
    }
    //BLACK BOX TESTING END

    //WHITE BOX TESTING
    @Test @DisplayName("Period constructor: Branch 1T")
    void Period_WhiteBox_TestCase1() {
        assertThrows(IllegalArgumentException.class,() -> new Period(5, 2));
    }

    @Test @DisplayName("Period constructor: Branch 1F, 2T")
    void Period_WhiteBox_TestCase2() {
        assertInstanceOf(Period.class, new Period(1, 2));
    }

    @Test @DisplayName("Period constructor: Branch 1F, 2F")
    void Period_WhiteBox_TestCase3() {
        assertThrows(IllegalArgumentException.class,() -> new Period(-1, 2));
    }
    final Period DefaultOverlapStartPeriod = new Period(1, 10);
    @Test @DisplayName("Period overlap: Periods overlap, Output TRUE")
    void PeriodOverlap_BlackBox_TestCase1() {
        assertTrue(DefaultOverlapStartPeriod.overlaps(new Period(5, 12)));
    }
    @Test @DisplayName("Period overlap: Periods don't overlap, Output FALSE")
    void PeriodOverlap_BlackBox_TestCase2() {
        assertFalse(DefaultOverlapStartPeriod.overlaps(new Period(11, 12)));
    }
    @Test @DisplayName("Period overlap: Overlap period is invalid")
    void PeriodOverlap_BlackBox_TestCase3() {
        assertThrows(IllegalArgumentException.class, () -> DefaultOverlapStartPeriod.overlaps(new Period(-1, 12)));
    }
    @Test @DisplayName("Period occurrences: Branch 1T, 2T, 2F, 1F")
    void PeriodOccurrences_WhiteBox_TestCase1(){
        assertEquals(3, new Period(1, 5).occurences(Arrays.asList(new Period(1,2), new Period(3,6))));
    }
    @Test @DisplayName("Period duration: Start and End between 0 and 24 inclusive")
    void PeriodDuration_BlackBox_TestCase1(){
        assertEquals(24, new Period(0, 24).duration());
    }
    @Test @DisplayName("Period duration: start less than end and end greater than start")
    void PeriodDuration_BlackBox_TestCase2(){
        assertEquals(14, new Period(10, 24).duration());
    }

    @Test @DisplayName("Period duration: 24 - 23 = 1")
    void PeriodDuration_BlackBox_TestCase3(){
        assertEquals(1, new Period(23, 24).duration());
    }

    @Test @DisplayName("Period duration: 24 - 0 = 24")
    void PeriodDuration_BlackBox_TestCase4(){
        assertEquals(24, new Period(0, 24).duration());
    }

    @Test @DisplayName("Period duration: 1 - 0 = 1")
    void PeriodDuration_BlackBox_TestCase5(){
        assertEquals(1, new Period(0, 1).duration());
    }
    final CarParkKind DefaultCarParkKind = CarParkKind.STUDENT;
    final ArrayList<Period> DefaultNormalPeriods = new ArrayList<>(Arrays.asList(new Period(0,5), new Period(6,10)));
    final ArrayList<Period> DefaultReducedPeriods = new ArrayList<>(Arrays.asList(new Period(11,15), new Period(16,20)));
    @Test @DisplayName("Rate Constructor: normalRate and reducedRate above 0")
    void RateConstructor_BlackBox_TestCase1(){
        assertInstanceOf(Rate.class, new Rate(DefaultCarParkKind, new BigDecimal("2.0"), new BigDecimal("1.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: NormalRate is greater than or equal reducedRate")
    void RateConstructor_BlackBox_TestCase2(){
        assertInstanceOf(Rate.class, new Rate(DefaultCarParkKind, new BigDecimal("10.0"), new BigDecimal("1.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: NormalPeriods and ReducedPeriods overlap")
    void RateConstructor_BlackBox_TestCase3(){
        assertInstanceOf(Rate.class, new Rate(DefaultCarParkKind, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(Arrays.asList(new Period(0,5), new Period(7,10))), DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: NormalPeriods and ReducedPeriods don't overlap")
    void RateConstructor_BlackBox_TestCase4(){
        assertInstanceOf(Rate.class, new Rate(DefaultCarParkKind, new BigDecimal("10.0"), new BigDecimal("1.0"), new ArrayList<>(List.of(new Period(0, 10))), DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: NormalRate less than 0")
    void RateConstructor_BlackBox_TestCase5(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("-1.0"), new BigDecimal("1.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: ReducedRate less than 0")
    void RateConstructor_BlackBox_TestCase6(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("1.0"), new BigDecimal("-1.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: ReducedRate greater than NormalRate")
    void RateConstructor_BlackBox_TestCase7(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("1.0"), new BigDecimal("10.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: normalPeriods overlap themselves")
    void RateConstructor_BlackBox_TestCase8(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("1.0"), new BigDecimal("1.0"), new ArrayList<>(List.of(new Period(0, 10), new Period(5, 10))), DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: reducedPeriods overlap themselves")
    void RateConstructor_BlackBox_TestCase9(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("1.0"), new BigDecimal("1.0"), DefaultNormalPeriods, new ArrayList<>(List.of(new Period(11, 20), new Period(16, 20)))));
    }
    //BLACK BOX TESTING END
    //WHITE BOX TESTING
    @Test @DisplayName("Rate Constructor: Branch 1T")
    void RateConstructor_WhiteBox_TestCase1(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("5.0"), new BigDecimal("2.0"), null, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: Branch 1F, 2T")
    void RateConstructor_WhiteBox_TestCase2(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, null, new BigDecimal("2.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: Branch 1F, 2F, 3T")
    void RateConstructor_WhiteBox_TestCase3(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("-1.0"), new BigDecimal("2.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: Branch 1F, 2F, 3F, 4T")
    void RateConstructor_WhiteBox_TestCase4(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("2.0"), new BigDecimal("5.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }
    @Test @DisplayName("Rate Constructor: Branch 1F, 2F, 3F, 4F, 5T")
    void RateConstructor_WhiteBox_TestCase5(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("5.0"), new BigDecimal("2.0"), DefaultNormalPeriods, new ArrayList<>(Arrays.asList(new Period(11,15), new Period(14,20)))));
    }
    @Test @DisplayName("Rate Constructor: Branch 1F, 2F, 3F, 4F, 5F, 6T")
    void RateConstructor_WhiteBox_TestCase6(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("5.0"), new BigDecimal("2.0"), DefaultNormalPeriods, new ArrayList<>(Arrays.asList(new Period(9,15), new Period(16,20)))));
    }
    @Test @DisplayName("Rate Constructor: Branch 1F, 2F, 3F, 4F, 5F, 6F")
    void RateConstructor_WhiteBox_TestCase7(){
        assertInstanceOf(Rate.class, new Rate(DefaultCarParkKind, new BigDecimal("5.0"), new BigDecimal("2.0"), DefaultNormalPeriods, DefaultReducedPeriods));
    }

    final Rate testRate = new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("0.5"), new ArrayList<>(Arrays.asList(new Period(8, 10), new Period(12,14))), new ArrayList<>(Arrays.asList(new Period(15, 18), new Period(20,22))));
    @Test @DisplayName("Rate calculate: periodStay is in a free period")
    void RateCalculate_BlackBox_TestCase1(){
        assertEquals(new BigDecimal("0"), testRate.calculate(new Period(0, 7)));
    }
    @Test @DisplayName("Rate calculate: periodStay is in a normalRate period")
    void RateCalculate_BlackBox_TestCase2(){
        assertEquals(new BigDecimal("2"), testRate.calculate(new Period(8, 10)));
    }
    @Test @DisplayName("Rate calculate: periodStay is in a free and reducedRate period")
    void RateCalculate_BlackBox_TestCase3(){
        assertEquals(new BigDecimal("1.5"), testRate.calculate(new Period(15, 18)));
    }
    @Test @DisplayName("Rate calculate: periodStay is in a free and normalRate period")
    void RateCalculate_BlackBox_TestCase4(){
        assertEquals(new BigDecimal("2"), testRate.calculate(new Period(6, 10)));
    }
    @Test @DisplayName("Rate calculate: periodStay is in a free and reducedRate period")
    void RateCalculate_BlackBox_TestCase5(){
        assertEquals(new BigDecimal("1"), testRate.calculate(new Period(20, 24)));
    }
    @Test @DisplayName("Rate calculate: period stay is in a normalRate and reducedRate periods")
    void RateCalculate_BlackBox_TestCase6(){
        assertEquals(new BigDecimal("2.5"), testRate.calculate(new Period(12, 16)));
    }
    @Test @DisplayName("Rate calculate: periodStay is in normalRate, reducedRate, and free periods")
    void RateCalculate_BlackBox_TestCase7(){
        assertEquals(new BigDecimal("4.5"), testRate.calculate(new Period(12, 22)));
    }
    @Test @DisplayName("Rate calculate: periodStay is for a whole day")
    void RateCalculate_BlackBox_TestCase8(){
        assertEquals(new BigDecimal("6.17"), testRate.calculate(new Period(0, 24)));
    }
    @Test @DisplayName("Rate calculate: periodStay is for the smallest increment near the end of the day")
    void RateCalculate_BlackBox_TestCase9(){
        assertEquals(new BigDecimal("0"), testRate.calculate(new Period(23, 24)));
    }
    @Test @DisplayName("Rate calculate: periodStay is for the smallest increment near the start of the day")
    void RateCalculate_BlackBox_TestCase10(){
        assertEquals(new BigDecimal("0"), testRate.calculate(new Period(0, 1)));
    }
    @Test @DisplayName("Rate calculate: periodStay is invalid")
    void RateCalculate_BlackBox_TestCase11(){
        assertThrows(IllegalArgumentException.class, () -> testRate.calculate(new Period(-1, -1)));
    }
    @Test @DisplayName("Rate calculate: periodStay is for 2 hours during normalPeriod where normal rate is 1, and outputs 2")
    void RateCalculate_BlackBox_TestCase12(){
        assertEquals(new BigDecimal("2"), testRate.calculate(new Period(8, 10)));
    }
    @Test @DisplayName("Rate calculate: periodStay is for 2 hours during reducedPeriod where reduced rate is 0.5, and outputs 1")
    void RateCalculate_BlackBox_TestCase13(){
        assertEquals(new BigDecimal("1"), testRate.calculate(new Period(20, 22)));
    }

    @Test @DisplayName("Rate calculate: Branch 1T")
    void RateCalculate_WhiteBox_TestCase1(){
        assertEquals(new BigDecimal("0"), new Rate(CarParkKind.VISITOR, new BigDecimal("5.0"), new BigDecimal("2.0"), new ArrayList<>(Arrays.asList(new Period(1, 5), new Period(10,12))), new ArrayList<>(Arrays.asList(new Period(13, 16), new Period(18,20)))).calculate(new Period(20, 22)));
    }
    @Test @DisplayName("Rate calculate: Branch 1F")
    void RateCalculate_WhiteBox_TestCase2(){
        assertEquals(new BigDecimal("15.22"), new Rate(CarParkKind.STUDENT, new BigDecimal("5.0"), new BigDecimal("2.0"), new ArrayList<>(Arrays.asList(new Period(1, 5), new Period(10,12))), new ArrayList<>(Arrays.asList(new Period(13, 16), new Period(18,20)))).calculate(new Period(1, 5)));
    }
    @Test @DisplayName("Period: invalid constructor argument")
    void Period_WhiteBox_TestCase4() {
        assertThrows(IllegalArgumentException.class,() -> new Period(25, 26));
    }

    @Test @DisplayName("Rate Constructor: Reduced periods overlap themselves")
    void RateConstructor_WhiteBox_TestCase8(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("5.0"), new BigDecimal("2.0"), DefaultNormalPeriods, new ArrayList<>(Arrays.asList(new Period(11,15), new Period(10, 13), new Period(16,20)))));
    }

    @Test @DisplayName("Rate Constructor: Reduced periods are null")
    void RateConstructor_WhiteBox_TestCase9(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("5.0"), new BigDecimal("2.0"), DefaultNormalPeriods, null));
    }

    @Test @DisplayName("Rate Constructor: Reduced periods are null")
    void RateConstructor_WhiteBox_TestCase10(){
        assertThrows(IllegalArgumentException.class, () -> new Rate(DefaultCarParkKind, new BigDecimal("5.0"), null, DefaultNormalPeriods, DefaultReducedPeriods));
    }
    Rate testRate_Student = new Rate(CarParkKind.STUDENT, new BigDecimal("1.0"), new BigDecimal("0.5"), new ArrayList<>(Arrays.asList(new Period(0, 10), new Period(12,14))), new ArrayList<>(Arrays.asList(new Period(15, 18), new Period(20,22))));
    Rate testRate_Visitor = new Rate(CarParkKind.VISITOR, new BigDecimal("1.0"), new BigDecimal("0.5"), new ArrayList<>(Arrays.asList(new Period(0, 10), new Period(12,14))), new ArrayList<>(Arrays.asList(new Period(15, 18), new Period(20,22))));
    Rate testRate_Staff = new Rate(CarParkKind.STAFF, new BigDecimal("1.0"), new BigDecimal("0.5"), new ArrayList<>(Arrays.asList(new Period(0, 10), new Period(12,14))), new ArrayList<>(Arrays.asList(new Period(15, 18), new Period(20,22))));
    Rate testRate_Management = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("1.0"), new BigDecimal("0.5"), new ArrayList<>(Arrays.asList(new Period(0, 10), new Period(12,14))), new ArrayList<>(Arrays.asList(new Period(15, 18), new Period(20,22))));
    Period testPeriod = new Period(0,14);
    @Test @DisplayName("Rate calculate: CarParkKind is STUDENT")
    void RateCalculate_BlackBox_TestCase14(){
        assertEquals(new BigDecimal("9.86"), testRate_Student.calculate(testPeriod));
    }

    @Test @DisplayName("Rate calculate: CarParkKind is VISITOR")
    void RateCalculate_BlackBox_TestCase15(){
        assertEquals(new BigDecimal("1"), testRate_Visitor.calculate(testPeriod));
    }

    @Test @DisplayName("Rate calculate: CarParkKind is STAFF")
    void RateCalculate_BlackBox_TestCase16(){
        assertEquals(new BigDecimal("10"), testRate_Staff.calculate(testPeriod));
    }

    @Test @DisplayName("Rate calculate: CarParkKind is MANAGEMENT")
    void RateCalculate_BlackBox_TestCase17(){
        assertEquals(new BigDecimal("12"), testRate_Management.calculate(testPeriod));
    }

    @Test @DisplayName("Rate calculate: Free for VISITOR up to 10")
    void RateCalculate_BlackBox_TestCase18(){
        assertEquals(new BigDecimal("0"), testRate_Visitor.calculate(new Period(0,10)));
    }

    @Test @DisplayName("Rate calculate: Free for MANAGEMENT is minimum 5")
    void RateCalculate_BlackBox_TestCase19(){
        assertEquals(new BigDecimal("5"), testRate_Management.calculate(new Period(0,1)));
    }

    @Test @DisplayName("Rate calculate: STAFF fee below 10 is same as usual")
    void RateCalculate_BlackBox_TestCase20(){
        assertEquals(new BigDecimal("1"), testRate_Staff.calculate(new Period(0,1)));
    }

    @Test @DisplayName("Rate calculate: STUDENT fee 5.50 OR below IS same as usual")
    void RateCalculate_BlackBox_TestCase21(){
        assertEquals(new BigDecimal("1"), testRate_Student.calculate(new Period(0,1)));
    }

    @Test @DisplayName("Rate calculate: Branch 1T")
    void RateCalculate_WhiteBox_TestCase1b(){
        assertEquals(new BigDecimal("0"), new Rate(CarParkKind.VISITOR, new BigDecimal("5.0"), new BigDecimal("2.0"), new ArrayList<>(Arrays.asList(new Period(1, 5), new Period(10,12))), new ArrayList<>(Arrays.asList(new Period(13, 16), new Period(18,20)))).calculate(new Period(20, 22)));
    }
    @Test @DisplayName("Rate calculate: Branch 1F")
    void RateCalculate_WhiteBox_TestCase2b(){
        assertEquals(new BigDecimal("15.22"), new Rate(CarParkKind.STUDENT, new BigDecimal("5.0"), new BigDecimal("2.0"), new ArrayList<>(Arrays.asList(new Period(1, 5), new Period(10,12))), new ArrayList<>(Arrays.asList(new Period(13, 16), new Period(18,20)))).calculate(new Period(1, 5)));
    }

    /* STAY Tests */
    @Test @DisplayName("Stay instantiation with valid entry and exit times")
    void testValidStayEntryAndExit() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("20.00");

        Stay stay = new Stay(1, 1, entryDateTime, exitDateTime, charge);

        assertNotNull(stay);
    }

    @Test @DisplayName("Stay instantiation with invalid entry and exit times")
    void testInvalidStayEntryAndExit() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        BigDecimal charge = new BigDecimal("20.00");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Stay(1, 1, entryDateTime, exitDateTime, charge);
        });

        System.out.println("Error: " + exception.getMessage());
        assertEquals("Entry date time must be before exit date time", exception.getMessage());
    }

    @Test @DisplayName("Stay instantiation with same gate for entry and exit")
    void testSameGateForEntryAndExit() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("20.00");

        Stay stay = new Stay(1, 1, entryDateTime, exitDateTime, charge);

        assertNotNull(stay);
    }

    @Test @DisplayName("Stay instantiation with different gates in the same CarPark")
    void testDifferentGatesSameCarPark() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("20.00");

        Stay stay = new Stay(1, 2, entryDateTime, exitDateTime, charge);
        stay.setCarParkId(1);

        assertNotNull(stay);
    }

    @Test @DisplayName("Stay instantiation with different gates in different CarParks")
    void testDifferentGatesDifferentCarParks() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("20.00");

        Stay stay = new Stay(1, 3, entryDateTime, exitDateTime, charge);
        stay.setCarParkId(1);
        stay.setExitCarParkId(2);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, stay::validateCarParks);

        System.out.println("Error: " + exception.getMessage());
        assertEquals("Entry and exit gates must belong to the same CarPark", exception.getMessage());
    }

    @Test @DisplayName("Stay instantiation with null entryDateTime")
    void testNullEntryDateTime() {
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("20.00");

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Stay(1, 1, null, exitDateTime, charge);
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Entry or exit date time cannot be null", exception.getMessage());
    }

    @Test @DisplayName("Stay instantiation with null exitDateTime")
    void testNullExitDateTime() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        BigDecimal charge = new BigDecimal("20.00");

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Stay(1, 1, entryDateTime, null, charge);
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Entry or exit date time cannot be null", exception.getMessage());
    }

    @Test @DisplayName("Stay instantiation with valid charge")
    void testValidCharge() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("20.00");

        Stay stay = new Stay(1, 1, entryDateTime, exitDateTime, charge);

        assertNotNull(stay);
        assertEquals(charge, stay.getCharge());
    }

    @Test @DisplayName("Stay instantiation with negative charge")
    void testNegativeCharge() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("-5.00");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Stay(1, 1, entryDateTime, exitDateTime, charge);
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Charge cannot be negative", exception.getMessage());
    }

    @Test
    @DisplayName("Stay instantiation with zero charge")
    void testZeroCharge() {
        LocalDateTime entryDateTime = LocalDateTime.of(2023, 7, 1, 10, 0);
        LocalDateTime exitDateTime = LocalDateTime.of(2023, 7, 1, 12, 0);
        BigDecimal charge = new BigDecimal("0.00");

        Stay stay = new Stay(1, 1, entryDateTime, exitDateTime, charge);

        assertNotNull(stay);
        assertEquals(charge, stay.getCharge());
    }

    /* GATE Tests */
    @Test @DisplayName("Gate instantiation with valid data")
    void testValidGateCreation() {
        Gate gate = new Gate(1, "Entrance A");

        assertNotNull(gate);
        assertEquals(1, gate.getGateId());
        assertEquals("Entrance A", gate.getLocation());
    }

    @Test @DisplayName("Gate instantiation with null gateId")
    void testNullGateID() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Gate(null, "Entrance A");
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Gate ID must be a non-negative integer", exception.getMessage());
    }

    @Test
    @DisplayName("Gate instantiation with negative gateId")
    void testNegativeGateID() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Gate(-1, "Entrance A");
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Gate ID must be a non-negative integer", exception.getMessage());
    }

    @Test @DisplayName("Gate instantiation with null location")
    void testNullLocation() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Gate(1, null);
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Location cannot be null or empty", exception.getMessage());
    }

    @Test @DisplayName("Gate instantiation with empty location")
    void testEmptyLocation() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Gate(1, "");
        });

        System.out.println("Caught exception: " + exception.getMessage());
        assertEquals("Location cannot be null or empty", exception.getMessage());
    }
}