package com.kush.prowler.service;

import com.kush.prowler.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MessagingService {
    private final MessageSource messageSource;

    public String getResponseMessage(ServiceException serviceException) {
        return getResponseMessage(serviceException.getStatusCode().getDescription(), serviceException.getArgs());
    }


    public String getResponseMessage(String mainArg,String[] args) {

        String[] arguments = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            arguments[i] = messageSource.getMessage(args[i],
                    new String[]{},
                    LocaleContextHolder.getLocale());
        }

        String[] allArguments = Arrays.copyOf(arguments, arguments.length );

        return messageSource.getMessage(mainArg,
                allArguments,
                LocaleContextHolder.getLocale());
    }
}
