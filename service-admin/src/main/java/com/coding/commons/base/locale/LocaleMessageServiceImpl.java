package com.coding.commons.base.locale;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.BizException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Named
public class LocaleMessageServiceImpl implements LocaleMessageService {
    @Inject
    private MessageSource messageSource;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    @Override
    public BizException getLocaleException(String code) {
        return new BizException(getMessage(code));
    }
}
