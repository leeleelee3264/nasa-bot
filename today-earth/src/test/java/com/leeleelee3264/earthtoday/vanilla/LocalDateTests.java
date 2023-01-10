package com.leeleelee3264.earthtoday.vanilla;

import java.time.LocalDate;

public class LocalDateTests {
    public static void main(String[] args) throws Exception {

        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();

        System.out.println(month);
    }

}
