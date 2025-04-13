package com.playground.auth8.nosql.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "clients")
public class ClientDocument {

    @Id
    private String mongoId; // Document "_id" field

    private String id;
    private String secret;
    private List<String> scopes;

}
