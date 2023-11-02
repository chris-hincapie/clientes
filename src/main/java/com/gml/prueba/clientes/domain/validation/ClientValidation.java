package com.gml.prueba.clientes.domain.validation;

import com.gml.prueba.clientes.infrastructure.configuration.constant.ClientConstant;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidation {

    private static final Logger logger = Logger.getLogger(ClientValidation.class.getName());

    public boolean validateEmail(String email) {
        logger.info("START: validateEmail ----> "+email);

        Pattern pattern = Pattern.compile(ClientConstant.PATT_VALID_EMAIL);
        Matcher matcher = pattern.matcher(email);

        logger.info("END: validateEmail");
        return matcher.matches();
    }

    public boolean validatePhone(String phone){
        logger.info("START: validatePhone ----> "+phone);

        if(phone.length()==ClientConstant.PATT_SIZE_MAX_PHONE){
            Pattern pattern2 = Pattern.compile(ClientConstant.PATT_VALID_PHONE);
            Matcher match2 = pattern2.matcher(phone);

            logger.info("END: validatePhone ----> "+Boolean.TRUE);
            return match2.matches();
        }else{
            logger.info("END: validatePhone ----> "+Boolean.FALSE);
            return Boolean.FALSE;
        }
    }

    public boolean validateName(String name){

        logger.info("START: validateName ----> "+name);

        if(!name.isEmpty()){
            logger.info("END: validateName ----> "+Boolean.TRUE);
            return Boolean.TRUE;
        }else{
            logger.info("END: validateName ----> "+Boolean.FALSE);
            return Boolean.FALSE;
        }
    }

    public boolean validateSharedKey(String sharedKey){

        logger.info("START: validateSharedKey ----> "+sharedKey);

        if(sharedKey.length() >= ClientConstant.MIN_VALUE_SEARCH){
            logger.info("END: validateSharedKey ----> "+Boolean.TRUE);
            return Boolean.TRUE;
        }else{
            logger.info("END: validateSharedKey ----> "+Boolean.FALSE);
            return Boolean.FALSE;
        }
    }
}
