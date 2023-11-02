package com.gml.prueba.clientes.infrastructure.configuration.constant;

public class ClientConstant {

    public static final int MIN_VALUE_SEARCH = 3;

    private ClientConstant()  {
        throw new IllegalStateException("Utility class");
    }

    public static final String PATT_VALID_EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final int PATT_SIZE_MAX_PHONE = 10;
    public static final String PATT_VALID_PHONE = "\\d{10}";

    public static final int ZERO_VALUE = 0;
    public static final int ZERO_2_VALUE = -1;
    public static final String AT_SIGN = "@";

    public static final String FORMAT_DATE = "yyyy-MM-dd";

}
