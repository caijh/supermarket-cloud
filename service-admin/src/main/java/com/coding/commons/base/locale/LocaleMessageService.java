package com.coding.commons.base.locale;

import com.coding.commons.base.BizException;

public interface LocaleMessageService {
    String getMessage(String code);

    BizException getLocaleException(String code);
}
