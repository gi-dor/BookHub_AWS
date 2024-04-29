package com.example.bookhub.user.util;

import java.util.Random;

public class RandomPassword {
    // 어차피 바뀌지 않을 변수라 상수 사용해봄
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789!@#";
    private static final Random RANDOM = new Random();

    public static String generatePassword(int minLength, int maxLength) {
        // 길이가 맞지 않으면 예외 발생하기
        if (minLength <= 0 || maxLength <= 0 || minLength > maxLength) {
            throw new IllegalArgumentException("유효하지 않은 길이입니다.");
        }

        // 범위내에서 임시비밀번호의 길이를  생성하기
        int length = RANDOM.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder password = new StringBuilder();

        // 위에서 나온 무작위 비밀번호의 길이만큼 문자열에서 문자 선택해서 임시비밀번호 생성하기
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        for(int i = 0; i<10; i++){
            String temporaryPassword = generatePassword(8, 16);
            System.out.println("임시 비밀번호: " + temporaryPassword);
        }
    }
}
