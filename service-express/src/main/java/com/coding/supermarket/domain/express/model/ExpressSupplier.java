package com.coding.supermarket.domain.express.model;

import java.io.IOException;
import javax.xml.bind.JAXBException;

public interface ExpressSupplier {

    String getName();

    String getExpressInfo(Request request) throws IOException, JAXBException;

}
