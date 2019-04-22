package com.galveston.services;

import java.io.IOException;

public interface Loader {
     void eventsFireUpTimeLoader();
     void userFireUpTimeLoader() throws IOException;
     void whoIsBoss() throws Exception;
     void loadPrizes();

}
