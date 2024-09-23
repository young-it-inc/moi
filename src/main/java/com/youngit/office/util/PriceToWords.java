package com.youngit.office.util;

public class PriceToWords {

    private static final String[] units = {
            "", "십", "백", "천", "만", "십", "백", "천", "억"
    };

    private static final String[] numbers = {
            "영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"
    };

    public static String convert(long amount) {
        if (amount == 0) {
            return numbers[0] + "원";
        }
        StringBuilder result = new StringBuilder();
        int unitIndex = 0;

        // 억 단위 처리
        if (amount >= 100000000) {
            result.append(convertPart(amount / 100000000)).append("억");
            amount %= 100000000; // 억 단위를 제외한 나머지
        }

        // 만 단위 처리
        if (amount >= 10000) {
            result.append(convertPart(amount / 10000)).append("만");
            amount %= 10000; // 만 단위를 제외한 나머지
        }

        // 나머지 4자리 처리
        if (amount > 0) {
            result.append(convertPart(amount));
        }

        return result.toString().trim() + "원"; // 마지막에 "원" 추가
    }

    private static String convertPart(long part) {
        StringBuilder partString = new StringBuilder();
        int digitIndex = 0;

        while (part > 0) {
            int digit = (int) (part % 10);
            if (digit > 0) {
                partString.insert(0, numbers[digit] + units[digitIndex]);
            }
            part /= 10;
            digitIndex++;
        }
        return partString.toString();
    }

    public static String formatPrice(long amount) {
        return String.format("%,d", amount);
    }




}
