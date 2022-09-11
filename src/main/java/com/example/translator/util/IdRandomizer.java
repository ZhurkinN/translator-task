package com.example.translator.util;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class IdRandomizer {

    public static Long randomizeId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
