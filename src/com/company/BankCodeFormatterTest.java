package com.company;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankCodeFormatterTest {

	@Test
    public void isCountryCodeValid() {
        assertTrue(BankCodeFormatter.isCountryCodeValid("LT"));
        assertTrue(BankCodeFormatter.isCountryCodeValid("lt"));
        assertFalse(BankCodeFormatter.isCountryCodeValid("lttt"));
        assertFalse(BankCodeFormatter.isCountryCodeValid("5A"));
        assertFalse(BankCodeFormatter.isCountryCodeValid("A5"));
        assertFalse(BankCodeFormatter.isCountryCodeValid("55"));
        assertFalse(BankCodeFormatter.isCountryCodeValid("55AA"));
        assertFalse(BankCodeFormatter.isCountryCodeValid(""));
        assertFalse(BankCodeFormatter.isCountryCodeValid(null));
    }

    @org.junit.Test
    public void isBankCodeValid() {
        assertTrue(BankCodeFormatter.isBankCodeValid("22"));
        assertTrue(BankCodeFormatter.isBankCodeValid("21564"));
        assertTrue(BankCodeFormatter.isBankCodeValid("1234567890"));
        assertFalse(BankCodeFormatter.isBankCodeValid("123AA"));
        assertFalse(BankCodeFormatter.isBankCodeValid("a5489"));
        assertFalse(BankCodeFormatter.isBankCodeValid("asdb"));
        assertFalse(BankCodeFormatter.isBankCodeValid("12345678901234567890"));
        assertFalse(BankCodeFormatter.isBankCodeValid(""));
        assertFalse(BankCodeFormatter.isBankCodeValid(null));
    }

    @org.junit.Test
    public void formatBankCode() {
        assertEquals("LT 123456", BankCodeFormatter.formatBankCode("lt", "123456"));
        assertEquals("LT 123456", BankCodeFormatter.formatBankCode("LT", "123456"));
        assertEquals("LT 1(22)3456", BankCodeFormatter.formatBankCode("LT", "1223456"));
        assertEquals("LT 1(22)34566", BankCodeFormatter.formatBankCode("LT", "12234566"));
        assertEquals("US 12223(4444)", BankCodeFormatter.formatBankCode("uS", "122234444"));
    }

    @org.junit.Test
    public void bankCodeInNumberGroups() {
        String[] groupedNumbers = BankCodeFormatter.bankCodeInNumberGroups("12234555");
        assertEquals("1", groupedNumbers[0]);
        assertEquals("22", groupedNumbers[1]);
        assertEquals("3", groupedNumbers[2]);
        assertEquals("4", groupedNumbers[3]);
        assertEquals("555", groupedNumbers[4]);
    }

    @org.junit.Test
    public void largestGroupIndex() {
        String[] groupedNumbers = BankCodeFormatter.bankCodeInNumberGroups("12234555");
        assertEquals(4, BankCodeFormatter.largestGroupIndex(groupedNumbers));

        groupedNumbers= BankCodeFormatter.bankCodeInNumberGroups("11112234555");
        assertEquals(0, BankCodeFormatter.largestGroupIndex(groupedNumbers));

        groupedNumbers = BankCodeFormatter.bankCodeInNumberGroups("122344");
        assertEquals(1, BankCodeFormatter.largestGroupIndex(groupedNumbers));
    }

}
