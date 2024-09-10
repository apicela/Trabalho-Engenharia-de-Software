package com.apicela.engsoft.utils.converter;


import java.util.Base64;

public class ImageHelper {
    public static byte[] base64ToByteArray(String base64String) {
        // Verifica se a string contém o prefixo "data:image/jpg;base64,"
        if (base64String == null || !base64String.startsWith("data:image/")) {
            throw new IllegalArgumentException("A string não contém um prefixo de tipo MIME esperado.");
        }

        // Remove o prefixo e decodifica a parte Base64
        String base64Data = base64String.split(",")[1];
        return Base64.getDecoder().decode(base64Data);
    }
}
