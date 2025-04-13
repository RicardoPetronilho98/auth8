package com.playground.auth8.dataprovider;

import com.playground.auth8.domain.Client;

public interface ClientDataProvider {

    Client findById(String id);

}
